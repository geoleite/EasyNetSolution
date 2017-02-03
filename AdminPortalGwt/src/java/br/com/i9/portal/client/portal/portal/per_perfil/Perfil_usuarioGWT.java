/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.per_perfil;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.BaseBorderLayoutGWT;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.dao.Pu_per_usuDAOGWT;
import br.com.i9.portal.client.portal.portal.dao.Usu_usuarioDAOGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Pu_per_usuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usuario_sistemaTGWT;
import br.com.i9.portal.client.portal.portal.usu_usuario.Usu_usuarioConsultGWT;
import br.com.i9.portal.client.portal.portal.usuario_sistema.ListUsuarioSistemaGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Pu_per_usuTService;
import br.com.i9.portal.client.rpc.Pu_per_usuTServiceAsync;
import br.com.i9.portal.client.rpc.Usu_usuarioService;
import br.com.i9.portal.client.rpc.Usu_usuarioServiceAsync;
import com.extjs.gxt.ui.client.dnd.GridDragSource;
import com.extjs.gxt.ui.client.dnd.GridDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author I9
 */
public class Perfil_usuarioGWT extends BaseBorderLayoutGWT {

    public static final String PAGE = "portal/portal/usuario_sistema/usuario_sistemaConsultGWT.jsp";
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private final Perfil_usuarioGWT perfil_usuarioGWT = this;
    private ContentPanel cpVinc = new ContentPanel(new FillLayout());
    private ContentPanel cpNotVinc = new ContentPanel(new FillLayout());
    private GridDropTarget targetVinc;
    private GridDropTarget targetNotVinc;
    private Per_perfilTGWT per_perfilTGWT = new Per_perfilTGWT();
    private Usu_usuarioDAOGWT usuDaoVinculado = new Usu_usuarioDAOGWT();
    private Usu_usuarioDAOGWT usuDaoNaoVinculado = new Usu_usuarioDAOGWT();
    private Pu_per_usuDAOGWT puDao = new Pu_per_usuDAOGWT();

    public Perfil_usuarioGWT() {
        this.setShadow(true);
        setModal(true);
        getCpMaster().setHeaderVisible(true);
        getCpRight().setHeaderVisible(true);
        getCpMaster().setHeading("Não Vinculado");
        getCpRight().setHeading("Vinculado");

        setHeading("Usuarios Perfil.");
        getCpRight().setWidth(230);
        getDataEAST().setSize(230);
        this.setSize(500, 250);

        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Usuario_sistemaTGWT>> numberRenderer = new NumberCellRenderer<Grid<Usuario_sistemaTGWT>>(currency);
    }

    public List<ColumnConfig> createConfig() {
        List<ColumnConfig> config = new Vector();
        ColumnConfig column = new ColumnConfig("usu_nr_id", "usuario", 20);
        column.setHidden(true);
        config.add(column);
        column = new ColumnConfig("usu_tx_nome", "nome", 200);
        config.add(column);
        return config;
    }

    public void load(Per_perfilTGWT per_perfilTGWT) {
        this.per_perfilTGWT = per_perfilTGWT;
        setHeading("Usuários do Perfil " + per_perfilTGWT.getPer_tx_nome());
        montarTela();
        layout();
        doLayout();
    }

    public void montarTela() {

        AsyncCallback<List<Usu_usuarioTGWT>> callbackNot = new AsyncCallback<List<Usu_usuarioTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Usu_usuarioTGWT> result) {
                ListStore<Usu_usuarioTGWT> store = new ListStore<Usu_usuarioTGWT>();
                store.add(result);
                ColumnModel cmnotvinc = new ColumnModel(createConfig());
                Grid<Usu_usuarioTGWT> grid_notvinc = new Grid<Usu_usuarioTGWT>(store, cmnotvinc);
                grid_notvinc.setLoadMask(true);

                grid_notvinc.setBorders(true);
                grid_notvinc.setStripeRows(true);
                getCpMaster().add(grid_notvinc);
                GridDragSource gridSourceNotVinc = new GridDragSource(grid_notvinc);

                GridDropTarget targetNotVinc = new GridDropTarget(grid_notvinc) {

                    protected void onDragDrop(DNDEvent event) {
                        List<Usu_usuarioTGWT> lis = event.getData();
                        
                        if (!lis.isEmpty()) {
                            for (Usu_usuarioTGWT usuT : lis) {
                                removerUsuarioDoPerfil(usuT);
                            }
                        }
                        super.onDragDrop(event);
                    }
                };

                targetNotVinc.setAllowSelfAsSource(false);
                layout();
                doLayout();

            }
        };
        Usu_usuarioServiceAsync asyncnot = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        asyncnot.consultByNaoPerfil(per_perfilTGWT, callbackNot);
        getCpMaster().removeAll();
        getCpRight().removeAll();

        AsyncCallback<List<Usu_usuarioTGWT>> callback = new AsyncCallback<List<Usu_usuarioTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Usu_usuarioTGWT> result) {
                ListStore<Usu_usuarioTGWT> store = new ListStore<Usu_usuarioTGWT>();
                store.add(result);
                final ColumnModel cmvinc = new ColumnModel(createConfig());
                Grid<Usu_usuarioTGWT> grid_vinc = new Grid<Usu_usuarioTGWT>(store, cmvinc);
                grid_vinc.setLoadMask(true);
                grid_vinc.setStripeRows(true);

                grid_vinc.setBorders(true);

                getCpRight().add(grid_vinc);

                GridDragSource gridSourceVinc = new GridDragSource(grid_vinc);

                GridDropTarget targetVinc = new GridDropTarget(grid_vinc) {

                    protected void onDragDrop(DNDEvent event) {
                       
                        List<Usu_usuarioTGWT> lis = event.getData();
                        if (!lis.isEmpty()) {
                            for (Usu_usuarioTGWT usuT : lis) {
                                adicionarUsuarioAoPerfil(usuT);
                            }
                        }
                        super.onDragDrop(event);
                    }
                };
                targetVinc.setAllowSelfAsSource(false);
                layout();
                doLayout();

            }
        };
        Usu_usuarioServiceAsync async = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        async.consultByPerfil(per_perfilTGWT, callback);
    }

    /**
     * @return the ConsultGWT
     */
    public void removerUsuarioDoPerfil(Usu_usuarioTGWT usuT) {
        Pu_per_usuTGWT puT = new Pu_per_usuTGWT();
        puT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
        puT.setUsu_nr_id(usuT.getUsu_nr_id());

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Problema ao remover o usuário", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resposta", "Sucesso!");
            }
        };
        Pu_per_usuTServiceAsync async = EasyAdmPortalRPCFactory.getPu_Per_UsuService();
        async.delete(puT, callback);

    }

    public void adicionarUsuarioAoPerfil(Usu_usuarioTGWT usuT) {

        Pu_per_usuTGWT puT = new Pu_per_usuTGWT();
        puT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
        puT.setUsu_nr_id(usuT.getUsu_nr_id());
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Problema ao inserir o usuário", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resposta", "Sucesso!");
            }
        };
        Pu_per_usuTServiceAsync async = EasyAdmPortalRPCFactory.getPu_Per_UsuService();
        async.insert(puT, callback);

    }
}
