/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Res_recall_senhaTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Res_recall_senhaService;
import br.com.i9.portal.server.bl.Res_recall_senhaBL;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Res_recall_senhaService", urlPatterns = {"/br.com.i9.portal.Main/rpc/res_recall_senhaservice"})
public class Res_recall_senhaServiceImpl extends RemoteServiceServlet implements Res_recall_senhaService {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void zeraContadorTentativas(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
        Res_recall_senhaBL rbl = new Res_recall_senhaBL();
        rbl.zerarContadorTentativas(res_recall_senhaT);
    }
}
