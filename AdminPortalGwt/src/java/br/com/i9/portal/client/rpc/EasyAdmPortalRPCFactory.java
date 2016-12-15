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
        Sis_sistemaServiceAsync sisServiceAsync = GWT.create(Sis_sistemaService.class);
        defineServiceUrl((ServiceDefTarget) sisServiceAsync, "sis_sistemaservice");
        return sisServiceAsync;
    }

    public static LogServiceAsync getLogService() {
        LogServiceAsync logServiceAsync = GWT.create(LogService.class);
        defineServiceUrl((ServiceDefTarget) logServiceAsync, "logservice");
        return logServiceAsync;
    }

    public static Men_menuServiceAsync getMen_MenuService() {
        Men_menuServiceAsync menServiceAsync = GWT.create(Men_menuService.class);
        defineServiceUrl((ServiceDefTarget) menServiceAsync, "men_menuservice");
        return menServiceAsync;
    }

    public static Met_metodoServiceAsync getMet_MetodoService() {
        Met_metodoServiceAsync metServiceAsync = GWT.create(Per_perfilService.class);
        defineServiceUrl((ServiceDefTarget) metServiceAsync, "met_metodoservice");
        return metServiceAsync;
    }

    public static Mep_men_perServiceAsync getMep_Men_PerService() {
        Mep_men_perServiceAsync mepServiceAsync = GWT.create(Mep_men_perService.class);
        defineServiceUrl((ServiceDefTarget) mepServiceAsync, "mep_men_perservice");
        return mepServiceAsync;
    }

    public static Ope_operacaoServiceAsync getOpe_operacaoService() {
        Ope_operacaoServiceAsync opeServiceAsync = GWT.create(Ope_operacaoService.class);
        defineServiceUrl((ServiceDefTarget) opeServiceAsync, "ope_operacaoservice");
        return opeServiceAsync;
    }

    public static Opm_met_ope_perServiceAsync getOpm_Met_Ope_PerService() {
        Opm_met_ope_perServiceAsync opmServiceAsync = GWT.create(Opm_met_ope_perService.class);
        defineServiceUrl((ServiceDefTarget) opmServiceAsync, "opm_met_ope_perservice");
        return opmServiceAsync;
    }

    public static Par_parametroServiceAsync getPar_ParametroService() {
        Par_parametroServiceAsync parServiceAsync = GWT.create(Par_parametroService.class);
        defineServiceUrl((ServiceDefTarget) parServiceAsync, "par_parametroservice");
        return parServiceAsync;
    }

    public static Per_perfilServiceAsync getPer_PerfilService() {
        Per_perfilServiceAsync perServiceAsync = GWT.create(Per_perfilService.class);
        defineServiceUrl((ServiceDefTarget) perServiceAsync, "per_perfilservice");
        return perServiceAsync;
    }

    public static Pu_per_usuTServiceAsync getPu_Per_UsuService() {
        Pu_per_usuTServiceAsync puServiceAsync = GWT.create(Pu_per_usuTService.class);
        defineServiceUrl((ServiceDefTarget) puServiceAsync, "pu_per_usutservice");
        return puServiceAsync;
    }

    public static Res_recall_senhaServiceAsync getRes_Recall_SenhaService() {
        Res_recall_senhaServiceAsync resServiceAsync = GWT.create(Res_recall_senhaService.class);
        defineServiceUrl((ServiceDefTarget) resServiceAsync, "res_recall_senhaservice");
        return resServiceAsync;
    }

    public static Usu_usuarioServiceAsync getUsu_UsuarioService() {
        Usu_usuarioServiceAsync usuServiceAsync = GWT.create(Usu_usuarioService.class);
        defineServiceUrl((ServiceDefTarget) usuServiceAsync, "usu_usuarioservice");
        return usuServiceAsync;
    }
    
    public static Per_perfilServiceAsync getVw_Dados_PedidoService() {
        Per_perfilServiceAsync perServiceAsync = GWT.create(Per_perfilService.class);
        defineServiceUrl((ServiceDefTarget) perServiceAsync, "per_perfilservice");
        return perServiceAsync;
    }
}
