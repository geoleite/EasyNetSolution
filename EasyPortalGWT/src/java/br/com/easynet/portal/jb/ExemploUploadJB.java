/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.portal.jb;

import br.com.easynet.jb.EasyFileUpload;
import br.com.easynet.portal.transfer.UpT;

/**
 *
 * @author geoleite
 */
public class ExemploUploadJB extends SystemBase {
    private UpT upt = new UpT();
    
    public void cadastrar() throws Exception {
        EasyFileUpload efu = (EasyFileUpload)getRequest().getAttribute("upt.anexo");
        easyLogger.debug("axexo " + efu);
    }

    public UpT getUpt() {
        return upt;
    }

    public void setUpt(UpT upt) {
        this.upt = upt;
    }
}
