/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.servlet;

import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.SystemBase;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author as02
 */
public class Certificado extends HttpServlet {
    protected Logger easyLogger = Logger.getLogger(this.getClass());
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final String CLIENT_CERT = "javax.servlet.request.X509Certificate";

        //out.println(request.getAttribute(CLIENT_CERT).getClass().getName());
        X509Certificate[] certs = (X509Certificate[]) request.getAttribute(CLIENT_CERT);
        try {
            String cpfCnpj = "";
            for (int i = 0; i < 1; i++) {
                X509Certificate x509Certificate = certs[i];
                x509Certificate.checkValidity();
                String param = x509Certificate.getSubjectDN().getName();
                //out.println(getNome(param) + " " + getCpfCnpj(param));
                SystemBase sb = new SystemBase();
                sb.setSession(request.getSession(true));
                sb.setApplication(getServletContext());
                Usu_usuarioT usuT = new Usu_usuarioT();
                usuT.setUsu_tx_login(getCpfCnpj(param));
                List<Usu_usuarioT> listUsu = sb.getUsu_usuarioDAO().getByUsu_tx_login(usuT);
                if (listUsu.size() > 0) {
                    usuT = listUsu.get(0);
                    sb.getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, usuT);
                }
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            out.close();
        }
    }

    private String getNome(String param) {
        String[] params = param.split(",");

        return params[0].split("=")[1].split(":")[0];
    }

    private String getCpfCnpj(String param) {
        String[] params = param.split(",");
        return params[0].split(":")[1];
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
