package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Per_perfilDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/per_perfil/per_perfilInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/per_perfil/per_perfilConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/per_perfil/per_perfilUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Per_perfilTGWT per_perfilT;

    public void consultarTodos() {
        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
    }

    public void inserir(Per_perfilTGWT per_perfilT) {
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
        param.put("per_perfilT.per_tx_nome", per_perfilT.getPer_tx_nome() + "");
        param.put("per_perfilT.per_tx_status", per_perfilT.getPer_tx_status() + "");
        param.put("per_perfilT.per_tx_class", per_perfilT.getPer_tx_class() == null ? " " : per_perfilT.getPer_tx_class());

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Per_perfilTGWT per_perfilT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("per_perfil").isObject();
                    Per_perfilDAOGWT.this.per_perfilT = lerRegistroJson(registro);
                }
            }
        };
        this.per_perfilT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("per_perfilT.per_nr_id", per_perfilT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Per_perfilTGWT per_perfilT) {
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
        param.put("per_perfilT.per_nr_id", per_perfilT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Per_perfilTGWT per_perfilT) {
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
        param.put("per_perfilT.per_nr_id", per_perfilT.getPer_nr_id() + "");
        param.put("per_perfilT.per_tx_nome", per_perfilT.getPer_tx_nome() + "");
        param.put("per_perfilT.per_tx_status", per_perfilT.getPer_tx_status() + "");
        param.put("per_perfilT.per_tx_class", per_perfilT.getPer_tx_class() == null ? " " : per_perfilT.getPer_tx_class());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Per_perfilTGWT> lista = new ListStore<Per_perfilTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Per_perfilTGWT per_perfilT = lerRegistroJson(registro);
                lista.add(per_perfilT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Per_perfilTGWT lerRegistroJson(JSONObject registro) {
        Per_perfilTGWT per_perfilTGWT = new Per_perfilTGWT();
        Integer per_nr_id = Integer.parseInt(registro.get("per_nr_id").isString().stringValue());
        per_perfilTGWT.setPer_nr_id(per_nr_id);

        String per_tx_nome = registro.get("per_tx_nome").isString().stringValue();
        per_perfilTGWT.setPer_tx_nome(per_tx_nome);

        String per_tx_status = registro.get("per_tx_status").isString().stringValue();
        per_perfilTGWT.setPer_tx_status(per_tx_status);

        String per_tx_class = registro.get("per_tx_class").isString().stringValue();
        per_perfilTGWT.setPer_tx_class(per_tx_class);

        return per_perfilTGWT;
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
     * @return the per_perfilT
     */
    public Per_perfilTGWT getPer_perfilT() {
        return per_perfilT;
    }

    /**
     * @param per_perfilTGWT the per_perfilTGWT to set
     */
    public void setPer_perfilTGWT(Per_perfilTGWT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }
}
