package br.com.easynet.easyportal.menu.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;

public interface IMep_men_perDAO {

    public void insert(Mep_men_perT mep_men_perT) throws Exception;

    public void update(Mep_men_perT mep_men_perT) throws Exception;

    public void delete(Mep_men_perT mep_men_perT) throws Exception;

    public List<Mep_men_perT> resultSetToObjectTransfer(ResultSet rs) throws Exception;

    public List<Mep_men_perT> getAll() throws Exception;

    public List<Mep_men_perT> getById(Mep_men_perT mep_men_perT) throws Exception;

    public List<Mep_men_perT> getByPer_nr_id(Mep_men_perT mep_men_perT) throws Exception;

    public List<Mep_men_perT> getByMen_nr_id(Mep_men_perT mep_men_perT) throws Exception;

    /** Many to One */
    public Men_menuT getByMen_menuT(Mep_men_perT mep_men_perT) throws Exception;

    /** Metodos FK */
    public List<Mep_men_perT> getByMen_menu(Mep_men_perT mep_men_perT) throws Exception;

    public List<Mep_men_perT> getByPerfis(List<Per_perfilT> listPer) throws Exception;

    public DataSet executeQuery(String sql, Object[] parameter) throws Exception;
    
    public void close();
}
