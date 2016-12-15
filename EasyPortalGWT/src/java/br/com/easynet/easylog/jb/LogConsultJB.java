package br.com.easynet.easylog.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easylog.dao.*;
import br.com.easynet.easylog.transfer.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class LogConsultJB extends SystemBase implements INotLog {

    // Atributos e propriedades
    private LogT logT = new LogT();
    private String datainicial, datafinal;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void setLogT(LogT logT) {
        this.logT = logT;
    }

    public LogT getLogT() {
        return logT;
    }
    private List<LogT> list;

    public List<LogT> getList() {
        return list;
    }

    public void setList(List<LogT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //consult();
    }

    public void consult() throws Exception {
        try {
/*
            List<Object> param = new Vector<Object>();
            StringBuffer sql = new StringBuffer("select * from usr_portal.log where 1=1  ");
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
            list = getLogDAO().getFilter(param, sql.toString());
*/
            
            list = getLogDAO().getFilterGenerico(logT, datainicial, datafinal);
            //list = logDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            
            getLogDAO().delete(logT);
            setMsg("Exclus?o efetuada com sucesso!");
            logT = new LogT();
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclus?o!");
        } finally {
            close();
        }
    }

    public void insert() throws Exception {
        // TODO Insert
        try {
            String page = "logInsert.jsp";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public String getDatainicial() {
        if (datainicial == null || datainicial.trim().length() > 0) {
            Date dt = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            dt = cal.getTime();
            datainicial = sdf.format(dt);
        }
        return datainicial;
    }

    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }

    public String getDatafinal() {
        if (datainicial == null || datainicial.trim().length() > 0) {
            Date dt = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dt = cal.getTime();
            datafinal = sdf.format(dt);
        }
        return datafinal;
    }

    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }
}
