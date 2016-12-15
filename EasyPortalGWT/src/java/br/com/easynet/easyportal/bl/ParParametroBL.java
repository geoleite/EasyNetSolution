/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.bl;

import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import static br.com.easynet.jb.BeanBase.ERROR;
import java.util.List;

/**
 *
 * @author georgejunior
 */
public class ParParametroBL extends EasyPortalBusinessBase {
    private Sis_sistemaT getSistema(String nomeSistema) throws Exception {
        int sisNrId = 0;
        Sis_sistemaT sisT = new Sis_sistemaT();
        sisT.setSis_tx_nome(nomeSistema);
        return getSis_sistemaDAO().getBySis_tx_nome(sisT);
    }
    
    public List<Par_parametroT> getBySystem(String nomeSistema) {
        try {
            Sis_sistemaT sisT = getSistema(nomeSistema);
            if (sisT != null) {
                Par_parametroT par_parametroT = new Par_parametroT();
                par_parametroT.setSis_nr_id(sisT.getSis_nr_id());
                return getPar_parametroDAO().getBySis_nr_id(par_parametroT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }
}
