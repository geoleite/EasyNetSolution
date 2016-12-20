/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.met_metodo;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;

import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author I9
 */
public class ListMetodoGWT implements IListenetResponse {

    public static final String PAGE = "i9factory/factory/met_metodo/met_metodoConsultGWT.jsp"; 
    private List<Met_metodoTGWT> list;
    public boolean preechido;
    public boolean allPerfilSistema;
    private final int ID_SISTEMA = 54;

    public void povoaMetodosVinculado(Per_perfilTGWT per_perfilTGWT, Ope_operacaoTGWT ope_operacaoTGWT) {
        try {
            allPerfilSistema = false;
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("op", "consultMetodosPerfil");
            param.put("per_pefilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            param.put("met_metodoT.ope_nr_id", ope_operacaoTGWT.getOpe_nr_id() + "");
            param.put("met_metodoT.sis_nr_id", ope_operacaoTGWT.getSis_nr_id() + "");
//            param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id()+"");
            //eaurl.accessJSonMapNoProgress(Constantes.URL + PAGE, param);
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);

        } catch (Exception ex) {
        }
    }

    public void povoaMetodosVinculadoPerfilSistema(Per_perfilTGWT per_perfilTGWT) {
        try {
            allPerfilSistema = false;
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("op", "consultMetodosPerfil");
            param.put("allSistema", "true");
            param.put("per_pefilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            param.put("met_metodoT.sis_nr_id", ID_SISTEMA+"");
            //eaurl.accessJSonMapNoProgress(Constantes.URL + PAGE, param);
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);
        } catch (Exception ex) {
        }
    }

    public void povoaMetodosNaoVinculado(Per_perfilTGWT per_perfilTGWT, Ope_operacaoTGWT ope_operacaoTGWT) {
        try {
            allPerfilSistema = true;
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();

            param.put("op", "consultMetodosNaoPerfil");
            param.put("per_pefilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            param.put("met_metodoT.ope_nr_id", ope_operacaoTGWT.getOpe_nr_id() + "");
            param.put("met_metodoT.sis_nr_id", ope_operacaoTGWT.getSis_nr_id() + "");
            //           param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id()+"");
            //eaurl.accessJSonMapNoProgress(Constantes.URL + PAGE, param);
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);

        } catch (Exception ex) {
        }


    }

    public void read(JSONValue jsonValue) {
        try {

            setList((List<Met_metodoTGWT>) new Vector());
            JSONObject jsonObject;
            if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                //Set<String> keys = jsonObject.keySet();
                JSONArray resultado = jsonObject.get("resultado").isArray();
                //Window.alert(resultado.toString());
                for (int i = 1; i < resultado.size(); i++) {
                    Met_metodoTGWT met_metodoTGWT = new Met_metodoTGWT();
                    JSONObject registro = resultado.get(i).isObject();
                    Integer ope_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("ope_nr_id").toString()));
                    met_metodoTGWT.setOpe_nr_id(ope_nr_id);

                    Integer met_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("met_nr_id").toString()));
                    met_metodoTGWT.setMet_nr_id(met_nr_id);

                    String met_tx_metodo = EasyContainer.clearAspas(registro.get("met_tx_metodo").toString());
                    met_metodoTGWT.setMet_tx_metodo(met_tx_metodo);

                    String met_tx_descricao = EasyContainer.clearAspas(registro.get("met_tx_descricao").toString());
                    met_metodoTGWT.setMet_tx_descricao(met_tx_descricao);

                    String met_tx_status = EasyContainer.clearAspas(registro.get("met_tx_status").toString());
                    met_metodoTGWT.setMet_tx_status(met_tx_status);

                    Integer sis_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("sis_nr_id").toString()));
                    met_metodoTGWT.setSis_nr_id(sis_nr_id);

                    getList().add(met_metodoTGWT);

                }
            }
        } catch (Exception e) {
        } finally {
            preechido = true;
        }

    }

    /**
     * @return the list
     */
    public List<Met_metodoTGWT> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Met_metodoTGWT> list) {
        this.list = list;
    }
}
