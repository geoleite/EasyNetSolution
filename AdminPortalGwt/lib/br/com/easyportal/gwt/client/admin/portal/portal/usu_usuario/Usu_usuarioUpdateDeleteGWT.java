package br.com.easyportal.gwt.client.admin.portal.portal.usu_usuario;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;

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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Usu_usuarioConsultGWT usu_usuarioConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private Usu_usuarioTGWT usu_usuarioT;
    private TextField<String> usu_nr_id = new TextField<String>();
    private TextField<String> usu_tx_nome = new TextField<String>();
    private TextField<String> usu_tx_login = new TextField<String>();
    private TextField<String> usu_tx_senha = new TextField<String>();
    private TextField<String> usu_tx_email = new TextField<String>();
    private Radio rUsu_tx_statusAtivo = new Radio();
    private Radio rUsu_tx_statusInativo = new Radio();
    private RadioGroup rgUsu_tx_status = new RadioGroup("Status");

    public Usu_usuarioUpdateDeleteGWT() {
        this.setSize("400", "300");


        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(usu_tx_nome);

        getCpMaster().add(new LabelField("Login:"));
        getCpMaster().add(usu_tx_login);


        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(rgUsu_tx_status);
        rUsu_tx_statusAtivo.setValue(true);
        rUsu_tx_statusAtivo.setBoxLabel("Ativo");
        rUsu_tx_statusInativo.setBoxLabel("Inativo");
        rgUsu_tx_status.add(rUsu_tx_statusAtivo);
        rgUsu_tx_status.add(rUsu_tx_statusInativo);


        getCpMaster().add(new LabelField("Email:"));
        getCpMaster().add(usu_tx_email);

        refresh(-10);
        refresh(10);
    }

    public void refresh(int valor) {
        setWidth(getWidth() + valor);
        Timer timer = new Timer() {

            @Override
            public void run() {

            }
        };
        timer.schedule(300);
    }

    public void load(Usu_usuarioTGWT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
        usu_nr_id.setValue(usu_usuarioT.getUsu_nr_id() + "");
        usu_tx_nome.setValue(usu_usuarioT.getUsu_tx_nome());
        usu_tx_login.setValue(usu_usuarioT.getUsu_tx_login());
        usu_tx_senha.setValue(usu_usuarioT.getUsu_tx_senha());
        if ("A".equalsIgnoreCase(usu_usuarioT.getUsu_tx_status())) {
            rUsu_tx_statusAtivo.setValue(true);
        } else {
            rUsu_tx_statusInativo.setValue(true);
        }
        usu_tx_email.setValue(usu_usuarioT.getUsu_tx_email());
    }

    public void btnUpdateAction(ButtonEvent ce) {
        usu_usuarioT.setUsu_nr_id(Integer.parseInt(usu_nr_id.getValue()));
        usu_usuarioT.setUsu_tx_nome(usu_tx_nome.getValue());
        usu_usuarioT.setUsu_tx_login(usu_tx_login.getValue());
        usu_usuarioT.setUsu_tx_senha(usu_tx_senha.getValue());
        usu_usuarioT.setUsu_tx_status(rUsu_tx_statusAtivo.getValue()?"A":"I");
        usu_usuarioT.setUsu_tx_email(usu_tx_email.getValue());

        usu_usuarioDao.alterar(usu_usuarioT);
        Timer timer = new Timer() {

            public void run() {
                String msg = usu_usuarioDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        usu_usuarioConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        usu_usuarioDao.excluir(usu_usuarioT);
        Timer timer = new Timer() {

            public void run() {
                String msg = usu_usuarioDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        usu_usuarioConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    /**
     * @return the usu_usuarioConsult
     */
    public Usu_usuarioConsultGWT getUsu_usuarioConsult() {
        return usu_usuarioConsult;
    }

    /**
     * @param usu_usuarioConsult the usu_usuarioConsult to set
     */
    public void setUsu_usuarioConsult(Usu_usuarioConsultGWT usu_usuarioConsult) {
        this.usu_usuarioConsult = usu_usuarioConsult;
    }
}
