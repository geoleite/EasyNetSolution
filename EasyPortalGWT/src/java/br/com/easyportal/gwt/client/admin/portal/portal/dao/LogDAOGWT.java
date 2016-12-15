package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.LogTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Sis_sistemaTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class LogDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/log/logInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/log/logConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/log/logUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private String msg;
    private ListStore list;
    private LogTGWT logT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void consultarCreterio(LogTGWT logTGWT, Date dtInicial, Date dtFinal) {

        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();

        param.put("op", "consult");
        if (logTGWT.getLog_tx_sistema() != null && logTGWT.getLog_tx_sistema().trim().length() > 0) {
            param.put("logT.log_tx_sistema", logTGWT.getLog_tx_sistema());
        }
        if (logTGWT.getLog_tx_usuario() != null && logTGWT.getLog_tx_usuario().trim().length() > 0) {
            param.put("logT.log_tx_usuario", logTGWT.getLog_tx_usuario());
        }
        if (logTGWT.getLog_tx_classe() != null && logTGWT.getLog_tx_classe().trim().length() > 0) {
            param.put("logT.log_tx_classe", logTGWT.getLog_tx_classe());
        }
        if (logTGWT.getLog_tx_metodo() != null && logTGWT.getLog_tx_metodo().trim().length() > 0) {
            param.put("logT.log_tx_metodo", logTGWT.getLog_tx_metodo());
        }
        if (dtInicial != null && dtFinal != null) {
            param.put("datainicial", dtfDate.format(dtInicial));
            param.put("datafinal", dtfDate.format(dtFinal));
        }


        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void pesquisar(LogTGWT logT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("log").isObject();
                    LogDAOGWT.this.logT = lerRegistroJson(registro);
                }
            }
        };
        this.logT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("logT.log_nr_id", logT.getLog_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(LogTGWT logT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
//                    String result = jsonObject.get("resultado").toString();
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();

//                    msg = result;
                }
            }
        };

        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "delete");
        param.put("logT.log_nr_id", logT.getLog_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(LogTGWT logT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();

//                    String result = jsonObject.get("resultado").toString();
//                    msg = result;
                }
            }
        };
        msg = null;
        list = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
        param.put("logT.log_nr_id", logT.getLog_nr_id() + "");
        param.put("logT.log_tx_sistema", logT.getLog_tx_sistema());
        param.put("logT.log_tx_classe", logT.getLog_tx_classe());
        param.put("logT.log_tx_metodo", logT.getLog_tx_metodo());
        param.put("logT.log_tx_usuario", logT.getLog_tx_usuario());
        param.put("logT.log_dt_datahora", dtfDateTime.format(logT.getLog_dt_datahora()));
        param.put("logT.log_tx_status", logT.getLog_tx_status());
        param.put("logT.log_tx_ip", logT.getLog_tx_ip());
        param.put("logT.log_tx_parametros", logT.getLog_tx_parametros());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<LogTGWT> lista = new ListStore<LogTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                LogTGWT logT = lerRegistroJson(registro);
                lista.add(logT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private LogTGWT lerRegistroJson(JSONObject registro) {
        LogTGWT logTGWT = new LogTGWT();
        long log_nr_id = Long.parseLong(EasyContainer.clearAspas(registro.get("log_nr_id").toString()));
        logTGWT.setLog_nr_id(log_nr_id);

        String log_tx_sistema = EasyContainer.clearAspas(registro.get("log_tx_sistema").toString());
        logTGWT.setLog_tx_sistema(log_tx_sistema);

        String log_tx_classe = EasyContainer.clearAspas(registro.get("log_tx_classe").toString());
        logTGWT.setLog_tx_classe(log_tx_classe);

        String log_tx_metodo = EasyContainer.clearAspas(registro.get("log_tx_metodo").toString());
        logTGWT.setLog_tx_metodo(log_tx_metodo);

        String log_tx_usuario = EasyContainer.clearAspas(registro.get("log_tx_usuario").toString());
        logTGWT.setLog_tx_usuario(log_tx_usuario);

        Date log_dt_datahora = dtfDateTime.parse(EasyContainer.clearAspas(registro.get("log_dt_datahora").toString()));
        logTGWT.setLog_dt_datahora(log_dt_datahora);

        String log_tx_status = EasyContainer.clearAspas(registro.get("log_tx_status").toString());
        logTGWT.setLog_tx_status(log_tx_status);

        String log_tx_ip = EasyContainer.clearAspas(registro.get("log_tx_ip").toString());
        logTGWT.setLog_tx_ip(log_tx_ip);

        //String log_tx_parametros = EasyContainer.clearAspas(registro.get("log_tx_parametros").toString());
        //logTGWT.setLog_tx_parametros(log_tx_parametros);


        return logTGWT;
    }

    /**
     * @return the list
     */
    public ListStore getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ListStore list) {
        this.list = list;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the logT
     */
    public LogTGWT getLogT() {
        return logT;
    }

    /**
     * @param logTGWT the logTGWT to set
     */
    public void setLogTGWT(LogTGWT logT) {
        this.logT = logT;
    }
}
