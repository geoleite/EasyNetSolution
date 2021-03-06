/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Opm_met_ope_perTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Opm_met_ope_perService;
import br.com.i9.portal.server.bl.Opm_met_ope_perBL;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Opm_met_ope_perService", urlPatterns = {"/br.com.i9.portal.Main/rpc/opm_met_ope_perservice"})
public class Opm_met_ope_perServiceImpl extends RemoteServiceServlet implements Opm_met_ope_perService {
    private Opm_met_ope_perBL opm_met_ope_perBL = new Opm_met_ope_perBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        opm_met_ope_perBL.insert(opm_met_ope_perT);
    }

    @Override
    public void delete(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        opm_met_ope_perBL.delete(opm_met_ope_perT);
    }
}
