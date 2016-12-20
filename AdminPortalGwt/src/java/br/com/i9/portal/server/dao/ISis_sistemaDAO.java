package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface ISis_sistemaDAO  {

    public void insert(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public void update(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public void delete(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public List<Sis_sistemaTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Sis_sistemaTGWT> getAll() throws Exception;

    public List<Sis_sistemaTGWT> getByPK(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public List<Sis_sistemaTGWT> getBySis_nr_id(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public List<Sis_sistemaTGWT> getBySis_tx_nome(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public List<Sis_sistemaTGWT> getBySis_tx_descricao(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public List<Sis_sistemaTGWT> getBySis_tx_status(Sis_sistemaTGWT sis_sistemaT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
