package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IPi_per_intDAO extends IEntityDAO{

    public void insert(Pi_per_intT pi_per_intT) throws Exception;

    public void update(Pi_per_intT pi_per_intT) throws Exception;

    public void delete(Pi_per_intT pi_per_intT) throws Exception;

    public List<Pi_per_intT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Pi_per_intT> getAll() throws Exception;

    public List<Pi_per_intT> getByPK(Pi_per_intT pi_per_intT) throws Exception;

    public List<Pi_per_intT> getByInt_nr_id(Pi_per_intT pi_per_intT) throws Exception;

    public List<Pi_per_intT> getByPer_nr_id(Pi_per_intT pi_per_intT) throws Exception;

    /** M todos FK */
    public List<Pi_per_intT> getByInt_interface(Pi_per_intT pi_per_intT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}
