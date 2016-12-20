
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
public class Mod_moduloTGWT extends BaseModel {
  public Mod_moduloTGWT() {
  }

  public int getMod_nr_id() {
    return   get("mod_nr_id")==null?0: ((Integer)get("mod_nr_id")).intValue();
  }

  public void setMod_nr_id(int mod_nr_id) {
    set("mod_nr_id", mod_nr_id);
  }

  public String getMod_tx_nome() {
    return   get("mod_tx_nome")==null?"": (String)get("mod_tx_nome");
  }

  public void setMod_tx_nome(String mod_tx_nome) {
    set("mod_tx_nome", mod_tx_nome);
  }

  public String getMod_tx_status() {
    return   get("mod_tx_status")==null?"": (String)get("mod_tx_status");
  }

  public void setMod_tx_status(String mod_tx_status) {
    set("mod_tx_status", mod_tx_status);
  }

  public int getMod_nr_ordem() {
    return   get("mod_nr_ordem")==null?0: ((Integer)get("mod_nr_ordem")).intValue();
  }

  public void setMod_nr_ordem(int mod_nr_ordem) {
    set("mod_nr_ordem", mod_nr_ordem);
  }

  public String getMod_tx_acao() {
    return   get("mod_tx_acao")==null?"": (String)get("mod_tx_acao");
  }

  public void setMod_tx_acao(String mod_tx_acao) {
    set("mod_tx_acao", mod_tx_acao);
  }

  public String getMod_tx_icone() {
    return   get("mod_tx_icone")==null?"": (String)get("mod_tx_icone");
  }

  public void setMod_tx_icone(String mod_tx_icone) {
    set("mod_tx_icone", mod_tx_icone);
  }
  public String getMod_tx_autologin() {
    return   get("mod_tx_autologin")==null?"": (String)get("mod_tx_autologin");
  }

  public void setMod_tx_autologin(String mod_tx_autologin) {
    set("mod_tx_autologin", mod_tx_autologin);
  }

  public String getMod_tx_urllogin() {
    return   get("mod_tx_urllogin")==null?"": (String)get("mod_tx_urllogin");
  }

  public void setMod_tx_urllogin(String mod_tx_urllogin) {
    set("mod_tx_urllogin", mod_tx_urllogin);
  }

}

