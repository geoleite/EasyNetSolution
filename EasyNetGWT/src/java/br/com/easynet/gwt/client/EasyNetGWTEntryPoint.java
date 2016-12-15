/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client;

import br.com.easynet.gwt.client.component.EasyAreaField;
import br.com.easynet.gwt.client.component.EasyDateField;
import br.com.easynet.gwt.client.component.EasyHtmlEditor;
import br.com.easynet.gwt.client.component.EasyMoneyField;
import br.com.easynet.gwt.client.component.EasyNumberField;
import br.com.easynet.gwt.client.component.EasyRichText;
import br.com.easynet.gwt.client.component.EasyTextField;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easynet.gwt.client.internationalization.EasyConstantes;
import br.com.easynet.gwt.client.util.EasyText;
import br.com.easynet.gwt.client.util.ValidacaoCPFCNPJ;
import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.ScaleProvider;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.asm.commons.Method;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.xhr.client.XMLHttpRequest;

/**
 * Main entry point.
 *
 * @author aluno
 */
public class EasyNetGWTEntryPoint implements EntryPoint, IListenetResponse {

    /**
     * Creates a new instance of EasyNetGWTEntryPoint
     */
    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        DebugMessage.setDebug(true);
        /*
        TextFieldMask tfm = new TextFieldMask();
        tfm.setMask("SSS-DDDD");
        RootPanel.get().add(tfm);
        GwtLocaleImpl gwtLocale = new GwtLocaleImpl();

        DateField df = new DateField();
        df.setMaxLength(30);
        df.setSize(80, 30);
        RootPanel.get().add(df);
         */

//        LocaleInfo locale = LocaleInfo.getCurrentLocale();
//        EasyConstantes constantes = GWT.create(EasyConstantes.class);
//        Info.display("Locale", constantes.goodbyeWorld());
//        EasyTextField numberField = new EasyTextField();
//        numberField.setBackGroundColor("red");
//        numberField.setColor("green");
//        numberField.setValue(1000.75);
//        //numberField.setFormat(NumberFormat.getCurrencyFormat());
//        RootPanel.get().add(numberField);


//
//        Window win = new Window();
//        win.setSize(400, 400);
//        final EasyMoneyField money = new EasyMoneyField();
//        //money.setValue("50000");
//        money.addListener(Events.OnBlur, new Listener<FieldEvent>() {
//
//            public void handleEvent(FieldEvent be) {
//                Info.display("DEBUG", "onblur");
//            }
//        });
//        money.addListener(Events.OnChange, new Listener<FieldEvent>() {
//
//            public void handleEvent(FieldEvent be) {
//                Info.display("DEBUG", "onchante");
//            }
//        });
//        final EasyMoneyField money1 = new EasyMoneyField();
//        money1.setValue(32000000.00);
//
//        //final EasyAreaField area1 = new EasyAreaField();
//        //area1.setValue(1000000);
//        //final EasyAreaField area2 = new EasyAreaField();
//        //area2.setValue(2000056);
//
//        win.add(money);
//        win.add(money1);
//        //win.add(area2);
//        win.setVisible(true);
//
//
//
//
//        try {
//            //"http://maps.googleapis.com/maps/api/geocode/json?address=albano%20franco,aracaju,sergipe&sensor=true"
//            String url = "http://maps.googleapis.com/maps/api/geocode/json";
//
//
//
//        } catch (Exception e) {
//            com.google.gwt.user.client.Window.alert("Error " + e.getMessage());
//        }

        DebugMessage.setDebug(true);
        DebugMessage.message(this.getClass().getName(), "Validando o debug do GWT no servidor");
/*        
        double d = 153054.82;
        long i = new Double(d * 100).longValue();
        double dTemp = Math.ceil(i);
        EasyMoneyField money = new EasyMoneyField();
        money.setValue(d);
        RootPanel.get().add(money);
        com.google.gwt.user.client.Window.alert(d + " " + i +  " " + dTemp);
        
        EasyRichText ert = new EasyRichText();
        ert.setSize("300", "300");
        RootPanel.get().add(ert);
        HtmlEditor html = new EasyHtmlEditor();
        html.addKeyListener(null);
        html.setSize(600, 300);
        RootPanel.get().add(html);
        */
    }

    public void read(JSONValue jsonValue) {
        com.google.gwt.user.client.Window.alert("Finalizado " + jsonValue);
    }
}
