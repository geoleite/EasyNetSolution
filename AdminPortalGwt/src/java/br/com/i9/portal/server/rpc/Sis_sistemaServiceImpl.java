/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.server.bl.Sis_sistemaBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Sis_sistemaService", urlPatterns = {"/br.com.i9.portal.Main/rpc/sis_sistemaservice"})
public class Sis_sistemaServiceImpl extends RemoteServiceServlet implements Sis_sistemaService {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        Sis_sistemaBL bL = new Sis_sistemaBL();
        bL.insert(sis_sistemaT);
    }

    @Override
    public void update(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        Sis_sistemaBL bL = new Sis_sistemaBL();
        bL.update(sis_sistemaT);
    }

    @Override
    public List<Sis_sistemaTGWT> consult() throws Exception {
        Sis_sistemaBL bL = new Sis_sistemaBL();
        return bL.consult();
    }

    @Override
    public void delete(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        Sis_sistemaBL bL = new Sis_sistemaBL();
        bL.delete(sis_sistemaT);
    }

}
