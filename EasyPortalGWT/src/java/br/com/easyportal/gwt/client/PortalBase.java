/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.CPBaseGWT;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Par_parametroDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Res_recall_senhaDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha.Res_recall_senhaInsertGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha.Res_recall_senhaUpdateDeleteGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Par_parametroTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import br.com.easyportal.gwt.client.rpc.EasyPortalRPCFactory;
import br.com.easyportal.gwt.client.rpc.EasyPortalServiceAsync;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.SwallowEvent;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class PortalBase extends CPBaseGWT {

    private static TreeMap<String, TreeMap<String, String>> parametrosSistema = new TreeMap<String, TreeMap<String, String>>();
    private String nomeSistema;
    private static Usu_usuarioTGWT usuarioLogado;
    protected Res_recall_senhaDAOGWT res_recall_senhaDAOGWT = new Res_recall_senhaDAOGWT();

    public void carregaParametrosSistema(final String sistema) {
//aki
        DebugMessage.message(this.getClass().getName(), "Carregando parametros " + sistema);
        EasyPortalServiceAsync epsAsync = EasyPortalRPCFactory.getEasyPortalService();
        AsyncCallback<List<Par_parametroTGWT>> callback = new AsyncCallback<List<Par_parametroTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Par_parametroTGWT> result) {
                //com.google.gwt.user.client.Window.alert(result + "");
                TreeMap<String, String> param = new TreeMap<String, String>();
                for (int i = 0; i < result.size(); i++) {
                    Par_parametroTGWT parT = result.get(i);
                    DebugMessage.message(this.getClass().getName(), "param " + parT.getPar_tx_nome() + ":" + parT.getPar_tx_valor());
                    param.put(parT.getPar_tx_nome(), parT.getPar_tx_valor());
                }
                parametrosSistema.put(sistema, param);
            }
        };
        epsAsync.getParametersBySystem(sistema, callback);
        /*        
         final Par_parametroDAOGWT parDao = new Par_parametroDAOGWT();
         //DebugMessage.message("Carregando Parametros do sistema " + sistema);
         parDao.consultBySistema(sistema);
         Timer timer = new Timer() {

         @Override
         public void run() {
         if (!parDao.isFinalized()) {
         schedule(500);
         } else {
         //DebugMessage.message(sistema + " Parametros " + parDao.getList().getCount() );
         ListStore<Par_parametroTGWT> listPar = parDao.getList();
         TreeMap<String, String> param = new TreeMap<String, String>();
         for (int i = 0; i < listPar.getCount(); i++) {
         Par_parametroTGWT parT = listPar.getAt(i);
         param.put(parT.getPar_tx_nome(), parT.getPar_tx_valor());
         }
         parametrosSistema.put(sistema, param);
         }
         }
         };
         timer.schedule(500);
         */
    }

    public void insertUpdateRecallSenha() {
        if (getUsuarioLogado() != null) {
            Res_recall_senhaTGWT resT = new Res_recall_senhaTGWT();
            resT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            res_recall_senhaDAOGWT.findByPK(resT);
            Timer timer = new Timer() {

                @Override
                public void run() {
                    if (!res_recall_senhaDAOGWT.isFinalized()) {
                        schedule(500);
                    } else {
                        Res_recall_senhaTGWT resT = res_recall_senhaDAOGWT.getRes_recall_senhaT();
                        if (resT.getRes_tx_pergunta() == null || resT.getRes_tx_pergunta().trim().length() == 0) {
                            Res_recall_senhaInsertGWT resInsert = new Res_recall_senhaInsertGWT();
                            resInsert.setVisible(true);
                        } else {
                            Res_recall_senhaUpdateDeleteGWT resUpdateDeleteGWT = new Res_recall_senhaUpdateDeleteGWT();
                            resUpdateDeleteGWT.load(resT);
                            resUpdateDeleteGWT.setVisible(true);
                        }
                    }
                }
            };
            timer.schedule(1000);
        }
    }

    public void checaRecallSenha() {
        if (getUsuarioLogado() != null) {
            if ("N".equalsIgnoreCase(getUsuarioLogado().getRes_recall_senha())) {
                Res_recall_senhaInsertGWT resInsert = new Res_recall_senhaInsertGWT();
                resInsert.setVisible(true);
            }
            /*
             Res_recall_senhaTGWT resT = new Res_recall_senhaTGWT();
             resT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());

             res_recall_senhaDAOGWT.findByPK(resT);
             Timer timer = new Timer() {

             @Override
             public void run() {
             if (!res_recall_senhaDAOGWT.isFinalized()) {
             schedule(500);
             } else {
             Res_recall_senhaTGWT resT = res_recall_senhaDAOGWT.getRes_recall_senhaT();
             if (resT.getRes_tx_pergunta() == null || resT.getRes_tx_pergunta().trim().length() == 0) {
             Res_recall_senhaInsertGWT resInsert = new Res_recall_senhaInsertGWT();
             resInsert.setVisible(true);
             }
             }
             }
             };
             timer.schedule(1000);
             */
        }
    }

    /**
     * Obtem o parametro de um sistema
     *
     * @param sistema
     * @param param
     * @return
     */
    public static String getParametro(String sistema, String nomeParam) {
        TreeMap<String, String> param = parametrosSistema.get(sistema);
        if (param != null) {
            return param.get(nomeParam);
        }
        return null;
    }

    /**
     * @return the nomeSistema
     */
    public String getNomeSistema() {
        return nomeSistema;
    }

    /**
     * @param nomeSistema the nomeSistema to set
     */
    public void setNomeSistema(String nomeSistema) {
        this.nomeSistema = nomeSistema;
    }

    /**
     * @return the usuarioLogado
     */
    public static Usu_usuarioTGWT getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param aUsuarioLogado the usuarioLogado to set
     */
    public static void setUsuarioLogado(Usu_usuarioTGWT aUsuarioLogado) {
        usuarioLogado = aUsuarioLogado;
    }
}
