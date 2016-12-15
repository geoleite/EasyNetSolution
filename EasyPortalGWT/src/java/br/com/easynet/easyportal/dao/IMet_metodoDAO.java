package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IMet_metodoDAO extends IEntityDAO{


    public void insert(Met_metodoT met_metodoT) throws Exception;

    public void update(Met_metodoT met_metodoT) throws Exception;

    public void delete(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getAllMetodosPerfilSistema(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Met_metodoT> getAll() throws Exception;

    public List<Met_metodoT> getById(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getByMetodosNaoPerfil(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getByMetodosPerfil(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception;
    public List<Met_metodoT> getByOpe_nr_id(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getByMet_nr_id(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getByMet_tx_metodo(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getByMet_tx_status(Met_metodoT met_metodoT) throws Exception;

    public List<Met_metodoT> getBySis_nr_id(Met_metodoT met_metodoT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}