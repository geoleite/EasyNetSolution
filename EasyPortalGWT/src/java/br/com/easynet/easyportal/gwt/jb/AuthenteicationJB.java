/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.gwt.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.dao.Usu_usuarioDAO;
import br.com.easynet.easyportal.jb.AutenticationJB;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.INotSecurity;
import br.com.easynet.easyportal.jb.SystemBase;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.jb.BeanBase;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class AuthenteicationJB extends AutenticationJB {

    private boolean loginOk = false;
    private String result = "false";
    private final static String CLIENT_CERT = "javax.servlet.request.X509Certificate";

    public void checklogin() throws Exception {
        setUsu_usuarioT(getUsuarioLogado());
        if (getUsu_usuarioT() != null) {
            setResult("true");
        }
    }

    private String getNome(String param) throws Exception {
        String[] params = param.split(",");
        return params[0].split("=")[1].split(":")[0];
    }

    private String getCpfCnpj(String param) {
        String[] params = param.split(",");
        return params[0].split(":")[1];
    }

    public void verificarSessao() throws Exception {
        try {
            if (getUsuarioLogado() == null) {
                setResult("false");
            } else {
                setResult("true");
                List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getById(getUsuarioLogado());
                if (listUsu.size() > 0) {
                    getUsuarioLogado().setUsu_tx_trocarsenha(listUsu.get(0).getUsu_tx_trocarsenha());
                    setUsu_usuarioT(getUsuarioLogado());
                }
            }
        } catch (Exception e) {
        } finally {
            close();
        }
    }

    public void autenticarDigital() throws Exception {
        try {
            clearSession();
            X509Certificate[] certs = (X509Certificate[]) getRequest().getAttribute(CLIENT_CERT);
            String cpfCnpj = "";
            String nome = "";
            if (certs == null) {
                registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticarDigital", "certificado nao informado", getRequest().getRemoteAddr(), "S");
                //setResult("true");
            } else {
                for (int i = 0; i < 1; i++) {
                    X509Certificate x509Certificate = certs[i];
                    x509Certificate.checkValidity();
                    String param = x509Certificate.getSubjectDN().getName();
                    nome = getNome(param);
                    cpfCnpj = getCpfCnpj(param);
                }
                getUsu_usuarioT().setUsu_tx_nome(nome);
                getUsu_usuarioT().setUsu_tx_login(cpfCnpj);
                IUsu_usuarioDAO usuDao = getUsu_usuarioDAO();
                // Criptografando a Senha do usuário com o Algoritmo MD5
                List<Usu_usuarioT> list = usuDao.getByNomeLogin(getUsu_usuarioT());
                if (list.size() == 0) {
                    getSession().invalidate();
                    setMsg("Usuario ou senha invalidos!");
                    registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", getUsu_usuarioT().getUsu_tx_login(), getRequest().getRemoteAddr(), "N");
                } else {
                    setUsu_usuarioT(list.get(0));
                    getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, getUsu_usuarioT());
                    setUsuarioLogado((Usu_usuarioT) getSession().getAttribute(USER_PRINCIPAL));
                    //getPage().forward("portal.jsp?op=");
                    loadPosLogin();
                    registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", getUsu_usuarioT().getUsu_tx_nome(), getRequest().getRemoteAddr(), "S");
                    setResult("true");
                    loginOk = true;
                    //getResponse().sendRedirect("portal/portal.jsp");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    public void autenticar() throws Exception {
        try {
            clearSession();
            IUsu_usuarioDAO usuDao = getUsu_usuarioDAO();
            // Criptografando a Senha do usuário com o Algoritmo MD5
            getUsu_usuarioT().setUsu_tx_senha(MD5.criptografar(getUsu_usuarioT().getUsu_tx_senha()));
            List<Usu_usuarioT> list = usuDao.getByAutentication(getUsu_usuarioT());
            if (list.size() == 0) {
                getSession().invalidate();
                setMsg("Usuário ou senha inválidos!");
                registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", getUsu_usuarioT().getUsu_tx_login(), getRequest().getRemoteAddr(), "N");
                registrarTentativa(getUsu_usuarioT());
            } else {
                setUsu_usuarioT(list.get(0));
                getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, getUsu_usuarioT());
                setUsuarioLogado((Usu_usuarioT) getSession().getAttribute(USER_PRINCIPAL));
                //getPage().forward("portal.jsp?op=");
                loadPosLogin();
                registroLog(application, "EasyPortal", "br.com.easynet.easyportal.jb.AutenticacaoJB", "autenticar", getUsu_usuarioT().getUsu_tx_nome(), getRequest().getRemoteAddr(), "S");
                setResult("true");
                zerarContadorTentativas(getUsu_usuarioT());
                //getResponse().sendRedirect("portal/portal.jsp");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the loginOk
     */
    public boolean isLoginOk() {
        return loginOk;
    }

    /**
     * @param loginOk the loginOk to set
     */
    public void setLoginOk(boolean loginOk) {
        this.loginOk = loginOk;
    }
}
