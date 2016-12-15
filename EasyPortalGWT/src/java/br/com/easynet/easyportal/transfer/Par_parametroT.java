package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Par_parametroT {

    private int sis_nr_id;
    private String par_tx_nome;
    private String par_tx_valor;
    private long par_nr_id;

    public String toString() {
        return par_tx_nome + "=" + par_tx_valor;
    }
    public void setSis_nr_id(int sis_nr_id) {
        this.sis_nr_id = sis_nr_id;
    }

    public int getSis_nr_id() {
        return sis_nr_id;
    }

    public void setPar_tx_nome(String par_tx_nome) {
        this.par_tx_nome = par_tx_nome;
    }

    public String getPar_tx_nome() {
        return par_tx_nome;
    }

    public void setPar_tx_valor(String par_tx_valor) {
        this.par_tx_valor = par_tx_valor;
    }

    public String getPar_tx_valor() {
        return par_tx_valor;
    }

    /**
     * @return the par_nr_id
     */
    public long getPar_nr_id() {
        return par_nr_id;
    }

    /**
     * @param par_nr_id the par_nr_id to set
     */
    public void setPar_nr_id(long par_nr_id) {
        this.par_nr_id = par_nr_id;
    }
}
