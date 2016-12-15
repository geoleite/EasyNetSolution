package br.com.easynet.portal.dao;

import br.com.easynet.database.DataSet;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public interface IPor_usuDAO {

    public void insert(Por_usuT por_usuT) throws Exception;

    public void update(Por_usuT por_usuT) throws Exception;

    public void delete(Por_usuT por_usuT) throws Exception;

    public List<Por_usuT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Por_usuT> getAll() throws Exception;

    public List<Por_usuT> getById(Por_usuT por_usuT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}