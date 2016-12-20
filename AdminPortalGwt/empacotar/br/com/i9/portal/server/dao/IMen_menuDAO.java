package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IMen_menuDAO {

    public void insert(Men_menuTGWT men_menuT) throws Exception;

    public void update(Men_menuTGWT men_menuT) throws Exception;

    public void delete(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Men_menuTGWT> getAll() throws Exception;

    public List<Men_menuTGWT> getByPK(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_nr_id(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception;

    public List<Men_menuTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception;

    public List<Men_menuTGWT> getBySupermenu_nr_id(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_tx_status(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_tx_nome(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_nr_ordem(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_tx_url(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_tx_acao(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getBySis_nr_id(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> getByMen_tx_icone(Men_menuTGWT men_menuT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
