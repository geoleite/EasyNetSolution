/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author geoleite
 */
public class SairGWT extends EasyContainer implements IListenetResponse {
    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private LabelField lblMensagem = new LabelField(easyLabels.confirmOut());
    private Button btnSim = new Button(easyLabels.yes());
    private Button btnNao = new Button(easyLabels.no());

    public SairGWT() {
        MessageBox mb = MessageBox.confirm(easyLabels.warning(), easyLabels.confirmOut(), new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {
                Info.display("DEBUG", easyLabels.yes() + "-" + be.getButtonClicked().getText());
                if (easyLabels.yes().equalsIgnoreCase(be.getButtonClicked().getText())) {
                    btnSimAction(be.getButtonClicked());
                }

            }
        });

    }

    public void btnSimAction(Widget sender) {
        try {
            EasyAccessURL eaurl = new EasyAccessURL(this);
            String url = Constantes.URL + "portalgwt/principalpage.jsp?op=sair";
            eaurl.accessJSon(url);
        } catch (Exception e) {
            Window.alert(easyLabels.operationFailure() + e.getMessage());
        }
    }

    /**
     * Fecha a aba da tela de sair
     * @param sender
     */
    public void btnNaoAction(Widget sender) {
        TabItem ti = Portal2GWT.tab.getItemByItemId("Sair");
        Portal2GWT.tab.remove(ti);
    }

    public void read(JSONValue jsonValue) {
        Portal2GWT.panelBase.setVisible(false);
        AuthenticationGWT authentication = new AuthenticationGWT();
        RootPanel.get().add(authentication);

    }
}
