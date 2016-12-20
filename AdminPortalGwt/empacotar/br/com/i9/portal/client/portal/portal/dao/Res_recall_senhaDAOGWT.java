package br.com.i9.portal.client.portal.portal.dao;
import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.portal.portal.transfer.Res_recall_senhaTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Res_recall_senhaDAOGWT implements IListenetResponse {
    public static final String PAGE_INSERT = "portalgwt/portal/res_recall_senha/res_recall_senhaInsertGWT.jsp";
    public static final String PAGE_CONSULT = "portalgwt/portal/res_recall_senha/res_recall_senhaConsultGWT.jsp";
    public static final String PAGE_REPORT = "portalgwt/portal/res_recall_senha/res_recall_senhaReport.jsp";
    public static final String PAGE_UPDATE_DELETE = "portalgwt/portal/res_recall_senha/res_recall_senhaUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;	
    private ListStore list;
    private Res_recall_senhaTGWT res_recall_senhaT;
    private boolean finalized=false;

    public void getAll() {        
        list = null;
        msg = null;
	finalized=false;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "consult");
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(Constantes.URL + PAGE_CONSULT, param);
    }

    public void insert(Res_recall_senhaTGWT res_recall_senhaT) {
        msg = null;
        list = null;
        finalized=false;
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    String result = jsonObject.get("resultado").isString().stringValue();
                    msg = result;
                    finalized=true;
                }
            }
        };
        String url = Constantes.URL + PAGE_INSERT;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "insert");
	param.put("res_recall_senhaT.usu_nr_id" , res_recall_senhaT.getUsu_nr_id() + "");
param.put("res_recall_senhaT.res_tx_pergunta" , res_recall_senhaT.getRes_tx_pergunta() + "");
param.put("res_recall_senhaT.res_tx_resposta" , res_recall_senhaT.getRes_tx_resposta() + "");
param.put("res_recall_senhaT.res_dt_ultimoacesso" , dtfDateTime.format(res_recall_senhaT.getRes_dt_ultimoacesso() ));
param.put("res_recall_senhaT.res_nr_tentativas" , res_recall_senhaT.getRes_nr_tentativas() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void findById(Res_recall_senhaTGWT res_recall_senhaT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject record = result.get("res_recall_senha").isObject();
                    Res_recall_senhaDAOGWT.this.res_recall_senhaT = readJsonRecord(record);
		    finalized=true;
                }
            }
        };
	this.res_recall_senhaT = null;
        list = null;
        msg = null;
	finalized=false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("res_recall_senhaT.usu_nr_id" , res_recall_senhaT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }
    public void delete(Res_recall_senhaTGWT res_recall_senhaT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();	
                    finalized=true;		
                }
            }
        };

        list = null;
        msg = null;
	finalized=false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "delete");
        param.put("res_recall_senhaT.usu_nr_id" , res_recall_senhaT.getUsu_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    /**
     * Limpa o contador de tentativas de acesso do usuário
     * @param res_recall_senhaT
     */
    public void zerarContadorTentativas(Res_recall_senhaTGWT res_recall_senhaT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    finalized=true;
                }
            }
        };

        list = null;
        msg = null;
	finalized=false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "zerarContatorTentativas");
        param.put("res_recall_senhaT.usu_nr_id" , res_recall_senhaT.getUsu_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void update(Res_recall_senhaTGWT res_recall_senhaT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    finalized=true;
                }
            }
        };
        msg = null;
        list = null;
	finalized=false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
	param.put("res_recall_senhaT.usu_nr_id" , res_recall_senhaT.getUsu_nr_id() + "");
param.put("res_recall_senhaT.res_tx_pergunta" , res_recall_senhaT.getRes_tx_pergunta() + "");
param.put("res_recall_senhaT.res_tx_resposta" , res_recall_senhaT.getRes_tx_resposta() + "");
param.put("res_recall_senhaT.res_dt_ultimoacesso" ,   dtfDateTime.format(res_recall_senhaT.getRes_dt_ultimoacesso() ));
param.put("res_recall_senhaT.res_nr_tentativas" , res_recall_senhaT.getRes_nr_tentativas() + "");

        
        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }
    
    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Res_recall_senhaTGWT> lista = new ListStore<Res_recall_senhaTGWT>();            

            for (int i = 1; i < resultado.size(); i++) {		
                JSONObject record = resultado.get(i).isObject();
	        Res_recall_senhaTGWT res_recall_senhaT = readJsonRecord(record);
                lista.add(res_recall_senhaT);            
            }
	    list = lista;
	    finalized=true;
        }
    }

    /**
     * Read the contents json data 
     */	
    private Res_recall_senhaTGWT readJsonRecord(JSONObject registro) {
	Res_recall_senhaTGWT res_recall_senhaTGWT = new Res_recall_senhaTGWT();
			                
		Integer usu_nr_id=Integer.parseInt(registro.get("usu_nr_id").isString().stringValue());
                res_recall_senhaTGWT.setUsu_nr_id(usu_nr_id);
		

		                
		String res_tx_pergunta= registro.get("res_tx_pergunta").isString().stringValue();
                res_recall_senhaTGWT.setRes_tx_pergunta(res_tx_pergunta);
		

		                
		String res_tx_resposta= registro.get("res_tx_resposta").isString().stringValue();
                res_recall_senhaTGWT.setRes_tx_resposta(res_tx_resposta);
		

		String strres_dt_ultimoacesso=registro.get("res_dt_ultimoacesso").isString().stringValue();
		if (strres_dt_ultimoacesso != null && strres_dt_ultimoacesso.trim().length() > 0 ) {
                
		  Date res_dt_ultimoacesso=dtfDateTime.parse(strres_dt_ultimoacesso);
                res_recall_senhaTGWT.setRes_dt_ultimoacesso(res_dt_ultimoacesso);
		}


		                
		Integer res_nr_tentativas=Integer.parseInt(registro.get("res_nr_tentativas").isString().stringValue());
                res_recall_senhaTGWT.setRes_nr_tentativas(res_nr_tentativas);
		


	return res_recall_senhaTGWT;
    }

    public boolean isFinalized() {
      return finalized;
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
     * @return the res_recall_senhaT
     */
    public Res_recall_senhaTGWT getRes_recall_senhaT() {
        return res_recall_senhaT;
    }

    /**
     * @param res_recall_senhaTGWT the res_recall_senhaTGWT to set
     */
    public void setRes_recall_senhaTGWT(Res_recall_senhaTGWT res_recall_senhaT) {
        this.res_recall_senhaT = res_recall_senhaT;
    }
}
