/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *

package br.com.easynet.easyportal.ws;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.jsp.JspFactory;

@WebService()
public class EasyPortalWS extends SystemBaseWS {

    
    @WebMethod(operationName = "autenticacao")
    public Usu_usuarioT autenticacao(@WebParam(name = "usuT")
    Usu_usuarioT usuT) {
//        try {
//            //TODO grava o seu código de implementação aqui:
//            usuT.setUsu_tx_senha(MD5.criptografar(usuT.getUsu_tx_senha()));
//            List<Usu_usuarioT> list = getUsu_usuarioDAO().getByAutentication(usuT);
//            return list.size()>0?list.get(0):null;
//        } catch (Exception ex) {
//            Logger.getLogger(EasyPortalWS.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

}
*/
