package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public interface IOpm_met_ope_perDAO {

    public void insert(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public void update(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public void delete(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Opm_met_ope_perTGWT> getAll() throws Exception;

    public List<Opm_met_ope_perTGWT> getByPK(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByPer_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByOpe_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getBySis_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByMet_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByPer_perfil(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByOpe_operacao(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public List<Opm_met_ope_perTGWT> getByMet_metodo(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception;

    public void close();

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception ;

}
