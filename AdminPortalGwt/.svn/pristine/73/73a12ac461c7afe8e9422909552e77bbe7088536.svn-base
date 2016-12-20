/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Usu_usuarioService;
import br.com.i9.portal.server.bl.Usu_usuarioBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Usu_usuarioService", urlPatterns = {"/br.com.i9.portal.Main/rpc/usu_usuarioservice"})
public class Usu_usuarioServiceImpl extends RemoteServiceServlet implements Usu_usuarioService {

    public Usu_usuarioBL ubl = new Usu_usuarioBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception {
        ubl.insert(usu_usuarioTGWT);
    }

    @Override
    public void findById(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception {
        ubl.findbyid(usu_usuarioTGWT);
    }

    @Override
    public void update(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception {
        ubl.update(usu_usuarioTGWT);
    }

    @Override
    public List<Usu_usuarioTGWT> getAll() throws Exception {
        return ubl.consult();
    }

    @Override
    public List<Usu_usuarioTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception {
        return ubl.consultByPerfil(per_perfilTGWT);
    }

    @Override
    public List<Usu_usuarioTGWT> consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception {
        return ubl.consultByNotPerfil(per_perfilTGWT);
    }

    @Override
    public void gerarNovaSenha(Usu_usuarioTGWT usu_usuarioT) throws Exception {
       
       ubl.gerarNovaSenha(usu_usuarioT);
    }

    @Override
    public List<Usu_usuarioTGWT> consultByNomeLogin(String login) throws Exception {
        return ubl.consultByNomeLogin(login);
    }

    @Override
    public void delete(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception {
        ubl.delete(usu_usuarioTGWT);
    }
}
