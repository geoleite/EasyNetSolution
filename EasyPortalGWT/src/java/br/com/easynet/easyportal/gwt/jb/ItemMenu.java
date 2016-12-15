/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.gwt.jb;

import java.util.List;

/**
 *
 * @author geoleite
 */
public class ItemMenu {
    private String nome;
    private String acao;
    private String icone;
    private String sistema;
    private String pagina;
    private List<ItemMenu> subMenu;

    public ItemMenu() {

    }

    public ItemMenu(String nome, String acao,String icone, String sistema, String pagina) {
        setNome(nome);
        setAcao(acao);
        setSistema(sistema);
        setPagina(pagina);
        setIcone(icone);
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the subMenu
     */
    public List<ItemMenu> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu the subMenu to set
     */
    public void setSubMenu(List<ItemMenu> subMenu) {
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

    /**
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    /**
     * @return the icone
     */
    public String getIcone() {
        return icone;
    }

    /**
     * @param icone the icone to set
     */
    public void setIcone(String icone) {
        this.icone = icone;
    }
}
