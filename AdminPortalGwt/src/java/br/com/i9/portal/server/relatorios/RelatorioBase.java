package br.com.i9.portal.server.relatorios;

import br.com.i9.portal.server.bl.SystemBusinessBase;
import br.com.i9.portal.client.portal.portal.transfer.*;

/**
 *
 * @author i9ti
 */
public class RelatorioBase extends br.com.easynet.relatorio.RelatorioBase {

    protected SystemBusinessBase systemBase = new SystemBusinessBase();
    protected String urlPathlogo;

    @Override
    public void pageLoad() throws Exception {
        super.pageLoad();
        //getParameters().put("urlPathlogo", application.getRealPath("images/logomarca.jpg"));
    }

    public void imprimir(){
        
    }

}
