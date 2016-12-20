package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Mod_moduloTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Mod_moduloDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERT = "portalgwt/mod_moduloInsertGWT.jsp";
    public static final String PAGE_CONSULT = "portalgwt/mod_moduloConsultGWT.jsp";
    public static final String PAGE_UPDATE_DELETE = "portalgwt/mod_moduloUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Mod_moduloTGWT mod_moduloT;
    private boolean finalized = false;

    public void getAll() {
        list = null;
        msg = null;
        finalized = false;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consult");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMapNoMessage(Constantes.URL + PAGE_CONSULT, param);
    }

    public void getByPerfil() {
        list = null;
        msg = null;
        finalized = false;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByPerfil");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMapNoMessage(Constantes.URL + PAGE_CONSULT, param);
    }

    public void insert(Mod_moduloTGWT mod_moduloT) {
        msg = null;
        list = null;
        finalized = false;
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    String result = jsonObject.get("resultado").isString().stringValue();
                    msg = result;
                    finalized = true;
                }
            }
        };
        String url = Constantes.URL + PAGE_INSERT;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "insert");
        param.put("mod_moduloT.mod_nr_id", mod_moduloT.getMod_nr_id() + "");
        param.put("mod_moduloT.mod_tx_nome", mod_moduloT.getMod_tx_nome() + "");
        param.put("mod_moduloT.mod_tx_status", mod_moduloT.getMod_tx_status() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMapNoMessage(url, param);
    }

    public void findById(Mod_moduloTGWT mod_moduloT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject record = result.get("mod_modulo").isObject();
                    Mod_moduloDAOGWT.this.mod_moduloT = readJsonRecord(record);
                    finalized = true;
                }
            }
        };
        this.mod_moduloT = null;
        list = null;
        msg = null;
        finalized = false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("mod_moduloT.mod_nr_id", mod_moduloT.getMod_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMapNoMessage(url, param);

    }

    public void delete(Mod_moduloTGWT mod_moduloT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    finalized = true;
                }
            }
        };

        list = null;
        msg = null;
        finalized = false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "delete");
        param.put("mod_moduloT.mod_nr_id", mod_moduloT.getMod_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void update(Mod_moduloTGWT mod_moduloT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    finalized = true;
                }
            }
        };
        msg = null;
        list = null;
        finalized = false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
        param.put("mod_moduloT.mod_nr_id", mod_moduloT.getMod_nr_id() + "");
        param.put("mod_moduloT.mod_tx_nome", mod_moduloT.getMod_tx_nome() + "");
        param.put("mod_moduloT.mod_tx_status", mod_moduloT.getMod_tx_status() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Mod_moduloTGWT> lista = new ListStore<Mod_moduloTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject record = resultado.get(i).isObject();
                Mod_moduloTGWT mod_moduloT = readJsonRecord(record);
                lista.add(mod_moduloT);
            }
            list = lista;
            finalized = true;
        }
    }

    /**
     * Read the contents json data 
     */
    private Mod_moduloTGWT readJsonRecord(JSONObject registro) {
        Mod_moduloTGWT mod_moduloTGWT = new Mod_moduloTGWT();

        Integer mod_nr_id = Integer.parseInt(registro.get("mod_nr_id").isString().stringValue());
        mod_moduloTGWT.setMod_nr_id(mod_nr_id);

        String mod_tx_nome = registro.get("mod_tx_nome").isString().stringValue();
        mod_moduloTGWT.setMod_tx_nome(mod_tx_nome);

        String mod_tx_status = registro.get("mod_tx_status").isString().stringValue();
        mod_moduloTGWT.setMod_tx_status(mod_tx_status);

        Integer mod_nr_ordem = Integer.parseInt(registro.get("mod_nr_ordem").isString().stringValue());
        mod_moduloTGWT.setMod_nr_ordem(mod_nr_ordem);

        String mod_tx_acao = registro.get("mod_tx_acao").isString().stringValue();
        mod_moduloTGWT.setMod_tx_acao(mod_tx_acao);

        String mod_tx_icone = registro.get("mod_tx_icone").isString().stringValue();
        mod_moduloTGWT.setMod_tx_icone(mod_tx_icone);

        String mod_tx_autologin = registro.get("mod_tx_autologin").isString().stringValue();
        mod_moduloTGWT.setMod_tx_autologin(mod_tx_autologin);

        String mod_tx_urllogin = registro.get("mod_tx_urllogin").isString().stringValue();
        mod_moduloTGWT.setMod_tx_urllogin(mod_tx_urllogin);
        return mod_moduloTGWT;
    }

    public boolean isFinalized() {
        return finalized;
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
     * @return the mod_moduloT
     */
    public Mod_moduloTGWT getMod_moduloT() {
        return mod_moduloT;
    }

    /**
     * @param mod_moduloTGWT the mod_moduloTGWT to set
     */
    public void setMod_moduloTGWT(Mod_moduloTGWT mod_moduloT) {
        this.mod_moduloT = mod_moduloT;
    }
}
