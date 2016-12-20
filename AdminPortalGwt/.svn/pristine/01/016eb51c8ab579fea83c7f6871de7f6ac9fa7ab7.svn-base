package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import br.com.jdragon.dao.*;
import java.text.SimpleDateFormat;

public class LogDAO_Mysql extends ObjectDAO implements ILogDAO {

    public LogDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into log  ( log_nr_id, log_tx_sistema, log_tx_classe, log_tx_metodo, log_tx_usuario, log_dt_datahora, log_tx_status, log_tx_ip, log_tx_parametros) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, logT.getLog_nr_id());
            pStmt.setObject(2, logT.getLog_tx_sistema());
            pStmt.setObject(3, logT.getLog_tx_classe());
            pStmt.setObject(4, logT.getLog_tx_metodo());
            pStmt.setObject(5, logT.getLog_tx_usuario());
            pStmt.setObject(6, logT.getLog_dt_datahora());
            pStmt.setObject(7, logT.getLog_tx_status());
            pStmt.setObject(8, logT.getLog_tx_ip());
            pStmt.setObject(9, logT.getLog_tx_parametros());
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

    public void update(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update log set  log_tx_sistema=?, log_tx_classe=?, log_tx_metodo=?, log_tx_usuario=?, log_dt_datahora=?, log_tx_status=?, log_tx_ip=?, log_tx_parametros=?  where  log_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, logT.getLog_tx_sistema());
            pStmt.setObject(2, logT.getLog_tx_classe());
            pStmt.setObject(3, logT.getLog_tx_metodo());
            pStmt.setObject(4, logT.getLog_tx_usuario());
            pStmt.setObject(5, logT.getLog_dt_datahora());
            pStmt.setObject(6, logT.getLog_tx_status());
            pStmt.setObject(7, logT.getLog_tx_ip());
            pStmt.setObject(8, logT.getLog_tx_parametros());
            pStmt.setObject(9, logT.getLog_nr_id());
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

    public void delete(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from log where  log_nr_id=?";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<LogTGWT> objs = new Vector();
        while (rs.next()) {
            LogTGWT logT = new LogTGWT();
            logT.setLog_nr_id(rs.getLong("log_nr_id"));
            logT.setLog_tx_sistema(rs.getString("log_tx_sistema"));
            logT.setLog_tx_classe(rs.getString("log_tx_classe"));
            logT.setLog_tx_metodo(rs.getString("log_tx_metodo"));
            logT.setLog_tx_usuario(rs.getString("log_tx_usuario"));
            logT.setLog_dt_datahora(rs.getTimestamp("log_dt_datahora"));
            logT.setLog_tx_status(rs.getString("log_tx_status"));
            logT.setLog_tx_ip(rs.getString("log_tx_ip"));
            logT.setLog_tx_parametros(rs.getString("log_tx_parametros"));
            objs.add(logT);
        }
        return objs;
    }

    public List<LogTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<LogTGWT> list = resultSetToObjectTransfer(rs);
            return list;
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

    public List<LogTGWT> getFilterGenerico(LogTGWT logT, String datainicial, String datafinal) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
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
                sql.append(" and log_dt_datahora between ? and ?");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                param.add(new java.sql.Date(sdf.parse(datainicial).getTime()));
                param.add(new java.sql.Date(sdf.parse(datafinal).getTime()));
            }
            sql.append(" order by log_dt_datahora desc, log_tx_usuario, log_tx_sistema, log_tx_classe, log_tx_metodo");
            pStmt = con.prepareStatement(sql.toString());
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

    public List<LogTGWT> getByPK(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  log_nr_id=?";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_nr_id(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  log_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_tx_sistema(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_sistema) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_tx_classe(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_classe) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_tx_metodo(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_metodo) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_tx_usuario(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_usuario) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_dt_datahora(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  log_dt_datahora = ? ";
            pStmt = con.prepareStatement(sql);
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

    public List<LogTGWT> getByLog_tx_status(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_status() + '%');
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

    public List<LogTGWT> getByLog_tx_ip(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_ip) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_ip() + '%');
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

    public List<LogTGWT> getByLog_tx_parametros(LogTGWT logT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from log where  Upper(log_tx_parametros) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + logT.getLog_tx_parametros() + '%');
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
