package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Pu_per_usuTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Pu_per_usuDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/pu_per_usu/pu_per_usuInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/pu_per_usu/pu_per_usuConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/pu_per_usu/pu_per_usuUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Pu_per_usuTGWT pu_per_usuT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Pu_per_usuTGWT pu_per_usuT) {
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
        param.put("pu_per_usuT.per_nr_id", pu_per_usuT.getPer_nr_id() + "");
        param.put("pu_per_usuT.usu_nr_id", pu_per_usuT.getUsu_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Pu_per_usuTGWT pu_per_usuT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("pu_per_usu").isObject();
                    Pu_per_usuDAOGWT.this.pu_per_usuT = lerRegistroJson(registro);
                }
            }
        };
        this.pu_per_usuT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("pu_per_usuT.per_nr_id", pu_per_usuT.getPer_nr_id() + "");
        param.put("pu_per_usuT.usu_nr_id", pu_per_usuT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Pu_per_usuTGWT pu_per_usuT) {
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
        param.put("pu_per_usuT.per_nr_id", pu_per_usuT.getPer_nr_id() + "");
        param.put("pu_per_usuT.usu_nr_id", pu_per_usuT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Pu_per_usuTGWT pu_per_usuT) {
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
        param.put("pu_per_usuT.per_nr_id", pu_per_usuT.getPer_nr_id() + "");
        param.put("pu_per_usuT.usu_nr_id", pu_per_usuT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Pu_per_usuTGWT> lista = new ListStore<Pu_per_usuTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Pu_per_usuTGWT pu_per_usuT = lerRegistroJson(registro);
                lista.add(pu_per_usuT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Pu_per_usuTGWT lerRegistroJson(JSONObject registro) {
        Pu_per_usuTGWT pu_per_usuTGWT = new Pu_per_usuTGWT();
        Integer per_nr_id = Integer.parseInt(registro.get("per_nr_id").isString().stringValue());
        pu_per_usuTGWT.setPer_nr_id(per_nr_id);

        Integer usu_nr_id = Integer.parseInt(registro.get("usu_nr_id").isString().stringValue());
        pu_per_usuTGWT.setUsu_nr_id(usu_nr_id);


        return pu_per_usuTGWT;
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
     * @return the pu_per_usuT
     */
    public Pu_per_usuTGWT getPu_per_usuT() {
        return pu_per_usuT;
    }

    /**
     * @param pu_per_usuTGWT the pu_per_usuTGWT to set
     */
    public void setPu_per_usuTGWT(Pu_per_usuTGWT pu_per_usuT) {
        this.pu_per_usuT = pu_per_usuT;
    }
}
