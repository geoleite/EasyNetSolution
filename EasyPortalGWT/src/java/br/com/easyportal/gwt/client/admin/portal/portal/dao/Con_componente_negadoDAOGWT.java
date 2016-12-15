package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Con_componente_negadoTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Con_componente_negadoDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/con_componente_negado/con_componente_negadoInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/con_componente_negado/con_componente_negadoConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/con_componente_negado/con_componente_negadoUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Con_componente_negadoTGWT con_componente_negadoT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void consultarInterfacePerfil(String classe) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultarInterfacePerfil");
        param.put("int_interfaceT.int_tx_nome", classe);
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMapNoMessage(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void inserir(Con_componente_negadoTGWT con_componente_negadoT) {
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
        param.put("con_componente_negadoT.int_nr_id", con_componente_negadoT.getInt_nr_id() + "");
        param.put("con_componente_negadoT.per_nr_id", con_componente_negadoT.getPer_nr_id() + "");
        param.put("con_componente_negadoT.con_tx_visivel", con_componente_negadoT.getCon_tx_visivel());
        param.put("con_componente_negadoT.con_tx_ativo", con_componente_negadoT.getCon_tx_ativo());

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Con_componente_negadoTGWT con_componente_negadoT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("con_componente_negado").isObject();
                    Con_componente_negadoDAOGWT.this.con_componente_negadoT = lerRegistroJson(registro);
                }
            }
        };
        this.con_componente_negadoT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("con_componente_negadoT.con_nr_id", con_componente_negadoT.getCon_nr_id() + "");
        param.put("con_componente_negadoT.int_nr_id", con_componente_negadoT.getInt_nr_id() + "");
        param.put("con_componente_negadoT.per_nr_id", con_componente_negadoT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Con_componente_negadoTGWT con_componente_negadoT) {
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
        param.put("con_componente_negadoT.con_nr_id", con_componente_negadoT.getCon_nr_id() + "");
        param.put("con_componente_negadoT.int_nr_id", con_componente_negadoT.getInt_nr_id() + "");
        param.put("con_componente_negadoT.per_nr_id", con_componente_negadoT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Con_componente_negadoTGWT con_componente_negadoT) {
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
        param.put("con_componente_negadoT.con_nr_id", con_componente_negadoT.getCon_nr_id() + "");
        param.put("con_componente_negadoT.int_nr_id", con_componente_negadoT.getInt_nr_id() + "");
        param.put("con_componente_negadoT.per_nr_id", con_componente_negadoT.getPer_nr_id() + "");
        param.put("con_componente_negadoT.con_tx_visivel", con_componente_negadoT.getCon_tx_visivel());
        param.put("con_componente_negadoT.con_tx_ativo", con_componente_negadoT.getCon_tx_ativo());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Con_componente_negadoTGWT> lista = new ListStore<Con_componente_negadoTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Con_componente_negadoTGWT con_componente_negadoT = lerRegistroJson(registro);
                lista.add(con_componente_negadoT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Con_componente_negadoTGWT lerRegistroJson(JSONObject registro) {
        Con_componente_negadoTGWT con_componente_negadoTGWT = new Con_componente_negadoTGWT();
        Integer con_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("con_nr_id").toString()));
        con_componente_negadoTGWT.setCon_nr_id(con_nr_id);

        Integer int_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("int_nr_id").toString()));
        con_componente_negadoTGWT.setInt_nr_id(int_nr_id);

        Integer per_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("per_nr_id").toString()));
        con_componente_negadoTGWT.setPer_nr_id(per_nr_id);

        String con_tx_visivel = EasyContainer.clearAspas(registro.get("con_tx_visivel").toString());
        con_componente_negadoTGWT.setCon_tx_visivel(con_tx_visivel);

        String con_tx_ativo = EasyContainer.clearAspas(registro.get("con_tx_ativo").toString());
        con_componente_negadoTGWT.setCon_tx_ativo(con_tx_ativo);

        String con_tx_nome = EasyContainer.clearAspas(registro.get("con_tx_nome").toString());
        con_componente_negadoTGWT.setCon_tx_nome(con_tx_nome);

        return con_componente_negadoTGWT;
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
     * @return the con_componente_negadoT
     */
    public Con_componente_negadoTGWT getCon_componente_negadoT() {
        return con_componente_negadoT;
    }

    /**
     * @param con_componente_negadoTGWT the con_componente_negadoTGWT to set
     */
    public void setCon_componente_negadoTGWT(Con_componente_negadoTGWT con_componente_negadoT) {
        this.con_componente_negadoT = con_componente_negadoT;
    }
}
