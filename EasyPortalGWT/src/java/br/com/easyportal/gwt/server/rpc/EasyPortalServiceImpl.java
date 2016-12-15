/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.server.rpc;

import br.com.easynet.easyportal.bl.MenMenuBL;
import br.com.easynet.easyportal.bl.ParParametroBL;
import br.com.easynet.easyportal.bl.PerPerfilBL;
import br.com.easynet.easyportal.bl.UsuUsuarioBL;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.SystemBase;
import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.jb.BeanBase;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Men_menuTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Par_parametroTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.easyportal.gwt.client.rpc.EasyPortalService;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author georgejunior
 */
@WebServlet(name = "EasyPortalService", urlPatterns = {"/br.com.easyportal.gwt.EasyPortalGWT/rpc/easyportalservice"})
public class EasyPortalServiceImpl /* *extends RemoteServiceServlet /*   */ extends EasySecurityRPC /* */ implements EasyPortalService {

    @Override
    public Usu_usuarioTGWT authentication(Usu_usuarioTGWT usuT) throws Exception {
        easyLogger.debug("autenticando " + usuT.getUsu_tx_login() + ":" + usuT.getUsu_tx_senha());
        clearSession();
        usuT.setUsu_nr_id(0);
        usuT.setUsu_tx_email("");
        usuT.setUsu_tx_nome("");
        usuT.setUsu_tx_status("A");
        usuT.setUsu_tx_trocarsenha("N");
        Usu_usuarioT usuTBD = UtilConverter.usuTGWTToUsuT(usuT);
        usuTBD.setUsu_tx_senha(usuT.getUsu_tx_senha());
        easyLogger.debug("convertendo usuario " );
        UsuUsuarioBL usuBL = new UsuUsuarioBL();        
        usuTBD = usuBL.autentication(usuTBD);
        easyLogger.debug("usuario autenticado " + usuTBD);
        if (usuTBD == null) {
            throw new Exception("Usuario ou senha invalidos!");
        } else {
            usuT = UtilConverter.usuTToUsuTGWT(usuTBD);
            Res_recall_senhaT resT = usuBL.getRecallSenha(usuTBD);
            usuT.setRes_recall_senha(resT != null ? "S" : "N");
            setUsuarioLogado(usuTBD);
            //getThreadLocalRequest().getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, usuTBD);
            easyLogger.debug("usuario na sessao " + getThreadLocalRequest().getSession().getId());
        }
        return usuT;
    }

    @Override
    public void getOut() {
        getThreadLocalRequest().getSession().invalidate();
        getThreadLocalRequest().getSession(true);
    }

    private void clearSession() {
        Enumeration enume = getThreadLocalRequest().getSession().getAttributeNames();
        while (enume.hasMoreElements()) {
            String nameKey = (String) enume.nextElement();
            getThreadLocalRequest().getSession().removeAttribute(nameKey);
        }
    }

    @Override 
    public Usu_usuarioTGWT getLoggedUser() throws Exception {
        Usu_usuarioT usuTBD = (Usu_usuarioT) getThreadLocalRequest().getSession().getAttribute(EasySecurityJB.USER_PRINCIPAL);
        if (usuTBD == null) {
            throw new Exception("Usuario nao logado!");
        } else {
            Usu_usuarioTGWT usuT = UtilConverter.usuTToUsuTGWT(usuTBD);
            loadPosLogin();
            return usuT;
        }
    }

