package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IMet_metodoDAO {

    public void insert(Met_metodoTGWT met_metodoT) throws Exception;

    public void update(Met_metodoTGWT met_metodoT) throws Exception;

    public void delete(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Met_metodoTGWT> getAll() throws Exception;

    public List<Met_metodoTGWT> getByPK(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByOpe_nr_id(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT perT) throws Exception;

    public List<Met_metodoTGWT> getByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT perT) throws Exception;

    public List<Met_metodoTGWT> getByMet_nr_id(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByMet_tx_metodo(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByMet_tx_status(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getBySis_nr_id(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByMet_tx_descricao(Met_metodoTGWT met_metodoT) throws Exception;

    public List<Met_metodoTGWT> getByOpe_operacao(Met_metodoTGWT met_metodoT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
