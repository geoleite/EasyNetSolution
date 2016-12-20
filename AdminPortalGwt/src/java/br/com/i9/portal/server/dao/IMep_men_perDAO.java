package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IMep_men_perDAO {

    public void insert(Mep_men_perTGWT mep_men_perT) throws Exception;

    public void update(Mep_men_perTGWT mep_men_perT) throws Exception;

    public void delete(Mep_men_perTGWT mep_men_perT) throws Exception;

    public List<Mep_men_perTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Mep_men_perTGWT> getAll() throws Exception;

    public List<Mep_men_perTGWT> getByPK(Mep_men_perTGWT mep_men_perT) throws Exception;

    public List<Mep_men_perTGWT> getByPer_nr_id(Mep_men_perTGWT mep_men_perT) throws Exception;

    public List<Mep_men_perTGWT> getByMen_nr_id(Mep_men_perTGWT mep_men_perT) throws Exception;

    public List<Mep_men_perTGWT> getByPer_perfil(Mep_men_perTGWT mep_men_perT) throws Exception;

    public List<Mep_men_perTGWT> getByMen_menu(Mep_men_perTGWT mep_men_perT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
