/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.easynet.gwt.client.icons.ExampleIcons;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author geoleite
 */
public class Authentication2GWT extends BaseGWT {

    public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
    public EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private Button btnGo = new Button(easyLabels.enter());
    private TextField<String> txtUserName = new TextField<String>();
    private LabelField lblUserName = new LabelField(easyLabels.user() + ":");
    private LabelField lblPassword = new LabelField(easyLabels.pass() + ":");
    private TextField<String> ptbPassword = new TextField<String>();
    private LabelField forgotPassword = new LabelField("<a href=\"#\">" + easyLabels.forgotPassword() + "</a>");

    public Authentication2GWT() {
        setIcon(ICONS.cadeado());
        setHeading(easyLabels.authentication());
        setResizable(false);
        setModal(true);
        setMaximizable(false);
        setMinimizable(false);
        setBodyBorder(false);
        setBorders(false);

        setClosable(false);
        getCpTop().setVisible(false);
        getCpLeft().setVisible(false);
        getCpRight().setVisible(false);
        getCpBotton().setVisible(false);
        
        ptbPassword.setPassword(true);
        TableLayout tl = new TableLayout(2);
        tl.setCellSpacing(6);
        getCpMaster().setLayout(tl);

        getCpMaster().setLayout(tl);
        getCpMaster().setFrame(false);
        getCpMaster().add(lblUserName);
        getCpMaster().add(txtUserName);
        getCpMaster().add(lblPassword);
        getCpMaster().add(ptbPassword);
        getCpMaster().addButton(btnGo);

        getDataEAST().setSplit(false);
        getDataCENTER().setSplit(false);
        getDataWEST().setSplit(false);
        getDataNORTH().setSplit(false);
        getDataSOUTH().setSplit(false);

        setBottomComponent(forgotPassword);

        setSize(250, 160);

        forgotPassword.addListener(Events.OnClick, new Listener<BaseEvent>() {

            public void handleEvent(BaseEvent be) {
                //MessageBox.alert("ATENCAO", "TESTE", null);
            }
        });
    }
}
