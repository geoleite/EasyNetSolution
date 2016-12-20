/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author georgejunior
 */
public class EasyAdmPortalRPCFactory {
//    public static Per_perfilServiceAsync getVw_Dados_PedidoService() {
//        return GWT.create(Per_perfilService.class);
//    }

    private static void defineServiceUrl(ServiceDefTarget serviceDef, String service) {
        String serviceUrl = GWT.getHostPageBaseURL() + "br.com.i9.portal.Main/rpc/" + service;
        serviceDef.setServiceEntryPoint(serviceUrl);

    }
    //public static Tip_tipoServiceAsync getTip_tipoService() {
    //    return GWT.create(Tip_tipoService.class);
    //}

    public static Sis_sistemaServiceAsync getSis_sistemaService() {
        Sis_sistemaServiceAsync sisServiceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
        defineServiceUrl((ServiceDefTarget) sisServiceAsync, "sis_sistemaservice");
        return sisServiceAsync;
    }

    public static LogServiceAsync getLogService() {
        LogServiceAsync logServiceAsync = EasyAdmPortalRPCFactory.getLogService();
        defineServiceUrl((ServiceDefTarget) logServiceAsync, "logservice");
        return logServiceAsync;
    }

    public static Men_menuServiceAsync getMen_MenuService() {
        Men_menuServiceAsync menServiceAsync = EasyAdmPortalRPCFactory.getMen_MenuService();
        defineServiceUrl((ServiceDefTarget) menServiceAsync, "men_menuservice");
        return menServiceAsync;
    }

    public static Met_metodoServiceAsync getMet_MetodoService() {
        Met_metodoServiceAsync metServiceAsync = EasyAdmPortalRPCFactory.getMet_MetodoService();
        defineServiceUrl((ServiceDefTarget) metServiceAsync, "met_metodoservice");
        return metServiceAsync;
    }

    public static Mep_men_perServiceAsync getMep_Men_PerService() {
        Mep_men_perServiceAsync mepServiceAsync = EasyAdmPortalRPCFactory.getMep_Men_PerService();
        defineServiceUrl((ServiceDefTarget) mepServiceAsync, "mep_men_perservice");
        return mepServiceAsync;
    }

    public static Ope_operacaoServiceAsync getOpe_operacaoService() {
        Ope_operacaoServiceAsync opeServiceAsync = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        defineServiceUrl((ServiceDefTarget) opeServiceAsync, "ope_operacaoservice");
        return opeServiceAsync;
    }

    public static Opm_met_ope_perServiceAsync getOpm_Met_Ope_PerService() {
        Opm_met_ope_perServiceAsync opmServiceAsync = EasyAdmPortalRPCFactory.getOpm_Met_Ope_PerService();
        defineServiceUrl((ServiceDefTarget) opmServiceAsync, "opm_met_ope_perservice");
        return opmServiceAsync;
    }

    public static Par_parametroServiceAsync getPar_ParametroService() {
        Par_parametroServiceAsync parServiceAsync = EasyAdmPortalRPCFactory.getPar_ParametroService();
        defineServiceUrl((ServiceDefTarget) parServiceAsync, "par_parametroservice");
        return parServiceAsync;
    }

    public static Per_perfilServiceAsync getPer_PerfilService() {
        Per_perfilServiceAsync perServiceAsync = EasyAdmPortalRPCFactory.getPer_PerfilService();
        defineServiceUrl((ServiceDefTarget) perServiceAsync, "per_perfilservice");
        return perServiceAsync;
    }

    public static Pu_per_usuTServiceAsync getPu_Per_UsuService() {
        Pu_per_usuTServiceAsync puServiceAsync = EasyAdmPortalRPCFactory.getPu_Per_UsuService();
        defineServiceUrl((ServiceDefTarget) puServiceAsync, "pu_per_usutservice");
        return puServiceAsync;
    }

    public static Res_recall_senhaServiceAsync getRes_Recall_SenhaService() {
        Res_recall_senhaServiceAsync resServiceAsync = EasyAdmPortalRPCFactory.getRes_Recall_SenhaService();
        defineServiceUrl((ServiceDefTarget) resServiceAsync, "res_recall_senhaservice");
        return resServiceAsync;
    }

    public static Usu_usuarioServiceAsync getUsu_UsuarioService() {
        Usu_usuarioServiceAsync usuServiceAsync = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        defineServiceUrl((ServiceDefTarget) usuServiceAsync, "usu_usuarioservice");
        return usuServiceAsync;
    }
    
    public static Per_perfilServiceAsync getVw_Dados_PedidoService() {
        Per_perfilServiceAsync perServiceAsync = EasyAdmPortalRPCFactory.getPer_PerfilService();
        defineServiceUrl((ServiceDefTarget) perServiceAsync, "per_perfilservice");
        return perServiceAsync;
    }
}
