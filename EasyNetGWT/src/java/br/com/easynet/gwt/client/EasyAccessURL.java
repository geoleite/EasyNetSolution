package br.com.easynet.gwt.client;

import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easynet.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HiddenField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author geoleite
 */
public class EasyAccessURL {

    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    protected JSONValue jsonValue = null;
    private IListenetResponse listener;
    private IListenerResponseGSON listenerGSon;
    private MessageBox mb;
    private static int nrMaxTentativas = 5;
    private static int timeMaxWait = 10000;
    private int contTentativas = 0;
    public static final String BROWSER_IE = "msie";

    public EasyAccessURL(IListenetResponse elr) {
        this.listener = elr;
    }

    public EasyAccessURL(IListenerResponseGSON elr) {
        this.listenerGSon = elr;
    }

    public void accessGSon(final RequestBuilder.Method method, final String url, final String requestData) {
        try {
            mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            //Info.display("AGUARDE", "Acessando informaÃ§Ãµes do servidor.");

            RequestBuilder rb = new RequestBuilder(method, URL.encode(url));

            if (method == RequestBuilder.POST) {
                rb.setHeader("Content-type", "application/x-www-form-urlencoded");
            }

            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(requestData, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    dados = dados.trim();
                    mb.close();

                    //jsonValue = JSONParser.parseLenient(dados);
                    getListenerGSon().read(dados);
                }

                public void onError(Request arg0, Throwable arg1) {
                    if (arg1 instanceof RequestTimeoutException) {
//                        com.google.gwt.user.client.Window.alert("Erro no accessJSON:EasyAccessURL Timeout " + arg1.getMessage());
                        DebugMessage.message(this.getClass().getName(), " Timeout " + contTentativas + " " + url);
                        contTentativas++;
                        mb.close();
                        if (contTentativas < getNrMaxTentativas()) {
                            accessGSon(method, url, requestData);
                        } else {
                            MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                            //jsonValue = JSONParser.parseLenient("{}");
                            getListenerGSon().read("{}");
                        }
                    } else {
                        com.google.gwt.user.client.Window.alert("Erro no accessJSON:EasyAccessURL" + arg1.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Window.alert("Error " + e.getMessage());
        }
    }

    /**
     * Acessa o JSP e envia e recebe dados
     *
     * @param url
     * @throws java.lang.Exception
     */
    public void accessJSon(final String url) {
        //Window.alert(url);
        try {
            mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            //Info.display("AGUARDE", "Acessando informações do servidor.");

            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(null, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    DebugMessage.message(EasyAccessURL.this.getClass().getName(), dados);
                    dados = dados.trim();
                    mb.close();

                    jsonValue = JSONParser.parseLenient(dados);
                    DebugMessage.message(EasyAccessURL.this.getClass().getName(), jsonValue + "");

                    listener.read(jsonValue);
                }

                public void onError(Request arg0, Throwable arg1) {
                    if (mb != null) {
                        mb.close();
                    }
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        jsonValue = JSONParser.parseLenient("{}");
                        listener.read(jsonValue);
                    }
//                    if (arg1 instanceof RequestTimeoutException) {
//                        DebugMessage.message(" Timeout " + contTentativas + " " + url);
//                        contTentativas++;
//                        mb.close();
//                        if (contTentativas < getNrMaxTentativas()) {
//                            accessJSon(url);
//                        } else {
//                            MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
//                            jsonValue = JSONParser.parseLenient("{}");
//                            listener.read(jsonValue);
//                        }
//                    } else {
//                        com.google.gwt.user.client.Window.alert("Erro no accessJSON:EasyAccessURL" + arg1.getMessage());
//                    }
                }
            });
        } catch (Exception e) {
            Window.alert("Error " + e.getMessage());
        }

    }

