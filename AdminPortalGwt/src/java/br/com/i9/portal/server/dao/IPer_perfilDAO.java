package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IPer_perfilDAO {

    public void insert(Per_perfilTGWT per_perfilT) throws Exception;

    public void update(Per_perfilTGWT per_perfilT) throws Exception;

    public void delete(Per_perfilTGWT per_perfilT) throws Exception;

    public List<Per_perfilTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Per_perfilTGWT> getAll() throws Exception;

    public List<Per_perfilTGWT> getByPK(Per_perfilTGWT per_perfilT) throws Exception;

    public List<Per_perfilTGWT> getByPer_nr_id(Per_perfilTGWT per_perfilT) throws Exception;

    public List<Per_perfilTGWT> getByPer_tx_nome(Per_perfilTGWT per_perfilT) throws Exception;

    public List<Per_perfilTGWT> getByPer_tx_status(Per_perfilTGWT per_perfilT) throws Exception;

    public List<Per_perfilTGWT> getByPer_tx_class(Per_perfilTGWT per_perfilT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
