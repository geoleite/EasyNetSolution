/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Per_perfilService;
import br.com.i9.portal.server.bl.Per_perfilBL;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Per_perfilService", urlPatterns = {"/br.com.i9.portal.Main/rpc/per_perfilservice"})
public class Per_perfilServiceImpl extends RemoteServiceServlet implements Per_perfilService {
 
    public Per_perfilBL pbl = new Per_perfilBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    public List<Per_perfilTGWT> getAll() throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        List<Per_perfilTGWT> list = pbl.consult();
        System.out.println("lista "+list.size());
        return list;
    }

    @Override
    public boolean insert(Per_perfilTGWT per_perfilTGWT) throws Exception {

        
        return pbl.insert(per_perfilTGWT);

    }

    @Override
    public boolean update(Per_perfilTGWT per_perfilTGWT) throws Exception {
        pbl.update(per_perfilTGWT);
        return true;
    }

    @Override
    public boolean delete(Per_perfilTGWT per_perfilTGWT) throws Exception {
        pbl.delete(per_perfilTGWT);
        return true;
    }

}
