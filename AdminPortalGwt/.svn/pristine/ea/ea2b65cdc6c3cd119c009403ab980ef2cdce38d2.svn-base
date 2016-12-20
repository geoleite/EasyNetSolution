package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import java.util.HashMap;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Men_menuUpdateDeleteGWTOri extends AlterarExcluirBaseGWT {
    private Men_menuConsultGWTori men_menuConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private Men_menuTGWT men_menuT;

    private EasyTextField<String> men_nr_id = new EasyTextField<String>();
    private EasyTextField<String> supermenu_nr_id = new EasyTextField<String>();
    private EasyTextField<String> men_tx_status = new EasyTextField<String>();
    private EasyTextField<String> men_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> men_nr_ordem = new EasyTextField<String>();
    private EasyTextField<String> men_tx_url = new EasyTextField<String>();
    private EasyTextField<String> men_tx_acao = new EasyTextField<String>();
    private EasyTextField<String> sis_nr_id = new EasyTextField<String>();
    private EasyTextField<String> men_tx_icone = new EasyTextField<String>();
    private Men_menuTGWT menTPai;

    public Men_menuUpdateDeleteGWTOri() {
        setHeading("Alterar Men_menu");
        this.setSize("500", "400");
	TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);
	LabelField lfmen_nr_id = new LabelField("men_nr_id:");
        lfmen_nr_id.setId("lfmen_nr_id");
	getCpMaster().add(lfmen_nr_id);
        men_nr_id.setId("men_nr_id");
        men_nr_id.setMaxLength(10);
        getCpMaster().add(men_nr_id);

	LabelField lfsupermenu_nr_id = new LabelField("supermenu_nr_id:");
        lfsupermenu_nr_id.setId("lfsupermenu_nr_id");
	getCpMaster().add(lfsupermenu_nr_id);
        supermenu_nr_id.setId("supermenu_nr_id");
        supermenu_nr_id.setMaxLength(10);
        getCpMaster().add(supermenu_nr_id);

	LabelField lfmen_tx_status = new LabelField("men_tx_status:");
        lfmen_tx_status.setId("lfmen_tx_status");
	getCpMaster().add(lfmen_tx_status);
        men_tx_status.setId("men_tx_status");
        men_tx_status.setMaxLength(1);
        getCpMaster().add(men_tx_status);

	LabelField lfmen_tx_nome = new LabelField("men_tx_nome:");
        lfmen_tx_nome.setId("lfmen_tx_nome");
	getCpMaster().add(lfmen_tx_nome);
        men_tx_nome.setId("men_tx_nome");
        men_tx_nome.setMaxLength(80);
        getCpMaster().add(men_tx_nome);

	LabelField lfmen_nr_ordem = new LabelField("men_nr_ordem:");
        lfmen_nr_ordem.setId("lfmen_nr_ordem");
	getCpMaster().add(lfmen_nr_ordem);
        men_nr_ordem.setId("men_nr_ordem");
        men_nr_ordem.setMaxLength(10);
        getCpMaster().add(men_nr_ordem);

	LabelField lfmen_tx_url = new LabelField("men_tx_url:");
        lfmen_tx_url.setId("lfmen_tx_url");
	getCpMaster().add(lfmen_tx_url);
        men_tx_url.setId("men_tx_url");
        men_tx_url.setMaxLength(1000);
        getCpMaster().add(men_tx_url);

	LabelField lfmen_tx_acao = new LabelField("men_tx_acao:");
        lfmen_tx_acao.setId("lfmen_tx_acao");
	getCpMaster().add(lfmen_tx_acao);
        men_tx_acao.setId("men_tx_acao");
        men_tx_acao.setMaxLength(80);
        getCpMaster().add(men_tx_acao);

	LabelField lfsis_nr_id = new LabelField("sis_nr_id:");
        lfsis_nr_id.setId("lfsis_nr_id");
	getCpMaster().add(lfsis_nr_id);
        sis_nr_id.setId("sis_nr_id");
        sis_nr_id.setMaxLength(10);
        getCpMaster().add(sis_nr_id);

	LabelField lfmen_tx_icone = new LabelField("men_tx_icone:");
        lfmen_tx_icone.setId("lfmen_tx_icone");
	getCpMaster().add(lfmen_tx_icone);
        men_tx_icone.setId("men_tx_icone");
        men_tx_icone.setMaxLength(80);
        getCpMaster().add(men_tx_icone);


    }

    public void load(Men_menuTGWT men_menuT) {
	this.men_menuT = men_menuT;
		men_nr_id.setValue(men_menuT.getMen_nr_id() + "");
		supermenu_nr_id.setValue(men_menuT.getSupermenu_nr_id() + "");
		men_tx_status.setValue(men_menuT.getMen_tx_status());
		men_tx_nome.setValue(men_menuT.getMen_tx_nome());
		men_nr_ordem.setValue(men_menuT.getMen_nr_ordem() + "");
		men_tx_url.setValue(men_menuT.getMen_tx_url());
		men_tx_acao.setValue(men_menuT.getMen_tx_acao());
		sis_nr_id.setValue(men_menuT.getSis_nr_id() + "");
		men_tx_icone.setValue(men_menuT.getMen_tx_icone());

    }
    public void btnUpdateAction(ButtonEvent ce) {
		men_menuT.setMen_nr_id( Integer.parseInt(men_nr_id.getValue()));
		men_menuT.setSupermenu_nr_id( Integer.parseInt(supermenu_nr_id.getValue()));
		men_menuT.setMen_tx_status(men_tx_status.getValue());
		men_menuT.setMen_tx_nome(men_tx_nome.getValue());
		men_menuT.setMen_nr_ordem( Integer.parseInt(men_nr_ordem.getValue()));
		men_menuT.setMen_tx_url(men_tx_url.getValue());
		men_menuT.setMen_tx_acao(men_tx_acao.getValue());
		men_menuT.setSis_nr_id( Integer.parseInt(sis_nr_id.getValue()));
		men_menuT.setMen_tx_icone(men_tx_icone.getValue());
                AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> listStore = new ListStore<Sis_sistemaTGWT>();
                listStore.add(result);
//                cbSistema.setStore(listStore);
//                cbSistema.getView().refresh();
            }
        };
        Sis_sistemaServiceAsync serviceAsync = GWT.create(Sis_sistemaService.class);
        serviceAsync.consult(callback);

