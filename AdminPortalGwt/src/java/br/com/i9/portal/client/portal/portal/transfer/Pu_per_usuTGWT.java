
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
public class Pu_per_usuTGWT extends BaseModel {
  public Pu_per_usuTGWT() {
  }

  public int getPer_nr_id() {
    return  ((Integer)get("per_nr_id")).intValue();
  }

  public void setPer_nr_id(int per_nr_id) {
    set("per_nr_id", per_nr_id);
  }

  public int getUsu_nr_id() {
    return  ((Integer)get("usu_nr_id")).intValue();
  }

  public void setUsu_nr_id(int usu_nr_id) {
    set("usu_nr_id", usu_nr_id);
  }



}

