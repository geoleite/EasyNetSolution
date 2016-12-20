package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Mep_men_perTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Mep_men_perDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/mep_men_per/mep_men_perInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/mep_men_per/mep_men_perConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/mep_men_per/mep_men_perUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Mep_men_perTGWT mep_men_perT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Mep_men_perTGWT mep_men_perT) {
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
        param.put("mep_men_perT.per_nr_id", mep_men_perT.getPer_nr_id() + "");
        param.put("mep_men_perT.men_nr_id", mep_men_perT.getMen_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Mep_men_perTGWT mep_men_perT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("mep_men_per").isObject();
                    Mep_men_perDAOGWT.this.mep_men_perT = lerRegistroJson(registro);
                }
            }
        };
        this.mep_men_perT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("mep_men_perT.per_nr_id", mep_men_perT.getPer_nr_id() + "");
        param.put("mep_men_perT.men_nr_id", mep_men_perT.getMen_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Mep_men_perTGWT mep_men_perT) {
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
        param.put("mep_men_perT.per_nr_id", mep_men_perT.getPer_nr_id() + "");
        param.put("mep_men_perT.men_nr_id", mep_men_perT.getMen_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Mep_men_perTGWT mep_men_perT) {
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
        param.put("mep_men_perT.per_nr_id", mep_men_perT.getPer_nr_id() + "");
        param.put("mep_men_perT.men_nr_id", mep_men_perT.getMen_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Mep_men_perTGWT> lista = new ListStore<Mep_men_perTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Mep_men_perTGWT mep_men_perT = lerRegistroJson(registro);
                lista.add(mep_men_perT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Mep_men_perTGWT lerRegistroJson(JSONObject registro) {
        Mep_men_perTGWT mep_men_perTGWT = new Mep_men_perTGWT();
        Integer per_nr_id = Integer.parseInt(registro.get("per_nr_id").isString().stringValue());
        mep_men_perTGWT.setPer_nr_id(per_nr_id);

        Integer men_nr_id = Integer.parseInt(registro.get("men_nr_id").isString().stringValue());
        mep_men_perTGWT.setMen_nr_id(men_nr_id);


        return mep_men_perTGWT;
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
     * @return the mep_men_perT
     */
    public Mep_men_perTGWT getMep_men_perT() {
        return mep_men_perT;
    }

    /**
     * @param mep_men_perTGWT the mep_men_perTGWT to set
     */
    public void setMep_men_perTGWT(Mep_men_perTGWT mep_men_perT) {
        this.mep_men_perT = mep_men_perT;
    }
}
