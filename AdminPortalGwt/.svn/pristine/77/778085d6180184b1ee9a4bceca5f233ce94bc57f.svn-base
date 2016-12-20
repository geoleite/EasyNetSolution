package br.com.i9.portal.server.bl;

import br.com.easynet.bl.BusinessBase;
import br.com.easynet.database.ConnectionRPL;
import br.com.easynet.easyportal.bl.EasyPortalBusinessBase;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.jdragon.dao.*;
import br.com.i9.portal.server.dao.*;
//import br.com.i9.portal.jb.SystemBase;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.I9Cluster;
import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;

public class SystemBusinessBase extends BusinessBase {

    private int typeDatabase = DAOFactory.POSTGRESQL;
    
//ver com george    
    public static String PATH_LOG_CLUSTER = "/var/logs/i9taxi/";
    private I9Cluster i9Cluster = null;
    private DAOFactoryCluster dao;
    private String datasourceName = "jdbc/easyportalds1";
    //private String datasourceName = "java:comp/env/jdbc/NOME_CONEXAO";
    public String SISTEMA_EASYPORTAL = "EASYPORTAL";
    private EasyPortalBusinessBase portalBusinessBase = new EasyPortalBusinessBase();

    public DAOFactoryCluster getDAO() throws Exception {
        /*
         if (dao == null) {
         if (i9Cluster == null) {
         String path = ConfigI9Cluster.REAL_PATH_BASE + SystemBase.fileI9Cluster;
         i9Cluster = I9Cluster.getI9Cluster(path);
         }
         List<DefinitionConnectionCluster> list = i9Cluster.getListConnectionClusters();
         dao = new DAOFactoryCluster();
         for (int i = 0; i < list.size(); i++) {
         DefinitionConnectionCluster dcc = list.get(i);
         dao.addConnectionRPL(getConnectionRpl(dcc.getName(), dcc.getDatasourceName()));
         }
         }
         return dao;
         */
        return null;
    }

