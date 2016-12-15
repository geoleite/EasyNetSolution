/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import br.com.easynet.gwt.client.debug.DebugMessage;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

/**
 *
 * @author geoleite
 */
public class EasyMoneyField extends EasyTextField<String> {

    private KeyListener keyListener = new KeyListener() {

        public void componentKeyUp(ComponentEvent event) {
            //txt += ((char)event.getKeyCode())+"";
            setValue(formatValue(EasyMoneyField.super.getValue()));
        }
    };

    private String formatValue(String v) {
        try {
            if (v != null) {
                if (v.length() == 0) {
                    v = "0";
                }
                v = v.replaceAll("\\D", ""); //Remove tudo o que não é dígito
                v = Long.parseLong(v) + "";
                if (v.length() == 1) {
                    v = "00" + v;
                } else if (v.length() == 2) {
                    v = "0" + v;
                }
                if (v.length() > 2 && v.length() < 6) {
                    v = v.replaceAll("(\\d)(\\d{2})$", "$1,$2"); //Coloca ponto antes dos 2 últimos digitos
                } else if (v.length() <= 8) {
                    v = v.replaceAll("(\\d)(\\d{3})(\\d{2})$", "$1.$2,$3"); //Coloca ponto antes dos 2 últimos digitos
                } else if (v.length() <= 11) {
                    v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3,$4"); //Coloca ponto antes dos 2 últimos digitos
                } else if (v.length() <= 14) {
                    v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4,$5"); //Coloca ponto antes dos 2 últimos digitos
                } else if (v.length() <= 17) {
                    v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4.$5,$6"); //Coloca ponto antes dos 2 últimos digitos
                } else {
                    v = v.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3.$4.$5.$6,$7"); //Coloca ponto antes dos 2 últimos digitos
                }
                return v;
            }
        } catch (Exception e) {
            DebugMessage.message(DebugMessage.ERROR,this.getClass().getName(), e.getMessage());
        }

        return "";
    }

    public EasyMoneyField() {
        removeAllListeners();
        addKeyListener(keyListener);
    }

    public void setValue(double d) {
        long i = new Double(d * 100).longValue();
        double dTemp = Math.ceil(i);
        super.setValueNew(formatValue(dTemp + ""));

//        double dTemp = Math.round(d*100)/100; 
//        super.setValue(formatValue(dTemp + ""));
    }

//    @Override
//    public void setValue(String value) {
//        try {
//            if (value == null || value.trim().isEmpty()) {
//                value = "0";
//            }
//            value = value.replaceAll(",", "\\.");
//            double d = Double.parseDouble(value);
//            long i = new Double(d * 10000).longValue();
//            double dTemp = Math.ceil(i) / 100;
//            super.setValue(formatValue(dTemp + ""));
//
//            //value = formatValue(value);
//            //super.setValue(value);
//        } catch (Exception e) {
//            //super.setValue("error " + e.getMessage());
//        }
//    }
    public String getValue() {
        return String.valueOf(getValueMoney());
    }

    public long toInt() {
        Double d = getValueMoney();
        if (d != null) {
            return d.intValue();
        }
        return 0;
    }

    public long toLong() {
        Double d = getValueMoney();
        if (d != null) {
            return d.longValue();
        }
        return 0;
    }

    public float toFloat() {
        Double d = getValueMoney();
        if (d != null) {
            return d.floatValue();
        }
        return 0;
    }

    public double toDouble() {
        Double d = getValueMoney();
        if (d != null) {
            return d.doubleValue();
        }
        return 0;
    }

    public Double getValueMoney() {
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
