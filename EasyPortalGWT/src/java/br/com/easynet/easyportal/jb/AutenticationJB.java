/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.dao.Usu_usuarioDAO;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.jb.BeanBase;
import java.util.Enumeration;
import java.util.List;

/**
 * Classe que realiza a autenticação
 * @author geoleite
 */
public class AutenticationJB extends EasySecurityJB implements INotSecurity, INotLog {

    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();

    public void sair() throws Exception {
        getSession().invalidate();
        getRequest().getSession(true);
    }

    /**
     * Antes de efetuar a autenticacao a sessao sera esvaziada
     */
    protected void clearSession() {
        Enumeration enume = getSession().getAttributeNames();
        while (enume.hasMoreElements()) {
            String nameKey = (String) enume.nextElement();
            getSession().removeAttribute(nameKey);
        }
    }


    /**
     * Inativa o Usuario após o número de tentativa informada no parâmetro
     * @param usuT
     */
    public void checaNumeroTentativasAcesso(Usu_usuarioT usuT) {
        try {
            if (usuT != null) {
                Par_parametroT parT = getParametro(SystemBase.SISTEMA_EASYPORTAL, "Numero_Tentativas");
                if (parT != null && !"0".equals(parT.getPar_tx_valor().trim())) {
                    Res_recall_senhaT resT = new Res_recall_senhaT();
                    resT.setUsu_nr_id(usuT.getUsu_nr_id());
                    List<Res_recall_senhaT> listRes = getRes_recall_senhaDAO().getByPK(resT);
                    if (listRes.size() > 0) {
                        resT = listRes.get(0);
                        int nrMaxTentativas = Integer.parseInt(parT.getPar_tx_valor());
                        if (resT.getRes_nr_tentativas() >= nrMaxTentativas) {
                            //Inativa o usuário
                            usuT.setUsu_tx_status("I");
                            getUsu_usuarioDAO().update(usuT);
                            setMsg("Numero de tentativas excedeu o limite!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    /**
     * Registrar tentativa inválida de acesso
     * @param usuT
     */
    public void registrarTentativa(Usu_usuarioT usuT) {
        try {
            List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getByUsu_tx_login(usuT);
            if (listUsu.size() > 0) {
                usuT = listUsu.get(0);
                Res_recall_senhaT resT = new Res_recall_senhaT();
                resT.setUsu_nr_id(usuT.getUsu_nr_id());
                getRes_recall_senhaDAO().registrarTentativa(resT);
                checaNumeroTentativasAcesso(usuT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    /**
     * Limpa o contador de tentativas de acesso
     * @param usuT
     */
    public void zerarContadorTentativas(Usu_usuarioT usuT) {
        try {
            List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getByUsu_tx_login(usuT);
            if (listUsu.size() > 0) {
                usuT = listUsu.get(0);
                Res_recall_senhaT resT = new Res_recall_senhaT();
                resT.setUsu_nr_id(usuT.getUsu_nr_id());
                getRes_recall_senhaDAO().zerarContadorTentativas(resT);
                getRes_recall_senhaDAO().registrarAcesso(resT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void autenticar() throws Exception {
        try {
            clearSession();
            IUsu_usuarioDAO usuDao = getUsu_usuarioDAO();
            // Criptografando a Senha do usuário com o Algoritmo MD5
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(usu_usuarioT.getUsu_tx_senha()));
            List<Usu_usuarioT> list = usuDao.getByAutentication(usu_usuarioT);
            close();
            if (list.size() == 0) {
                getSession().invalidate();
                setMsg("Usuário ou senha inválidos!");
                registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", usu_usuarioT.getUsu_tx_login(), getRequest().getRemoteAddr(), "N");
                registrarTentativa(usu_usuarioT);
            } else {
                usu_usuarioT = list.get(0);
                getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, usu_usuarioT);
                setUsuarioLogado((Usu_usuarioT) getSession().getAttribute(USER_PRINCIPAL));
                //getPage().forward("portal.jsp?op=");

                loadPosLogin();
                registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", usu_usuarioT.getUsu_tx_nome(), getRequest().getRemoteAddr(), "S");
                zerarContadorTentativas(usu_usuarioT);
               
                getResponse().sendRedirect("portal/portal.jsp");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }

    public void loadPosLogin() {
        try {
            List<Per_perfilT> listPer = getPer_perfilDAO().getByUsuario(usu_usuarioT);
            close();
            for (Per_perfilT per_perfilT : listPer) {
                easyLogger.debug(per_perfilT.getPer_tx_nome() + " " + per_perfilT.getPer_tx_class());
                if (per_perfilT.getPer_tx_class() != null && per_perfilT.getPer_tx_class().trim().length() > 0) {
                    BeanBase bb = null;
                    try {
                        // Carregar a classe do perfil para inicializar o usuário
                        Object obj = Class.forName(per_perfilT.getPer_tx_class()).newInstance();
                        easyLogger.debug("Event pos login: " + obj.getClass().getName() + " " + (obj instanceof BeanBase));
                        bb = (BeanBase) obj;
                        bb.setPage(getPage());

                        bb.pageLoad();

                    } catch (Exception e) {
                        easyLogger.error(e.getMessage(), e);
                        System.err.println("Erro ao carregar a classe do perfil. (" + per_perfilT.getPer_tx_class() + ")");
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
        } finally {
            close();
        }
    }

    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }
}
