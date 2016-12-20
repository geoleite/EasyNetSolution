package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Met_metodoDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/met_metodo/met_metodoInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/met_metodo/met_metodoConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/met_metodo/met_metodoUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Met_metodoTGWT met_metodoT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void consultByOperacao(Met_metodoTGWT metT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByOperacao");
        param.put("met_metodoT.ope_nr_id", metT.getOpe_nr_id() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByOperacaoPerfil(Met_metodoTGWT metT, Per_perfilTGWT perT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByOperacaoPerfil");
        param.put("met_metodoT.ope_nr_id", metT.getOpe_nr_id() + "");
        param.put("per_perfilT.per_nr_id", perT.getPer_nr_id() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }


    public void consultByOperacaoNaoPerfil(Met_metodoTGWT metT, Per_perfilTGWT perT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByOperacaoNaoPerfil");
        param.put("met_metodoT.ope_nr_id", metT.getOpe_nr_id() + "");
        param.put("per_perfilT.per_nr_id", perT.getPer_nr_id() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void inserir(Met_metodoTGWT met_metodoT) {
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
        param.put("met_metodoT.ope_nr_id", met_metodoT.getOpe_nr_id() + "");
        param.put("met_metodoT.met_tx_metodo", met_metodoT.getMet_tx_metodo() + "");
        param.put("met_metodoT.met_tx_status", met_metodoT.getMet_tx_status() + "");
        param.put("met_metodoT.sis_nr_id", met_metodoT.getSis_nr_id() + "");
        param.put("met_metodoT.met_tx_descricao", met_metodoT.getMet_tx_descricao() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Met_metodoTGWT met_metodoT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("met_metodo").isObject();
                    Met_metodoDAOGWT.this.met_metodoT = lerRegistroJson(registro);
                }
            }
        };
        this.met_metodoT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("met_metodoT.met_nr_id", met_metodoT.getMet_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Met_metodoTGWT met_metodoT) {
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
        param.put("met_metodoT.met_nr_id", met_metodoT.getMet_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Met_metodoTGWT met_metodoT) {
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
        param.put("met_metodoT.ope_nr_id", met_metodoT.getOpe_nr_id() + "");
        param.put("met_metodoT.met_nr_id", met_metodoT.getMet_nr_id() + "");
        param.put("met_metodoT.met_tx_metodo", met_metodoT.getMet_tx_metodo() + "");
        param.put("met_metodoT.met_tx_status", met_metodoT.getMet_tx_status() + "");
        param.put("met_metodoT.sis_nr_id", met_metodoT.getSis_nr_id() + "");
        param.put("met_metodoT.met_tx_descricao", met_metodoT.getMet_tx_descricao() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Met_metodoTGWT> lista = new ListStore<Met_metodoTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Met_metodoTGWT met_metodoT = lerRegistroJson(registro);
                lista.add(met_metodoT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Met_metodoTGWT lerRegistroJson(JSONObject registro) {
        Met_metodoTGWT met_metodoTGWT = new Met_metodoTGWT();
        Integer ope_nr_id = Integer.parseInt(registro.get("ope_nr_id").isString().stringValue());
        met_metodoTGWT.setOpe_nr_id(ope_nr_id);

        Integer met_nr_id = Integer.parseInt(registro.get("met_nr_id").isString().stringValue());
        met_metodoTGWT.setMet_nr_id(met_nr_id);

        String met_tx_metodo = registro.get("met_tx_metodo").isString().stringValue();
        met_metodoTGWT.setMet_tx_metodo(met_tx_metodo);

        String met_tx_status = registro.get("met_tx_status").isString().stringValue();
        met_metodoTGWT.setMet_tx_status(met_tx_status);

        Integer sis_nr_id = Integer.parseInt(registro.get("sis_nr_id").isString().stringValue());
        met_metodoTGWT.setSis_nr_id(sis_nr_id);

        String met_tx_descricao = registro.get("met_tx_descricao").isString().stringValue();
        met_metodoTGWT.setMet_tx_descricao(met_tx_descricao);


        return met_metodoTGWT;
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
     * @return the met_metodoT
     */
    public Met_metodoTGWT getMet_metodoT() {
        return met_metodoT;
    }

    /**
     * @param met_metodoTGWT the met_metodoTGWT to set
     */
    public void setMet_metodoTGWT(Met_metodoTGWT met_metodoT) {
        this.met_metodoT = met_metodoT;
    }
}
