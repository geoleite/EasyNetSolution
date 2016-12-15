package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Int_interfaceTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Int_interfaceDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/int_interface/int_interfaceInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/int_interface/int_interfaceConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/int_interface/int_interfaceUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Int_interfaceTGWT int_interfaceT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Int_interfaceTGWT int_interfaceT) {
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
        param.put("int_interfaceT.int_tx_nome", int_interfaceT.getInt_tx_nome());

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Int_interfaceTGWT int_interfaceT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("int_interface").isObject();
                    Int_interfaceDAOGWT.this.int_interfaceT = lerRegistroJson(registro);
                }
            }
        };
        this.int_interfaceT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("int_interfaceT.int_nr_id", int_interfaceT.getInt_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Int_interfaceTGWT int_interfaceT) {
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
        param.put("int_interfaceT.int_nr_id", int_interfaceT.getInt_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Int_interfaceTGWT int_interfaceT) {
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
        param.put("int_interfaceT.int_nr_id", int_interfaceT.getInt_nr_id() + "");
        param.put("int_interfaceT.int_tx_nome", int_interfaceT.getInt_tx_nome());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Int_interfaceTGWT> lista = new ListStore<Int_interfaceTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Int_interfaceTGWT int_interfaceT = lerRegistroJson(registro);
                lista.add(int_interfaceT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Int_interfaceTGWT lerRegistroJson(JSONObject registro) {
        Int_interfaceTGWT int_interfaceTGWT = new Int_interfaceTGWT();
        Integer int_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("int_nr_id").toString()));
        int_interfaceTGWT.setInt_nr_id(int_nr_id);

        String int_tx_nome = EasyContainer.clearAspas(registro.get("int_tx_nome").toString());
        int_interfaceTGWT.setInt_tx_nome(int_tx_nome);


        return int_interfaceTGWT;
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
     * @return the int_interfaceT
     */
    public Int_interfaceTGWT getInt_interfaceT() {
        return int_interfaceT;
    }

    /**
     * @param int_interfaceTGWT the int_interfaceTGWT to set
     */
    public void setInt_interfaceTGWT(Int_interfaceTGWT int_interfaceT) {
        this.int_interfaceT = int_interfaceT;
    }
}
