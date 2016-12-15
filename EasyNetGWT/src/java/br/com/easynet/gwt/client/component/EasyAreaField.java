/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

/**
 *
 * @author geoleite
 */
public class EasyAreaField extends EasyTextField<String> {

    private KeyListener keyListener = new KeyListener() {

        public void componentKeyUp(ComponentEvent event) {
            setValue(formatValue(EasyAreaField.super.getValue()));
        }
    };

    private String formatValue(String v) {
        try {
            if (v != null) {
                if (v.length() == 0) {
                    v = "000";
                }
                v = v.replaceAll("\\D", ""); //Remove tudo o que não é dígito
                v = Long.parseLong(v) + "";
                if (v.length() == 1) {
                    v = "00" + v;
                }
                if (v.length() == 2) {
                    v = "0" + v;
                }
//            if (v.length() < 6) {
                v = v.replaceAll("(\\d)(\\d{2})$", "$1,$2"); //Coloca ponto antes do  último digito
//            } else if (v.length() <= 8) {
//                v = v.replaceAll("(\\d)(\\d{3})(\\d{2})$", "$1.$2,$3"); //Coloca ponto antes dos 2 últimos digitos
//            } else if (v.length() <= 11) {
//                v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3,$4"); //Coloca ponto antes dos 2 últimos digitos
//            } else if (v.length() <= 14) {
//                v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4,$5"); //Coloca ponto antes dos 2 últimos digitos
//            } else if (v.length() <= 17) {
//                v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4.$5,$6"); //Coloca ponto antes dos 2 últimos digitos
//            } else {
//                v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4.$5.$6,$7"); //Coloca ponto antes dos 2 últimos digitos
//            }
            }
            return v;
        } catch (Exception e) {
        }
        return "";
    }

    public EasyAreaField() {
        removeAllListeners();
        addKeyListener(keyListener);
    }

    public void setValue(double d) {
        try {
            long i = new Double(d * 100).longValue();
            double dTemp = i/100d;
            //double dTemp = Math.ceil(i);
//            super.setValue(formatValue(dTemp + ""));
            super.setValue(formatValue(i + ""));
        } catch (Exception e) {
        }

    }

    private String checkDecimais(String value) {
        try {
            if (value != null) {
                if (!value.contains(".")) {
                    value += "0";
                } else {
                    long i = new Double(Double.parseDouble(value) * 10).longValue();
                    double d = Math.ceil(i) / 10;
                    return d + "";
                }
            }
        } catch (Exception e) {
        }
        return value;
    }

    public long toInt() {
        Double d = getValueArea();
        if (d != null) {
            return d.intValue();
        }
        return 0;
    }

    public long toLong() {
        Double d = getValueArea();
        if (d != null) {
            return d.longValue();
        }
        return 0;
    }

    public float toFloat() {
        Double d = getValueArea();
        if (d != null) {
            return d.floatValue();
        }
        return 0;
    }

    public double toDouble() {
        Double d = getValueArea();
        if (d != null) {
            return d.doubleValue();
        }
        return 0;
    }

    public void setValue(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                value = "0";
            }
            value = formatValue(value);
            super.setValue(value);
        } catch (Exception e) {
        }
    }

    public String getValue() {
        return String.valueOf(getValueArea());
    }

    public Double getValueArea() {
        String v = super.getValue();
        if (v == null) {
            return 0.0;
        } else {
            v = v.replaceAll("\\D", ""); //Remove tudo o que não é dígito
            v = v.replaceAll("(\\d)(\\d{2})$", "$1.$2"); //Coloca ponto antes dos 2 últimos digitos;
            return Double.parseDouble(v);
        }
    }

    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        getInputEl().setElementAttribute("style", "text-align:right");
    }
}
