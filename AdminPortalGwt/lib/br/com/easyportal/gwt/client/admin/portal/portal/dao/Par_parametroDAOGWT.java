package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Par_parametroTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Par_parametroDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/par_parametro/par_parametroInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/par_parametro/parametroConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/par_parametro/par_parametroUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Par_parametroTGWT par_parametroT;
    private boolean finalized = false;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void consultBySistema(String nomeSistema) {
        list = null;
        msg = null;
        finalized = false;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultBySistema");
        param.put("nomeSistema", nomeSistema);
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMapNoMessage(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultBySistemaParametro(String nomeSistema, String parametro) {
        list = null;
        msg = null;
        finalized = false;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultBySistemaParametro");
        param.put("nomeSistema", nomeSistema);
        param.put("par_parametroT.par_tx_nome", parametro);

        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMapNoMessage(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void inserir(Par_parametroTGWT par_parametroT) {
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
        param.put("par_parametroT.sis_nr_id", par_parametroT.getSis_nr_id() + "");
        param.put("par_parametroT.par_tx_nome", par_parametroT.getPar_tx_nome() + "");
        param.put("par_parametroT.par_tx_valor", par_parametroT.getPar_tx_valor() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Par_parametroTGWT par_parametroT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("par_parametro").isObject();
                    Par_parametroDAOGWT.this.par_parametroT = lerRegistroJson(registro);
                }
            }
        };
        this.par_parametroT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("par_parametroT.sis_nr_id", par_parametroT.getSis_nr_id() + "");
        param.put("par_parametroT.par_tx_nome", par_parametroT.getPar_tx_nome() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void excluir(Par_parametroTGWT par_parametroT) {
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
        param.put("par_parametroT.sis_nr_id", par_parametroT.getSis_nr_id() + "");
        param.put("par_parametroT.par_tx_nome", par_parametroT.getPar_tx_nome() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Par_parametroTGWT par_parametroT) {
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
        param.put("par_parametroT.sis_nr_id", par_parametroT.getSis_nr_id() + "");
        param.put("par_parametroT.par_tx_nome", par_parametroT.getPar_tx_nome() + "");
        param.put("par_parametroT.par_tx_valor", par_parametroT.getPar_tx_valor() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Par_parametroTGWT> lista = new ListStore<Par_parametroTGWT>();
            try {
                for (int i = 1; i < resultado.size(); i++) {
                    JSONObject registro = resultado.get(i).isObject();
                    Par_parametroTGWT par_parametroT = lerRegistroJson(registro);
                    lista.add(par_parametroT);
                }
            } catch (Exception e) {
            }
            finalized = true;
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Par_parametroTGWT lerRegistroJson(JSONObject registro) {
        Par_parametroTGWT par_parametroTGWT = new Par_parametroTGWT();
        Integer sis_nr_id = Integer.parseInt(registro.get("sis_nr_id").isString().stringValue());
        par_parametroTGWT.setSis_nr_id(sis_nr_id);

        String par_tx_nome = registro.get("par_tx_nome").isString().stringValue();
        par_parametroTGWT.setPar_tx_nome(par_tx_nome);

        String par_tx_valor = registro.get("par_tx_valor").isString().stringValue();
        par_parametroTGWT.setPar_tx_valor(par_tx_valor);


        return par_parametroTGWT;
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
     * @return the par_parametroT
     */
    public Par_parametroTGWT getPar_parametroT() {
        return par_parametroT;
    }

    /**
     * @param par_parametroTGWT the par_parametroTGWT to set
     */
    public void setPar_parametroTGWT(Par_parametroTGWT par_parametroT) {
        this.par_parametroT = par_parametroT;
    }

    /**
     * @return the finalized
     */
    public boolean isFinalized() {
        return finalized;
    }

    /**
     * @param finalized the finalized to set
     */
    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
}
