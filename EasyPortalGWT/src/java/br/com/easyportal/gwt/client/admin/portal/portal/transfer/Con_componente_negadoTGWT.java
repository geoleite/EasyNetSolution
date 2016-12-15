
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
public class Con_componente_negadoTGWT extends BaseModel {
  public Con_componente_negadoTGWT() {
  }

  public int getCon_nr_id() {
    return  ((Integer)get("con_nr_id")).intValue();
//    return get("con_nr_id");
  }

  public void setCon_nr_id(int con_nr_id) {
    set("con_nr_id", con_nr_id);
  }

  public int getInt_nr_id() {
    return  ((Integer)get("int_nr_id")).intValue();
//    return get("int_nr_id");
  }

  public void setInt_nr_id(int int_nr_id) {
    set("int_nr_id", int_nr_id);
  }

  public int getPer_nr_id() {
    return  ((Integer)get("per_nr_id")).intValue();
//    return get("per_nr_id");
  }

  public void setPer_nr_id(int per_nr_id) {
    set("per_nr_id", per_nr_id);
  }

  public String getCon_tx_visivel() {
    return  get("con_tx_visivel");
//    return get("con_tx_visivel");
  }

  public void setCon_tx_visivel(String con_tx_visivel) {
    set("con_tx_visivel", con_tx_visivel);
  }

  public String getCon_tx_ativo() {
    return  get("con_tx_ativo");
//    return get("con_tx_ativo");
  }

  public void setCon_tx_ativo(String con_tx_ativo) {
    set("con_tx_ativo", con_tx_ativo);
  }

  public String getCon_tx_nome() {
    return  get("con_tx_nome");
//    return get("con_tx_ativo");
  }

  public void setCon_tx_nome(String con_tx_nome) {
    set("con_tx_nome", con_tx_nome);
  }


}

