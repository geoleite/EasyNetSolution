/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.i9.portal.client.BaseBorderLayoutGWT;
import br.com.i9.portal.client.portal.portal.dao.Men_menuDAOGWT;
import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Mep_men_perTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Men_menuServiceAsync;
import br.com.i9.portal.client.rpc.Mep_men_perServiceAsync;

import java.util.ArrayList;
import java.util.List;
import com.extjs.gxt.ui.client.dnd.GridDragSource;
import com.extjs.gxt.ui.client.dnd.GridDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class MenuPerfilPermissao extends BaseBorderLayoutGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Per_perfilTGWT per_perfilTGWT;
    private Men_menuDAOGWT menDaoVinculado = new Men_menuDAOGWT();
    private Men_menuDAOGWT menDaoNaoVinculado = new Men_menuDAOGWT();
    private GridDropTarget targetVinc;
    private GridDropTarget targetNotVinc;
    private Grid<Men_menuTGWT> grid_vinc;
    private Grid<Men_menuTGWT> grid_notvinc;

    public MenuPerfilPermissao() {
        setModal(true);
        getCpMaster().setHeaderVisible(true);
        getCpRight().setHeaderVisible(true);
        getCpMaster().setHeading("Não Vinculado");
        getCpRight().setHeading("Vinculado");
        this.setSize(510, 250);
        getDataEAST().setSize(240);
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Men_menuTGWT>> numberRenderer = new NumberCellRenderer<Grid<Men_menuTGWT>>(currency);
    }

    public void montarTela() {
//        getCpRight().removeAll();
//        getCpMaster().removeAll();

        AsyncCallback<List<Men_menuTGWT>> callbackYes = new AsyncCallback<List<Men_menuTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Atnção", "erro ao realizar a operação", null);
            }

            @Override
            public void onSuccess(List<Men_menuTGWT> result) {
                if (grid_vinc == null) {
                    final ColumnModel cmvinc = new ColumnModel(createConfig());
                    ListStore<Men_menuTGWT> list = new ListStore<Men_menuTGWT>();
                    list.add(result);

                    grid_vinc = new Grid<Men_menuTGWT>(list, cmvinc);

                    grid_vinc.setLoadMask(true);
                    grid_vinc.setBorders(true);
                    grid_vinc.setStripeRows(true);
                    getCpRight().add(grid_vinc);

                    GridDragSource gridSourceVinc = new GridDragSource(grid_vinc);

                    GridDropTarget targetVinc = new GridDropTarget(grid_vinc) {

                        protected void onDragDrop(DNDEvent event) {

                            List<Men_menuTGWT> lis = event.getData();
                            if (!lis.isEmpty()) {
                                for (Men_menuTGWT men_menuTGWT : lis) {
                                    adionarMenuAoPerfil(men_menuTGWT);
                                }
                            }
                            super.onDragDrop(event);
                        }
                    };
                    targetVinc.setAllowSelfAsSource(false);
                } else {
                    grid_vinc.getStore().removeAll();
                    grid_vinc.getStore().add(result);
                    grid_vinc.getView().refresh(true);

                }
            }
        };

        Men_menuServiceAsync serviceYes = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceYes.consultByPerfil(per_perfilTGWT, callbackYes);

        AsyncCallback<List<Men_menuTGWT>> callbackNot = new AsyncCallback<List<Men_menuTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Atenção", "Erro a realizar a operação", null);
            }

            @Override
            public void onSuccess(List<Men_menuTGWT> result) {
                if(grid_notvinc == null){

                ListStore<Men_menuTGWT> list = new ListStore<Men_menuTGWT>();
                list.add(result);
                ColumnModel cmnotvinc = new ColumnModel(createConfig());
                grid_notvinc = new Grid<Men_menuTGWT>(list, cmnotvinc);
                grid_notvinc.setLoadMask(true);
                grid_notvinc.setAutoExpandMin(200);
                grid_notvinc.setBorders(true);
                grid_notvinc.setStripeRows(true);
                getCpMaster().add(grid_notvinc);

                GridDragSource gridSourceNotVinc = new GridDragSource(grid_notvinc);

                GridDropTarget targetNotVinc = new GridDropTarget(grid_notvinc) {

                    protected void onDragDrop(DNDEvent event) {
                        List<Men_menuTGWT> lis = event.getData();
                        if (!lis.isEmpty()) {
                            for (Men_menuTGWT men_menuTGWT : lis) {
                                removerMenuAoPerfil(men_menuTGWT);
                            }
                        }
                        super.onDragDrop(event);
                    }
                };

                targetNotVinc.setAllowSelfAsSource(false);

                layout();
                }else{
                    grid_notvinc.getStore().removeAll();
                    grid_notvinc.getStore().add(result);
                    grid_notvinc.getView().refresh(true);
                }
            }
        };
        Men_menuServiceAsync serviceNot = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceNot.consultByNotPerfil(per_perfilTGWT, callbackNot);
    }

    public void load(Per_perfilTGWT per_perfilTGWT) {
        this.per_perfilTGWT = per_perfilTGWT;
        setHeading("Menus do Perfil: " + per_perfilTGWT.getPer_tx_nome());
        montarTela();
    }

    public List<ColumnConfig> createConfig() {
        List<ColumnConfig> config = new Vector();
        ColumnConfig column = new ColumnConfig("men_nr_id", "id", 20);
        column.setHidden(true);
        config.add(column);
        column = new ColumnConfig("men_tx_nome", "Nome", 200);
        config.add(column);
        return config;
    }

    public void adionarMenuAoPerfil(Men_menuTGWT men_menuTGWT) {
        Mep_men_perTGWT mepT = new Mep_men_perTGWT();
        mepT.setMen_nr_id(men_menuTGWT.getMen_nr_id());
        mepT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Problemas", "Falha ao adicionar o menu", null);
            }

            @Override
            public void onSuccess(Boolean result) {
                Info.display("Resultado", "Sucesso!");
                montarTela();
            }
        };
        Mep_men_perServiceAsync async = EasyAdmPortalRPCFactory.getMep_Men_PerService();

        async.insert(mepT, callback);
//        Mep_men_perTGWT mepT = new Mep_men_perTGWT();
//        mepT.setMen_nr_id(men_menuTGWT.getMen_nr_id());
//        mepT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
//        final Mep_men_perDAOGWT mepDao = new Mep_men_perDAOGWT();
//        mepDao.inserir(mepT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = mepDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        montarTela();
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void removerMenuAoPerfil(Men_menuTGWT men_menuTGWT) {
        Mep_men_perTGWT mepT = new Mep_men_perTGWT();
        mepT.setMen_nr_id(men_menuTGWT.getMen_nr_id());
        mepT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Problemas", "Falha ao extornar o menu", null);
            }

            @Override
            public void onSuccess(Boolean result) {
                Info.display("Resultado", "Sucesso!");
                montarTela();
            }
        };
        Mep_men_perServiceAsync async = EasyAdmPortalRPCFactory.getMep_Men_PerService();

        async.delete(mepT, callback);

//        final Mep_men_perDAOGWT mepDao = new Mep_men_perDAOGWT();
//        mepDao.excluir(mepT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = mepDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        montarTela();
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }
}
