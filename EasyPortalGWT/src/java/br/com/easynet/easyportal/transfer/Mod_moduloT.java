package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Mod_moduloT {

    private int mod_nr_id;
    private String mod_tx_nome;
    private String mod_tx_status;
    private int mod_nr_ordem = 1;
    private String mod_tx_acao="";
    private String mod_tx_icone="";
    private String mod_tx_autologin="S";
    private String mod_tx_urllogin="";

    public void setMod_nr_id(int mod_nr_id) {
        this.mod_nr_id = mod_nr_id;
    }

    public int getMod_nr_id() {
        return mod_nr_id;
    }

    public void setMod_tx_nome(String mod_tx_nome) {
        this.mod_tx_nome = mod_tx_nome;
    }

    public String getMod_tx_nome() {
        return mod_tx_nome;
    }

    public void setMod_tx_status(String mod_tx_status) {
        this.mod_tx_status = mod_tx_status;
    }

    public String getMod_tx_status() {
        return mod_tx_status;
    }

    /**
     * @return the mod_nr_ordem
     */
    public int getMod_nr_ordem() {
        return mod_nr_ordem;
    }

    /**
     * @param mod_nr_ordem the mod_nr_ordem to set
     */
    public void setMod_nr_ordem(int mod_nr_ordem) {
        this.mod_nr_ordem = mod_nr_ordem;
    }

    /**
     * @return the mod_tx_acao
     */
    public String getMod_tx_acao() {
        return mod_tx_acao;
    }

    /**
     * @param mod_tx_acao the mod_tx_acao to set
     */
    public void setMod_tx_acao(String mod_tx_acao) {
        this.mod_tx_acao = mod_tx_acao;
    }

    /**
     * @return the mod_tx_icone
     */
    public String getMod_tx_icone() {
        return mod_tx_icone;
    }

    /**
     * @param mod_tx_icone the mod_tx_icone to set
     */
    public void setMod_tx_icone(String mod_tx_icone) {
        this.mod_tx_icone = mod_tx_icone;
    }

    /**
     * @return the mod_tx_autologin
     */
    public String getMod_tx_autologin() {
        return mod_tx_autologin;
    }

    /**
     * @param mod_tx_autologin the mod_tx_autologin to set
     */
    public void setMod_tx_autologin(String mod_tx_autologin) {
        this.mod_tx_autologin = mod_tx_autologin;
    }

    /**
     * @return the mod_tx_urllogin
     */
    public String getMod_tx_urllogin() {
        return mod_tx_urllogin;
    }

    /**
     * @param mod_tx_urllogin the mod_tx_urllogin to set
     */
    public void setMod_tx_urllogin(String mod_tx_urllogin) {
        this.mod_tx_urllogin = mod_tx_urllogin;
    }
}
