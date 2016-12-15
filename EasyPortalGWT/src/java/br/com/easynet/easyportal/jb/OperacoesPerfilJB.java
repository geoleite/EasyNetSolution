/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.dao.IMet_metodoDAO;
import br.com.easynet.easyportal.dao.IOp_ope_perDAO;
import br.com.easynet.easyportal.dao.IOpe_operacaoDAO;
import br.com.easynet.easyportal.dao.IPer_perfilDAO;
import br.com.easynet.easyportal.dao.Met_metodoDAO;
import br.com.easynet.easyportal.dao.Op_ope_perDAO;
import br.com.easynet.easyportal.dao.Ope_operacaoDAO;
import br.com.easynet.easyportal.dao.Per_perfilDAO;
import br.com.easynet.easyportal.transfer.Met_metodoT;
import br.com.easynet.easyportal.transfer.Op_ope_perT;
import br.com.easynet.easyportal.transfer.Ope_operacaoT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class OperacoesPerfilJB extends EasySecurityJB {

    private Per_perfilT per_perfilT = new Per_perfilT();
    private String[] operacoes;
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private List<Ope_operacaoT> operacoesPerfil;
    private List<Ope_operacaoT> operacoesNaoPerfil;
    private List<Met_metodoT> metodosPerfil;
    private String operacaoSistema;
    

    public void pageLoad() throws Exception {
        findbyid();
        consultOperacoesPerfil();
    }

    public void consultOperacoesPerfil() throws Exception {
        try {
            IOpe_operacaoDAO opeDao = getOpe_operacaoDAO();
            operacoesPerfil = opeDao.getOperacoesPerfil(per_perfilT);
            operacoesNaoPerfil = opeDao.getOperacoesNaoPerfil(per_perfilT);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }
    
    public void selectMetodos() throws Exception {
        try {
            IMet_metodoDAO metDAO = getMet_metodoDAO();
            findbyidOperacao();
            Met_metodoT metT = new Met_metodoT();
            String param[] = operacaoSistema.split("_");
            int sisNrId = Integer.parseInt(param[0]);
            int opeNrId = Integer.parseInt(param[1]);
            metT.setSis_nr_id(sisNrId);
            metT.setOpe_nr_id(opeNrId);
            //setMetodosPerfil(metDAO.getByMetodosNaoPerfil(metT));           
//            per_perfilT = listTemp.size() > 0 ? listTemp.get(0) : new Per_perfilT();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }        
    }

    private void findbyidOperacao() throws Exception {
        try {
            IOpe_operacaoDAO opeDAO = getOpe_operacaoDAO();
            List<Ope_operacaoT> listTemp = opeDAO.getById(ope_operacaoT);

            ope_operacaoT = listTemp.size() > 0 ? listTemp.get(0) : new Ope_operacaoT();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }
    
    public void findbyid() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            List<Per_perfilT> listTemp = per_perfilDAO.getById(per_perfilT);

            per_perfilT = listTemp.size() > 0 ? listTemp.get(0) : new Per_perfilT();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    /**
     * Adiciona operações no perfil
     * @throws java.lang.Exception
     */
    public void adicionar() throws Exception {
        try {
            IOp_ope_perDAO opDao = getOp_ope_perDAO();
            Op_ope_perT opT = new Op_ope_perT();
            opT.setPer_nr_id(per_perfilT.getPer_nr_id());
            for (int i = 0; i < operacoes.length; i++) {
                String[] codigos = operacoes[i].split("_");
                int sisNrId = Integer.parseInt(codigos[0]);
                int opeNrId = Integer.parseInt(codigos[1]);
                opT.setSis_nr_id(sisNrId);
                opT.setOpe_nr_id(opeNrId);
                opDao.insert(opT);
            }
            consultOperacoesPerfil();


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }

    /**
     * Remover operações do perfil
     * @throws java.lang.Exception
     */
    public void remover() throws Exception {
        try {
            IOp_ope_perDAO opDao = getOp_ope_perDAO();
            Op_ope_perT opT = new Op_ope_perT();
            opT.setPer_nr_id(per_perfilT.getPer_nr_id());
            for (int i = 0; i < operacoes.length; i++) {
                String[] codigos = operacoes[i].split("_");
                int sisNrId = Integer.parseInt(codigos[0]);
                int opeNrId = Integer.parseInt(codigos[1]);
                opT.setSis_nr_id(sisNrId);
                opT.setOpe_nr_id(opeNrId);
                opDao.delete(opT);
            }
            consultOperacoesPerfil();


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    public void adicionarTodos() throws Exception {

    }

    public void removerTodos() throws Exception {

    }

    public List<Ope_operacaoT> getOperacoesPerfil() {
        return operacoesPerfil;
    }

    public void setOperacoesPerfil(List<Ope_operacaoT> operacoesPerfil) {
        this.operacoesPerfil = operacoesPerfil;
    }

    public List<Ope_operacaoT> getOperacoesNaoPerfil() {
        return operacoesNaoPerfil;
    }

    public void setOperacoesNaoPerfil(List<Ope_operacaoT> operacoesNaoPerfil) {
        this.operacoesNaoPerfil = operacoesNaoPerfil;
    }

    public Per_perfilT getPer_perfilT() {
        return per_perfilT;
    }

    public void setPer_perfilT(Per_perfilT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }

    public String[] getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String[] operacoes) {
        this.operacoes = operacoes;
    }

    public Ope_operacaoT getOpe_operacaoT() {
        return ope_operacaoT;
    }

    public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
    }

    public List<Met_metodoT> getMetodosPerfil() {
        return metodosPerfil;
    }

    public void setMetodosPerfil(List<Met_metodoT> metodosPerfil) {
        this.metodosPerfil = metodosPerfil;
    }

    public String getOperacaoSistema() {
        return operacaoSistema;
    }

    public void setOperacaoSistema(String operacaoSistema) {
        this.operacaoSistema = operacaoSistema;
    }
}
