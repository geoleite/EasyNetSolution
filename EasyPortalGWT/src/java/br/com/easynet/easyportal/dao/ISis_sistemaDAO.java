package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface ISis_sistemaDAO extends IEntityDAO{

    public void insert(Sis_sistemaT sis_sistemaT) throws Exception;

    public void update(Sis_sistemaT sis_sistemaT) throws Exception;

    public void delete(Sis_sistemaT sis_sistemaT) throws Exception;

    public List<Sis_sistemaT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Sis_sistemaT> getAll() throws Exception;

    public List<Sis_sistemaT> getAllAtivos() throws Exception;

    public List<Sis_sistemaT> getById(Sis_sistemaT sis_sistemaT) throws Exception;

    public List<Sis_sistemaT> getBySis_nr_id(Sis_sistemaT sis_sistemaT) throws Exception;

    public Sis_sistemaT getBySis_tx_nome(Sis_sistemaT sis_sistemaT) throws Exception;

    public List<Sis_sistemaT> getBySis_tx_descricao(Sis_sistemaT sis_sistemaT) throws Exception;

    public List<Sis_sistemaT> getBySis_tx_status(Sis_sistemaT sis_sistemaT) throws Exception;

    /** One To Many */
    public List<Ope_operacaoT> getByOpe_operacaoTs(Sis_sistemaT sis_sistemaT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();

    public DataSet getSistemaByOperacao(String classe)throws Exception;
}
