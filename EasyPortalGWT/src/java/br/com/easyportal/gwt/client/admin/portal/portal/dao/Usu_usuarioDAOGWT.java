package br.com.easyportal.gwt.client.admin.portal.portal.dao;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Usu_usuarioDAOGWT implements IListenetResponse {

    public static final String PAGE_INSERIR = "portalgwt/usu_usuario/usu_usuarioInsertGWT.jsp";
    public static final String PAGE_ALTERAR_SENHA = "portalgwt/usu_usuario/resetSenha.jsp";
    public static final String PAGE_CONSULTAR = "portalgwt/usu_usuario/usu_usuarioConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portalgwt/usu_usuario/usu_usuarioUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private String msg;
    private ListStore list;
    private Usu_usuarioTGWT usu_usuarioT;

    public void consultarTodos() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    /*
     * Pesquisa os usuaŕios pelo login e/ou nome
     */
    public void consultarUsuarios(String tipo, String texto) {
        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consult");
        param.put("tipo", tipo);
        param.put("texto", texto);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void consultByNomeLogin(Usu_usuarioTGWT usuT) {
        list = null;
        msg = null;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consultByNomeLogin");
        param.put("usu_usuarioT.usu_tx_nome", usuT.getUsu_tx_nome());
        param.put("usu_usuarioT.usu_tx_login", usuT.getUsu_tx_login());
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULTAR, param);
    }

    public void alterarSenha(Usu_usuarioTGWT usu_usuarioT, String confSenha) {
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
        String url = Constantes.URL + PAGE_ALTERAR_SENHA;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "resetar");
        param.put("usu_usuarioT.usu_tx_senha", usu_usuarioT.getUsu_tx_senha());
        param.put("confsenha", confSenha);
        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }
    public void inserir(Usu_usuarioTGWT usu_usuarioT, String confSenha) {
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
        param.put("usu_usuarioT.usu_tx_nome", usu_usuarioT.getUsu_tx_nome());
        param.put("usu_usuarioT.usu_tx_login", usu_usuarioT.getUsu_tx_login());
        param.put("usu_usuarioT.usu_tx_senha", usu_usuarioT.getUsu_tx_senha());
        param.put("confsenha", confSenha);
        param.put("usu_usuarioT.usu_tx_status", usu_usuarioT.getUsu_tx_status());
        param.put("usu_usuarioT.usu_tx_email", usu_usuarioT.getUsu_tx_email());
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
        param.put("usu_usuarioT.usu_nr_id", usu_usuarioT.getUsu_nr_id() + "");
        param.put("usu_usuarioT.usu_tx_nome", usu_usuarioT.getUsu_tx_nome());
        param.put("usu_usuarioT.usu_tx_login", usu_usuarioT.getUsu_tx_login());
        param.put("usu_usuarioT.usu_tx_senha", usu_usuarioT.getUsu_tx_senha());
        param.put("usu_usuarioT.usu_tx_status", usu_usuarioT.getUsu_tx_status());
        param.put("usu_usuarioT.usu_tx_email", usu_usuarioT.getUsu_tx_email());


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
        Integer usu_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("usu_nr_id").toString()));
        usu_usuarioTGWT.setUsu_nr_id(usu_nr_id);

        String usu_tx_nome = EasyContainer.clearAspas(registro.get("usu_tx_nome").toString());
        usu_usuarioTGWT.setUsu_tx_nome(usu_tx_nome);

        String usu_tx_login = EasyContainer.clearAspas(registro.get("usu_tx_login").toString());
        usu_usuarioTGWT.setUsu_tx_login(usu_tx_login);

        String usu_tx_senha = EasyContainer.clearAspas(registro.get("usu_tx_senha").toString());
        usu_usuarioTGWT.setUsu_tx_senha(usu_tx_senha);

        String usu_tx_status = EasyContainer.clearAspas(registro.get("usu_tx_status").toString());
        usu_usuarioTGWT.setUsu_tx_status(usu_tx_status);

        String usu_tx_email = EasyContainer.clearAspas(registro.get("usu_tx_email").toString());
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
