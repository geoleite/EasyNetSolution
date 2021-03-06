/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.server.bl.Ope_operacaoBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Ope_operacaoService", urlPatterns = {"/br.com.i9.portal.Main/rpc/ope_operacaoservice"})
public class Ope_operacaoServiceImpl extends RemoteServiceServlet implements Ope_operacaoService {

    public Ope_operacaoBL ope_operacaoBL = new Ope_operacaoBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        ope_operacaoBL.insert(ope_operacaoT);
    }

    @Override
    public void update(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        ope_operacaoBL.update(ope_operacaoT);
    }

    @Override
    public List<Ope_operacaoTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception {
        return ope_operacaoBL.consultByPerfil(per_perfilTGWT);
    }

    @Override
    public List<Ope_operacaoTGWT> consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception {
        return ope_operacaoBL.consultByNotPerfil(per_perfilTGWT);
    }

    @Override
    public List<Ope_operacaoTGWT> getAll() throws Exception {
        return ope_operacaoBL.consult();
    }

    @Override
    public List<Ope_operacaoTGWT> consultBySistema(Ope_operacaoTGWT opeT) throws Exception {
        return ope_operacaoBL.consultBySistema(opeT);
    }

    @Override
    public void delete(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        ope_operacaoBL.delete(ope_operacaoT);
    }
}
