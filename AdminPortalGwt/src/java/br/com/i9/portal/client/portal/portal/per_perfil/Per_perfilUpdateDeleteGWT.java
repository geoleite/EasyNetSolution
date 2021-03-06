package br.com.i9.portal.client.portal.portal.per_perfil;

import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Per_perfilService;
import br.com.i9.portal.client.rpc.Per_perfilServiceAsync;

import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Per_perfilUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Per_perfilConsultGWT per_perfilConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Per_perfilDAOGWT per_perfilDao = new Per_perfilDAOGWT();
    private Per_perfilTGWT per_perfilT;
    private EasyTextField<String> per_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> per_tx_class = new EasyTextField<String>();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private RadioGroup per_tx_status = new RadioGroup();

    public Per_perfilUpdateDeleteGWT() {
        setHeading("Alterar Perfil");
        this.setSize("500", "180");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lfper_tx_nome = new LabelField("Nome:");
        lfper_tx_nome.setId("lfper_tx_nome");
        getCpMaster().add(lfper_tx_nome);
        per_tx_nome.setId("per_tx_nome");
        per_tx_nome.setMaxLength(80);
        per_tx_nome.setWidth(250);
        per_tx_nome.setUpperCase(false);
        per_tx_nome.setAllowCharSpecial(true);

        getCpMaster().add(per_tx_nome);

        LabelField lfper_tx_status = new LabelField("Status:");
        lfper_tx_status.setId("lfper_tx_status");
        getCpMaster().add(lfper_tx_status);
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        per_tx_status.add(rAtivo);
        per_tx_status.add(rInativo);
        per_tx_status.setValue(rAtivo);
        getCpMaster().add(per_tx_status);

        LabelField lfper_tx_class = new LabelField("Classe:");
        lfper_tx_class.setId("lfper_tx_class");
        getCpMaster().add(lfper_tx_class);
        per_tx_class.setId("per_tx_class");
        per_tx_class.setMaxLength(500);
        per_tx_class.setWidth(300);
        per_tx_class.setUpperCase(false);
        per_tx_class.setAllowCharSpecial(true);

        getCpMaster().add(per_tx_class);
    }

    public void load(Per_perfilTGWT per_perfilT) {
        this.per_perfilT = per_perfilT;
        per_tx_nome.setValue(per_perfilT.getPer_tx_nome());
        per_tx_status.setValue("A".equals(per_perfilT.getPer_tx_status()) ? rAtivo : rInativo);
        per_tx_class.setValue(per_perfilT.getPer_tx_class());

    }

    public void btnUpdateAction(ButtonEvent ce) {
        if (per_tx_nome.getValue() == null || per_tx_nome.getValue().isEmpty()) {
            MessageBox.alert("Falha", "É necessário definir o nome do perfil.", null);
            return;
        }
        per_perfilT.setPer_tx_nome(per_tx_nome.getValue());
        per_perfilT.setPer_tx_status(per_tx_status.getValue() == rAtivo ? "A" : "I");
        per_perfilT.setPer_tx_class(per_tx_class.getValue());

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("ATENÇÃO", caught.getMessage(), null);
            }

            @Override
            public void onSuccess(Boolean result) {
                Info.display("Resultado", "Alteração realizada com sucesso!");
            }
        };
        Per_perfilServiceAsync async = EasyAdmPortalRPCFactory.getPer_PerfilService();
        async.update(per_perfilT, callback);

//        per_perfilDao.alterar(per_perfilT);
//
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = per_perfilDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        per_perfilConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    per_perfilDao.excluir(per_perfilT);
                    Timer timer = new Timer() {

                        public void run() {
                            String msg = per_perfilDao.getMsg();
                            if (msg == null) {
                                schedule(500);
                            } else {
                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                                    MessageBox.alert("Problemas", msg, null);
                                } else {
                                    Info.display("Resultado", msg);
                                    per_perfilConsult.load();
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
     * @return the per_perfilConsult
     */
    public Per_perfilConsultGWT getPer_perfilConsult() {
        return per_perfilConsult;
    }

    /**
     * @param per_perfilConsult the per_perfilConsult to set
     */
    public void setPer_perfilConsult(Per_perfilConsultGWT per_perfilConsult) {
        this.per_perfilConsult = per_perfilConsult;
    }
}
