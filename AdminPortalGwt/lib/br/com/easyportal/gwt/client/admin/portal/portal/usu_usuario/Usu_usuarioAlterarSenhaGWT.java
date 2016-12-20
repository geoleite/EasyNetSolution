/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.admin.portal.portal.usu_usuario;

import br.com.easynet.gwt.client.CadastrarBaseGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Usu_usuarioDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioAlterarSenhaGWT extends CadastrarBaseGWT{
    private Usu_usuarioTGWT usu_usuarioT;
    private TextField<String> novaSenha = new TextField<String>();
    private TextField<String> confSenha = new TextField<String>();
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    public Usu_usuarioAlterarSenhaGWT() {
        setHeading("Definir Nova senha do Usuário: ");
        setSize(200, 200);
        //getCpMaster().
    }

    public void btnFecharAction(ButtonEvent ce){
        setVisible(false);
    }

    /**
     * Validando os campos de senha
     * @return
     */
    private boolean validandoSenhas() {
        boolean invalideSenha = false;
        // Valida se a senha é diferente de null, no mínimo com 6 caracteres e igual a senha
        if (confSenha.getValue() == null) {
            MessageBox.alert("Erro na senha", "Senha não pode ser nula!", null);
            invalideSenha = true;
        } else if (confSenha.getValue().trim().length() < 6) {
            MessageBox.alert("Erro na senha", "Senha deve conter 6 ou mais caracteres!", null);
            invalideSenha = true;
        } else if (!confSenha.getValue().equals(novaSenha.getValue())) {
            MessageBox.alert("Ërro na senha", "Senha não confirmada!", null);
            invalideSenha = true;
        }
        if (invalideSenha) {
            return false;
        }
        return true;
    }
    public void btnInsertAction(ButtonEvent ce) {
        //Só submet se as senhas estiverem validadas
        if (validandoSenhas()) {
            usu_usuarioT.setUsu_tx_senha(novaSenha.getValue());
            usu_usuarioDao.alterarSenha(usu_usuarioT, confSenha.getValue());
            Timer timer = new Timer() {

                public void run() {
                    String msg = usu_usuarioDao.getMsg();
                    if (msg == null) {
                        schedule(500);
                    } else {
                        if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                            MessageBox.alert("Problemas", msg, null);
                        } else {
                            Info.display("Resultado", msg);
                            btnLimpartAction(null);                            
                            setVisible(false);
                        }
                    }
                }
            };
            timer.schedule(500);
        }
    }
    /**
     * @return the usu_usuarioTGWT
     */
    public Usu_usuarioTGWT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    /**
     * @param usu_usuarioTGWT the usu_usuarioTGWT to set
     */
    public void setUsu_usuarioTGWT(Usu_usuarioTGWT usu_usuarioTGWT) {
        this.usu_usuarioT = usu_usuarioTGWT;
        getCpMaster().add(new LabelField("Usuário: " + usu_usuarioT.getUsu_tx_nome()));
        getCpMaster().add(new LabelField("Nova Senha:"));
        novaSenha.setPassword(true);
        getCpMaster().add(novaSenha);
        getCpMaster().add(new LabelField("Conf. Senha:"));
        confSenha.setPassword(true);
        getCpMaster().add(confSenha);
    }
}
