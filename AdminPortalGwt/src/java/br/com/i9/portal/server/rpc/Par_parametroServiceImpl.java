/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Par_parametroTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Par_parametroService;
import br.com.i9.portal.server.bl.Par_parametroBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Par_parametroService", urlPatterns = {"/br.com.i9.portal.Main/rpc/par_parametroservice"})
public class Par_parametroServiceImpl extends RemoteServiceServlet implements Par_parametroService {
private Par_parametroBL par_parametroBL = new Par_parametroBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Par_parametroTGWT par_parametroT) throws Exception {
        par_parametroBL.insert(par_parametroT);
    }

    @Override
    public void update(Par_parametroTGWT par_parametroT) throws Exception {
        par_parametroBL.update(par_parametroT);
    }

    @Override
    public List<Par_parametroTGWT> getParamSistema(Sis_sistemaTGWT sis_sistemaTGWT) throws Exception {
         return par_parametroBL.consultBySistema(sis_sistemaTGWT);
    }

    @Override
    public void delete(Par_parametroTGWT par_parametroT) throws Exception {
        par_parametroBL.delete(par_parametroT);
    }
}
