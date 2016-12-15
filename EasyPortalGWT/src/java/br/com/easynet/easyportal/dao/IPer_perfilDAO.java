package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IPer_perfilDAO extends IEntityDAO{

    public void insert(Per_perfilT per_perfilT) throws Exception;

    public void update(Per_perfilT per_perfilT) throws Exception;

    public void delete(Per_perfilT per_perfilT) throws Exception;

    public List<Per_perfilT> resultSetToObjectTransfer(ResultSet rs) throws Exception;
    public List<Per_perfilT> getAll() throws Exception;

    public List<Per_perfilT> getById(Per_perfilT per_perfilT) throws Exception;

    public List<Per_perfilT> getByPer_nr_id(Per_perfilT per_perfilT) throws Exception;
    public List<Per_perfilT> getByPer_tx_nome(Per_perfilT per_perfilT) throws Exception;

    public List<Per_perfilT> getByPer_tx_status(Per_perfilT per_perfilT) throws Exception;

    public List<Per_perfilT> getByUsuario(Usu_usuarioT usu_usuarioT) throws Exception;

    /** One To Many */
    public List<Op_ope_perT> getByOp_ope_perTs(Per_perfilT per_perfilT) throws Exception;

    /** One To Many */
    public List<Pu_per_usuT> getByPu_per_usuTs(Per_perfilT per_perfilT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}