/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/usu_usuarioservice")
public interface Usu_usuarioService extends RemoteService {

    public String myMethod(String s);
    public void insert(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception;
    public void delete(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception;
    public void findById(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception;
    public void update(Usu_usuarioTGWT usu_usuarioTGWT) throws Exception;
    public List<Usu_usuarioTGWT> getAll() throws Exception;
    public List<Usu_usuarioTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception;
    public List<Usu_usuarioTGWT> consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception;
    public void gerarNovaSenha(Usu_usuarioTGWT usu_usuarioT) throws Exception;
    public List<Usu_usuarioTGWT> consultByNomeLogin(String login)throws Exception;
}
