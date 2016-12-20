package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IPar_parametroDAO {

    public void insert(Par_parametroTGWT par_parametroT) throws Exception;

    public void update(Par_parametroTGWT par_parametroT) throws Exception;

    public void delete(Par_parametroTGWT par_parametroT) throws Exception;

    public List<Par_parametroTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Par_parametroTGWT> getAll() throws Exception;

    public List<Par_parametroTGWT> getByPK(Par_parametroTGWT par_parametroT) throws Exception;

    public List<Par_parametroTGWT> getBySis_nr_id(Par_parametroTGWT par_parametroT) throws Exception;

    public List<Par_parametroTGWT> getByPar_tx_nome(Par_parametroTGWT par_parametroT) throws Exception;

    public List<Par_parametroTGWT> getByPar_tx_valor(Par_parametroTGWT par_parametroT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
