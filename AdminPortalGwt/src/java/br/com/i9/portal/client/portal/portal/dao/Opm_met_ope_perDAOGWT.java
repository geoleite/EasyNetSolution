package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Opm_met_ope_perTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Opm_met_ope_perDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/opm_met_ope_per/opm_met_ope_perInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/opm_met_ope_per/opm_met_ope_perConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/opm_met_ope_per/opm_met_ope_perUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Opm_met_ope_perTGWT opm_met_ope_perT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Opm_met_ope_perTGWT opm_met_ope_perT) {
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
        param.put("opm_met_ope_perT.per_nr_id", opm_met_ope_perT.getPer_nr_id() + "");
        param.put("opm_met_ope_perT.ope_nr_id", opm_met_ope_perT.getOpe_nr_id() + "");
        param.put("opm_met_ope_perT.sis_nr_id", opm_met_ope_perT.getSis_nr_id() + "");
        param.put("opm_met_ope_perT.met_nr_id", opm_met_ope_perT.getMet_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Opm_met_ope_perTGWT opm_met_ope_perT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("opm_met_ope_per").isObject();
                    Opm_met_ope_perDAOGWT.this.opm_met_ope_perT = lerRegistroJson(registro);
                }
            }
        };
        this.opm_met_ope_perT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("opm_met_ope_perT.per_nr_id", opm_met_ope_perT.getPer_nr_id() + "");
        param.put("opm_met_ope_perT.ope_nr_id", opm_met_ope_perT.getOpe_nr_id() + "");
        param.put("opm_met_ope_perT.sis_nr_id", opm_met_ope_perT.getSis_nr_id() + "");
        param.put("opm_met_ope_perT.met_nr_id", opm_met_ope_perT.getMet_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Opm_met_ope_perTGWT opm_met_ope_perT) {
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
        param.put("opm_met_ope_perT.per_nr_id", opm_met_ope_perT.getPer_nr_id() + "");
        param.put("opm_met_ope_perT.ope_nr_id", opm_met_ope_perT.getOpe_nr_id() + "");
        param.put("opm_met_ope_perT.sis_nr_id", opm_met_ope_perT.getSis_nr_id() + "");
        param.put("opm_met_ope_perT.met_nr_id", opm_met_ope_perT.getMet_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Opm_met_ope_perTGWT opm_met_ope_perT) {
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
        param.put("opm_met_ope_perT.per_nr_id", opm_met_ope_perT.getPer_nr_id() + "");
        param.put("opm_met_ope_perT.ope_nr_id", opm_met_ope_perT.getOpe_nr_id() + "");
        param.put("opm_met_ope_perT.sis_nr_id", opm_met_ope_perT.getSis_nr_id() + "");
        param.put("opm_met_ope_perT.met_nr_id", opm_met_ope_perT.getMet_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Opm_met_ope_perTGWT> lista = new ListStore<Opm_met_ope_perTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Opm_met_ope_perTGWT opm_met_ope_perT = lerRegistroJson(registro);
                lista.add(opm_met_ope_perT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Opm_met_ope_perTGWT lerRegistroJson(JSONObject registro) {
        Opm_met_ope_perTGWT opm_met_ope_perTGWT = new Opm_met_ope_perTGWT();
        Integer per_nr_id = Integer.parseInt(registro.get("per_nr_id").isString().stringValue());
        opm_met_ope_perTGWT.setPer_nr_id(per_nr_id);

        Integer ope_nr_id = Integer.parseInt(registro.get("ope_nr_id").isString().stringValue());
        opm_met_ope_perTGWT.setOpe_nr_id(ope_nr_id);

        Integer sis_nr_id = Integer.parseInt(registro.get("sis_nr_id").isString().stringValue());
        opm_met_ope_perTGWT.setSis_nr_id(sis_nr_id);

        Integer met_nr_id = Integer.parseInt(registro.get("met_nr_id").isString().stringValue());
        opm_met_ope_perTGWT.setMet_nr_id(met_nr_id);


        return opm_met_ope_perTGWT;
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
     * @return the opm_met_ope_perT
     */
    public Opm_met_ope_perTGWT getOpm_met_ope_perT() {
        return opm_met_ope_perT;
    }

    /**
     * @param opm_met_ope_perTGWT the opm_met_ope_perTGWT to set
     */
    public void setOpm_met_ope_perTGWT(Opm_met_ope_perTGWT opm_met_ope_perT) {
        this.opm_met_ope_perT = opm_met_ope_perT;
    }
}
