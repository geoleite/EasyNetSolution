/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Mep_men_perTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Mep_men_perService;
import br.com.i9.portal.server.bl.Mep_men_perBL;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Mep_men_perService", urlPatterns = {"/br.com.i9.portal.Main/rpc/mep_men_perservice"})
public class Mep_men_perServiceImpl extends RemoteServiceServlet implements Mep_men_perService {

    private Mep_men_perBL mep_men_perBL = new Mep_men_perBL();
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public boolean insert(Mep_men_perTGWT mep_men_perTGWT) throws Exception {
        return mep_men_perBL.insert(mep_men_perTGWT);
    }

    @Override
    public boolean delete(Mep_men_perTGWT mep_men_perTGWT) throws Exception {
       return mep_men_perBL.delete(mep_men_perTGWT);
    }
}
