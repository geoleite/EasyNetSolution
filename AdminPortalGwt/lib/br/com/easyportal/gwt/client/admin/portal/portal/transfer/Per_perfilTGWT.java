
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
public class Per_perfilTGWT extends BaseModel {
  public Per_perfilTGWT() {
  }

  public int getPer_nr_id() {
    return  ((Integer)get("per_nr_id")).intValue();
//    return get("per_nr_id");
  }

  public void setPer_nr_id(int per_nr_id) {
    set("per_nr_id", per_nr_id);
  }

  public String getPer_tx_nome() {
    return  get("per_tx_nome");
//    return get("per_tx_nome");
  }

  public void setPer_tx_nome(String per_tx_nome) {
    set("per_tx_nome", per_tx_nome);
  }

  public String getPer_tx_status() {
    return  get("per_tx_status");
//    return get("per_tx_status");
  }

  public void setPer_tx_status(String per_tx_status) {
    set("per_tx_status", per_tx_status);
  }

  public String getPer_tx_class() {
    return  get("per_tx_class");
//    return get("per_tx_class");
  }

  public void setPer_tx_class(String per_tx_class) {
    set("per_tx_class", per_tx_class);
  }



}

