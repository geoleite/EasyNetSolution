/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Pu_per_usuTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Pu_per_usuTService;
import br.com.i9.portal.server.bl.Pu_per_usuBL;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Pu_per_usuService", urlPatterns = {"/br.com.i9.portal.Main/rpc/pu_per_ususervice"})
public class Pu_per_usuTServiceImpl extends RemoteServiceServlet implements Pu_per_usuTService {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        Pu_per_usuBL bL = new Pu_per_usuBL();
        bL.insert(pu_per_usuT);
    }

    @Override
    public void delete(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        Pu_per_usuBL bL = new Pu_per_usuBL();
        bL.delete(pu_per_usuT);
    }
}
