/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.criptografia.PasswordGenerate;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.email.SendMail;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Res_recall_senhaUpdateDeleteJB extends EasySecurityJB implements INotSecurity {

    private Res_recall_senhaT res_recall_senhaT = new Res_recall_senhaT();
    private String login;
    private String email;

    public void update() {
        try {
            res_recall_senhaT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            res_recall_senhaT.setRes_dt_ultimoacesso(new Timestamp(System.currentTimeMillis()));
            res_recall_senhaT.setRes_nr_tentativas(0);
            getRes_recall_senhaDAO().update(res_recall_senhaT);
            setMsg(INFO, "Alteracao efetuado com sucesso!");
        } catch (Exception e) {
            setMsg(ERROR, "Falha: ".concat(e.getMessage()));
        } finally {
            close();
        }    
    }

    public void findByPK() {
        try {
            List<Res_recall_senhaT> listRes = getRes_recall_senhaDAO().getByPK(res_recall_senhaT);
            if (listRes.size() > 0) {
                res_recall_senhaT = listRes.get(0);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void findByLoginEmail() {
        try {
            List<Res_recall_senhaT> listRes = getRes_recall_senhaDAO().getByLoginEmail(login, email);
            if (listRes.size() > 0) {
                res_recall_senhaT = listRes.get(0);
            } else {
                setMsg("Login and Email not confere.");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    private void enviarEmail(Usu_usuarioT usu_usuarioT, String senha) {
        try {
            String emailServer = getParametro("emailServer");
            String sslServer = getParametro("sslServer");
            String userMail = getParametro("userMail");
            String portServer = getParametro("portServer");
            String fromName = getParametro("fromName");
            String fromEmail = getParametro("fromEmail");
            String fromPassword = getParametro("fromPassword");
            String subject = getParametro("subject");
            String toName = usu_usuarioT.getUsu_tx_nome();
            String toEmail = usu_usuarioT.getUsu_tx_email();
            String contentType = "text/html";
            String msg = getParametro("msg");
            msg = msg.replaceAll(";USUARIO;", usu_usuarioT.getUsu_tx_nome());
            msg = msg.replaceAll(";SENHA;", senha);
            SendMail sm = new SendMail(emailServer);
            sm.enviarEmailGmail(fromName, userMail, fromEmail, fromPassword, toName, toEmail, subject,
                    msg, contentType, portServer, sslServer);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
    }

    public void generatePassword() {
        try {
            if (res_recall_senhaT.getUsu_nr_id() == 0 || res_recall_senhaT.getRes_tx_resposta() == null
                    || res_recall_senhaT.getRes_tx_resposta().trim().length() == 0) {

                setMsg("Resposta não informada");
            } else {
                List<Res_recall_senhaT> listRes = getRes_recall_senhaDAO().getByPK(res_recall_senhaT);
                if (listRes.size() > 0) {
                    Res_recall_senhaT resT = listRes.get(0);
                    if (res_recall_senhaT.getRes_tx_resposta().equalsIgnoreCase(resT.getRes_tx_resposta())) {
                        //Gerar nova senha e enviar por email
                        Usu_usuarioT usuT = new Usu_usuarioT();
                        usuT.setUsu_nr_id(resT.getUsu_nr_id());
                        List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getById(usuT);
                        if (listUsu.size() > 0) {
                            usuT = listUsu.get(0);
                            String novaSenha = PasswordGenerate.genereteStringNumber(8);
                            novaSenha = novaSenha.toLowerCase().replace('l', 'i');
                            usuT.setUsu_tx_senha(MD5.criptografar(novaSenha));
                            getUsu_usuarioDAO().updateSenha(usuT);
                            enviarEmail(usuT, novaSenha);
                            setMsg("Nova senha gerada e enviada com sucesso!");
                        }
                    } else {
                        setMsg("Resposta não confere.");
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the res_recall_senhaT
     */
    public Res_recall_senhaT getRes_recall_senhaT() {
        return res_recall_senhaT;
    }

    /**
     * @param res_recall_senhaT the res_recall_senhaT to set
     */
    public void setRes_recall_senhaT(Res_recall_senhaT res_recall_senhaT) {
        this.res_recall_senhaT = res_recall_senhaT;
    }
}
