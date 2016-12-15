package br.com.easynet.portal.dao;

import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public interface IPor_portalDAO {

    public void insert(Por_portalT por_portalT) throws Exception;

    public void update(Por_portalT por_portalT) throws Exception;

    public void delete(Por_portalT por_portalT) throws Exception;

    public List<Por_portalT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Por_portalT> getAll() throws Exception;

    public List<Por_portalT> getById(Por_portalT por_portalT) throws Exception;

    public List<Por_portalT> getByIdUsuario(Usu_usuarioT usu_usuarioT) throws Exception;

    public List<Por_portalT> getByPor_tx_nome(Por_portalT por_portalT) throws Exception;

    public List<Por_portalT> getByPor_tx_status(Por_portalT por_portalT) throws Exception;

    public List<Por_portalT> getByPor_nr_colunas(Por_portalT por_portalT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}