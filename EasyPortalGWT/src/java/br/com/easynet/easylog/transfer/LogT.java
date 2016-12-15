package br.com.easynet.easylog.transfer;

import br.com.easynet.annotation.Conversion;

public class LogT {

    private long log_nr_id;
    private String log_tx_sistema;
    private String log_tx_classe;
    private String log_tx_metodo="";
    @Conversion(classe = "br.com.easynet.convesion.ConvertTime", format = "HH:mm")
    private java.sql.Timestamp log_dt_datahora;
    private String log_tx_usuario;
    private String log_tx_ip;
    private String log_tx_status = "S";
    private String log_tx_parametro = "";

    public void setLog_nr_id(long log_nr_id) {
        this.log_nr_id = log_nr_id;
    }

    public long getLog_nr_id() {
        return log_nr_id;
    }

    public void setLog_tx_sistema(String log_tx_sistema) {
        this.log_tx_sistema = log_tx_sistema;
    }

    public String getLog_tx_sistema() {
        return log_tx_sistema;
    }

    public void setLog_tx_classe(String log_tx_classe) {
        this.log_tx_classe = log_tx_classe;
    }

    public String getLog_tx_classe() {
        return log_tx_classe;
    }

    public void setLog_tx_metodo(String log_tx_metodo) {
        this.log_tx_metodo = log_tx_metodo;
    }

    public String getLog_tx_metodo() {
        return log_tx_metodo;
    }

    public void setLog_dt_datahora(java.sql.Timestamp log_dt_datahora) {
        this.log_dt_datahora = log_dt_datahora;
    }

    public java.sql.Timestamp getLog_dt_datahora() {
        return log_dt_datahora;
    }

    public void setLog_tx_usuario(String log_tx_usuario) {
        this.log_tx_usuario = log_tx_usuario;
    }

    public String getLog_tx_usuario() {
        return log_tx_usuario;
    }

    public String getLog_tx_status() {
        return log_tx_status;
    }

    public void setLog_tx_status(String log_tx_status) {
        this.log_tx_status = log_tx_status;
    }

    public String getLog_tx_ip() {
        return log_tx_ip;
    }

    public void setLog_tx_ip(String log_tx_ip) {
        this.log_tx_ip = log_tx_ip;
    }

    /**
     * @return the log_tx_parametro
     */
    public String getLog_tx_parametro() {
        return log_tx_parametro;
    }

    /**
     * @param log_tx_parametro the log_tx_parametro to set
     */
    public void setLog_tx_parametro(String log_tx_parametro) {
        this.log_tx_parametro = log_tx_parametro;
    }
}
