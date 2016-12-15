package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IOpe_operacaoDAO extends IEntityDAO{

    public void insert(Ope_operacaoT ope_operacaoT) throws Exception;

    public void update(Ope_operacaoT ope_operacaoT) throws Exception;

    public void delete(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Ope_operacaoT> getAll() throws Exception;

    public List<Ope_operacaoT> getById(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getOperacoesPerfil(Per_perfilT per_perfilT) throws Exception;

    public List<Ope_operacaoT> getOperacoesNaoPerfil(Per_perfilT per_perfilT) throws Exception;

    public List<Ope_operacaoT> getByOpe_nr_id(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getBySis_nr_id(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getByOpe_tx_nome(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getByOpe_tx_status(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getByOpe_tx_url(Ope_operacaoT ope_operacaoT) throws Exception;

    public List<Ope_operacaoT> getByOpe_tx_descricao(Ope_operacaoT ope_operacaoT) throws Exception;

    /** Many to One */
    public Sis_sistemaT getBySis_sistemaT(Ope_operacaoT ope_operacaoT) throws Exception;

    /** One To Many */
    public List<Op_ope_perT> getByOp_ope_perTs(Ope_operacaoT ope_operacaoT) throws Exception;

    /** Metodos FK */
    public List<Ope_operacaoT> getBySis_sistema(Ope_operacaoT ope_operacaoT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}