//	men_menuDao.alterar(men_menuT);
//	Timer timer = new Timer() {
//	   public void run() {
// 	     String msg = men_menuDao.getMsg();
// 	     if (msg == null) {
//		schedule(500);
//	     } else {
//		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
//		  MessageBox.alert("Problemas", msg, null);
//		} else {
//		  Info.display("Resultado", msg);
//		  men_menuConsult.load();
//		  setVisible(false);
//		}
//	     }
//	   }
//        };
//	timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
		  men_menuDao.excluir(men_menuT);
		  Timer timer = new Timer() {
	   	    public void run() {
 	     		String msg = men_menuDao.getMsg();
 	     		if (msg == null) {
			  schedule(500);
	     		} else {
			  if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  		MessageBox.alert("Problemas", msg, null);
			  } else {
		  		Info.display("Resultado", msg);
		  		men_menuConsult.load();
		  		setVisible(false);
			  }
	     		}
	   	  }
        	};
		timer.schedule(500);                    
                }
            }
        });
    }


   /**
     * @return the men_menuConsult
     */
    public Men_menuConsultGWTori getMen_menuConsult() {
        return men_menuConsult;
    }

    /**
     * @param men_menuConsult the men_menuConsult to set
     */
    public void setMen_menuConsult(Men_menuConsultGWTori men_menuConsult) {
        this.men_menuConsult = men_menuConsult;
    }

    /**
     * @return the menTPai
     */
    public Men_menuTGWT getMenTPai() {
        return menTPai;
    }

    /**
     * @param menTPai the menTPai to set
     */
    public void setMenTPai(Men_menuTGWT menTPai) {
        this.menTPai = menTPai;
    }

}

