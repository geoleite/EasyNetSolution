
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
public class Res_recall_senhaTGWT extends BaseModel {
  public Res_recall_senhaTGWT() {
  }

  public int getUsu_nr_id() {
    return   get("usu_nr_id")==null?0: ((Integer)get("usu_nr_id")).intValue();
  }

  public void setUsu_nr_id(int usu_nr_id) {
    set("usu_nr_id", usu_nr_id);
  }

  public String getRes_tx_pergunta() {
    return   get("res_tx_pergunta")==null?"": (String)get("res_tx_pergunta");
  }

  public void setRes_tx_pergunta(String res_tx_pergunta) {
    set("res_tx_pergunta", res_tx_pergunta);
  }

  public String getRes_tx_resposta() {
    return   get("res_tx_resposta")==null?"": (String)get("res_tx_resposta");
  }

  public void setRes_tx_resposta(String res_tx_resposta) {
    set("res_tx_resposta", res_tx_resposta);
  }

  public Date getRes_dt_ultimoacesso() {
    return  (Date)get("res_dt_ultimoacesso");
  }

  public void setRes_dt_ultimoacesso(Date res_dt_ultimoacesso) {
    set("res_dt_ultimoacesso", res_dt_ultimoacesso);
  }

  public int getRes_nr_tentativas() {
    return   get("res_nr_tentativas")==null?0: ((Integer)get("res_nr_tentativas")).intValue();
  }

  public void setRes_nr_tentativas(int res_nr_tentativas) {
    set("res_nr_tentativas", res_nr_tentativas);
  }



}

