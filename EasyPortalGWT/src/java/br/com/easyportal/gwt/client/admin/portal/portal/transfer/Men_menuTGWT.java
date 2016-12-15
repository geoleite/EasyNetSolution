
/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.transfer;

import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Men_menuTGWT extends BaseModel {
    private List<Men_menuTGWT> subMenu;
    public Men_menuTGWT() {
    }

    public int getMen_nr_id() {
        return ((Integer) get("men_nr_id")).intValue();
//    return get("men_nr_id");
    }

    public void setMen_nr_id(int men_nr_id) {
        set("men_nr_id", men_nr_id);
    }

    public int getSupermenu_nr_id() {
        return ((Integer) get("supermenu_nr_id")).intValue();
//    return get("supermenu_nr_id");
    }

    public void setSupermenu_nr_id(int supermenu_nr_id) {
        set("supermenu_nr_id", supermenu_nr_id);
    }

    public String getMen_tx_status() {
        return get("men_tx_status");
//    return get("men_tx_status");
    }

    public void setMen_tx_status(String men_tx_status) {
        set("men_tx_status", men_tx_status);
    }

    public String getMen_tx_nome() {
        return get("men_tx_nome");
//    return get("men_tx_nome");
    }

    public void setMen_tx_nome(String men_tx_nome) {
        set("men_tx_nome", men_tx_nome);
    }

    public int getMen_nr_ordem() {
        return ((Integer) get("men_nr_ordem")).intValue();
//    return get("men_nr_ordem");
    }

    public void setMen_nr_ordem(int men_nr_ordem) {
        set("men_nr_ordem", men_nr_ordem);
    }

    public String getMen_tx_url() {
        return get("men_tx_url");
//    return get("men_tx_url");
    }

    public void setMen_tx_url(String men_tx_url) {
        set("men_tx_url", men_tx_url);
    }

    public int getSis_nr_id() {
        return ((Integer) get("sis_nr_id")).intValue();
//    return get("sis_nr_id");
    }

    public void setSis_nr_id(int sis_nr_id) {
        set("sis_nr_id", sis_nr_id);
    }

    public String getMen_tx_acao() {
        return get("men_tx_acao");
//    return get("men_tx_acao");
    }

    public void setMen_tx_acao(String men_tx_acao) {
        set("men_tx_acao", men_tx_acao);
    }

    public String getMen_tx_icone() {
        return get("men_tx_icone");
//    return get("men_tx_icone");
    }

    public void setMen_tx_icone(String men_tx_icone) {
        set("men_tx_icone", men_tx_icone);
    }

    public String getSistema() {
        return get("sistema");
//    return get("men_tx_icone");
    }

    public void setSistema(String sistema) {
        set("sistema", sistema);
    }

    /**
     * @return the subMenu
     */
    public List<Men_menuTGWT> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu the subMenu to set
     */
    public void setSubMenu(List<Men_menuTGWT> subMenu) {
        this.subMenu = subMenu;
    }
}
