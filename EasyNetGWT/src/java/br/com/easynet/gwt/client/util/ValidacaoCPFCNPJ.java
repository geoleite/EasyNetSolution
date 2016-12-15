/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.util;

import com.extjs.gxt.ui.client.widget.MessageBox;

/**
 *
 * @author geoleite
 */
public class ValidacaoCPFCNPJ {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11) ||
                ("11111111111".equals(cpf) ) ||
                ("22222222222".equals(cpf) ) ||
                ("33333333333".equals(cpf) ) ||
                ("44444444444".equals(cpf) ) ||
                ("55555555555".equals(cpf) ) ||
                ("66666666666".equals(cpf) ) ||
                ("77777777777".equals(cpf) ) ||
                ("88888888888".equals(cpf) ) ||
                ("99999999999".equals(cpf) ) ||
                ("00000000000".equals(cpf) ) ) {
            return false;
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }
}
