
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
public class Par_parametroTGWT extends BaseModel {
  public Par_parametroTGWT() {
  }

  public int getSis_nr_id() {
    return  ((Integer)get("sis_nr_id")).intValue();
  }

  public void setSis_nr_id(int sis_nr_id) {
    set("sis_nr_id", sis_nr_id);
  }

  public String getPar_tx_nome() {
    return  get("par_tx_nome");
  }

  public void setPar_tx_nome(String par_tx_nome) {
    set("par_tx_nome", par_tx_nome);
  }

  public String getPar_tx_valor() {
    return  get("par_tx_valor");
  }

  public void setPar_tx_valor(String par_tx_valor) {
    set("par_tx_valor", par_tx_valor);
  }



}

