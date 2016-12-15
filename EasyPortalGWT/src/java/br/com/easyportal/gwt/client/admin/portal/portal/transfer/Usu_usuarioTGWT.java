
/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.transfer;
import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.Date;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioTGWT extends BaseModel {
  public Usu_usuarioTGWT() {
  }

  public int getUsu_nr_id() {
    return  ((Integer)get("usu_nr_id")).intValue();
//    return get("usu_nr_id");
  }

  public void setUsu_nr_id(int usu_nr_id) {
    set("usu_nr_id", usu_nr_id);
  }

  public String getUsu_tx_nome() {
    return  get("usu_tx_nome");
//    return get("usu_tx_nome");
  }

  public void setUsu_tx_nome(String usu_tx_nome) {
    set("usu_tx_nome", usu_tx_nome);
  }

  public String getUsu_tx_login() {
    return  get("usu_tx_login");
//    return get("usu_tx_login");
  }

  public void setUsu_tx_login(String usu_tx_login) {
    set("usu_tx_login", usu_tx_login);
  }

  public String getUsu_tx_senha() {
    return  get("usu_tx_senha");
//    return get("usu_tx_senha");
  }

  public void setUsu_tx_senha(String usu_tx_senha) {
    set("usu_tx_senha", usu_tx_senha);
  }

  public String getUsu_tx_status() {
    return  get("usu_tx_status");
//    return get("usu_tx_status");
  }

  public void setUsu_tx_status(String usu_tx_status) {
    set("usu_tx_status", usu_tx_status);
  }

  public String getUsu_tx_email() {
    return  get("usu_tx_email");
//    return get("usu_tx_email");
  }

  public void setUsu_tx_email(String usu_tx_email) {
    set("usu_tx_email", usu_tx_email);
  }

  public String getUsu_tx_trocarsenha() {
    return  get("usu_tx_trocarsenha");
  }

  public void setUsu_tx_trocarsenha(String usu_tx_trocarsenha) {
    set("usu_tx_trocarsenha", usu_tx_trocarsenha);
  }
  
  public String getRes_recall_senha() {
    return  get("res_recall_senha");
  }

  public void setRes_recall_senha(String res_recall_senha) {
    set("res_recall_senha", res_recall_senha);
  }
  


}

