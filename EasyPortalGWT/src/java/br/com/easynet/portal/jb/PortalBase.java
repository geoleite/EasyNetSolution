/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.portal.jb;

import br.com.easynet.jb.BeanBase;
import br.com.easynet.portal.dao.Can_canalDAO;
import br.com.easynet.portal.dao.ICan_canalDAO;
import br.com.easynet.portal.dao.IPor_portalDAO;
import br.com.easynet.portal.dao.Por_portalDAO;
import br.com.easynet.portal.transfer.Can_canalT;
import br.com.easynet.portal.transfer.Por_portalT;
import br.com.jdragon.dao.Util;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class PortalBase extends SystemBase {

    public final static String MAP_BEAN = "mapbean";
    public final static String CHANNEL = "CHANNEL";
    private String classjb, url, pageActual;
    private Map<String, BeanBase> mapBean;
    private List<Can_canalT> canais;
    private Por_portalT portalT = new Por_portalT();
    private Can_canalT can_canalT = new Can_canalT();

    /**
     * Cria um atributo no request com o nome do bean que foi executado
     * @param bb
     */
    private void setBeanRequestAttribute(BeanBase bb) {
        String classTemp = classjb;
        classTemp = classTemp.replace('.', ',');
        String[] param = classTemp.split(",");
        String jbName = Util.getFirstCharLower(param[param.length - 1]);
        getRequest().setAttribute(jbName, bb);
    }

    /**
     * Instacia a classe defina pelo canal no portal
     * @return
     * @throws java.lang.Exception
     */
    private BeanBase createInstance() throws Exception {
        try {

            if (classjb != null && classjb.trim().length() > 0) {
                BeanBase bb = (BeanBase) Class.forName(classjb).newInstance();
                bb.setPage(getPage());
                mapBean = (Map) getRequest().getAttribute(MAP_BEAN);
                if (mapBean == null) {
                    mapBean = new TreeMap<String, BeanBase>();
                    getRequest().setAttribute(MAP_BEAN, mapBean);
                }
                mapBean.put(MAP_BEAN, bb);
                setBeanRequestAttribute(bb);
                return bb;
            }
        } catch (Exception exception) {
            throw new Exception("Definition Class of Portal not found!");
        }
        return null;
    }

    private void executeJBChannel(Can_canalT canT) {
        try {
            //classjb = canT.getCan_tx_class();
            BeanBase bb = createInstance();
            if (bb != null) {
                //setExecuteAjax(true);
                bb.getExecute();
            }
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }
    
    private String getParameter() throws Exception{
        StringBuffer param = new StringBuffer();
        Enumeration enume = getRequest().getParameterNames();
        while (enume.hasMoreElements()) {
            String name = (String)enume.nextElement();
            Object value = getRequest().getParameter(name);
            param.append(name).append("=").append(value).append("&");
        }
        return param.toString();
    }
            
    private ByteArrayOutputStream readRequest(InputStream is) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(is);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int control = bis.read(data);        
        while (control != -1) {
            baos.write(data);
            control = bis.read(data);        
        }
        return baos;
    }
    private byte[] requestPage(String urlPage) throws Exception {
        String urlTemp = getRequest().getRequestURL().toString();
        int pos = urlTemp.lastIndexOf("/");
//        pageActual = urlTemp.substring(pos + 1, urlTemp.length());
        urlTemp = urlTemp.substring(0, pos + 1) + urlPage;
        URL urlCon = new URL(urlTemp);
        HttpURLConnection http = (HttpURLConnection)urlCon.openConnection();
        return readRequest(http.getInputStream()).toByteArray();
    }
    private byte[] executeChannel() throws Exception {
        String param = getParameter();
        return requestPage(url + "?" + param);
    } 
        /**
         * Execução no formato do portal
         * @return
         * @throws java.lang.Exception
         */
    public String getExecute() throws Exception {
        if (url != null) {
            getRequest().setAttribute( getRequest().getQueryString(),executeChannel());
        }
/*
          Map map = getRequest().getParameterMap();

        Object obj = map.get("classjb");
        //getPage().
        easyLogger.debug(obj == null ? "" : obj.getClass().getName() + " " + getOp());
        if (obj != null) {
            setHashData(map);
            BeanBase bb = createInstance();
            if (bb != null) {
                bb.setOp(getOp());
                setOp("");
                easyLogger.debug("operacao " + bb.getOp());
                //setExecuteAjax(true);
                bb.getExecute();
            }
        } else {
            super.getExecute();
        }
 */
        findPortal();
        consultCanais();
        return "";
    }

    public void fechar() throws Exception {
        modificaEstado("CLO");
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

    /**
     * Pesquisa um Portal pelo código
     * @throws java.lang.Exception
     */
    public void findPortal() throws Exception {
        try {
            IPor_portalDAO porDAO = getPor_portalDAO();
            portalT.setPor_nr_id(1);
            List<Por_portalT> list = porDAO.getById(portalT);
            portalT = list.size() > 0 ? list.get(0) : new Por_portalT();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 
     * @throws java.lang.Exception
     */
    public void consultCanais() throws Exception {
        try {
            ICan_canalDAO canDAO = getCan_canalDAO();
            canais = canDAO.getByPortal(portalT);
            boolean isMax = false;
            for (Can_canalT can_canalT : canais) {
                if ("MAX".equals(can_canalT.getCan_tx_estado())) {
                    isMax = true;
                    break;
                }
            }
            if (isMax) {
                for (int i = canais.size() - 1; i >= 0; i--) {
                    if (!"MAX".equals(canais.get(i).getCan_tx_estado())) {
                        canais.remove(i);
                    }
                }
            }
            
        // Executando os canais 
            for (Can_canalT can_canalT : canais) {
                if (!"MIN".equals(can_canalT.getCan_tx_estado()) )
                    executeJBChannel(can_canalT);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getClassjb() {
        return classjb;
    }

    public void setClassjb(String classjb) {
        this.classjb = classjb;
    }

    public Map getMapBean() {
        return mapBean;
    }

    public void setMapBean(Map map) {
        this.mapBean = map;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageActual() {
        return pageActual;
    }

    public void setPageActual(String pageActual) {
        this.pageActual = pageActual;
    }
}
