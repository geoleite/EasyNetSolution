package br.com.easynet.easyportal.menu.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;

public interface IMen_menuDAO {

    public void insert(Men_menuT men_menuT) throws Exception;

    public void update(Men_menuT men_menuT) throws Exception;

    public void delete(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    /**
     * Obtém os submenu menu de um menu
     * @param usu_usuarioT
     * @return
     * @throws java.lang.Exception
     */
    public List<Men_menuT> getSubMenu(Men_menuT menT) throws Exception;

    public List<Men_menuT> getMenuPrimario(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Men_menuT> getMenuPrimarioDash(Usu_usuarioT usu_usuarioT) throws Exception;
    
    public List<Men_menuT> getMenuPrimario(Usu_usuarioT usu_usuarioT, String sistema) throws Exception;

    public List<Men_menuT> getAll() throws Exception;

    public List<Men_menuT> getAllExceto(Men_menuT menT) throws Exception;

    public List<Men_menuT> getMenuPerfil(Per_perfilT perT) throws Exception;

    public List<Men_menuT> getMenuNaoPerfil(Per_perfilT perT) throws Exception;

    public List<Men_menuT> getById(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getBySistema(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getByMen_nr_id(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getBySupermenu_nr_id(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getByMen_tx_status(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getByMen_tx_nome(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getByMen_nr_ordem(Men_menuT men_menuT) throws Exception;

    /** One To Many */
    public List<Mep_men_perT> getByMep_men_perTs(Men_menuT men_menuT) throws Exception;

    public List<Men_menuT> getAllUsuario(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Men_menuT> getAllUsuarioDash(Usu_usuarioT usu_usuarioT) throws Exception;
    
    public List<Men_menuT> getAllUsuario(Usu_usuarioT usu_usuarioT, String sistema) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}