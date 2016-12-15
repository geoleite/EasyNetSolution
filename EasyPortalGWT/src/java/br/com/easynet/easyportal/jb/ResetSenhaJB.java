/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.dao.Usu_usuarioDAO;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class ResetSenhaJB extends EasySecurityJB {

    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private String confsenha;
    

    public void resetar() throws Exception {
        try {
            boolean invalideSenha = false;
            if (confsenha == null || confsenha.trim().length() < 6) {
                setMsg("A senha deve conter 6 ou mais caracteres!");
                invalideSenha = true;
            } else if (!confsenha.equals(usu_usuarioT.getUsu_tx_senha())) {
                setMsg("Senha não confirmada!");
                invalideSenha = true;
            }
            if (invalideSenha) {
                return;
            }
            IUsu_usuarioDAO usuDao = getUsu_usuarioDAO();
            getUsu_usuarioT().setUsu_tx_senha(MD5.criptografar(confsenha));
            usuDao.updateSenha(getUsu_usuarioT());
            findbyid();
            setMsg("Senha alterada com sucesso!");
        } catch (Exception exception) {
            setMsg("Falha ao resetar senha!");
        } finally {

        }
    }

    public void findbyid() throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            List<Usu_usuarioT> listTemp = usu_usuarioDAO.getById(getUsu_usuarioT());

            setUsu_usuarioT(listTemp.size() > 0 ? listTemp.get(0) : new Usu_usuarioT());

        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
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

    public String getConfsenha() {
        return confsenha;
    }

    public void setConfsenha(String confsenha) {
        this.confsenha = confsenha;
    }
}
