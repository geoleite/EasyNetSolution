package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IPar_parametroDAO extends IEntityDAO{


    public void insert(Par_parametroT par_parametroT) throws Exception;

    public void update(Par_parametroT par_parametroT) throws Exception;

    public void delete(Par_parametroT par_parametroT) throws Exception;

    public List<Par_parametroT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Par_parametroT> getAll() throws Exception;

    public Par_parametroT getByPK(Par_parametroT par_parametroT) throws Exception;
    public List<Par_parametroT> getBySis_nr_id(Par_parametroT par_parametroT) throws Exception;

    public List<Par_parametroT> getByPar_tx_nome(Par_parametroT par_parametroT) throws Exception;
    public List<Par_parametroT> getBySistemaPar_tx_nome(Par_parametroT par_parametroT) throws Exception;

    public List<Par_parametroT> getByPar_tx_valor(Par_parametroT par_parametroT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}
