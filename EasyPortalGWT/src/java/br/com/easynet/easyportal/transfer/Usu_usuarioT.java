package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;
import java.util.Date;

public class Usu_usuarioT {

    private int usu_nr_id;
    private String usu_tx_nome;
    private String usu_tx_login;
    private String usu_tx_senha;
    private String usu_tx_status;
    private String usu_tx_email = "";
    private String usu_tx_trocarsenha = "N";
    private Date usu_dt_cadastro;

    public void setUsu_nr_id(int usu_nr_id) {
        this.usu_nr_id = usu_nr_id;
    }

    public int getUsu_nr_id() {
        return usu_nr_id;
    }

    public void setUsu_tx_nome(String usu_tx_nome) {
        this.usu_tx_nome = usu_tx_nome;
    }

    public String getUsu_tx_nome() {
        return usu_tx_nome;
    }

    public void setUsu_tx_login(String usu_tx_login) {
        this.usu_tx_login = usu_tx_login;
    }

    public String getUsu_tx_login() {
        return usu_tx_login;
    }

    public void setUsu_tx_senha(String usu_tx_senha) {
        this.usu_tx_senha = usu_tx_senha;
    }

    public String getUsu_tx_senha() {
        return usu_tx_senha;
    }

    public void setUsu_tx_status(String usu_tx_status) {
        this.usu_tx_status = usu_tx_status;
    }

    public String getUsu_tx_status() {
        return usu_tx_status;
    }

    public String getUsu_tx_email() {
        return usu_tx_email;
    }

    public void setUsu_tx_email(String usu_tx_email) {
        this.usu_tx_email = usu_tx_email;
    }

    /**
     * @return the usu_tx_trocarsenha
     */
    public String getUsu_tx_trocarsenha() {
        return usu_tx_trocarsenha;
    }

    /**
     * @param usu_tx_trocarsenha the usu_tx_trocarsenha to set
     */
    public void setUsu_tx_trocarsenha(String usu_tx_trocarsenha) {
        this.usu_tx_trocarsenha = usu_tx_trocarsenha;
    }

    public String toString() {
        return usu_tx_login;
    }

    /**
     * @return the usu_dt_cadastro
     */
    public Date getUsu_dt_cadastro() {
        return usu_dt_cadastro;
    }

    /**
     * @param usu_dt_cadastro the usu_dt_cadastro to set
     */
    public void setUsu_dt_cadastro(Date usu_dt_cadastro) {
        this.usu_dt_cadastro = usu_dt_cadastro;
    }

}
