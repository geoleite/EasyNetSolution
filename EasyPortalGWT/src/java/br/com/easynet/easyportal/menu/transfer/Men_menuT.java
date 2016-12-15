package br.com.easynet.easyportal.menu.transfer;

import br.com.easynet.annotation.Conversion;
import java.util.List;

public class Men_menuT {

    private int men_nr_id;
    private int supermenu_nr_id;
    private String men_tx_status = "A";
    private String men_tx_nome;
    private int men_nr_ordem = 1;
    private String men_tx_url = "";
    private String men_tx_acao = " ";
    private String men_tx_icone = "icone";
    private int sis_nr_id = 1;
    private String men_tx_tipo = "M";//indicando o tipo M=Menu ou A=Aba
    private String sistema="";
    private List<Men_menuT> subMenu;

    public void setMen_nr_id(int men_nr_id) {
        this.men_nr_id = men_nr_id;
    }

    public int getMen_nr_id() {
        return men_nr_id;
    }

    public void setSupermenu_nr_id(int supermenu_nr_id) {
        this.supermenu_nr_id = supermenu_nr_id;
    }

    public int getSupermenu_nr_id() {
        return supermenu_nr_id;
    }

    public void setMen_tx_status(String men_tx_status) {
        this.men_tx_status = men_tx_status;
    }

    public String getMen_tx_status() {
        return men_tx_status;
    }

    public void setMen_tx_nome(String men_tx_nome) {
        this.men_tx_nome = men_tx_nome;
    }

    public String getMen_tx_nome() {
        return men_tx_nome;
    }

    public void setMen_nr_ordem(int men_nr_ordem) {
        this.men_nr_ordem = men_nr_ordem;
    }

    public int getMen_nr_ordem() {
        return men_nr_ordem;
    }

    public String getMen_tx_url() {
        return men_tx_url;
    }

    public void setMen_tx_url(String men_tx_url) {
        this.men_tx_url = men_tx_url;
    }

    public int getSis_nr_id() {
        return sis_nr_id;
    }

    public void setSis_nr_id(int sis_nr_id) {
        this.sis_nr_id = sis_nr_id;
    }

    /**
     * @return the men_tx_acao
     */
    public String getMen_tx_acao() {
        return men_tx_acao;
    }

    /**
     * @param men_tx_acao the men_tx_acao to set
     */
    public void setMen_tx_acao(String men_tx_acao) {
        this.men_tx_acao = men_tx_acao;
    }

    /**
     * @return the men_tx_icone
     */
    public String getMen_tx_icone() {
        return men_tx_icone;
    }

    /**
     * @param men_tx_icone the men_tx_icone to set
     */
    public void setMen_tx_icone(String men_tx_icone) {
        this.men_tx_icone = men_tx_icone;
    }

    /**
     * @return the men_tx_tipo
     */
    public String getMen_tx_tipo() {
        return men_tx_tipo;
    }

    /**
     * @param men_tx_tipo the men_tx_tipo to set
     */
    public void setMen_tx_tipo(String men_tx_tipo) {
        this.men_tx_tipo = men_tx_tipo;
    }

    /**
     * @return the subMenu
     */
    public List<Men_menuT> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu the subMenu to set
     */
    public void setSubMenu(List<Men_menuT> subMenu) {
        this.subMenu = subMenu;
    }

    /**
     * @return the sistema
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
}
