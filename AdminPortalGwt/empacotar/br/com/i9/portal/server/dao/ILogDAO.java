package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import br.com.jdragon.dao.*;
import java.text.SimpleDateFormat;

public interface ILogDAO  {

    public void insert(LogTGWT logT) throws Exception;

    public void update(LogTGWT logT) throws Exception;

    public void delete(LogTGWT logT) throws Exception;

    public List<LogTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<LogTGWT> getAll() throws Exception;

    public List<LogTGWT> getFilterGenerico(LogTGWT logT, String datainicial, String datafinal) throws Exception;

    public List<LogTGWT> getByPK(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_nr_id(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_sistema(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_classe(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_metodo(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_usuario(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_dt_datahora(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_status(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_ip(LogTGWT logT) throws Exception;

    public List<LogTGWT> getByLog_tx_parametros(LogTGWT logT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
