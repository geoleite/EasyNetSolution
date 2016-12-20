/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/met_metodoservice")
public interface Met_metodoService extends RemoteService {

    public String myMethod(String s);
    public boolean insert(Met_metodoTGWT met_metodoTGWT)throws Exception;
    public boolean update(Met_metodoTGWT met_metodoTGWT)throws Exception;
    public boolean delete(Met_metodoTGWT met_metodoTGWT)throws Exception;
    public List<Met_metodoTGWT> consultByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) throws Exception;
    public List<Met_metodoTGWT> consultByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) throws Exception;
    public List<Met_metodoTGWT> consultByOperacao(Met_metodoTGWT met_metodoT) throws Exception;
}
