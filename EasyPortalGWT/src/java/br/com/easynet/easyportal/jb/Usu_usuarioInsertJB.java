package br.com.easynet.easyportal.jb;

import br.com.easynet.criptografia.MD5;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Usu_usuarioInsertJB extends EasySecurityJB {

    // Atributos e propriedades
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private String confsenha;

    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }

    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    // Metodos de Eventos
    public void insert() throws Exception {

        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            boolean invalideSenha = false;
            // Valida se a senha é diferente de null, no mínimo com 6 caracteres e igual a senha
            if (confsenha == null) {
                setMsg("Senha não pode ser nula!");
                invalideSenha = true;
            } else if (confsenha.trim().length() < 6) {
                setMsg("Senha deve conter 6 ou mais caracteres!");
                invalideSenha = true;
            } else if (!confsenha.equals(usu_usuarioT.getUsu_tx_senha())) {
                setMsg("Senha não confirmada!");
                invalideSenha = true;
            }
            if (invalideSenha) {
                return;
            }

            // Criptografando a Senha do usuário com o Algoritmo MD5
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(usu_usuarioT.getUsu_tx_senha()));
            usu_usuarioT.setUsu_nr_id(getIncUsuario());
            usu_usuarioDAO.insert(usu_usuarioT);
            setMsg("Cadastro efetuado com sucesso!");
            clear();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar cadastro!");
        } finally {
            close();
        }
    }

    public void clear() throws Exception {

        usu_usuarioT = new Usu_usuarioT();
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "usu_usuarioConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public String getConfsenha() {
        return confsenha;
    }

    public void setConfsenha(String confsenha) {
        this.confsenha = confsenha;
    }
}
