/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.portal.jb;

import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.io.EasyJSPOut;
import br.com.easynet.jb.EasyFileUpload;
import br.com.easynet.portal.dao.Can_canalDAO;
import br.com.easynet.portal.dao.ICan_canalDAO;
import br.com.easynet.portal.dao.IPor_portalDAO;
import br.com.easynet.portal.dao.Por_portalDAO;
import br.com.easynet.portal.transfer.Can_canalT;
import br.com.easynet.portal.transfer.Por_portalT;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author geoleite
 */
public class PortalBaseJB extends SystemBase {

    private List<Can_canalT> canais;
    private Por_portalT portalT = new Por_portalT();
    private Can_canalT can_canalT = new Can_canalT();
    private static String MODEL_CHANNEL = null;
    private String portal;
    private String urlCanal;
    private String urlCanalAtual;
    private Usu_usuarioT usuarioLogado;
    private Map req;
    //private DAOFactory dao;

    public String getExecute() throws Exception {
        try {

            RequestContext rc = new ServletRequestContext(getRequest());
            boolean isMultiPart = FileUpload.isMultipartContent(rc);
            if (isMultiPart) {
                req = readDataFormUpload(rc);
                String[] paramUrlCanal = (String[]) req.get("urlCanal");
                String[] paramUrlCanalAtual = (String[]) req.get("urlCanalAtual");
                if (paramUrlCanal.length > 0) {
                    urlCanal = paramUrlCanal[0];
                }
                if (paramUrlCanalAtual.length > 0) {
                    urlCanalAtual = paramUrlCanalAtual[0];
                }

            }
            usuarioLogado = (Usu_usuarioT) getSession().getAttribute(EasySecurityJB.USER_PRINCIPAL);
            if (usuarioLogado == null) {
                getResponse().sendRedirect(EasySecurityJB.PAGE_INDEX + "?msg=Usuário não autenticado!");
            } else {
                if (MODEL_CHANNEL == null) {
                    MODEL_CHANNEL = readFile("canalTemplate.htm");
                }

                // Verifica se é pra executar alguma ação do portal
                if (portal != null) {
                    super.getExecute();

                }
            }

        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
        return "";
    }

    public String getMontaCanal() {
        try {

            //easyLogger.debug("Ponto A");
            usuarioLogado = (Usu_usuarioT) getSession().getAttribute(EasySecurityJB.USER_PRINCIPAL);
            if (usuarioLogado == null) {
                getResponse().sendRedirect(EasySecurityJB.PAGE_INDEX + "?msg=Usuario nao logado!");
                return "";
            }
            findPortal();
            consultCanais();
        //easyLogger.debug("Ponto D");
        } catch (Exception e) {
            //easyLogger.debug("Ponto D1");
            easyLogger.error(e.getMessage(), e);
        //easyLogger.debug("Ponto D2");

        } 
        return "";
    }

    /**
     * Pesquisa um Portal pelo código
     * @throws java.lang.Exception
     */
    public void findPortal() throws Exception {
        try {
            IPor_portalDAO porDAO = getPor_portalDAO();

            List<Por_portalT> list = porDAO.getByIdUsuario(usuarioLogado);
            portalT = list.size() > 0 ? list.get(0) : new Por_portalT();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * 
     * @throws java.lang.Exception
     */
    public void consultCanais() throws Exception {
        try {
            ICan_canalDAO canDAO = getCan_canalDAO();
            setCanais(canDAO.getByPortal(getPortalT()));
            boolean isMax = false;
            for (Can_canalT can_canalT : getCanais()) {
                if ("MAX".equals(can_canalT.getCan_tx_estado())) {
                    isMax = true;
                    break;
                }
            }
            if (isMax) {
                for (int i = getCanais().size() - 1; i >= 0; i--) {
                    if (!"MAX".equals(canais.get(i).getCan_tx_estado())) {
                        getCanais().remove(i);
                    }
                }
            }
            close();
            displayCanal();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }

    /**
     * Monta os canais no html foi complicado pra caramba
     * @throws java.lang.Exception
     */
    private void displayCanal() throws Exception {
        // Executando os canais 
        StringBuffer[] cols = new StringBuffer[portalT.getPor_nr_colunas()];
        for (int i = 0; i < cols.length; i++) {
            cols[i] = new StringBuffer();
        }

        StringBuffer table = new StringBuffer(readFile("containerCanal.htm"));
        StringBuffer tmp1 = new StringBuffer();
        //easyLogger.debug("Ponto K " + canais.size());

        for (int i = 0; i <= getCanais().size();) {
            int j = i;
            for (int cont = 0; cont < (portalT.getPor_nr_colunas()) &&
                    j < getCanais().size(); cont++) {
                cols[cont].append(executeJBChannel(getCanais().get(j)));
                j++;
            }
            i += portalT.getPor_nr_colunas();
        }
        //easyLogger.debug("Ponto M");

        StringBuffer tmp2 = new StringBuffer();
        for (int i = 0; i < cols.length; i++) {
            StringBuffer column = new StringBuffer(readFile("columnCanal.htm"));
            replace(column, "|CANAL|", cols[i].toString());
            tmp2.append(column);
        }
        StringBuffer row = new StringBuffer(readFile("rowCanal.htm"));
        replace(row, "|COLUMNCANAL|", tmp2.toString());
        tmp1.append(row);
        replace(table, "|ROWCANAL|", tmp1.toString());
        getOut().println(table.toString());
    }

    private String readRequest(InputStream is) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        while (br.ready()) {
            sb.append(br.readLine());
        }
        return sb.toString();
    }

    private String requestPage(String urlPage) throws Exception {
//        String urlTemp = getRequest().getRequestURL().toString();
//        int pos = urlTemp.lastIndexOf("/");
//        urlTemp = urlTemp.substring(0, pos + 1) + urlPage;
        URL urlCon = new URL(urlPage);
        HttpURLConnection http = (HttpURLConnection) urlCon.openConnection();
        return readRequest(http.getInputStream());
    }

    private Map copyMap(Map map) {
        Map mapTemp = new TreeMap();
        Set set = map.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Object nome = iter.next();
            mapTemp.put(nome, map.get(nome));
        }
        return mapTemp;
    }

    private String getPathBase(String path, String urlCanal) {
        String[] pastas = path.split("/");
        //int pos = path.lastIndexOf("/");
        StringBuffer newPath = new StringBuffer();
        for (int i = 0; i < pastas.length - 2; i++) {
            String string = pastas[i];
            //easyLogger.debug(string);
            newPath.append(string).append("/");
        }
        newPath.append(urlCanal);
        return newPath.toString();
    }

    private String executeJBChannel(Can_canalT canT) {
        try {
//easyLogger.debug("Ponto J");            
//            MODEL_CHANNEL = readFile("canalTemplate.htm");
            String url = canT.getCan_tx_url();
            StringBuffer sb = new StringBuffer(MODEL_CHANNEL.toString());
//            EasyJSPOut easyOut = new EasyJSPOut();
//            org.apache.jasper.runtime.JspRuntimeLibrary.include(getRequest(),
//                    getResponse(), url, easyOut, true);

            replace(sb, "|NAME_CANAL|", canT.getCan_tx_nome());
            replace(sb, "|DESCRIPTION_CANAL|", canT.getCan_tx_descricao());
            replace(sb, "|ID_CANAL|", canT.getCan_nr_id() + "");
            if ("MIN".equals(canT.getCan_tx_estado())) {
                replace(sb, "|CONTENT_CANAL|", "");
            } else {
                String str = "";
                // Se for pra usar iframe
                if ("S".equals(canT.getCan_tx_iframe())) {
                    StringBuffer iframe = new StringBuffer();
                    iframe.append("<iframe name=\"").append(canT.getCan_tx_nome()).append("\" frameborder=0 width=300 height=400 src=\"").append(canT.getCan_tx_url()).append("\" title=\"").append(canT.getCan_tx_descricao()).append(canT.getCan_tx_descricao()).append("\"");
                    iframe.append("</iframe>");
                    str = iframe.toString();
                } else { // Execuxao sem iframe
//easyLogger.debug("L");
                    // Montando a url a ser chamada

                    String urlTemp = getRequest().getRequestURL().toString();
                    int pos = urlTemp.lastIndexOf("/");
                    urlTemp = urlTemp.substring(0, pos + 1) + url;

                    // Verifica se é pra passar parametro para a execucao de um canal
                    if (getUrlCanal() != null && canT.getCan_tx_url().equals(urlCanalAtual)) {
                        urlTemp = getRequest().getRequestURL().toString();
                        pos = urlTemp.lastIndexOf("/");

                        //easyLogger.debug(getPathBase(getRequest().getRequestURL().toString(), urlCanal));
                        urlTemp = getPathBase(getRequest().getRequestURL().toString(), urlCanal);
                        /*if (urlCanal.toCharArray()[0] == '/')
                        urlTemp = urlTemp.substring(0, pos) + urlCanal;
                        else 
                        urlTemp = urlTemp.substring(0, pos + 1) + urlCanal;
                         */
                        RequestContext rc = new ServletRequestContext(getRequest());
                        boolean isMultiPart = FileUpload.isMultipartContent(rc);
                        if (!isMultiPart) {
                            req = copyMap(request.getParameterMap());// Obtendo os parametros do request

                        }
//                        Map req = copyMap(request.getParameterMap());// Obtendo os parametros do request

                        String[] ops = (String[]) req.get("op");
                        // coloca na sessao para que a execucao da página obtenha os parametros
                        getSession().setAttribute(urlTemp, req);
                        //getSession().setAttribute("portal", req);
                        url = getUrlCanal() + "?urlsession=" + urlTemp;
                    // nao deve limpar o op quando for dar um include em um jsp que está chamando outro dentro do canal
                    } else {
                        url += "?clearop=true&op= "; // limpa o op antes de dar o include nos canais

                    }
                    //easyLogger.debug("\nPonto 1");
                    //str = new String(requestPage(urlTemp));
                    EasyJSPOut easyOut = new EasyJSPOut();
                    // dá um include dinamicamente
                    try {

//                        easyLogger.debug(url);
                        if (url.indexOf(".jsp") > 0) {
//                            org.apache.jasper.runtime.JspRuntimeLibrary.include(getRequest(),
//                                    getResponse(), url, easyOut, true);
                        }
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                    }
                    //easyLogger.debug("\nPonto 2");
                    str = easyOut.toString();

                }
                replace(sb, "|CONTENT_CANAL|", str);
            }
            return sb.toString();
        } catch (Exception exception) {
            //exception.printStackTrace();
        }
        return "";
    }

    /**
     * Ler dados do formulário de upload
     * @param rc
     * @return
     * @throws java.lang.Exception
     */
    private Map readDataFormUpload(RequestContext rc) throws Exception {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String formulario = "";
        List items = upload.parseRequest(rc);
        Iterator iter = items.iterator();
        Map<String, Object> map = new TreeMap<String, Object>();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            String paramName = item.getFieldName();
            if (!item.isFormField()) {
                if (item.getName().length() > 0) {
                    EasyFileUpload efu = new EasyFileUpload();
                    efu.setContentType(item.getContentType());
                    efu.setPath(item.getName());
                    // getFilesUpload().add(item);
                    //easyLogger.debug(item.getFieldName());
                    getRequest().setAttribute(item.getFieldName(), efu);
                    byte[] paramValue = item.get();
                    //getRequest().setAttribute(paramName, paramValue);
                    map.put(paramName, paramValue);
                }
            } else {
                String paramValue = new String(item.get());
                //getRequest().setAttribute(paramName, paramValue);
                // if ("op".equals(paramName)) {
                //  setOp(paramValue);
                //} else {
                // Se aparecer dois ou mais campos com o mesmo nome 
                //é criado um array 
                if (getHashData().containsKey(paramName)) {
                    Object obj = getHashData().get(paramName);
                    String[] paramsNew = null;
                    if (obj instanceof String[]) {
                        String[] params = (String[]) obj;
                        paramsNew = new String[params.length + 1];
                        for (int i = 0; i < params.length; i++) {
                            paramsNew[i] = params[i];
                        }
                        paramsNew[params.length] = paramValue;
                    } else {
                        paramsNew = new String[2];
                        paramsNew[0] = (String) obj;
                        paramsNew[1] = paramValue;
                    }
                    getHashData().put(paramName, paramsNew);
                } else {
                    String[] param = {paramValue + ""};
                    map.put(paramName, param);
                }
            //}
            }
        }
        return map;
    }

    public String readFile(String pathModel) throws Exception {
        //File file = new File(pathModel);

        //FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(pathModel)));
        StringBuffer sb = new StringBuffer();
        while (br.ready()) {
            sb.append(br.readLine());
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

    /**
     * Substitui as macações no arquivo modelo
     * @param sb
     * @param markup
     * @param content
     */
    public void replace(StringBuffer sb, String markup, String content) {

        int i = sb.indexOf(markup);
        while (i > -1) {
            int f = i + markup.length();
            //sb.delete(i, f);
            sb.replace(i, f, content);
            i = sb.indexOf(markup);
        }
    }

    public void fechar() throws Exception {
        //modificaEstado("CLO");
        modificaEstado("NOR");// POR ENQUANTO O FECHAR MINIMIZA, 
    //ENQUANTO NAO TEM O GERENCIADOR DE CANAIS

    }

    public void normalizar() throws Exception {
        modificaEstado("NOR");

    }

    public void minimizar() throws Exception {
        modificaEstado("MIN");

    }

    public void maximizar() throws Exception {
        modificaEstado("MAX");
    }

    /**
     * Modifica o estado de acordo com a solicitacao do usuário NOR-normal, 
     * MIM-minimo, MAX-maximo
     * @param estado
     * @throws java.lang.Exception
     */
    private void modificaEstado(String estado) throws Exception {
        try {
            ICan_canalDAO canDAO = getCan_canalDAO();
            List<Can_canalT> list = canDAO.getById(getCan_canalT());
            setCan_canalT(list.size() > 0 ? list.get(0) : null);
            if (getCan_canalT() != null) {
                getCan_canalT().setCan_tx_estado(estado);
                canDAO.update(getCan_canalT());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Can_canalT> getCanais() {
        return canais;
    }

    public void setCanais(List<Can_canalT> canais) {
        this.canais = canais;
    }

    public Por_portalT getPortalT() {
        return portalT;
    }

    public void setPortalT(Por_portalT portalT) {
        this.portalT = portalT;
    }

    public Can_canalT getCan_canalT() {
        return can_canalT;
    }

    public void setCan_canalT(Can_canalT can_canalT) {
        this.can_canalT = can_canalT;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getUrlCanal() {
        return urlCanal;
    }

    public void setUrlCanal(String urlCanal) {
        this.urlCanal = urlCanal;
    }

    public String getUrlCanalAtual() {
        return urlCanalAtual;
    }

    public void setUrlCanalAtual(String urlCanalAtual) {
        this.urlCanalAtual = urlCanalAtual;
    }

    public Usu_usuarioT getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usu_usuarioT usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
