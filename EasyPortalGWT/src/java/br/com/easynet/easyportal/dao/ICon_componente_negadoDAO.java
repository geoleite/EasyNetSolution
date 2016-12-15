package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public interface ICon_componente_negadoDAO {

    public void insert(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public void update(Con_componente_negadoT con_componente_negadoT) throws Exception;

    public void delete(Con_componente_negadoT con_componente_negadoT) throws Exception;

    public List<Con_componente_negadoT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Con_componente_negadoT> getAll() throws Exception;
    public List<Con_componente_negadoT> getByPK(Con_componente_negadoT con_componente_negadoT) throws Exception;

    public List<Con_componente_negadoT> getByCon_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public List<Con_componente_negadoT> getByInt_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public List<Con_componente_negadoT> getByInterfacePerfis(Con_componente_negadoT con_componente_negadoT, String perfis) throws Exception;
    public List<Con_componente_negadoT> getByInterfacePerfil(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public List<Con_componente_negadoT> getByPer_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public List<Con_componente_negadoT> getByCon_tx_visivel(Con_componente_negadoT con_componente_negadoT) throws Exception;
    public List<Con_componente_negadoT> getByCon_tx_ativo(Con_componente_negadoT con_componente_negadoT) throws Exception;
    /** M todos FK */
    public List<Con_componente_negadoT> getByPi_per_int(Con_componente_negadoT con_componente_negadoT) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;
}