    public void accessGSon(final String url) {
        try {
            mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(null, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    dados = dados.trim();
                    mb.close();

                    getListenerGSon().read(dados);
                }

                public void onError(Request arg0, Throwable arg1) {
                    if (mb != null) {
                        mb.close();
                    }
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        getListenerGSon().read("{}");
                    }
                }
            });
        } catch (Exception e) {
            Window.alert("Error " + e.getMessage());
        }

    }

    public void accessJSonPost(final String url, String requestData) {
        //Window.alert(url);
        try {
            mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            //Info.display("AGUARDE", "Acessando informações do servidor.");

            RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, url);
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(requestData, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    dados = dados.trim();
                    mb.close();

                    jsonValue = JSONParser.parseLenient(dados);

                    listener.read(jsonValue);
                }

                public void onError(Request arg0, Throwable arg1) {
                    if (mb != null) {
                        mb.close();
                    }
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        jsonValue = JSONParser.parseLenient("{}");
                        listener.read(jsonValue);
                    }

                }
            });
        } catch (Exception e) {
            Window.alert("Error " + e.getMessage());
        }

    }

    public void accessJSonPostNoMessage(final String url, String requestData) {
        //Window.alert(url);
        try {
            //mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            //Info.display("AGUARDE", "Acessando informações do servidor.");

            RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, url);
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(requestData, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    dados = dados.trim();

                    jsonValue = JSONParser.parseLenient(dados);

                    listener.read(jsonValue);
                }

                public void onError(Request arg0, Throwable arg1) {
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        jsonValue = JSONParser.parseLenient("{}");
                        listener.read(jsonValue);
                    }

                }
            });
        } catch (Exception e) {
            Window.alert("Error " + e.getMessage());
        }

    }

    public void accessJSonNoMessage(final String url) {
        //Window.alert(url);
        try {

            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(null, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    try {
                        String dados = res.getText();

                    //Window.alert(dados);
                        dados = dados.trim();

                        DebugMessage.message(EasyAccessURL.this.getClass().getName(), "Vendo os dados requisitados: " + dados.length());
                        jsonValue = JSONParser.parseLenient(dados);
                        DebugMessage.message(EasyAccessURL.this.getClass().getName(), "JSON convertido corretamente.");
//                        DebugMessage.message(EasyAccessURL.this.getClass().getName(), jsonValue + "");

                        listener.read(jsonValue);
                    } catch (Exception e) {
                        DebugMessage.message(DebugMessage.ERROR, EasyAccessURL.this.getClass().getName(), "erro: " + e.getMessage());
                    }

                }

                public void onError(Request arg0, Throwable arg1) {
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        jsonValue = JSONParser.parseLenient("{}");
                        listener.read(jsonValue);
                    }
//                    if (arg1 instanceof RequestTimeoutException) {
//                        contTentativas++;
//                        DebugMessage.message(" Timeout " + contTentativas + " " + url);
//                        if (contTentativas < getNrMaxTentativas()) {
//                            accessJSonNoMessage(url);
//                        } else {
//                            MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
//                            jsonValue = JSONParser.parseLenient("{}");
//                            listener.read(jsonValue);
//                            //com.google.gwt.user.client.Window.alert("Problemas ao acessar o servidor.");
//                        }
//                    } else {
//                        com.google.gwt.user.client.Window.alert("Erro no accessJSON:EasyAccessURL" + arg1.getMessage());
//                    }
                }
            });
        } catch (Exception e) {
        }

    }

    public void accessGSonNoMessage(final String url) {
        try {
            RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
            rb.setTimeoutMillis(getTimeMaxWait());
            rb.sendRequest(null, new RequestCallback() {

                public void onResponseReceived(Request req, Response res) {
                    String dados = res.getText();

                    //Window.alert(dados);
                    dados = dados.trim();

                    getListenerGSon().read(dados);

                }

                public void onError(Request arg0, Throwable arg1) {
                    if (arg1 instanceof RequestTimeoutException) {
                        MessageBox.alert("ATENCAO", "Problemas ao acessar o servidor. Tempo maximo excedido.", null);
                        getListenerGSon().read("{}");
                    }
                }
            });
        } catch (Exception e) {
        }

    }

    /**
     * Submete os dados utilizando um formulário configurado com o méthod post
     *
     * @param url
     * @param listField
     */
    public void accessJSonForm(String url, List<Field> listField) {
        //Window.alert(url);
        mb = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
        try {
            FormPanel fp = new FormPanel();
            fp.setAction(url);
            fp.setMethod(FormPanel.Method.POST);
            for (int i = 0; i < listField.size(); i++) {
                Field field = listField.get(i);
                fp.add(field);
            }
            fp.addListener(Events.Submit, new Listener<FormEvent>() {

                @Override
                protected void finalize() throws Throwable {
                    super.finalize(); //To change body of generated methods, choose Tools | Templates.
                    if (mb != null) {
                        mb.close();
                    }

                }

                public void handleEvent(FormEvent be) {
                    String result = be.getResultHtml();
                    result = result.trim();
                    mb.close();
                    jsonValue = JSONParser.parseLenient(result);
                    listener.read(jsonValue);
                }
            });
            fp.submit();
        } catch (Exception e) {
        }
    }

    /**
     * Submete os dados utilizando um formulário configurado com o méthod post,
     * sem exibir uma mensagem de espera
     *
     * @param url
     * @param listField
     */
    public void accessJSonFormNoMessage(String url, List<Field> listField) {
        //Window.alert(url);
        try {
            FormPanel fp = new FormPanel();
            fp.setAction(url);
            fp.setMethod(FormPanel.Method.POST);
            for (int i = 0; i < listField.size(); i++) {
                Field field = listField.get(i);
                fp.add(field);
            }
            fp.addListener(Events.Submit, new Listener<FormEvent>() {

                public void handleEvent(FormEvent be) {
                    String result = be.getResultHtml();
                    result = result.trim();

                    jsonValue = JSONParser.parseLenient(result);
                    listener.read(jsonValue);
                }
            });
            fp.submit();
        } catch (Exception e) {
        }

    }

    /**
     * Acessa o JSP e envia e recebe dados
     *
     * @param url
     * @throws java.lang.Exception
     */
    public void accessJSonMap(String url, HashMap map) {
        //Window.alert(url);
        if (Window.Navigator.getUserAgent().toLowerCase().indexOf(BROWSER_IE) >= 0) {
            accessJSonPostMap(url, map);
        } else {
            url += "?controlidentity=" + System.currentTimeMillis() + "&";
            Set<String> keys = map.keySet();
            Iterator<String> iter = keys.iterator();

            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                String key = it.next();
                String value = null;
                if (map.containsKey(key)) {
                    value = map.get(key).toString();
                }
                if (value != null) {
                    url += key + "=" + value + "&";
                }
            }
            accessJSon(url);

        }

    }

    public void accessGSonMap(String url, HashMap map) {
        if (Window.Navigator.getUserAgent().toLowerCase().indexOf(BROWSER_IE) >= 0) {
            accessJSonPostMap(url, map);
        } else {
            url += "?controlidentity=" + System.currentTimeMillis() + "&";
            Set<String> keys = map.keySet();
            Iterator<String> iter = keys.iterator();

            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                String key = it.next();
                String value = null;
                if (map.containsKey(key)) {
                    value = map.get(key).toString();
                }
                if (value != null) {
                    url += key + "=" + value + "&";
                }
            }
            accessGSon(url);

        }

    }

    public void accessJSonPostMap(String url, HashMap map) {
        //Window.alert(url);

        String dados = "controlidentity=" + System.currentTimeMillis() + "&";
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();

        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            String value = null;
            if (map.containsKey(key)) {
                value = map.get(key).toString();
            }
            if (value != null) {
                dados += key + "=" + value + "&";
            }
        }
        //Window.alert(url);
        accessJSonPost(url, dados);
    }

    public void accessJSonPostMapNoMessage(String url, HashMap map) {
        //Window.alert(url);

        String dados = "controlidentity=" + System.currentTimeMillis() + "&";
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();

        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            String value = null;
            if (map.containsKey(key)) {
                value = map.get(key).toString();
            }
            if (value != null) {
                dados += key + "=" + value + "&";
            }
        }
        //Window.alert(url);
        accessJSonPostNoMessage(url, dados);
    }

    public void accessJSonMapNoMessage(String url, HashMap map) {
        //Window.alert(url);
        if (Window.Navigator.getUserAgent().toLowerCase().indexOf(BROWSER_IE) >= 0) {
            accessJSonPostMapNoMessage(url, map);
        } else {
            url += "?controlidentity=" + System.currentTimeMillis() + "&";
            Set<String> keys = map.keySet();
            Iterator<String> iter = keys.iterator();

            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                String key = it.next();
                String value = null;
                if (map.containsKey(key)) {
                    value = map.get(key).toString();
                }
                if (value != null) {
                    url += key + "=" + value + "&";
                }
            }
            //Window.alert(url);
            accessJSonNoMessage(url);
        }
    }

    public void accessGSonMapNoMessage(String url, HashMap map) {
        //Window.alert(url);
        if (Window.Navigator.getUserAgent().toLowerCase().indexOf(BROWSER_IE) >= 0) {
            accessJSonPostMapNoMessage(url, map);
        } else {
            url += "?controlidentity=" + System.currentTimeMillis() + "&";
            Set<String> keys = map.keySet();
            Iterator<String> iter = keys.iterator();

            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                String key = it.next();
                String value = null;
                if (map.containsKey(key)) {
                    value = map.get(key).toString();
                }
                if (value != null) {
                    url += key + "=" + value + "&";
                }
            }
            //Window.alert(url);
            accessGSonNoMessage(url);
        }
    }

    public void accessJSonMapForm(String url, HashMap map) {
        //Window.alert(url);
        List<Field> listField = new ArrayList<Field>();
        HiddenField controlidentity = new HiddenField();
        controlidentity.setName("controlidentity");
        controlidentity.setValue(System.currentTimeMillis() + "");
        listField.add(controlidentity);

        Set<String> keys = map.keySet();
        //Iterator<String> iter = keys.iterator();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            String value = null;
            HiddenField field = new HiddenField();
            field.setName(key);
            if (map.containsKey(key)) {
                value = map.get(key).toString();
            }
            if (value != null) {
                field.setValue(value);
            }
            listField.add(field);
        }
        //Window.alert(url);
        accessJSonForm(url, listField);
    }

    public void accessJSonMapFormNoMessage(String url, HashMap map) {
        //Window.alert(url);
        List<Field> listField = new ArrayList<Field>();
        HiddenField controlidentity = new HiddenField();
        controlidentity.setName("controlidentity");
        controlidentity.setValue(System.currentTimeMillis() + "");
        listField.add(controlidentity);

        Set<String> keys = map.keySet();
        //Iterator<String> iter = keys.iterator();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            String value = null;
            HiddenField field = new HiddenField();
            field.setName(key);
            if (map.containsKey(key)) {
                value = map.get(key).toString();
            }
            if (value != null) {
                field.setValue(value);
            }
            listField.add(field);
        }
        //Window.alert(url);
        accessJSonFormNoMessage(url, listField);
    }

    public IListenetResponse getListener() {
        return listener;
    }

    public void setListener(IListenetResponse listener) {
        this.listener = listener;
    }

    /**
     * @return the nrMaxTentativas
     */
    public static int getNrMaxTentativas() {
        return nrMaxTentativas;
    }

    /**
     * @param aNrMaxTentativas the nrMaxTentativas to set
     */
    public static void setNrMaxTentativas(int aNrMaxTentativas) {
        nrMaxTentativas = aNrMaxTentativas;
    }

    /**
     * @return the timeMaxWait
     */
    public static int getTimeMaxWait() {
        return timeMaxWait;
    }

    /**
     * @param aTimeMaxWait the timeMaxWait to set
     */
    public static void setTimeMaxWait(int aTimeMaxWait) {
        timeMaxWait = aTimeMaxWait;
    }

    /**
     * @return the listenerGSon
     */
    public IListenerResponseGSON getListenerGSon() {
        return listenerGSon;
    }

    /**
     * @param listenerGSon the listenerGSon to set
     */
    public void setListenerGSon(IListenerResponseGSON listenerGSon) {
        this.listenerGSon = listenerGSon;
    }
}
