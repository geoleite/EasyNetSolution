package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface IOp_ope_perDAO extends IEntityDAO{

    public void insert(Op_ope_perT op_ope_perT) throws Exception;

    public void delete(Op_ope_perT op_ope_perT) throws Exception;

    public List<Op_ope_perT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Op_ope_perT> getAll() throws Exception;

    public List<Op_ope_perT> getById(Op_ope_perT op_ope_perT) throws Exception;

    public List<Op_ope_perT> getByPer_nr_id(Op_ope_perT op_ope_perT) throws Exception;
    public List<Op_ope_perT> getByOpe_nr_id(Op_ope_perT op_ope_perT) throws Exception;

    public List<Op_ope_perT> getBySis_nr_id(Op_ope_perT op_ope_perT) throws Exception;

    /** Many to One */
    public Per_perfilT getByPer_perfilT(Op_ope_perT op_ope_perT) throws Exception;
    /** Many to One */
    public Ope_operacaoT getByOpe_operacaoT(Op_ope_perT op_ope_perT) throws Exception;

    public List<Ope_operacaoT> getOperacoes() throws Exception;

    /** Metodos FK */
    public List<Op_ope_perT> getByPer_perfil(Op_ope_perT op_ope_perT) throws Exception;

    /** Metodos FK */
    public List<Op_ope_perT> getByOpe_operacao(Op_ope_perT op_ope_perT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;

    public void close();
}