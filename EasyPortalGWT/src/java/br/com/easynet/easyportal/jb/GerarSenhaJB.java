/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.email.SendMail;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class GerarSenhaJB extends SystemBase{
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();

    private void findUser() throws Exception {
        try {
            List<Usu_usuarioT> list = getUsu_usuarioDAO().getByLoginEmail(getUsu_usuarioT());
            usu_usuarioT =list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            usu_usuarioT = null;
        } finally {
            close();
        }
    }

    public void gerarNovaSenha () throws Exception {
        findUser();
        
        if (getUsu_usuarioT() == null || "anonimo".equals(usu_usuarioT.getUsu_tx_login()) ||
                (usu_usuarioT.getUsu_tx_login().trim().length() ==0)) {
            setMsg("Usuário ou email Inválido!");
        } else {
            String server = "smtp.geoleite.com.br";
            String fromEmail = "admin@geoleite.com.br";
            String fromPassword = "eusoumuitobom";// Senha do email
            String fromName = "EasyPortal";
            String toEmail = getUsu_usuarioT().getUsu_tx_email();
            String toName = getUsu_usuarioT().getUsu_tx_nome();
            String subject = "Gerar Nova Senha";
            String contentType = "text/plain";
            StringBuffer msg = new StringBuffer("");
            msg.append("");
            String novaSenha = "" + System.currentTimeMillis();
            msg.append("\nHouve uma solicitacao por uma nova senha, por isto enviamos para o seu email sua nova senha!\n");
            msg.append("Sua senha:").append(novaSenha);
            msg.append("\n").append("Apos entrar no sistema altere sua senha.");
            try {
                getUsu_usuarioT().setUsu_tx_senha(MD5.criptografar(novaSenha));
                getUsu_usuarioDAO().updateSenha(getUsu_usuarioT());
                // Define o servidor de email
                SendMail sm = new SendMail(server);
                // Enviando o email
                sm.enviarEmail(fromName, fromEmail, fromPassword, toName, 
                               toEmail, subject, msg.toString(), contentType);
                setMsg("Solicitacao de nova senha enviada com sucesso!");
            } catch (Exception e) {
                easyLogger.error(e.getMessage(), e);
            } finally {
                close();
            }
        }
    }
    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }
}
