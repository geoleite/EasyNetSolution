package br.com.easynet.easylog.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easylog.transfer.*;
import br.com.easynet.easyportal.dao.ObjectDAOClusterEasyPortal;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;
import java.text.SimpleDateFormat;

public class LogDAO_Mysql extends ObjectDAOClusterEasyPortal implements ILogDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public LogDAO_Mysql(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(LogT logT) throws Exception {
        try {
            String sql = "insert into log  ( log_tx_sistema, log_tx_classe, log_tx_metodo, log_dt_datahora, log_tx_usuario, log_tx_status, log_tx_ip, log_tx_parametros) values ( ? , ? , ? , ? , ? , ?, ?, ?)";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, logT.getLog_tx_sistema());
            pStmt.setObject(2, logT.getLog_tx_classe());
            pStmt.setObject(3, logT.getLog_tx_metodo());

            pStmt.setObject(4, logT.getLog_dt_datahora());
            pStmt.setObject(5, logT.getLog_tx_usuario());
            pStmt.setObject(6, logT.getLog_tx_status());
            pStmt.setObject(7, logT.getLog_tx_ip());
            pStmt.setObject(8, logT.getLog_tx_parametro());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public void update(LogT logT) throws Exception {
        try {
            String sql = "update log set  log_tx_sistema=?, log_tx_classe=?, log_tx_metodo=?, log_dt_datahora=?, log_tx_usuario=?  where  log_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, logT.getLog_tx_sistema());
            pStmt.setObject(2, logT.getLog_tx_classe());
            pStmt.setObject(3, logT.getLog_tx_metodo());
            pStmt.setObject(4, logT.getLog_dt_datahora());
            pStmt.setObject(5, logT.getLog_tx_usuario());
            pStmt.setObject(6, logT.getLog_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }
    }

    public void delete(LogT logT) throws Exception {
        try {
            String sql = "delete from log where  log_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, logT.getLog_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<LogT> objs = new Vector();
        while (rs.next()) {
            LogT logT = new LogT();
            logT.setLog_nr_id(rs.getLong("log_nr_id"));
            logT.setLog_tx_sistema(rs.getString("log_tx_sistema"));
            logT.setLog_tx_classe(rs.getString("log_tx_classe"));
            logT.setLog_tx_metodo(rs.getString("log_tx_metodo"));
            logT.setLog_dt_datahora(rs.getTimestamp("log_dt_datahora"));
            logT.setLog_tx_usuario(rs.getString("log_tx_usuario"));
            logT.setLog_tx_status(rs.getString("log_tx_status"));
            logT.setLog_tx_ip(rs.getString("log_tx_ip"));
            logT.setLog_tx_parametro(rs.getString("log_tx_parametros"));
            objs.add(logT);
        }
        return objs;
    }

    public List<LogT> getAll() throws Exception {
        try {
            String sql = "select * from log";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getFilterGenerico(LogT logT, String datainicial, String datafinal) throws Exception {
        try {
            List<Object> param = new Vector<Object>();
            StringBuffer sql = new StringBuffer("select * from log where 1=1  ");
            if (logT.getLog_tx_usuario() != null && logT.getLog_tx_usuario().trim().length() > 0) {
                sql.append(" and upper(log_tx_usuario) like upper('%'||?||'%') ");
                param.add(logT.getLog_tx_usuario());
            }
            if (logT.getLog_tx_sistema() != null && logT.getLog_tx_sistema().trim().length() > 0) {
                sql.append(" and upper(log_tx_sistema) like upper('%'||?||'%') ");
                param.add(logT.getLog_tx_sistema());
            }
            if (logT.getLog_tx_classe() != null && logT.getLog_tx_classe().trim().length() > 0) {
                sql.append(" and upper(log_tx_classe) like upper('%'||?||'%') ");
                param.add(logT.getLog_tx_classe());
            }
            if (logT.getLog_tx_metodo() != null && logT.getLog_tx_metodo().trim().length() > 0) {
                sql.append(" and upper(log_tx_metodo) like upper('%'||?||'%') ");
                param.add(logT.getLog_tx_metodo());
            }
            if (logT.getLog_tx_status() != null && logT.getLog_tx_status().trim().length() > 0) {
                sql.append(" and upper(log_tx_status) like upper(?) ");
                param.add(logT.getLog_tx_status());
            }
            if (datainicial != null && datainicial.trim().length() > 0) {
//          sql.append(" and log_dt_datahora between '").append(datainicial).append("' and '").append(datafinal).append("' ");
                sql.append(" and log_dt_datahora between ? and ?");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                param.add(new java.sql.Date(sdf.parse(datainicial).getTime()));
                param.add(new java.sql.Date(sdf.parse(datafinal).getTime()));
            }

            sql.append(" order by log_dt_datahora desc, log_tx_usuario, log_tx_sistema, log_tx_classe, log_tx_metodo");
            //pStmt = con.prepareStatement(sql.toString());
            pStmt = createPrepareStatment(sql.toString());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw  e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }
    public List<LogT> getFilter(List<Object> param, String sql) throws Exception {
        try {
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            for (int i = 0; i < param.size(); i++) {
                Object object = param.get(i);
                pStmt.setObject(i + 1, object);
            }
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getById(LogT logT) throws Exception {
        try {
        String sql = "select * from log where  log_nr_id=?";
        //pStmt = con.prepareStatement(sql);
        pStmt = createPrepareStatment(sql);
        pStmt.setObject(1, logT.getLog_nr_id());
        rs = pStmt.executeQuery();
        return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }
    }

    public List<LogT> getByLog_nr_id(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  log_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, logT.getLog_nr_id());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getByLog_tx_sistema(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  Upper(log_tx_sistema) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_sistema() + '%');
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getByLog_tx_classe(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  Upper(log_tx_classe) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_classe() + '%');
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getByLog_tx_metodo(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  Upper(log_tx_metodo) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_metodo() + '%');
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getByLog_dt_datahora(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  log_dt_datahora = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, logT.getLog_dt_datahora());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<LogT> getByLog_tx_usuario(LogT logT) throws Exception {
        try {
            String sql = "select * from log where  Upper(log_tx_usuario) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_usuario() + '%');
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }
}