    public void loadPosLogin() {
        try {
            Usu_usuarioT usuT = getUsuarioLogado();
            List<Per_perfilT> listPer = new PerPerfilBL().getPerfisUsuario(usuT);

            for (Per_perfilT per_perfilT : listPer) {
                easyLogger.debug(per_perfilT.getPer_tx_nome() + " " + per_perfilT.getPer_tx_class());
                if (per_perfilT.getPer_tx_class() != null && per_perfilT.getPer_tx_class().trim().length() > 0) {
                    BeanBase bb = null;
                    try {
                        // Carregar a classe do perfil para inicializar o usuário
                        bb = (BeanBase) Class.forName(per_perfilT.getPer_tx_class()).newInstance();
                        //bb.setPage(getPage());
                        bb.setSession(getThreadLocalRequest().getSession());
                        bb.setRequest(getThreadLocalRequest());
                        bb.setResponse(getThreadLocalResponse());
                        bb.pageLoad();
                    } catch (Exception e) {
                        easyLogger.error(e.getMessage(), e);
                        easyLogger.error("Erro ao carregar a classe do perfil. (" + per_perfilT.getPer_tx_class() + ")");
                    } finally {
                        try {
                            bb.close();
                        } catch (Exception e) {
                        }

                    }
                }
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Per_perfilTGWT> getUserPerfils() throws Exception {
        Usu_usuarioT usuT = getUsuarioLogado();
        List<Per_perfilT> listSrc = new PerPerfilBL().getPerfisUsuario(usuT);
        List<Per_perfilTGWT> listDst = new ArrayList<Per_perfilTGWT>();
        for (int i = 0; i < listSrc.size(); i++) {
            Per_perfilT src = listSrc.get(i);
            Per_perfilTGWT dst = UtilConverter.perTToPerTGWT(src);
            listDst.add(dst);
        }
        return listDst;
    }

    @Override
    public List<Par_parametroTGWT> getParametersBySystem(String systemName) throws Exception {
        List<Par_parametroT> listSrc = new ParParametroBL().getBySystem(systemName);
        List<Par_parametroTGWT> listDst = new ArrayList<Par_parametroTGWT>();
        for (int i = 0; i < listSrc.size(); i++) {
            Par_parametroT src = listSrc.get(i);
            Par_parametroTGWT dst = UtilConverter.parTToParTGWT(src);
            listDst.add(dst);
        }
        return listDst;
    }

    @Override
    public List<Men_menuTGWT> getUserMenu(String systemName) throws Exception {
        Usu_usuarioT usuT = getUsuarioLogado();
        easyLogger.debug("Usuario Logado " + usuT);
        Men_menuT menRootT = new MenMenuBL().createMenu(systemName, usuT);        
        easyLogger.debug("Menu montando no servidor");
        Men_menuTGWT menRootTGWT = UtilConverter.menTToMenTGWT(menRootT);
        easyLogger.debug("Convertendo menu " + menRootTGWT);
        converter(menRootT, menRootTGWT);
        return menRootTGWT.getSubMenu();
    }
    
    private void converter(Men_menuT menTSrc, Men_menuTGWT menTDst) {        
        List<Men_menuT> listSrc = menTSrc.getSubMenu();
        if (listSrc == null || listSrc.size() == 0) {
            return;
        }
        List<Men_menuTGWT> listDst = new ArrayList<Men_menuTGWT>();
        easyLogger.debug("Convertendo menu " + listSrc.size());
        for (int i = 0; i < listSrc.size(); i++) {
            Men_menuT mTSrc = listSrc.get(i);
            Men_menuTGWT mTDst = UtilConverter.menTToMenTGWT(mTSrc);
            easyLogger.debug("menuT " + mTDst.getMen_tx_nome());
            listDst.add(mTDst);
            converter(mTSrc, mTDst);
        }
        menTDst.setSubMenu(listDst);
    } 

    @Override
    public void alterPassword(String actualPass, String newPass, String newConfPass) throws Exception{
        try {
            Usu_usuarioT usuTBD = getUsuarioLogado();
            if (usuTBD == null) {
                throw new Exception("Sessão expirou!");
            }
            new UsuUsuarioBL().changePassword(usuTBD, actualPass, newPass, newConfPass);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void insertRecallPassword(Res_recall_senhaTGWT recallPassT) throws Exception {
        try {
            Usu_usuarioT usuTBD = getUsuarioLogado();
            if (usuTBD == null) {
                throw new Exception("Sessão expirou!");
            }
            recallPassT.setUsu_nr_id(usuTBD.getUsu_nr_id());
            Res_recall_senhaT resT = UtilConverter.resTGWTToResT(recallPassT);
            new UsuUsuarioBL().insertRecallPassword(resT);
        } catch (Exception e) {
            throw e;
        }        
    }

    @Override
    public void updateRecallPassword(Res_recall_senhaTGWT recallPassT) throws Exception {
        try {
            Usu_usuarioT usuTBD = getUsuarioLogado();
            if (usuTBD == null) {
                throw new Exception("Sessão expirou!");
            }
            recallPassT.setUsu_nr_id(usuTBD.getUsu_nr_id());
            Res_recall_senhaT resT = UtilConverter.resTGWTToResT(recallPassT);
            new UsuUsuarioBL().updateRecallPassword(resT);
        } catch (Exception e) {
            throw e;
        }     }
}
