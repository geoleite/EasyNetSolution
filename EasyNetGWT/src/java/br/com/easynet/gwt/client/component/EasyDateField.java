/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.PreviewEvent;
import com.extjs.gxt.ui.client.util.BaseEventPreview;
import com.extjs.gxt.ui.client.util.DateWrapper;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.menu.DateMenu;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

/**
 * Provides a date input field with a {@link DatePicker} dropdown and automatic
 * date validation.
 *
 * <dl>
 * <dt>Inherited Events:</dt>
 * <dd>Field Focus</dd>
 * <dd>Field Blur</dd>
 * <dd>Field Change</dd>
 * <dd>Field Invalid</dd>
 * <dd>Field Valid</dd>
 * <dd>Field KeyPress</dd>
 * <dd>Field SpecialKey</dd>
 * <dd>TriggerField TriggerClick</dd>
 * </dl>
 */
public class EasyDateField extends EasyTriggerField<String> {

    private String dateFormat = "dd/MM/yyyy";
    private DateTimeFormat dtf;

    /**
     * @return the dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        if (dateFormat != null) {
            dtf = DateTimeFormat.getFormat(dateFormat);
        }
    }


    /**
     * DateField error messages.
     */
    public class DateFieldMessages extends TextFieldMessages {

        private String minText;
        private String maxText;
        private String invalidText;
        private String ariaText = "Press Down arrow to select date from a calendar grid";

        /**
         * Returns the ARIA instruction text.
         *
         * @return the text
         */
        public String getAriaText() {
            return ariaText;
        }

        /**
         * Returns the invalid text.
         *
         * @return the invalid text
         */
        public String getInvalidText() {
            return invalidText;
        }

        /**
         * Returns the max error text.
         *
         * @return the error text
         */
        public String getMaxText() {
            return maxText;
        }

        /**
         * Returns the min error text.
         *
         * @return the error text
         */
        public String getMinText() {
            return minText;
        }

        /**
         * Sets the ARIA instructions for invoking the date picker (defaults to
         * 'Press Down arrow to select date from a calendar grid').
         *
         * @param ariaText the aria text
         */
        public void setAriaText(String ariaText) {
            this.ariaText = ariaText;
        }

        /**
         * "The error text to display when the date in the field is invalid " +
         * "(defaults to '{value} is not a valid date - it must be in the format
         * {format}')."
         *
         * @param invalidText the invalid text
         */
        public void setInvalidText(String invalidText) {
            this.invalidText = invalidText;
        }

        /**
         * Sets the error text to display when the date in the cell is after
         * maxValue (defaults to 'The date in this field must be before {
         * {@link #setMaxValue}').
         *
         * @param maxText the max error text
         */
        public void setMaxText(String maxText) {
            this.maxText = maxText;
        }

        /**
         * The error text to display when the date in the cell is before minValue
         * (defaults to 'The date in this field must be after {@link #setMinValue}
         * ').
         *
         * @param minText the min text
         */
        public void setMinText(String minText) {
            this.minText = minText;
        }
    }
    private Date minValue;
    private Date maxValue;
    private DateMenu menu;
    private boolean formatValue;
    private BaseEventPreview eventPreview;

    /**
     * Creates a new date field.
     */
    public EasyDateField() {
        super();
        setMaskFormat("00/00/0000");
        dtf = DateTimeFormat.getFormat(dateFormat);
        autoValidate = false;
        //propertyEditor = new DateTimePropertyEditor();
        //getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
        messages = new DateFieldMessages();
        setTriggerStyle("x-form-date-trigger");
    }

    public Date toDate() {
        if (getValueWithMask() != null) {
            return dtf.parse(getValueWithMask());
        }
        return null;
    }

    public String getValue() {
        return getValueWithMask();
    }

    /**
     * Returns the field's date picker.
     *
     * @return the date picker
     */
    public DatePicker getDatePicker() {
        if (menu == null) {
            menu = new DateMenu();

            menu.getDatePicker().addListener(Events.Select, new Listener<ComponentEvent>() {

                public void handleEvent(ComponentEvent ce) {
                    focusValue = getValue();
                    setValue(dtf.format(menu.getDate()));
                    menu.hide();
                }
            });
            menu.addListener(Events.Hide, new Listener<ComponentEvent>() {

                public void handleEvent(ComponentEvent be) {
                    eventPreview.remove();
                    focus();
                }
            });
        }
        return menu.getDatePicker();
    }

    /**
     * Returns the field's max value.
     *
     * @return the max value
     */
    public Date getMaxValue() {
        return maxValue;
    }

    @Override
    public DateFieldMessages getMessages() {
        return (DateFieldMessages) messages;
    }

