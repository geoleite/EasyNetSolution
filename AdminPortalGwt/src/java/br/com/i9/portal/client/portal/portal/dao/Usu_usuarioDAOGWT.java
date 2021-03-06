package br.com.i9.portal.client.portal.portal.dao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

import java.util.*;

public class Usu_usuarioDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/portal/usu_usuario/usu_usuarioInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/portal/usu_usuario/usu_usuarioConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/portal/usu_usuario/usu_usuarioUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;
    private ListStore list;
    private Usu_usuarioTGWT usu_usuarioT;

    public void consultarTodos() {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consult");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByNome(Usu_usuarioTGWT usuT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByNome");
        param.put("usu_usuarioT.usu_tx_nome", usuT.getUsu_tx_nome() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByPerfil(Per_perfilTGWT perT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByPerfil");
        param.put("per_perfilT.per_nr_id", perT.getPer_nr_id() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByNotPerfil(Per_perfilTGWT perT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByNotPerfil");
        param.put("per_perfilT.per_nr_id", perT.getPer_nr_id() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByLogin(Usu_usuarioTGWT usuT) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByLogin");
        param.put("usu_usuarioT.usu_tx_nome", usuT.getUsu_tx_nome() + "");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByNomeLogin(String texto) {
        list = null;
        msg = null;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByNomeLogin");
        param.put("texto", texto);
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }


    public void inserir(Usu_usuarioTGWT usu_usuarioT) {
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
        param.put("usu_usuarioT.usu_tx_nome", usu_usuarioT.getUsu_tx_nome() + "");
        param.put("usu_usuarioT.usu_tx_login", usu_usuarioT.getUsu_tx_login() + "");
        param.put("usu_usuarioT.usu_tx_senha", usu_usuarioT.getUsu_tx_senha() + "");
        param.put("usu_usuarioT.usu_tx_status", usu_usuarioT.getUsu_tx_status() + "");
        param.put("usu_usuarioT.usu_tx_trocarsenha", usu_usuarioT.getUsu_tx_trocarsenha() + "");
        param.put("usu_usuarioT.usu_tx_email", usu_usuarioT.getUsu_tx_email() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Usu_usuarioTGWT usu_usuarioT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("usu_usuario").isObject();
                    Usu_usuarioDAOGWT.this.usu_usuarioT = lerRegistroJson(registro);
                }
            }
        };
        this.usu_usuarioT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }

    public void excluir(Usu_usuarioTGWT usu_usuarioT) {
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
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void gerarNovaSenha(Usu_usuarioTGWT usu_usuarioT) {
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
        param.put("op", "gerarNovaSenha");
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }


    public void alterar(Usu_usuarioTGWT usu_usuarioT) {
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
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");
        param.put("usu_usuarioT.usu_tx_nome", usu_usuarioT.getUsu_tx_nome() + "");
        param.put("usu_usuarioT.usu_tx_login", usu_usuarioT.getUsu_tx_login() + "");
        param.put("usu_usuarioT.usu_tx_status", usu_usuarioT.getUsu_tx_status() + "");
        param.put("usu_usuarioT.usu_tx_trocarsenha", usu_usuarioT.getUsu_tx_trocarsenha() + "");
        param.put("usu_usuarioT.usu_tx_email", usu_usuarioT.getUsu_tx_email() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterarSenha(Usu_usuarioTGWT usu_usuarioT) {
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
        param.put("op", "updateSenha");
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");
        param.put("usu_usuarioT.usu_tx_senha", usu_usuarioT.getUsu_tx_senha() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Usu_usuarioTGWT> lista = new ListStore<Usu_usuarioTGWT>();

            for (int i = 1; i < resultado.size(); i++) {
                JSONObject registro = resultado.get(i).isObject();
                Usu_usuarioTGWT usu_usuarioT = lerRegistroJson(registro);
                lista.add(usu_usuarioT);
            }
            list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */
    private Usu_usuarioTGWT lerRegistroJson(JSONObject registro) {
        Usu_usuarioTGWT usu_usuarioTGWT = new Usu_usuarioTGWT();

        Integer usu_nr_id = Integer.parseInt(registro.get("usu_nr_id").isString().stringValue());
        usu_usuarioTGWT.setUsu_nr_id(usu_nr_id);

        String usu_tx_nome = registro.get("usu_tx_nome").isString().stringValue();
        usu_usuarioTGWT.setUsu_tx_nome(usu_tx_nome);

        String usu_tx_login = registro.get("usu_tx_login").isString().stringValue();
        usu_usuarioTGWT.setUsu_tx_login(usu_tx_login);

        usu_usuarioTGWT.setUsu_tx_senha("");

        String usu_tx_status = registro.get("usu_tx_status").isString().stringValue();
        usu_usuarioTGWT.setUsu_tx_status(usu_tx_status);

        String usu_tx_trocarsenha = registro.get("usu_tx_trocarsenha").isString().stringValue();
        usu_usuarioTGWT.setUsu_tx_trocarsenha(usu_tx_trocarsenha);

        String usu_tx_email = registro.get("usu_tx_email").isString().stringValue();
        usu_usuarioTGWT.setUsu_tx_email(usu_tx_email);


        return usu_usuarioTGWT;
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
     * @return the usu_usuarioT
     */
    public Usu_usuarioTGWT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    /**
     * @param usu_usuarioTGWT the usu_usuarioTGWT to set
     */
    public void setUsu_usuarioTGWT(Usu_usuarioTGWT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }
}
