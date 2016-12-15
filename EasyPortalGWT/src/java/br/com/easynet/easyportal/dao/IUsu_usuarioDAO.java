package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.easynet.portal.transfer.Por_portalT;

public interface IUsu_usuarioDAO extends IEntityDAO{

    public void insert(Usu_usuarioT usu_usuarioT) throws Exception;

    /**
     * Obtém o código autoincremento
     * @return
     * @throws java.lang.Exception
     */
    public int getCodigoAutoIncremento() throws Exception;

    public Usu_usuarioT createUser(Usu_usuarioT usuT) throws Exception;

    public void update(Usu_usuarioT usu_usuarioT) throws Exception;

    /**
     * Altera apenas a senha do usuário
     * @param usu_usuarioT
     * @throws java.lang.Exception
     */
    public void updateSenha(Usu_usuarioT usu_usuarioT) throws Exception;

    /**
     * Altera os dados do usuário sem a senha
     * @param usu_usuarioT
     * @throws java.lang.Exception
     */
    public void updateDados(Usu_usuarioT usu_usuarioT) throws Exception;

    public void delete(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Usu_usuarioT> getAll() throws Exception;

    /**
     * Obtém o usuário com maior código inserido na tabela
     * @return
     * @throws java.lang.Exception
     */
    public Usu_usuarioT getUltimoUsuarioInserido() throws Exception;

    public List<Usu_usuarioT> getUsuariosPortal(Por_portalT porT) throws Exception;

    public List<Usu_usuarioT> getUsuariosNaoPortal(Por_portalT porT) throws Exception;

    public List<Usu_usuarioT> getUsuariosPerfil(Per_perfilT per_perfilT) throws Exception;

    public List<Usu_usuarioT> getUsuariosNaoPerfil(Per_perfilT per_perfilT) throws Exception;

    public List<Usu_usuarioT> getById(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByUsu_nr_id(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByUsu_tx_nome(Usu_usuarioT usu_usuarioT) throws Exception;

    /**
     * Pesquisar usuário pelo nome e/ou pelo Login
     * @param usu_usuarioT
     * @return
     * @throws Exception
     */
    public List<Usu_usuarioT> getByNomeLogin(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByLoginEmail(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByUsu_tx_login(Usu_usuarioT usu_usuarioT) throws Exception;

    /**
     * Realiza a autenticação do usuário
     * @param usu_usuarioT
     * @return
     * @throws java.lang.Exception
     */
    public List<Usu_usuarioT> getByAutentication(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByUsu_tx_senha(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Usu_usuarioT> getByUsu_tx_status(Usu_usuarioT usu_usuarioT) throws Exception;

    /** One To Many */
    public List<Pu_per_usuT> getByPu_per_usuTs(Usu_usuarioT usu_usuarioT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();

    public DataSet checaPermissao(int idUsuario, String classMetodo)  throws Exception;

    public DataSet checaPermissao(int idUsuario, String op, String classMetodo)  throws Exception;

    public void updateTrocarSenha(Usu_usuarioT usu_usuarioT) throws Exception;
}
