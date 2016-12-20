package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IOpe_operacaoDAO {

    public void insert(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public void update(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public void delete(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Ope_operacaoTGWT> getAll() throws Exception;

    public List<Ope_operacaoTGWT> getByPK(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByOpe_nr_id(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getBySis_nr_id(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception;

    public List<Ope_operacaoTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception;


    public List<Ope_operacaoTGWT> getByOpe_tx_nome(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByOpe_tx_status(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByOpe_tx_url(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByOpe_tx_descricao(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getByOpe_tx_classe(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getBySis_sistema(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
