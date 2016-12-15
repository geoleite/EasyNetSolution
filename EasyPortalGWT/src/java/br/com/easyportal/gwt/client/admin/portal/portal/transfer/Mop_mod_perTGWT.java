
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
public class Mop_mod_perTGWT extends BaseModel {
  public Mop_mod_perTGWT() {
  }

  public int getMod_nr_id() {
    return   get("mod_nr_id")==null?0: ((Integer)get("mod_nr_id")).intValue();
  }

  public void setMod_nr_id(int mod_nr_id) {
    set("mod_nr_id", mod_nr_id);
  }

  public int getPer_nr_id() {
    return   get("per_nr_id")==null?0: ((Integer)get("per_nr_id")).intValue();
  }

  public void setPer_nr_id(int per_nr_id) {
    set("per_nr_id", per_nr_id);
  }



}

