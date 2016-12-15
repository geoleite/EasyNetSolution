/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/ope_operacaoservice")
public interface Ope_operacaoService extends RemoteService {

    public String myMethod(String s);

    public void insert(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public void update(Ope_operacaoTGWT ope_operacaoT) throws Exception;
    
    public void delete(Ope_operacaoTGWT ope_operacaoT) throws Exception;

    public List<Ope_operacaoTGWT> getAll() throws Exception;

    public List<Ope_operacaoTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception;

    public List<Ope_operacaoTGWT> consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT) throws Exception;
    
    public List<Ope_operacaoTGWT> consultBySistema(Ope_operacaoTGWT opeT) throws Exception;

}
