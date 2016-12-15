
/*
 * EasyNet JDragon
 */

package br.com.i9.portal.client.portal.portal.transfer;

import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.Date;

/**
 *
 * @author geoleite
 */
public class LogTGWT extends BaseModel {
  public LogTGWT() {
  }

  public long getLog_nr_id() {
    return  ((Long)get("log_nr_id")).longValue();
  }

  public void setLog_nr_id(long log_nr_id) {
    set("log_nr_id", log_nr_id);
  }

  public String getLog_tx_sistema() {
    return  get("log_tx_sistema");
  }

  public void setLog_tx_sistema(String log_tx_sistema) {
    set("log_tx_sistema", log_tx_sistema);
  }

  public String getLog_tx_classe() {
    return  get("log_tx_classe");
  }

  public void setLog_tx_classe(String log_tx_classe) {
    set("log_tx_classe", log_tx_classe);
  }

  public String getLog_tx_metodo() {
    return  get("log_tx_metodo");
  }

  public void setLog_tx_metodo(String log_tx_metodo) {
    set("log_tx_metodo", log_tx_metodo);
  }

  public String getLog_tx_usuario() {
    return  get("log_tx_usuario");
  }

  public void setLog_tx_usuario(String log_tx_usuario) {
    set("log_tx_usuario", log_tx_usuario);
  }

  public Date getLog_dt_datahora() {
    return  (Date)get("log_dt_datahora");
  }

  public void setLog_dt_datahora(Date log_dt_datahora) {
    set("log_dt_datahora", log_dt_datahora);
  }

  public String getLog_tx_status() {
    return  get("log_tx_status");
  }

  public void setLog_tx_status(String log_tx_status) {
    set("log_tx_status", log_tx_status);
  }

  public String getLog_tx_ip() {
    return  get("log_tx_ip");
  }

  public void setLog_tx_ip(String log_tx_ip) {
    set("log_tx_ip", log_tx_ip);
  }

  public String getLog_tx_parametros() {
    return  get("log_tx_parametros");
  }

  public void setLog_tx_parametros(String log_tx_parametros) {
    set("log_tx_parametros", log_tx_parametros);
  }



}

