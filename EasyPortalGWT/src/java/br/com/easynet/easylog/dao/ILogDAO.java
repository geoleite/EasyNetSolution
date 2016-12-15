package br.com.easynet.easylog.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easylog.transfer.*;

public interface ILogDAO  {

    public void insert(LogT logT) throws Exception;

    public void update(LogT logT) throws Exception;

    public void delete(LogT logT) throws Exception;

    public List<LogT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<LogT> getAll() throws Exception;

    public List<LogT> getFilter(List<Object> param, String sql) throws Exception;

    public List<LogT> getById(LogT logT) throws Exception;

    public List<LogT> getByLog_nr_id(LogT logT) throws Exception;

    public List<LogT> getByLog_tx_sistema(LogT logT) throws Exception;

    public List<LogT> getByLog_tx_classe(LogT logT) throws Exception;

    public List<LogT> getByLog_tx_metodo(LogT logT) throws Exception;

    public List<LogT> getByLog_dt_datahora(LogT logT) throws Exception;

    public List<LogT> getByLog_tx_usuario(LogT logT) throws Exception;

    public List<LogT> getFilterGenerico(LogT logT, String datainicial, String datafinal) throws Exception;
}