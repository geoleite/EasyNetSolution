
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
public class Int_interfaceTGWT extends BaseModel {
  public Int_interfaceTGWT() {
  }

  public int getInt_nr_id() {
    return  ((Integer)get("int_nr_id")).intValue();
//    return get("int_nr_id");
  }

  public void setInt_nr_id(int int_nr_id) {
    set("int_nr_id", int_nr_id);
  }

  public String getInt_tx_nome() {
    return  get("int_tx_nome");
//    return get("int_tx_nome");
  }

  public void setInt_tx_nome(String int_tx_nome) {
    set("int_tx_nome", int_tx_nome);
  }



}