    /**
     * Obtem a conexao para o I9Cluster
     *
     * @param nameConnection
     * @param datasourceName
     * @return
     */
    public ConnectionRPL getConnectionRpl(String nameConnection, String datasourceName) {
        try {
            ConnectionRPL conRPL = new ConnectionRPL();
            conRPL.setPathLog(PATH_LOG_CLUSTER);
            conRPL.setNome(nameConnection);
            conRPL.setCon(getDAODatasource(datasourceName));
            conRPL.recoverLog();
            return conRPL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param dataSourceName
     * @return
     * @throws Exception
     */
    public Connection getDAODatasource(String dataSourceName) throws Exception {

        DataSource ds = getDataSource(dataSourceName);
        return ds.getConnection();
    }

    public void close() {
        try {
            if (dao != null) {
                dao.close();
                dao = null;
            }
            //i9Cluster = null;
        } catch (Exception e) {
        }
    }

    /**
     * M�todo para validar a seguranca
     */
    public boolean valide(String metodo) throws Exception {
        // Logica da seguranca
        return true;
    }

    public ILogDAO getLogDAO() throws Exception {
        //dao = getDAO();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new LogDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
//            return new LogDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
//            return new LogDAO_Mysql(dao);
        }
        return null;
    }

    public IMen_menuDAO getMen_menuDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Men_menuDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Men_menuDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Men_menuDAO_Mysql(dao);
        }
        return null;
    }

    public IMep_men_perDAO getMep_men_perDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Mep_men_perDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Mep_men_perDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Mep_men_perDAO_Mysql(dao);
        }
        return null;
    }

    public IMet_metodoDAO getMet_metodoDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Met_metodoDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Met_metodoDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Met_metodoDAO_Mysql(dao);
        }
        return null;
    }

    public IOpe_operacaoDAO getOpe_operacaoDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Ope_operacaoDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Ope_operacaoDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Ope_operacaoDAO_Mysql(dao);
        }
        return null;
        
    }

    public IOpm_met_ope_perDAO getOpm_met_ope_perDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Opm_met_ope_perDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Opm_met_ope_perDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Opm_met_ope_perDAO_Mysql(dao);
        }
        return null;
    }

    public IPer_perfilDAO getPer_perfilDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Per_perfilDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Per_perfilDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Per_perfilDAO_Mysql(dao);
        }
        return null;
    }

    public IPu_per_usuDAO getPu_per_usuDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Pu_per_usuDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Pu_per_usuDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Pu_per_usuDAO_Mysql(dao);
        }
        return null;
    }

    public ISis_sistemaDAO getSis_sistemaDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Sis_sistemaDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Sis_sistemaDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Sis_sistemaDAO_Mysql(dao);
        }
        return null;
    }

    public IUsu_usuarioDAO getUsu_usuarioDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Usu_usuarioDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Usu_usuarioDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Usu_usuarioDAO_Mysql(dao);
        }
        return null;
    }

    public IPar_parametroDAO getPar_parametroDAO() throws Exception {
        //dao = getDAO();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Par_parametroDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Par_parametroDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Par_parametroDAO_Mysql(dao);
        }
        return null;
    }

    public LogTGWT findbyIdLog(LogTGWT logT) throws Exception {
        try {
            ILogDAO logDAO = getLogDAO();

            List<LogTGWT> listTemp = logDAO.getByPK(logT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Res_recall_senhaDAO getRes_recall_senhaDAO() throws Exception {
        //dao = getDAO();
        return new Res_recall_senhaDAO(dao);
    }

    public Res_recall_senhaTGWT findbyIdRes_recall_senha(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
        try {
            Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();

            List<Res_recall_senhaTGWT> listTemp = res_recall_senhaDAO.getByPK(res_recall_senhaT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Men_menuTGWT findbyIdMen_menu(Men_menuTGWT men_menuT) throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();

            List<Men_menuTGWT> listTemp = men_menuDAO.getByPK(men_menuT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Mep_men_perTGWT findbyIdMep_men_per(Mep_men_perTGWT mep_men_perT) throws Exception {
        try {
            IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();

            List<Mep_men_perTGWT> listTemp = mep_men_perDAO.getByPK(mep_men_perT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Met_metodoTGWT findbyIdMet_metodo(Met_metodoTGWT met_metodoT) throws Exception {
        try {
            IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();

            List<Met_metodoTGWT> listTemp = met_metodoDAO.getByPK(met_metodoT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Ope_operacaoTGWT findbyIdOpe_operacao(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        try {
            IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();

            List<Ope_operacaoTGWT> listTemp = ope_operacaoDAO.getByPK(ope_operacaoT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Opm_met_ope_perTGWT findbyIdOpm_met_ope_per(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        try {
            IOpm_met_ope_perDAO opm_met_ope_perDAO = getOpm_met_ope_perDAO();

            List<Opm_met_ope_perTGWT> listTemp = opm_met_ope_perDAO.getByPK(opm_met_ope_perT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Per_perfilTGWT findbyIdPer_perfil(Per_perfilTGWT per_perfilT) throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();

            List<Per_perfilTGWT> listTemp = per_perfilDAO.getByPK(per_perfilT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Pu_per_usuTGWT findbyIdPu_per_usu(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        try {
            IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();

            List<Pu_per_usuTGWT> listTemp = pu_per_usuDAO.getByPK(pu_per_usuT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Sis_sistemaTGWT findbyIdSis_sistema(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();

            List<Sis_sistemaTGWT> listTemp = sis_sistemaDAO.getByPK(sis_sistemaT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Usu_usuarioTGWT findbyIdUsu_usuario(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();

            List<Usu_usuarioTGWT> listTemp = usu_usuarioDAO.getByPK(usu_usuarioT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public Par_parametroTGWT findbyIdPar_parametro(Par_parametroTGWT par_parametroT) throws Exception {
        try {
            IPar_parametroDAO par_parametroDAO = getPar_parametroDAO();

            List<Par_parametroTGWT> listTemp = par_parametroDAO.getByPK(par_parametroT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
 
    /**
     * @return the typeDatabase
     */
    public int getTypeDatabase() {
        return typeDatabase;
    }

    
    // ver com george, onde o Objeto é Par_parametroT onde deveria ser Par_parametroTGWT
    public String getParametro(String nomeParam) throws Exception {
         Par_parametroT parT = portalBusinessBase.getParametro(SISTEMA_EASYPORTAL, nomeParam);
        return parT == null ? null : parT.getPar_tx_valor();
    }
//    /**
//     * @param typeDatabase the typeDatabase to set
//     */
    public void setTypeDatabase(int typeDatabase) {
        this.typeDatabase = typeDatabase;
    }
    public int getIncUsuario() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "USU_USUARIO", "USU_NR_ID");
    }

        public int getIncParametro() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "PAR_PARAMETRO", "PAR_NR_ID");
    }
    public int getIncSistema() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "SIS_SISTEMA", "SIS_NR_ID");
    }

    public int getIncMenu() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "MEN_MENU", "MEN_NR_ID");
    }

    public int getIncPerfil() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "PER_PERFIL", "PER_NR_ID");
    }

    public int getIncMetodo() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "MET_METODO", "MET_NR_ID");
    }

    public int getIncOperacao() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "OPE_OPERACAO", "OPE_NR_ID");
    }

    public int getIncInterface() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "INT_INTERFACE", "INT_NR_ID");
    }

    public int getIncComponente() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "CON_COMPONENTE_NEGADO", "CON_NR_ID");
    }

    public int getIncCModulo() throws Exception {
        return portalBusinessBase.getAutoIncremento(SISTEMA_EASYPORTAL, "MOD_MODULO", "MOD_NR_ID");
    }
}
