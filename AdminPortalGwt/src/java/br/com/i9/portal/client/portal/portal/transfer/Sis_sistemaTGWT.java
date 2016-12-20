
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
public class Sis_sistemaTGWT extends BaseModel {
  public Sis_sistemaTGWT() {
  }

  public int getSis_nr_id() {
    return  ((Integer)get("sis_nr_id")).intValue();
  }

  public void setSis_nr_id(int sis_nr_id) {
    set("sis_nr_id", sis_nr_id);
  }

  public String getSis_tx_nome() {
    return  get("sis_tx_nome");
  }

  public void setSis_tx_nome(String sis_tx_nome) {
    set("sis_tx_nome", sis_tx_nome);
  }

  public String getSis_tx_descricao() {
    return  get("sis_tx_descricao");
  }

  public void setSis_tx_descricao(String sis_tx_descricao) {
    set("sis_tx_descricao", sis_tx_descricao);
  }

  public String getSis_tx_status() {
    return  get("sis_tx_status");
  }

  public void setSis_tx_status(String sis_tx_status) {
    set("sis_tx_status", sis_tx_status);
  }



}

