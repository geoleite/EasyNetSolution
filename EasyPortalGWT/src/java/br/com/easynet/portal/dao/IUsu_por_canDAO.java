package br.com.easynet.portal.dao;

import br.com.easynet.database.DataSet;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public interface IUsu_por_canDAO {

    public void insert(Usu_por_canT usu_por_canT) throws Exception;

    public void update(Usu_por_canT usu_por_canT) throws Exception;

    public void delete(Usu_por_canT usu_por_canT) throws Exception;

    public List<Usu_por_canT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Usu_por_canT> getAll() throws Exception;

    public List<Usu_por_canT> getById(Usu_por_canT usu_por_canT) throws Exception;

    public List<Usu_por_canT> getByUpc_tx_status(Usu_por_canT usu_por_canT) throws Exception;

    public List<Usu_por_canT> getByUpc_nr_ordem(Usu_por_canT usu_por_canT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;
    
    public void close();
}