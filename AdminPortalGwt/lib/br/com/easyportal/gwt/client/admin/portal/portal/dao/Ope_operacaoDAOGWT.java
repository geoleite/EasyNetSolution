package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Sis_sistemaTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Ope_operacaoDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/ope_operacao/ope_operacaoInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/ope_operacao/ope_operacaoConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/ope_operacao/ope_operacaoUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private String msg;
    private ListStore list;
    private Ope_operacaoTGWT ope_operacaoT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    /**
     * COnsulta as operacoes por sistemas
     * @param sisT
     */
    public void consultarPorSistema(Sis_sistemaTGWT sisT) {
        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        //param.put("op", "consult");
        param.put("ope_operacaoT.sis_nr_id", sisT.getSis_nr_id() + "");
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void inserir(Ope_operacaoTGWT ope_operacaoT) {
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
        Info.display("DEBUG", "teste1");
        param.put("ope_operacaoT.sis_nr_id", ope_operacaoT.getSis_nr_id() + "");
        Info.display("DEBUG", "teste2");
        param.put("ope_operacaoT.ope_tx_nome", ope_operacaoT.getOpe_tx_nome());
        Info.display("DEBUG", "teste3");
        param.put("ope_operacaoT.ope_tx_status", ope_operacaoT.getOpe_tx_status());
        Info.display("DEBUG", "teste4");
        param.put("ope_operacaoT.ope_tx_url", ope_operacaoT.getOpe_tx_url());
        Info.display("DEBUG", "teste5");
        param.put("ope_operacaoT.ope_tx_descricao", ope_operacaoT.getOpe_tx_descricao());
        Info.display("DEBUG", "teste6");
        param.put("ope_operacaoT.ope_tx_classe", ope_operacaoT.getOpe_tx_classe());
        Info.display("DEBUG", "teste7");
        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Ope_operacaoTGWT ope_operacaoT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("ope_operacao").isObject();
                    Ope_operacaoDAOGWT.this.ope_operacaoT = lerRegistroJson(registro);
                }
            }
        };
        this.ope_operacaoT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("ope_operacaoT.ope_nr_id", ope_operacaoT.getOpe_nr_id() + "");
        param.put("ope_operacaoT.sis_nr_id", ope_operacaoT.getSis_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Ope_operacaoTGWT ope_operacaoT) {
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
        param.put("ope_operacaoT.ope_nr_id", ope_operacaoT.getOpe_nr_id() + "");
        param.put("ope_operacaoT.sis_nr_id", ope_operacaoT.getSis_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Ope_operacaoTGWT ope_operacaoT) {
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
        param.put("ope_operacaoT.ope_nr_id", ope_operacaoT.getOpe_nr_id() + "");
        param.put("ope_operacaoT.sis_nr_id", ope_operacaoT.getSis_nr_id() + "");
        param.put("ope_operacaoT.ope_tx_nome", ope_operacaoT.getOpe_tx_nome());
        param.put("ope_operacaoT.ope_tx_status", ope_operacaoT.getOpe_tx_status());
        param.put("ope_operacaoT.ope_tx_url", ope_operacaoT.getOpe_tx_url());
        param.put("ope_operacaoT.ope_tx_descricao", ope_operacaoT.getOpe_tx_descricao());
        param.put("ope_operacaoT.ope_tx_classe", ope_operacaoT.getOpe_tx_classe());


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Ope_operacaoTGWT> lista = new ListStore<Ope_operacaoTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Ope_operacaoTGWT ope_operacaoT = lerRegistroJson(registro);
                lista.add(ope_operacaoT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Ope_operacaoTGWT lerRegistroJson(JSONObject registro) {
        Ope_operacaoTGWT ope_operacaoTGWT = new Ope_operacaoTGWT();
        Integer ope_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("ope_nr_id").toString()));
        ope_operacaoTGWT.setOpe_nr_id(ope_nr_id);

        Integer sis_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("sis_nr_id").toString()));
        ope_operacaoTGWT.setSis_nr_id(sis_nr_id);

        String ope_tx_nome = EasyContainer.clearAspas(registro.get("ope_tx_nome").toString());
        ope_operacaoTGWT.setOpe_tx_nome(ope_tx_nome);

        String ope_tx_status = EasyContainer.clearAspas(registro.get("ope_tx_status").toString());
        ope_operacaoTGWT.setOpe_tx_status(ope_tx_status);

        String ope_tx_url = EasyContainer.clearAspas(registro.get("ope_tx_url").toString());
        ope_operacaoTGWT.setOpe_tx_url(ope_tx_url);

        String ope_tx_descricao = EasyContainer.clearAspas(registro.get("ope_tx_descricao").toString());
        ope_operacaoTGWT.setOpe_tx_descricao(ope_tx_descricao);

        String ope_tx_classe = EasyContainer.clearAspas(registro.get("ope_tx_classe").toString());
        ope_operacaoTGWT.setOpe_tx_classe(ope_tx_classe);


        return ope_operacaoTGWT;
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
     * @return the ope_operacaoT
     */
    public Ope_operacaoTGWT getOpe_operacaoT() {
        return ope_operacaoT;
    }

    /**
     * @param ope_operacaoTGWT the ope_operacaoTGWT to set
     */
    public void setOpe_operacaoTGWT(Ope_operacaoTGWT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
    }
}
