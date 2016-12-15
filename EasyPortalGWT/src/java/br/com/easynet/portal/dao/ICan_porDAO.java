package br.com.easynet.portal.dao;

import br.com.easynet.database.DataSet;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public interface ICan_porDAO {

    public void insert(Can_porT can_porT) throws Exception;

    public void update(Can_porT can_porT) throws Exception;

    public void delete(Can_porT can_porT) throws Exception;

    public List<Can_porT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    /**
     * Lista todos os canais de um Portal
     * @param cpT
     * @return
     * @throws java.lang.Exception
     */
    public List<Can_porT> getAllPortal(Can_porT cpT) throws Exception;

    public List<Can_porT> getById(Can_porT can_porT) throws Exception;

    public List<Can_porT> getByCp_nr_ordem(Can_porT can_porT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}
