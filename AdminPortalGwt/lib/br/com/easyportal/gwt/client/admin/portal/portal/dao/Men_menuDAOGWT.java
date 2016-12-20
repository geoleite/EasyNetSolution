package br.com.easyportal.gwt.client.admin.portal.portal.dao;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Men_menuTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Men_menuDAOGWT implements IListenetResponse {
    public static final String PAGE_INSERIR = "portal/portal/men_menu/men_menuInsertGWT.jsp";
    public static final String PAGE_CONSULTAR = "portal/portal/men_menu/men_menuConsultGWT.jsp";
    public static final String PAGE_ALTERAR_EXCLUIR = "portal/portal/men_menu/men_menuUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private String msg;	
    private ListStore list;
    private Men_menuTGWT men_menuT;	
    public void consultarTodos() {        
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE_CONSULTAR);
        list = null;
        msg = null;
    }

    public void inserir(Men_menuTGWT men_menuT) {
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
	param.put("men_menuT.supermenu_nr_id" , men_menuT.getSupermenu_nr_id() + "");
param.put("men_menuT.men_tx_status" , men_menuT.getMen_tx_status());
param.put("men_menuT.men_tx_nome" , men_menuT.getMen_tx_nome());
param.put("men_menuT.men_nr_ordem" , men_menuT.getMen_nr_ordem() + "");
param.put("men_menuT.men_tx_url" , men_menuT.getMen_tx_url());
param.put("men_menuT.sis_nr_id" , men_menuT.getSis_nr_id() + "");
param.put("men_menuT.men_tx_acao" , men_menuT.getMen_tx_acao());
param.put("men_menuT.men_tx_icone" , men_menuT.getMen_tx_icone());

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void pesquisar(Men_menuTGWT men_menuT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject registro = result.get("men_menu").isObject();
                    Men_menuDAOGWT.this.men_menuT = lerRegistroJson(registro);
                }
            }
        };
	this.men_menuT = null;
        list = null;
        msg = null;
        String url = Constantes.URL + PAGE_ALTERAR_EXCLUIR;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("men_menuT.men_nr_id" , men_menuT.getMen_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }
    public void excluir(Men_menuTGWT men_menuT) {
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
        param.put("men_menuT.men_nr_id" , men_menuT.getMen_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void alterar(Men_menuTGWT men_menuT) {
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
	param.put("men_menuT.men_nr_id" , men_menuT.getMen_nr_id() + "");
param.put("men_menuT.supermenu_nr_id" , men_menuT.getSupermenu_nr_id() + "");
param.put("men_menuT.men_tx_status" , men_menuT.getMen_tx_status());
param.put("men_menuT.men_tx_nome" , men_menuT.getMen_tx_nome());
param.put("men_menuT.men_nr_ordem" , men_menuT.getMen_nr_ordem() + "");
param.put("men_menuT.men_tx_url" , men_menuT.getMen_tx_url());
param.put("men_menuT.sis_nr_id" , men_menuT.getSis_nr_id() + "");
param.put("men_menuT.men_tx_acao" , men_menuT.getMen_tx_acao());
param.put("men_menuT.men_tx_icone" , men_menuT.getMen_tx_icone());

        
        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }
    
    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Men_menuTGWT> lista = new ListStore<Men_menuTGWT>();            

            for (int i = 1; i < resultado.size(); i++) {		
                JSONObject registro = resultado.get(i).isObject();
	        Men_menuTGWT men_menuT = lerRegistroJson(registro);
                lista.add(men_menuT);            
            }
	    list = lista;
        }
    }

    /**
     * Ler os dados o conte do json 
     */	
    private Men_menuTGWT lerRegistroJson(JSONObject registro) {
	Men_menuTGWT men_menuTGWT = new Men_menuTGWT();
	                Integer men_nr_id=Integer.parseInt(EasyContainer.clearAspas(registro.get("men_nr_id").toString()));
                men_menuTGWT.setMen_nr_id(men_nr_id);

                Integer supermenu_nr_id=Integer.parseInt(EasyContainer.clearAspas(registro.get("supermenu_nr_id").toString()));
                men_menuTGWT.setSupermenu_nr_id(supermenu_nr_id);

                String men_tx_status=EasyContainer.clearAspas(registro.get("men_tx_status").toString());
                men_menuTGWT.setMen_tx_status(men_tx_status);

                String men_tx_nome=EasyContainer.clearAspas(registro.get("men_tx_nome").toString());
                men_menuTGWT.setMen_tx_nome(men_tx_nome);

                Integer men_nr_ordem=Integer.parseInt(EasyContainer.clearAspas(registro.get("men_nr_ordem").toString()));
                men_menuTGWT.setMen_nr_ordem(men_nr_ordem);

                String men_tx_url=EasyContainer.clearAspas(registro.get("men_tx_url").toString());
                men_menuTGWT.setMen_tx_url(men_tx_url);

                Integer sis_nr_id=Integer.parseInt(EasyContainer.clearAspas(registro.get("sis_nr_id").toString()));
                men_menuTGWT.setSis_nr_id(sis_nr_id);

                String men_tx_acao=EasyContainer.clearAspas(registro.get("men_tx_acao").toString());
                men_menuTGWT.setMen_tx_acao(men_tx_acao);

                String men_tx_icone=EasyContainer.clearAspas(registro.get("men_tx_icone").toString());
                men_menuTGWT.setMen_tx_icone(men_tx_icone);


	return men_menuTGWT;
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
     * @return the men_menuT
     */
    public Men_menuTGWT getMen_menuT() {
        return men_menuT;
    }

    /**
     * @param men_menuTGWT the men_menuTGWT to set
     */
    public void setMen_menuTGWT(Men_menuTGWT men_menuT) {
        this.men_menuT = men_menuT;
    }
}
