/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.server.rpc;

import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Men_menuTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Par_parametroTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import java.sql.Timestamp;

/**
 *
 * @author georgejunior
 */
public class UtilConverter {
    static public Usu_usuarioTGWT usuTToUsuTGWT(Usu_usuarioT usuTSrc) {
        Usu_usuarioTGWT usuTDst = new Usu_usuarioTGWT();
        usuTDst.setUsu_nr_id(usuTSrc.getUsu_nr_id());
        usuTDst.setUsu_tx_email(usuTSrc.getUsu_tx_email());
        usuTDst.setUsu_tx_login(usuTSrc.getUsu_tx_login());
        usuTDst.setUsu_tx_nome(usuTSrc.getUsu_tx_nome());
        usuTDst.setUsu_tx_status(usuTSrc.getUsu_tx_status());
        usuTDst.setUsu_tx_trocarsenha(usuTSrc.getUsu_tx_trocarsenha());
        return usuTDst;
    }
    
    static public Usu_usuarioT usuTGWTToUsuT(Usu_usuarioTGWT usuTSrc) {
        Usu_usuarioT usuTDst = new Usu_usuarioT();
        usuTSrc.setUsu_nr_id(usuTSrc.getUsu_nr_id());
        usuTDst.setUsu_tx_email(usuTSrc.getUsu_tx_email());
        usuTDst.setUsu_tx_login(usuTSrc.getUsu_tx_login());
        usuTDst.setUsu_tx_nome(usuTSrc.getUsu_tx_nome());
        usuTDst.setUsu_tx_status(usuTSrc.getUsu_tx_status());
        usuTDst.setUsu_tx_trocarsenha(usuTSrc.getUsu_tx_trocarsenha());
        return usuTDst;
    }
    
    static public Per_perfilTGWT perTToPerTGWT(Per_perfilT perTSrc) {
        Per_perfilTGWT perTDst = new Per_perfilTGWT();
        perTDst.setPer_nr_id(perTSrc.getPer_nr_id());
        perTDst.setPer_tx_class(perTSrc.getPer_tx_class());
        perTDst.setPer_tx_nome(perTSrc.getPer_tx_nome());
        perTDst.setPer_tx_status(perTSrc.getPer_tx_status());
        return perTDst;
    }
    
    static public Per_perfilT perTGWTToPerT(Per_perfilTGWT perTSrc) {
        Per_perfilT perTDst = new Per_perfilT();
        perTDst.setPer_nr_id(perTSrc.getPer_nr_id());
        perTDst.setPer_tx_class(perTSrc.getPer_tx_class());
        perTDst.setPer_tx_nome(perTSrc.getPer_tx_nome());
        perTDst.setPer_tx_status(perTSrc.getPer_tx_status());
        return perTDst;
    }
    
    static public Par_parametroTGWT parTToParTGWT(Par_parametroT parTSrc) {
        Par_parametroTGWT parTDst = new Par_parametroTGWT();
        parTDst.setSis_nr_id(parTSrc.getSis_nr_id());
        parTDst.setPar_tx_nome(parTSrc.getPar_tx_nome());
        parTDst.setPar_tx_valor(parTSrc.getPar_tx_valor());
        return parTDst;
    }
    
    static public Men_menuTGWT menTToMenTGWT(Men_menuT menTSrc) {
        Men_menuTGWT menTDst = new Men_menuTGWT();
        menTDst.setSis_nr_id(menTSrc.getSis_nr_id());
        menTDst.setMen_nr_id(menTSrc.getMen_nr_id());
        menTDst.setMen_nr_ordem(menTSrc.getMen_nr_ordem());
        menTDst.setMen_tx_acao(menTSrc.getMen_tx_acao());
        menTDst.setMen_tx_icone(menTSrc.getMen_tx_icone());
        menTDst.setMen_tx_nome(menTSrc.getMen_tx_nome());
        menTDst.setMen_tx_status(menTSrc.getMen_tx_status());
        menTDst.setMen_tx_url(menTSrc.getMen_tx_url());
        menTDst.setSistema(menTSrc.getSistema());
        return menTDst;
    }    

    static Res_recall_senhaT resTGWTToResT(Res_recall_senhaTGWT recallPassT) {
        Res_recall_senhaT resT = new Res_recall_senhaT();
        resT.setRes_dt_ultimoacesso(new Timestamp(System.currentTimeMillis()));
        resT.setRes_nr_tentativas(0);
        resT.setRes_tx_pergunta(recallPassT.getRes_tx_pergunta());
        resT.setRes_tx_resposta(recallPassT.getRes_tx_resposta());
        resT.setUsu_nr_id(recallPassT.getUsu_nr_id());
        return resT;
    }
}
