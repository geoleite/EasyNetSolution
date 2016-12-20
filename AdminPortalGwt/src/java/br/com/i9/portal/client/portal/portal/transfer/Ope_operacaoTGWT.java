
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
public class Ope_operacaoTGWT extends BaseModel {
  public Ope_operacaoTGWT() {
  }

  public int getOpe_nr_id() {
    return  ((Integer)get("ope_nr_id")).intValue();
  }

  public void setOpe_nr_id(int ope_nr_id) {
    set("ope_nr_id", ope_nr_id);
  }

  public int getSis_nr_id() {
    return  ((Integer)get("sis_nr_id")).intValue();
  }

  public void setSis_nr_id(int sis_nr_id) {
    set("sis_nr_id", sis_nr_id);
  }

  public String getOpe_tx_nome() {
    return  get("ope_tx_nome");
  }

  public void setOpe_tx_nome(String ope_tx_nome) {
    set("ope_tx_nome", ope_tx_nome);
  }

  public String getOpe_tx_status() {
    return  get("ope_tx_status");
  }

  public void setOpe_tx_status(String ope_tx_status) {
    set("ope_tx_status", ope_tx_status);
  }

  public String getOpe_tx_url() {
    return  get("ope_tx_url");
  }

  public void setOpe_tx_url(String ope_tx_url) {
    set("ope_tx_url", ope_tx_url);
  }

  public String getOpe_tx_descricao() {
    return  get("ope_tx_descricao");
  }

  public void setOpe_tx_descricao(String ope_tx_descricao) {
    set("ope_tx_descricao", ope_tx_descricao);
  }

  public String getOpe_tx_classe() {
    return  get("ope_tx_classe");
  }

  public void setOpe_tx_classe(String ope_tx_classe) {
    set("ope_tx_classe", ope_tx_classe);
  }



}

