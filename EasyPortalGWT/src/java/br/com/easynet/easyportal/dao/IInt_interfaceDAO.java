package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IInt_interfaceDAO extends IEntityDAO{

    public void insert(Int_interfaceT int_interfaceT) throws Exception;

    public void update(Int_interfaceT int_interfaceT) throws Exception;

    public void delete(Int_interfaceT int_interfaceT) throws Exception;

    public List<Int_interfaceT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Int_interfaceT> getAll() throws Exception;
    public List<Int_interfaceT> getByPK(Int_interfaceT int_interfaceT) throws Exception;

    public List<Int_interfaceT> getByInt_nr_id(Int_interfaceT int_interfaceT) throws Exception;

    public List<Int_interfaceT> getByInt_tx_nome(Int_interfaceT int_interfaceT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}