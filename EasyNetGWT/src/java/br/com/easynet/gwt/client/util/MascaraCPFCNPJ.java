/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.util;

/**
 *
 * @author marcos
 */
public class MascaraCPFCNPJ {

    public static String getMascaraCPFCNPJ(String cpf_cnpj) {
        String valueMax = "";

        if(cpf_cnpj.trim().length() == 11){
            valueMax =  getValueCPF(cpf_cnpj);
        }else if (cpf_cnpj.trim().length() == 14){
            valueMax = getvalueCNPJ(cpf_cnpj);
        }
        if (valueMax.trim().length() == 0) {
            valueMax = cpf_cnpj;
        }
        return valueMax;
    }

    private static String getValueCPF(String cpf) {
        String value = "";
        for (int i = 0; i < cpf.length(); i++) {
            value += cpf.substring(i, i + 1);
            if (i == 2 || i == 5) {
                value += ".";
            } else if (i == 8) {
                value += "-";
            }
        }
        return value;
    }

    private static String getvalueCNPJ(String cnpj) {
        String value = "";
        for (int i = 0; i < cnpj.length(); i++) {
            value += cnpj.substring(i, i + 1);
            if (i == 1 || i == 4) {
                value += ".";
            } else if (i == 7) {
                value += "/";
            } else if (i == 11) {
                value += "-";
            }
        }
        return value;
    }
}
