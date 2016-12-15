package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IPu_per_usuDAO extends IEntityDAO{

    public void insert(Pu_per_usuT pu_per_usuT) throws Exception;

    public void update(Pu_per_usuT pu_per_usuT) throws Exception;

    public void deleteByUsuario(Pu_per_usuT pu_per_usuT) throws Exception;

    public void delete(Pu_per_usuT pu_per_usuT) throws Exception;

    public List<Pu_per_usuT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Pu_per_usuT> getAll() throws Exception;

    public List<Pu_per_usuT> getById(Pu_per_usuT pu_per_usuT) throws Exception;

    public List<Pu_per_usuT> getByPer_nr_id(Pu_per_usuT pu_per_usuT) throws Exception;

    public List<Pu_per_usuT> getByUsu_nr_id(Pu_per_usuT pu_per_usuT) throws Exception;

    /** Many to One */
    public Per_perfilT getByPer_perfilT(Pu_per_usuT pu_per_usuT) throws Exception;

    /** Many to One */
    public Usu_usuarioT getByUsu_usuarioT(Pu_per_usuT pu_per_usuT) throws Exception;

    /** Metodos FK */
    public List<Pu_per_usuT> getByPer_perfil(Pu_per_usuT pu_per_usuT) throws Exception;

    /** Metodos FK */
    public List<Pu_per_usuT> getByUsu_usuario(Pu_per_usuT pu_per_usuT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}