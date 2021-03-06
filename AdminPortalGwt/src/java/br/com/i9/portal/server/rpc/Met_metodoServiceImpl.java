/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Met_metodoService;
import br.com.i9.portal.server.bl.Met_metodoBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Met_metodoService", urlPatterns = {"/br.com.i9.portal.Main/rpc/met_metodoservice"})
public class Met_metodoServiceImpl extends RemoteServiceServlet implements Met_metodoService {
private Met_metodoBL met_metodoBL = new Met_metodoBL();
    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public boolean insert(Met_metodoTGWT met_metodoTGWT) throws Exception {
        return met_metodoBL.insert(met_metodoTGWT);
    }

    @Override
    public boolean update(Met_metodoTGWT met_metodoTGWT) throws Exception {
        return met_metodoBL.update(met_metodoTGWT);
    }

    @Override
    public boolean delete(Met_metodoTGWT met_metodoTGWT) throws Exception {
       return met_metodoBL.delete(met_metodoTGWT);
    }

    @Override
    public List<Met_metodoTGWT> consultByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) throws Exception {
        return met_metodoBL.consultByOperacaoPerfil(met_metodoT, per_perfilT);
    }

    @Override
    public List<Met_metodoTGWT> consultByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) throws Exception {
        return met_metodoBL.consultByOperacaoNaoPerfil(met_metodoT, per_perfilT);
    }

    @Override
    public List<Met_metodoTGWT> consultByOperacao(Met_metodoTGWT met_metodoT) throws Exception {
       return met_metodoBL.consultByOperacao(met_metodoT);
    }
}
