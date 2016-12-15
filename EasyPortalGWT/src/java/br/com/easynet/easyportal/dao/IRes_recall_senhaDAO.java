package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.jdragon.dao.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public interface IRes_recall_senhaDAO extends IEntityDAO {

    public void insert(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public void update(Res_recall_senhaT res_recall_senhaT) throws Exception;
    public void delete(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public void zerarContadorTentativas(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public void registrarTentativa(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public void registrarAcesso(Res_recall_senhaT res_recall_senhaT) throws Exception;
        
    public List<Res_recall_senhaT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Res_recall_senhaT> getAll() throws Exception;

    public List<Res_recall_senhaT> getByPK(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByUsu_nr_id(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByRes_tx_pergunta(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByRes_tx_resposta(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByRes_dt_ultimoacesso(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByRes_nr_tentativas(Res_recall_senhaT res_recall_senhaT) throws Exception;

    public List<Res_recall_senhaT> getByLoginEmail(String login, String email) throws Exception;
}
