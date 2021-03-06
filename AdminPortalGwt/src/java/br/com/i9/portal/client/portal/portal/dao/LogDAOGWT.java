package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;




import java.util.*;

public class LogDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/log/logInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/log/logConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/log/logUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private LogTGWT logT;

    public void consultarTodos() {
        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
    }

    public void consultByFiltroGenerico(LogTGWT logT, String dtInicio, String dtFinal) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consult");
        if (logT.getLog_tx_sistema() != null && !logT.getLog_tx_sistema().isEmpty())
            param.put("logT.log_tx_sistema", logT.getLog_tx_sistema() + "");
        if (logT.getLog_tx_classe() != null && !logT.getLog_tx_classe().isEmpty())
            param.put("logT.log_tx_classe", logT.getLog_tx_classe() != null?logT.getLog_tx_classe():"" );
        if (logT.getLog_tx_metodo() != null && !logT.getLog_tx_metodo().isEmpty())
            param.put("logT.log_tx_metodo", logT.getLog_tx_metodo() != null?logT.getLog_tx_metodo():"");
        if (logT.getLog_tx_usuario() != null && !logT.getLog_tx_usuario().isEmpty())
            param.put("logT.log_tx_usuario", logT.getLog_tx_usuario() !=null?logT.getLog_tx_usuario():"");
        
        param.put("dtInicio", dtInicio);
        param.put("dtFinal", dtFinal);
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void inserir(LogTGWT logT) {
        msg = null;
        list = null;
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    String result = jsonObject.get("resultado").isString().stringValue();
                    msg = result;
                }
            }
        };
        String url = Constantes.URL + PAGE_INSERIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "insert");
        param.put("logT.log_nr_id", logT.getLog_nr_id() + "");
        param.put("logT.log_tx_sistema", logT.getLog_tx_sistema() + "");
        param.put("logT.log_tx_classe", logT.getLog_tx_classe() + "");
        param.put("logT.log_tx_metodo", logT.getLog_tx_metodo() + "");
        param.put("logT.log_tx_usuario", logT.getLog_tx_usuario() + "");
        param.put("logT.log_dt_datahora", dtfDateTime.format(logT.getLog_dt_datahora()));
        param.put("logT.log_tx_status", logT.getLog_tx_status() + "");
        param.put("logT.log_tx_ip", logT.getLog_tx_ip() + "");
        param.put("logT.log_tx_parametros", logT.getLog_tx_parametros() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
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
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
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
                }
            }
        };
        msg = null;
        list = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
        param.put("logT.log_nr_id", logT.getLog_nr_id() + "");
        param.put("logT.log_tx_sistema", logT.getLog_tx_sistema() + "");
        param.put("logT.log_tx_classe", logT.getLog_tx_classe() + "");
        param.put("logT.log_tx_metodo", logT.getLog_tx_metodo() + "");
        param.put("logT.log_tx_usuario", logT.getLog_tx_usuario() + "");
        param.put("logT.log_dt_datahora", dtfDateTime.format(logT.getLog_dt_datahora()));
        param.put("logT.log_tx_status", logT.getLog_tx_status() + "");
        param.put("logT.log_tx_ip", logT.getLog_tx_ip() + "");
        param.put("logT.log_tx_parametros", logT.getLog_tx_parametros() + "");


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
        long log_nr_id = Long.parseLong(registro.get("log_nr_id").isString().stringValue());
        logTGWT.setLog_nr_id(log_nr_id);

        String log_tx_sistema = registro.get("log_tx_sistema").isString().stringValue();
        logTGWT.setLog_tx_sistema(log_tx_sistema);

        String log_tx_classe = registro.get("log_tx_classe").isString().stringValue();
        logTGWT.setLog_tx_classe(log_tx_classe);

        String log_tx_metodo = registro.get("log_tx_metodo").isString().stringValue();
        logTGWT.setLog_tx_metodo(log_tx_metodo);

        String log_tx_usuario = registro.get("log_tx_usuario").isString().stringValue();
        logTGWT.setLog_tx_usuario(log_tx_usuario);

        Date log_dt_datahora = dtfDateTime.parse(registro.get("log_dt_datahora").isString().stringValue());
        logTGWT.setLog_dt_datahora(log_dt_datahora);

        String log_tx_status = registro.get("log_tx_status").isString().stringValue();
        logTGWT.setLog_tx_status(log_tx_status);

        String log_tx_ip = registro.get("log_tx_ip").isString().stringValue();
        logTGWT.setLog_tx_ip(log_tx_ip);

        String log_tx_parametros = registro.get("log_tx_parametros").isString().stringValue();
        logTGWT.setLog_tx_parametros(log_tx_parametros);


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
