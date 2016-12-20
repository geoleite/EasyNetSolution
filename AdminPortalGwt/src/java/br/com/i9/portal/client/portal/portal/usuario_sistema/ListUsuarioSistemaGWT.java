/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.usuario_sistema;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usuario_sistemaTGWT;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author I9
 */
public class ListUsuarioSistemaGWT implements IListenetResponse {

    public static final String PAGE = "i9factory/factory/usuario_sistema/usuario_sistemaConsultGWT.jsp";
    private List<Usuario_sistemaTGWT> list;
    public boolean preechido;

    public void povoaUsuarioVinculado(Per_perfilTGWT per_perfilTGWT) {
        preechido = false;
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "ususriosPerfil");
        param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
        eaurl.accessJSonMap(Constantes.URL + PAGE, param);
    }

    public void povoaUsuarioNaoVinculado(Per_perfilTGWT per_perfilTGWT) {
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("op", "usuariosNaoPerfil");
            param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);
    }

    public void read(JSONValue jsonValue) {
        try {
            JSONObject jsonObject;
            setList((List<Usuario_sistemaTGWT>) new Vector());
            if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                JSONArray resultado = jsonObject.get("resultado").isArray();
                for (int i = 1; i < resultado.size(); i++) {
                    Usuario_sistemaTGWT obj = new Usuario_sistemaTGWT();
                    JSONObject registro = resultado.get(i).isObject();
                    Integer id = Integer.parseInt(EasyContainer.clearAspas(registro.get("usu_nr_id").toString()));
                    obj.setUsu_nr_id(id);
                    String dispaly = EasyContainer.clearAspas(registro.get("usu_tx_nome").toString());
                    obj.setUsu_tx_nome(dispaly);
                    getList().add(obj);
                    //com.google.gwt.user.client.Window.alert(dispaly);
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
    public List<Usuario_sistemaTGWT> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Usuario_sistemaTGWT> list) {
        this.list = list;
    }
}
