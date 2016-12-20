/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.usuario_sistema;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easyportal.gwt.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Usuario_sistemaTGWT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.MessageBox;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author I9
 */
public class Usuario_sistemaConsultGWT extends CPConsultarBaseGWT implements br.com.easynet.gwt.client.IListenetResponse {

    public static final String PAGE = "i9factory/factory/usuario_sistema/usuario_sistemaConsultGWT.jsp";
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    final Usuario_sistemaConsultGWT consultGWT = this;

    public Usuario_sistemaConsultGWT() {
        try {

            this.setHeaderVisible(false);
            getToolBarMaster().setHeight(26);
            setHeaderVisible(false);
            getDataEAST().setHidden(true);
            getDataWEST().setHidden(true);
            getDataNORTH().setSize(27);


            ColumnConfig column = null;

            column = new ColumnConfig();
            column.setId("usu_tx_nome");
            column.setHeader("nome");
            column.setWidth(200);
            column.setAlignment(HorizontalAlignment.LEFT);
            configs.add(column);

            column = new ColumnConfig();
            column.setId("usu_tx_email");
            column.setHeader("e-mail");
            column.setWidth(200);
            column.setAlignment(HorizontalAlignment.LEFT);
            configs.add(column);


            GridCellRenderer<Usuario_sistemaTGWT> btnEdit = new GridCellRenderer<Usuario_sistemaTGWT>() {

                public Object render(final Usuario_sistemaTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Usuario_sistemaTGWT> store, Grid<Usuario_sistemaTGWT> grid) {

                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                        //@Override
                        public void componentSelected(ButtonEvent ce) {

                            Usuario_sistemaUpdateDeleteGWT updateDeleteGWT = new Usuario_sistemaUpdateDeleteGWT();
                            updateDeleteGWT.setConsultGWT(consultGWT);
                            updateDeleteGWT.load(model);
                            updateDeleteGWT.show();

                        }
                    });
                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Alterar");
                    btnCol.setIcon(ICONS.edit());
                    btnCol.setBorders(false);

                    return btnCol;
                }
            };


            GridCellRenderer<Usuario_sistemaTGWT> btnDel = new GridCellRenderer<Usuario_sistemaTGWT>() {

                private boolean init;

                public Object render(final Usuario_sistemaTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Usuario_sistemaTGWT> store, Grid<Usuario_sistemaTGWT> grid) {
                    if (!init) {
                        init = true;
                        grid.addListener(Events.ColumnResize, new Listener<GridEvent<Usuario_sistemaTGWT>>() {

                            public void handleEvent(GridEvent<Usuario_sistemaTGWT> be) {
                                for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
                                    if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
                                            && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
                                        ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
                                    }
                                }
                            }
                        });
                    }

                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                        @Override
                        public void componentSelected(ButtonEvent ce) {
                            MessageBox.confirm("Aviso", "Confirma o remocao do usuario?", new Listener<MessageBoxEvent>() {

                                public void handleEvent(MessageBoxEvent be) {

                                    if ("Yes".equalsIgnoreCase(be.getButtonClicked().getText())) {
                                        EasyAccessURL eaurl = new EasyAccessURL(consultGWT);
                                        HashMap<String, String> param = new HashMap<String, String>();

                                        param.put("op", "delete");
                                        param.put("usu_usuarioT.usu_nr_id", model.getUsu_nr_id() + "");

                                        eaurl.accessJSonMap(Constantes.URL + PAGE, param);
                                        load();
                                    }
                                }
                            });
                        }
                    });
                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Excluir usuario.");
                    btnCol.setBorders(false);
                    btnCol.setBounds(0, 0, 0, 0);
                    btnCol.setIcon(ICONS.del());
                    return btnCol;
                }
            };


            column = new ColumnConfig();
            column.setHeader("");
            column.setRenderer(btnEdit);
            configs.add(column);
            column.setWidth(30);

            column = new ColumnConfig();
            column.setHeader("");
            column.setRenderer(btnDel);
            configs.add(column);
            column.setWidth(30);
            load();
        } catch (Exception e) {
        }
    }

    public void load() {
        try {
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("op", "consult");
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);//"portalgwt/exemplos/gridexemplo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(JSONValue jsonValue) {

        JSONObject jsonObject;

        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            //Set<String> keys = jsonObject.keySet();
            //Window.alert(usuario);
            JSONArray resultado = jsonObject.get("resultado").isArray();
            ListStore<Usuario_sistemaTGWT> store = new ListStore<Usuario_sistemaTGWT>();

            for (int i = 1; i < resultado.size(); i++) {

                Usuario_sistemaTGWT usuario_sistemaTGWT = new Usuario_sistemaTGWT();
                JSONObject registro = resultado.get(i).isObject();

                String usu_tx_nome = EasyContainer.clearAspas(registro.get("usu_tx_nome").toString());
                usuario_sistemaTGWT.setUsu_tx_nome(usu_tx_nome);

                String usu_tx_status = EasyContainer.clearAspas(registro.get("usu_tx_status").toString());
                usuario_sistemaTGWT.setUsu_tx_status(usu_tx_status);

                String usu_tx_email = EasyContainer.clearAspas(registro.get("usu_tx_email").toString());
                usuario_sistemaTGWT.setUsu_tx_email(usu_tx_email);


                Integer usu_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("usu_nr_id").toString()));
                usuario_sistemaTGWT.setUsu_nr_id(usu_nr_id);

                String usu_tx_login = EasyContainer.clearAspas(registro.get("usu_tx_login").toString());
                usuario_sistemaTGWT.setUsu_tx_login(usu_tx_login);

                String usu_tx_senha = EasyContainer.clearAspas(registro.get("usu_tx_senha").toString());
                usuario_sistemaTGWT.setUsu_tx_senha(usu_tx_senha);

                store.add(usuario_sistemaTGWT);

            }
            ColumnModel cm = new ColumnModel(configs);

            Grid<Usuario_sistemaTGWT> grid = new Grid<Usuario_sistemaTGWT>(store, cm);
            grid.setLoadMask(true);
            grid.setStyleAttribute("borderTop", "none");
            grid.setBorders(true);

            grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            grid.addListener(Events.OnDoubleClick, new Listener<GridEvent<Usuario_sistemaTGWT>>() {

                public void handleEvent(GridEvent<Usuario_sistemaTGWT> be) {
                    Usuario_sistemaUpdateDeleteGWT usuario_sistemaUpdateDeleteGWT = new Usuario_sistemaUpdateDeleteGWT();
                    usuario_sistemaUpdateDeleteGWT.load(be.getModel());
                    usuario_sistemaUpdateDeleteGWT.setConsultGWT(Usuario_sistemaConsultGWT.this);
                    usuario_sistemaUpdateDeleteGWT.show();
                }
            });
            grid.setStripeRows(true);
            getCpMaster().removeAll();
            getCpMaster().add(grid);
            this.layout();
            doLayout();
        }
    }

    @Override
    public void btnNovoAction(ButtonEvent be) {
        Usuario_sistemaInsertGWT insert;
        try {
            insert = new Usuario_sistemaInsertGWT();
            insert.setConsultGWT(Usuario_sistemaConsultGWT.this);
            insert.setModal(true);
            insert.show();

        } catch (Exception e) {
        }
    }
}
