/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.bl;

import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;

/**
 *
 * @author georgejunior
 */
public class PerPerfilBL extends EasyPortalBusinessBase {

    public List<Per_perfilT> getPerfisUsuario(Usu_usuarioT usuT) {
        try {
            List<Per_perfilT> listPer = getPer_perfilDAO().getByUsuario(usuT);
            return listPer;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }
}
