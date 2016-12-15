package br.com.easynet.easyportal.transfer;

import br.com.easynet.annotation.Conversion;

public class Per_perfilT {

    private int per_nr_id;
    private String per_tx_nome;
    private String per_tx_status;
    private String per_tx_class = "";

    public void setPer_nr_id(int per_nr_id) {
        this.per_nr_id = per_nr_id;
    }

    public int getPer_nr_id() {
        return per_nr_id;
    }

    public void setPer_tx_nome(String per_tx_nome) {
        this.per_tx_nome = per_tx_nome;
    }

    public String getPer_tx_nome() {
        return per_tx_nome;
    }

    public void setPer_tx_status(String per_tx_status) {
        this.per_tx_status = per_tx_status;
    }

    public String getPer_tx_status() {
        return per_tx_status;
    }

    public String getPer_tx_class() {
        return per_tx_class;
    }

    public void setPer_tx_class(String per_tx_class) {
        this.per_tx_class = per_tx_class;
    }
}
