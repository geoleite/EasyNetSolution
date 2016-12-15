/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.jb;

import br.com.easynet.easyportal.transfer.Met_metodoT;
import br.com.easynet.easyportal.transfer.Op_ope_perT;
import br.com.easynet.easyportal.transfer.Ope_operacaoT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class OperacoesPerfil2JB extends EasySecurityJB {
    
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();
    private Per_perfilT per_perfilT = new Per_perfilT();
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private Met_metodoT met_metodoT = new Met_metodoT();
    
    private boolean existOperacoes = false;
    private boolean existMetodos = false;
            
    private List<Sis_sistemaT> listSistema;
    private List<Ope_operacaoT> listOperacao;
    private List<Met_metodoT> listMetodoAdicionados;
    private List<Met_metodoT> listMetodoNaoAdicionados;
    
    private int[] metodosAdd;
    private int[] metodosRem;
    
    public void pageLoad() throws Exception {
        consultSistemas(); // consulta todos os sistemas ativos do portal
        consultOperacoes();
        consultMetodos();
        findbyid();
    }
    
    /**
     * REaliza a consulta de todos os sistemas
     * @throws java.lang.Exception
     */
    public void consultSistemas() throws Exception {
        try {
            listSistema = getSis_sistemaDAO().getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    
    public void consultMetodos() throws Exception {
        try {
            
            met_metodoT.setSis_nr_id(sis_sistemaT.getSis_nr_id());
            met_metodoT.setOpe_nr_id(ope_operacaoT.getOpe_nr_id());
            listMetodoNaoAdicionados = getMet_metodoDAO().getByMetodosNaoPerfil(per_perfilT, met_metodoT);            
            listMetodoAdicionados = getMet_metodoDAO().getByMetodosPerfil(per_perfilT, met_metodoT);
            setExistMetodos(listMetodoAdicionados.size() > 0);
            if (!isExistMetodos())
                setExistMetodos(listMetodoNaoAdicionados.size() > 0);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    
    /**
     * Consultas todas operações de um sistema selecionado
     * @throws java.lang.Exception
     */
    public void consultOperacoes() throws Exception {
        try {
            ope_operacaoT.setSis_nr_id(sis_sistemaT.getSis_nr_id());
            listOperacao = getOpe_operacaoDAO().getBySis_sistema(ope_operacaoT);
            existOperacoes = listOperacao.size() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    
    /**
     * Adicionar o método ao perfil
     * @throws java.lang.Exception
     */
    public void adicionar() throws Exception {
        try {
           Op_ope_perT op = new Op_ope_perT();
           op.setSis_nr_id(sis_sistemaT.getSis_nr_id());
           op.setOpe_nr_id(ope_operacaoT.getOpe_nr_id());
           op.setPer_nr_id(per_perfilT.getPer_nr_id());
            for (int i = 0; i < metodosRem.length; i++) {                
                op.setMet_nr_id(metodosRem[i]);           
                getOp_ope_perDAO().insert(op);
            }
           // Atualizar as listas dos métodos
           consultMetodos();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    /**
     * Remover o método ao perfil
     * @throws java.lang.Exception
     */
    public void remover() throws Exception {
        try {
           Op_ope_perT op = new Op_ope_perT();
           op.setSis_nr_id(sis_sistemaT.getSis_nr_id());
           op.setOpe_nr_id(ope_operacaoT.getOpe_nr_id());
           op.setPer_nr_id(per_perfilT.getPer_nr_id());
            for (int i = 0; i < metodosAdd.length; i++) {                
                op.setMet_nr_id(metodosAdd[i]);           
                getOp_ope_perDAO().delete(op);
            }
           // Atualizar as listas dos métodos
           consultMetodos();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }
    
    /**
     * Pesquisa os dados do perfil em questão
     * @throws java.lang.Exception
     */
    public void findbyid() throws Exception {
        try {
            
            List<Per_perfilT> listTemp = getPer_perfilDAO().getById(per_perfilT);

            per_perfilT = listTemp.size() > 0 ? listTemp.get(0) : new Per_perfilT();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }
    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }

    public Per_perfilT getPer_perfilT() {
        return per_perfilT;
    }

    public void setPer_perfilT(Per_perfilT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }

    public Ope_operacaoT getOpe_operacaoT() {
        return ope_operacaoT;
    }

    public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
    }

    public Met_metodoT getMet_metodoT() {
        return met_metodoT;
    }

    public void setMet_metodoT(Met_metodoT met_metodoT) {
        this.met_metodoT = met_metodoT;
    }

    public List<Sis_sistemaT> getListSistema() {
        return listSistema;
    }

    public void setListSistema(List<Sis_sistemaT> listSistema) {
        this.listSistema = listSistema;
    }

    public List<Ope_operacaoT> getListOperacao() {
        return listOperacao;
    }

    public void setListOperacao(List<Ope_operacaoT> listOperacao) {
        this.listOperacao = listOperacao;
    }

    public List<Met_metodoT> getListMetodoNaoAdicionados() {
        return listMetodoNaoAdicionados;
    }

    public void setListMetodoNaoAdicionados(List<Met_metodoT> listMetodoNaoAdicionados) {
        this.listMetodoNaoAdicionados = listMetodoNaoAdicionados;
    }

    public List<Met_metodoT> getListMetodoAdicionados() {
        return listMetodoAdicionados;
    }

    public void setListMetodoAdicionados(List<Met_metodoT> listMetodoAdicionados) {
        this.listMetodoAdicionados = listMetodoAdicionados;
    }

    public boolean isExistOperacoes() {
        return existOperacoes;
    }

    public void setExistOperacoes(boolean existOperacoes) {
        this.existOperacoes = existOperacoes;
    }

    public boolean isExistMetodos() {
        return existMetodos;
    }

    public void setExistMetodos(boolean existMetodos) {
        this.existMetodos = existMetodos;
    }

    public int[] getMetodosAdd() {
        return metodosAdd;
    }

    public void setMetodosAdd(int[] metodosAdd) {
        this.metodosAdd = metodosAdd;
    }

    public int[] getMetodosRem() {
        return metodosRem;
    }

    public void setMetodosRem(int[] metodosRem) {
        this.metodosRem = metodosRem;
    }
    

}
