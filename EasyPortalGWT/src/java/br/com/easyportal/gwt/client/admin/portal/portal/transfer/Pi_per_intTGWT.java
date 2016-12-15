
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
public class Pi_per_intTGWT extends BaseModel {
  public Pi_per_intTGWT() {
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



}

