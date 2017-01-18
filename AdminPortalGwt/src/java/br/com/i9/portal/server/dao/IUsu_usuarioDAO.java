package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IUsu_usuarioDAO {

    public void insert(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public void update(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public void updateSenha(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public void updateSenhaByUsuario(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public void delete(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Usu_usuarioTGWT> getAll() throws Exception;

    public List<Usu_usuarioTGWT> getByPK(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_nr_id(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception;

    public List<Usu_usuarioTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_tx_nome(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByNomeLogin(String texto) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_tx_login(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_tx_senha(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_tx_status(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public List<Usu_usuarioTGWT> getByUsu_tx_email(Usu_usuarioTGWT usu_usuarioT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;
    
    public Usu_usuarioTGWT getConfirmeSenha(Usu_usuarioTGWT usuT) throws Exception;
}
