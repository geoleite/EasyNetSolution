package br.com.easyportal.gwt.client.admin.portal.portal.dao;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Mop_mod_perTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import java.util.*;

public class Mop_mod_perDAOGWT implements IListenetResponse {
    public static final String PAGE_INSERT = "portalgwt/portal/mop_mod_per/mop_mod_perInsertGWT.jsp";
    public static final String PAGE_CONSULT = "portalgwt/portal/mop_mod_per/mop_mod_perConsultGWT.jsp";
    public static final String PAGE_UPDATE_DELETE = "portalgwt/portal/mop_mod_per/mop_mod_perUpdateDeleteGWT.jsp";
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
    private String msg;	
    private ListStore list;
    private Mop_mod_perTGWT mop_mod_perT;
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

    public void insert(Mop_mod_perTGWT mop_mod_perT) {
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
	param.put("mop_mod_perT.mod_nr_id" , mop_mod_perT.getMod_nr_id() + "");
param.put("mop_mod_perT.per_nr_id" , mop_mod_perT.getPer_nr_id() + "");

        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void findById(Mop_mod_perTGWT mop_mod_perT) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

                    JSONObject result = jsonObject.get("resultado").isObject();
                    msg = result.get("msg").isString().stringValue();
                    JSONObject record = result.get("mop_mod_per").isObject();
                    Mop_mod_perDAOGWT.this.mop_mod_perT = readJsonRecord(record);
		    finalized=true;
                }
            }
        };
	this.mop_mod_perT = null;
        list = null;
        msg = null;
	finalized=false;
        String url = Constantes.URL + PAGE_UPDATE_DELETE;
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "findbyid");
        param.put("mop_mod_perT.mod_nr_id" , mop_mod_perT.getMod_nr_id() + "");
param.put("mop_mod_perT.per_nr_id" , mop_mod_perT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);

    }
    public void delete(Mop_mod_perTGWT mop_mod_perT) {
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
        param.put("mop_mod_perT.mod_nr_id" , mop_mod_perT.getMod_nr_id() + "");
param.put("mop_mod_perT.per_nr_id" , mop_mod_perT.getPer_nr_id() + "");


        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }

    public void update(Mop_mod_perTGWT mop_mod_perT) {
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
	param.put("mop_mod_perT.mod_nr_id" , mop_mod_perT.getMod_nr_id() + "");
param.put("mop_mod_perT.per_nr_id" , mop_mod_perT.getPer_nr_id() + "");

        
        EasyAccessURL eaurl = new EasyAccessURL(listener);
        eaurl.accessJSonMap(url, param);
    }
    
    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Mop_mod_perTGWT> lista = new ListStore<Mop_mod_perTGWT>();            

            for (int i = 1; i < resultado.size(); i++) {		
                JSONObject record = resultado.get(i).isObject();
	        Mop_mod_perTGWT mop_mod_perT = readJsonRecord(record);
                lista.add(mop_mod_perT);            
            }
	    list = lista;
	    finalized=true;
        }
    }

    /**
     * Read the contents json data 
     */	
    private Mop_mod_perTGWT readJsonRecord(JSONObject registro) {
	Mop_mod_perTGWT mop_mod_perTGWT = new Mop_mod_perTGWT();
			                
		Integer mod_nr_id=Integer.parseInt(registro.get("mod_nr_id").isString().stringValue());
                mop_mod_perTGWT.setMod_nr_id(mod_nr_id);
		

		                
		Integer per_nr_id=Integer.parseInt(registro.get("per_nr_id").isString().stringValue());
                mop_mod_perTGWT.setPer_nr_id(per_nr_id);
		


	return mop_mod_perTGWT;
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
     * @return the mop_mod_perT
     */
    public Mop_mod_perTGWT getMop_mod_perT() {
        return mop_mod_perT;
    }

    /**
     * @param mop_mod_perTGWT the mop_mod_perTGWT to set
     */
    public void setMop_mod_perTGWT(Mop_mod_perTGWT mop_mod_perT) {
        this.mop_mod_perT = mop_mod_perT;
    }
}
