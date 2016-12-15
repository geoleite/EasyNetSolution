/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.dao.Usu_usuarioDAO;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;

/**
 *
 * @author geoleite
 */
public class AlterarSenhaJB extends EasySecurityJB implements INotSecurity {

    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private String confnovasenha,  senhaatual,  novasenha;
    private boolean result = false;

    public void pageLoad() throws Exception {
        usu_usuarioT = getUsuarioLogado();
    }

    public void alterarSenha() throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            boolean invalideSenha = false;
            // Valida se a senha é diferente de null, no mínimo com 6 caracteres e igual a senha
            if (confnovasenha == null) {
                setMsg("Senha nao pode ser nula!");
                invalideSenha = true;
            } else if (confnovasenha.trim().length() < 6) {
                setMsg("Senha deve conter 6 ou mais caracteres!");
                invalideSenha = true;
            } else if (!confnovasenha.equals(novasenha)) {
                setMsg("Senha nao confirmada!");
                invalideSenha = true;
            } else if (senhaatual == null) {
                setMsg("Senha atual invalida!");
                invalideSenha = true;
            } else if (senhaatual.equals(confnovasenha)) {
                setMsg("Nova senha deve ser diferente da senha atual!");
                invalideSenha = true;
            } 
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(senhaatual));
            
            if (usu_usuarioDAO.getByAutentication(usu_usuarioT).size() == 0) {
                setMsg("Senha atual nao confere!");
                invalideSenha = true;
            }
            if (invalideSenha) {
                return;
            }

            // Criptografando a Senha do usuário com o Algoritmo MD5  
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(novasenha));
            usu_usuarioDAO.updateSenha(usu_usuarioT);
            setMsg("Senha alterada com sucesso!");
            setResult(true);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao alterar senha!");
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

    public String getConfnovasenha() {
        return confnovasenha;
    }

    public void setConfnovasenha(String confnovasenha) {
        this.confnovasenha = confnovasenha;
    }

    public String getSenhaatual() {
        return senhaatual;
    }

    public void setSenhaatual(String senhaatual) {
        this.senhaatual = senhaatual;
    }

    public String getNovasenha() {
        return novasenha;
    }

    public void setNovasenha(String novasenha) {
        this.novasenha = novasenha;
    }

    /**
     * @return the result
     */
    public boolean isResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(boolean result) {
        this.result = result;
    }
}
