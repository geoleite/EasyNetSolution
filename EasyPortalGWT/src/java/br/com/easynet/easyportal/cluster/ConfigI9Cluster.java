/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.cluster;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author geoleite
 */
@WebServlet(name = "ConfigI9Cluster", urlPatterns = {"/ConfigI9Cluster"}, loadOnStartup = 1)
public class ConfigI9Cluster extends HttpServlet {
    public final static String PATH_BASE = "/WEB-INF/";
    public static String REAL_PATH_BASE = "./";
    protected Logger easyLogger = Logger.getLogger(this.getClass());
    public void init(ServletConfig config) throws ServletException {
        REAL_PATH_BASE = config.getServletContext().getRealPath(PATH_BASE);
        easyLogger.debug("REAL_PATH_BASE " + REAL_PATH_BASE);
    }
}
