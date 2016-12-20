/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.rpc;

import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.i9.portal.client.rpc.LogService;
import br.com.i9.portal.server.bl.LogBL;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author topfontes
 */
@WebServlet(name = "LogService", urlPatterns = {"/br.com.i9.portal.Main/rpc/logservice"})
public class LogServiceImpl extends RemoteServiceServlet implements LogService {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }

    @Override
    public List<LogTGWT> consult(LogTGWT logT, String dtinicio, String dtfinal) throws Exception {
        LogBL logBL = new LogBL();
        return logBL.consult(logT, dtinicio, dtfinal);
    }
}
