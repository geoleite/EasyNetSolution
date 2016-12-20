/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.Men_menuService;
import br.com.i9.portal.server.bl.Men_menuBL;
import com.google.gwt.user.client.Window;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "Men_menuService", urlPatterns = {"/br.com.i9.portal.Main/rpc/men_menuservice"})
public class Men_menuServiceImpl extends RemoteServiceServlet implements Men_menuService {

    public Men_menuBL men_menuBL = new Men_menuBL();

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public void insert(Men_menuTGWT men_menuT) throws Exception {
        men_menuBL.insert(men_menuT);
    }

    @Override
    public void updade(Men_menuTGWT men_menuT) throws Exception {
        men_menuBL.update(men_menuT);
    }

    @Override
    public void delete(Men_menuTGWT men_menuT) throws Exception {
        men_menuBL.delete(men_menuT);
    }

    @Override
    public List<Men_menuTGWT> consult() throws Exception {
        return men_menuBL.consult();
    }

    @Override
    public List<Men_menuTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception{
       
        return men_menuBL.consultByPerfil(per_perfilTGWT);
    }

    @Override
    public List<Men_menuTGWT> consultByNotPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception{
        return men_menuBL.consultByNotPerfil(per_perfilTGWT);
    }

}