    /**
     * Returns the field's min value.
     *
     * @return the min value
     */
    public Date getMinValue() {
        return minValue;
    }

    /**
     * Returns true if formatting is enabled.
     *
     * @return the format value state
     */
    public boolean isFormatValue() {
        return formatValue;
    }

    /**
     * True to format the user entered value using the field's property editor
     * after passing validation (defaults to false). Format value should not be
     * enabled when auto validating.
     *
     * @param formatValue true to format the user value
     */
    public void setFormatValue(boolean formatValue) {
        this.formatValue = formatValue;
    }

    /**
     * Sets the field's max value.
     *
     * @param maxValue the max value
     */
    public void setMaxValue(Date maxValue) {
        if (maxValue != null) {
            maxValue = new DateWrapper(maxValue).resetTime().asDate();
        }
        this.maxValue = maxValue;
    }

    /**
     * The maximum date allowed.
     *
     * @param minValue the max value
     */
    public void setMinValue(Date minValue) {
        if (minValue != null) {
            minValue = new DateWrapper(minValue).resetTime().asDate();
        }
        this.minValue = minValue;
    }

    protected void collapseIf(PreviewEvent pe) {
        if (!menu.el().isOrHasChild(pe.getTarget()) && !el().isOrHasChild(pe.getTarget())) {
            menu.hide();
        }
    }

    protected void expand() {
        DatePicker picker = getDatePicker();

        Object v = getValue();
        Date d = null;
        if (v instanceof Date) {
            d = (Date) v;
        } else {
            d = new Date();
        }

        picker.setMinDate(minValue);
        picker.setMaxDate(maxValue);
        picker.setValue(d, true);

        eventPreview.add();

        // handle case when down arrow is opening menu
        DeferredCommand.addCommand(new Command() {

            public void execute() {
                menu.show(el().dom, "tl-bl?");
                menu.getDatePicker().focus();
            }
        });
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (eventPreview != null) {
            eventPreview.remove();
        }
    }

    @Override
    protected void onKeyDown(FieldEvent fe) {
        super.onKeyDown(fe);
        if (fe.getKeyCode() == KeyCodes.KEY_DOWN) {
            fe.stopEvent();
            if (menu == null || !menu.isAttached()) {
                expand();
            }
        }
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        eventPreview = new BaseEventPreview() {

            @Override
            protected boolean onPreview(PreviewEvent pe) {
                switch (pe.getType().getEventCode()) {
                    case Event.ONSCROLL:
                    case Event.ONMOUSEWHEEL:
                    case Event.ONMOUSEDOWN:
                        collapseIf(pe);
                }
                return true;
            }
        };
        eventPreview.setAutoHide(false);

        new KeyNav<FieldEvent>(this) {

            @Override
            public void onEsc(FieldEvent fe) {
                if (menu != null && menu.isAttached()) {
                    menu.hide();
                }
            }
        };

        if (GXT.isAriaEnabled()) {
            getInputEl().dom.setAttribute("title", getMessages().getAriaText());
        }
    }

    @Override
    protected void onTriggerClick(ComponentEvent ce) {
        super.onTriggerClick(ce);
        expand();
    }

    @Override
    protected boolean validateBlur(DomEvent e, Element target) {
        return menu == null || (menu != null && !menu.isVisible());
    }

    @Override
    protected boolean validateValue(String value) {
        if (!super.validateValue(value)) {
            return false;
        }
        if (value.length() < 1) { // if it's blank and textfield didn't flag it then
            // it's valid
            return true;
        }

        DateTimeFormat format = dtf;

        Date date = null;

        try {
            date = format.parse(value);
        } catch (Exception e) {
        }

        if (date == null) {
            String error = null;
            if (getMessages().getInvalidText() != null) {
                error = Format.substitute(getMessages().getInvalidText(), value, format.getPattern().toUpperCase());
            } else {
                error = GXT.MESSAGES.dateField_invalidText(value, format.getPattern().toUpperCase());
            }
            markInvalid(error);
            return false;
        }

        if (minValue != null && date.before(minValue)) {
            String error = null;
            if (getMessages().getMinText() != null) {
                error = Format.substitute(getMessages().getMinText(), format.format(minValue));
            } else {
                error = GXT.MESSAGES.dateField_minText(format.format(minValue));
            }
            markInvalid(error);
            return false;
        }
        if (maxValue != null && date.after(maxValue)) {
            String error = null;
            if (getMessages().getMaxText() != null) {
                error = Format.substitute(getMessages().getMaxText(), format.format(maxValue));
            } else {
                error = GXT.MESSAGES.dateField_maxText(format.format(maxValue));
            }
            markInvalid(error);
            return false;
        }

        if (formatValue && dtf != null) {
            setRawValue(dtf.format(date));
        }

        return true;
    }
}
