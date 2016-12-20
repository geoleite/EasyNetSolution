package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Sis_sistemaTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Sis_sistemaDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/sis_sistema/sis_sistemaInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/sis_sistema/sis_sistemaConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/sis_sistema/sis_sistemaUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private String msg;
    private ListStore list;
    private Sis_sistemaTGWT sis_sistemaT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }
    public void consultarAtivos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultAtivos");
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
        list = null;
        msg = null;
    }

    public void inserir(Sis_sistemaTGWT sis_sistemaT) {
        msg = null;
        list = null;
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    String result = jsonObject.get("resultado").toString();
                    msg = result;
                }
            }
        };
        String url = Constantes.URL + PAGE_INSERIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "insert");
        param.put("sis_sistemaT.sis_tx_nome", sis_sistemaT.getSis_tx_nome());
        param.put("sis_sistemaT.sis_tx_descricao", sis_sistemaT.getSis_tx_descricao());
        param.put("sis_sistemaT.sis_tx_status", sis_sistemaT.getSis_tx_status());

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Sis_sistemaTGWT sis_sistemaT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("sis_sistema").isObject();
                    Sis_sistemaDAOGWT.this.sis_sistemaT = lerRegistroJson(registro);
                }
            }
        };
        this.sis_sistemaT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("sis_sistemaT.sis_nr_id", sis_sistemaT.getSis_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Sis_sistemaTGWT sis_sistemaT) {
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
        param.put("sis_sistemaT.sis_nr_id", sis_sistemaT.getSis_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Sis_sistemaTGWT sis_sistemaT) {
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
        param.put("sis_sistemaT.sis_nr_id", sis_sistemaT.getSis_nr_id() + "");
        param.put("sis_sistemaT.sis_tx_nome", sis_sistemaT.getSis_tx_nome());
        param.put("sis_sistemaT.sis_tx_descricao", sis_sistemaT.getSis_tx_descricao());
        param.put("sis_sistemaT.sis_tx_status", sis_sistemaT.getSis_tx_status());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Sis_sistemaTGWT> lista = new ListStore<Sis_sistemaTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Sis_sistemaTGWT sis_sistemaT = lerRegistroJson(registro);
                lista.add(sis_sistemaT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Sis_sistemaTGWT lerRegistroJson(JSONObject registro) {
        Sis_sistemaTGWT sis_sistemaTGWT = new Sis_sistemaTGWT();
        Integer sis_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("sis_nr_id").toString()));
        sis_sistemaTGWT.setSis_nr_id(sis_nr_id);

        String sis_tx_nome = EasyContainer.clearAspas(registro.get("sis_tx_nome").toString());
        sis_sistemaTGWT.setSis_tx_nome(sis_tx_nome);

        String sis_tx_descricao = EasyContainer.clearAspas(registro.get("sis_tx_descricao").toString());
        sis_sistemaTGWT.setSis_tx_descricao(sis_tx_descricao);

        String sis_tx_status = EasyContainer.clearAspas(registro.get("sis_tx_status").toString());
        sis_sistemaTGWT.setSis_tx_status(sis_tx_status);


        return sis_sistemaTGWT;
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
     * @return the sis_sistemaT
     */
    public Sis_sistemaTGWT getSis_sistemaT() {
        return sis_sistemaT;
    }

    /**
     * @param sis_sistemaTGWT the sis_sistemaTGWT to set
     */
    public void setSis_sistemaTGWT(Sis_sistemaTGWT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }
}
