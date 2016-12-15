/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.CadastrarBaseGWT;
import br.com.easynet.gwt.client.component.EasyTextField;
import br.com.easynet.gwt.client.icons.ExampleIcons;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Res_recall_senhaDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class GerarNovaSenha extends CadastrarBaseGWT {

    public EasyLabels easyLabels = GWT.create(EasyLabels.class);
    public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
    protected Button btnFindUsuario = new Button("",ICONS.find());
    protected Button btnEnviar = new Button(easyLabels.generate());
    protected EasyTextField<String> etfLogin = new EasyTextField<String>();
    protected EasyTextField<String> etfEmail = new EasyTextField<String>();
    protected EasyTextField<String> etfResposta = new EasyTextField<String>();
    protected LabelField lfPergunta = new LabelField("");
    protected Res_recall_senhaDAOGWT res_recall_senhaDAOGWT = new Res_recall_senhaDAOGWT();
    protected Res_recall_senhaTGWT res_recall_senhaTGWT;

    public GerarNovaSenha() {
        setHeading(easyLabels.generateNewPassword());
        setSize(300, 250);
        setResizable(false);
        setModal(true);
        setMaximizable(false);
        setMinimizable(false);
        TableLayout tl = new TableLayout(3);
        tl.setCellSpacing(2);
        tl.setCellPadding(2);
        getCpMaster().setLayout(tl);
        getCpMaster().add(new LabelField(easyLabels.user() + ":"));
        getCpMaster().add(etfLogin);
        getCpMaster().add(new LabelField(""));
        getCpMaster().add(new LabelField(easyLabels.emailReport() + ":"));
        getCpMaster().add(etfEmail);
        getCpMaster().add(btnFindUsuario);
        getCpMaster().add(new LabelField(easyLabels.question() + ":"));
        getCpMaster().add(lfPergunta);
        getCpMaster().add(new LabelField(""));
        getCpMaster().add(new LabelField(easyLabels.answer() + ":"));
        getCpMaster().add(etfResposta);
        getCpMaster().add(new LabelField(""));

        etfLogin.setAllowBlank(false);
        etfLogin.setAllowCharSpecial(true);
        etfLogin.setUpperCase(false);

        etfEmail.setAllowBlank(false);
        etfEmail.setAllowCharSpecial(true);
        etfEmail.setUpperCase(false);

        etfResposta.setAllowBlank(false);
        etfResposta.setAllowCharSpecial(true);
        etfResposta.setUpperCase(false);

//
//        KeyListener keyListenerEmail = new KeyEvento() {
//
//            public void componentKeyUp(ComponentEvent event) {
//                if (event.getKeyCode() == KeyCodes.KEY_ENTER) {// enter
//                    loadPergunta();
//                }
//            }
//        };

//        etfEmail.addKeyListener(keyListenerEmail);
        getBtnInsert().setVisible(false);
        getBtnFechar().setVisible(false);
        getBtnLimpar().setVisible(false);
        getToolBarMaster().add(btnEnviar);
        btnEnviar.setIcon(ICONS.generatekey());

        btnFindUsuario.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                loadPergunta();
            }
        });
        btnEnviar.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                enviarAction(ce);
            }
        });
    }

    public void loadPergunta() {
        lfPergunta.setValue("");
        String login = etfLogin.getValue();
        String email = etfEmail.getValue();
        if ( login == null || login.trim().length() == 0 || email == null || email.trim().length() == 0) {
            MessageBox.alert(easyLabels.warning(), easyLabels.userEmailRequired(), null);
            return;
        } else {
            
            res_recall_senhaDAOGWT.findByLoginEmail(login, email);
            Timer timer = new Timer() {

                @Override
                public void run() {
                    if (!res_recall_senhaDAOGWT.isFinalized()) {
                        schedule(500);
                    } else {
                        res_recall_senhaTGWT = res_recall_senhaDAOGWT.getRes_recall_senhaT();
                        if (res_recall_senhaTGWT == null || res_recall_senhaTGWT.getUsu_nr_id() == 0) {
                            MessageBox.alert(easyLabels.warning(), easyLabels.userEmailNotConfer(), null);
                        } else {
                            lfPergunta.setText(res_recall_senhaTGWT.getRes_tx_pergunta());
                        }
                    }
                }
            };
            timer.schedule(1000);
        }
        
    }

    public void enviarAction(ButtonEvent ce) {
        if (res_recall_senhaTGWT == null || res_recall_senhaTGWT.getUsu_nr_id() == 0) {
            MessageBox.alert(easyLabels.warning(), easyLabels.userEmailNotConfer(), null);
        } else {
            String resposta = etfResposta.getValue();
            if ( resposta == null || resposta.trim().length() == 0) {
                MessageBox.alert(easyLabels.warning(), easyLabels.answerRequied(), null);
            } else {
                res_recall_senhaTGWT.setRes_tx_resposta(resposta);
                res_recall_senhaDAOGWT.generatePassword(res_recall_senhaTGWT);
                Timer timer = new Timer() {

                    @Override
                    public void run() {
                        if (!res_recall_senhaDAOGWT.isFinalized()) {
                            schedule(2000);
                        } else {
                           GerarNovaSenha.this.setVisible(false);
                        }
                    }
                };
                timer.schedule(3000);
            }
        }
    }
}
