/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.jb;

import java.util.Vector;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;

/**
 *
 * @author geoleite
 */
public class ExemploBoletoJB extends BeanBase {

    public void gerarBoleto() throws Exception {
        JBoletoBean jBoletoBean = new JBoletoBean();
        jBoletoBean.setDataDocumento("31/05/2006");
        jBoletoBean.setDataProcessamento("31/05/2006");

        jBoletoBean.setCedente("AINODE Solucoes");
        jBoletoBean.setCarteira("17");

        jBoletoBean.setNomeSacado("GtTurbo");
        jBoletoBean.setEnderecoSacado("Rua Araticum 951");
        jBoletoBean.setBairroSacado("Anil");
        jBoletoBean.setCidadeSacado("Rio de Janeiro");
        jBoletoBean.setUfSacado("RJ");
        jBoletoBean.setCepSacado("22753-501");
        jBoletoBean.setCpfSacado("87524988753");

        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO BANCO DO BRASIL");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BANCO DO BRASIL");

        Vector descricoes = new Vector();
        descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        jBoletoBean.setDescricoes(descricoes);

        jBoletoBean.setDataVencimento("10/06/2006");
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        jBoletoBean.setAgencia("3415");
        jBoletoBean.setContaCorrente("00543004"); //completar com zeros quando necessario

        jBoletoBean.setNumConvenio("1101354");
        jBoletoBean.setNossoNumero("0005963971", 10);
        jBoletoBean.setValorBoleto("1.00");
        getRequest().setAttribute(EasyBoletoJB.BOLETO_BEAN, jBoletoBean);
        getRequest().setAttribute(EasyBoletoJB.TIPO_BANCO_BOLETO, new Integer(JBoleto.BANCO_DO_BRASIL));
        getPage().forward("easyboleto.jsp");
    }
}
