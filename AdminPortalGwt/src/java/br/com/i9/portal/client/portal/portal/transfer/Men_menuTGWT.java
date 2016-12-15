
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
public class Men_menuTGWT extends BaseModel {
  public Men_menuTGWT() {
  }

  public int getMen_nr_id() {
    return  ((Integer)get("men_nr_id")).intValue();
  }

  public void setMen_nr_id(int men_nr_id) {
    set("men_nr_id", men_nr_id);
  }

  public int getSupermenu_nr_id() {
    return  ((Integer)get("supermenu_nr_id")).intValue();
  }

  public void setSupermenu_nr_id(int supermenu_nr_id) {
    set("supermenu_nr_id", supermenu_nr_id);
  }

  public String getMen_tx_status() {
    return  get("men_tx_status");
  }

  public void setMen_tx_status(String men_tx_status) {
    set("men_tx_status", men_tx_status);
  }

  public String getMen_tx_nome() {
    return  get("men_tx_nome");
  }

  public void setMen_tx_nome(String men_tx_nome) {
    set("men_tx_nome", men_tx_nome);
  }

  public int getMen_nr_ordem() {
    return  ((Integer)get("men_nr_ordem")).intValue();
  }

  public void setMen_nr_ordem(int men_nr_ordem) {
    set("men_nr_ordem", men_nr_ordem);
  }

  public String getMen_tx_url() {
    return  get("men_tx_url");
  }

  public void setMen_tx_url(String men_tx_url) {
    set("men_tx_url", men_tx_url);
  }

  public String getMen_tx_acao() {
    return  get("men_tx_acao");
  }

  public void setMen_tx_acao(String men_tx_acao) {
    set("men_tx_acao", men_tx_acao);
  }

  public int getSis_nr_id() {
    return  ((Integer)get("sis_nr_id")).intValue();
  }

  public void setSis_nr_id(int sis_nr_id) {
    set("sis_nr_id", sis_nr_id);
  }

  public String getMen_tx_icone() {
    return  get("men_tx_icone");
  }

  public void setMen_tx_icone(String men_tx_icone) {
    set("men_tx_icone", men_tx_icone);
  }

  public String getMen_tx_super() {
    return  get("men_tx_super");
  }

  public void setMen_tx_super(String men_tx_super) {
    set("men_tx_super", men_tx_super);
  }
//getMen_tx_tipo
    public String getMen_tx_tipo() {
    return  get("men_tx_tipo");
  }

  public void setMen_tx_tipo(String men_tx_tipo) {
    set("men_tx_tipo", men_tx_tipo);
  }
}

