package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Pi_per_intTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Pi_per_intDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/pi_per_int/pi_per_intInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/pi_per_int/pi_per_intConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/pi_per_int/pi_per_intUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Pi_per_intTGWT pi_per_intT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Pi_per_intTGWT pi_per_intT) {
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
        param.put("pi_per_intT.int_nr_id", pi_per_intT.getInt_nr_id() + "");
        param.put("pi_per_intT.per_nr_id", pi_per_intT.getPer_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Pi_per_intTGWT pi_per_intT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("pi_per_int").isObject();
                    Pi_per_intDAOGWT.this.pi_per_intT = lerRegistroJson(registro);
                }
            }
        };
        this.pi_per_intT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("pi_per_intT.int_nr_id", pi_per_intT.getInt_nr_id() + "");
        param.put("pi_per_intT.per_nr_id", pi_per_intT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Pi_per_intTGWT pi_per_intT) {
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
        param.put("pi_per_intT.int_nr_id", pi_per_intT.getInt_nr_id() + "");
        param.put("pi_per_intT.per_nr_id", pi_per_intT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Pi_per_intTGWT pi_per_intT) {
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
        param.put("pi_per_intT.int_nr_id", pi_per_intT.getInt_nr_id() + "");
        param.put("pi_per_intT.per_nr_id", pi_per_intT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Pi_per_intTGWT> lista = new ListStore<Pi_per_intTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Pi_per_intTGWT pi_per_intT = lerRegistroJson(registro);
                lista.add(pi_per_intT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Pi_per_intTGWT lerRegistroJson(JSONObject registro) {
        Pi_per_intTGWT pi_per_intTGWT = new Pi_per_intTGWT();
        Integer int_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("int_nr_id").toString()));
        pi_per_intTGWT.setInt_nr_id(int_nr_id);

        Integer per_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("per_nr_id").toString()));
        pi_per_intTGWT.setPer_nr_id(per_nr_id);


        return pi_per_intTGWT;
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
     * @return the pi_per_intT
     */
    public Pi_per_intTGWT getPi_per_intT() {
        return pi_per_intT;
    }

    /**
     * @param pi_per_intTGWT the pi_per_intTGWT to set
     */
    public void setPi_per_intTGWT(Pi_per_intTGWT pi_per_intT) {
        this.pi_per_intT = pi_per_intT;
    }
}
