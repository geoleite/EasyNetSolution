package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IPu_per_usuDAO {

    public void insert(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public void update(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public void delete(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public List<Pu_per_usuTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Pu_per_usuTGWT> getAll() throws Exception;

    public List<Pu_per_usuTGWT> getByPK(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public List<Pu_per_usuTGWT> getByPer_nr_id(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public List<Pu_per_usuTGWT> getByUsu_nr_id(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public List<Pu_per_usuTGWT> getByPer_perfil(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public List<Pu_per_usuTGWT> getByUsu_usuario(Pu_per_usuTGWT pu_per_usuT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
