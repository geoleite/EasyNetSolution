/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.rpc;

import br.com.easynet.easylog.jb.INotLog;
import static br.com.easynet.easyportal.jb.EasySecurityJB.USER_PRINCIPAL;
import static br.com.easynet.easyportal.jb.SystemBase.SISTEMA_EASYPORTAL;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author georgejunior
 */
public class ServiceRPCBase extends RemoteServiceServlet {
    private HttpSession session; 
    public static final String USER_PRINCIPAL = "user_principal";
    private Usu_usuarioT usuarioLogado;
    public final static String SISTEMA_EASYPORTAL = "EASYPORTAL";
    protected Logger easyLogger = Logger.getLogger(this.getClass());
    
    @Override
    protected void onBeforeRequestDeserialized(String serializedRequest) {
        super.onBeforeRequestDeserialized(serializedRequest); //To change body of generated methods, choose Tools | Templates.
        session = getThreadLocalRequest().getSession(true);
        String[] dados = serializedRequest.split("\\|");
        String metodo = dados[6];
        //Obtendo usuário logado
        setUsuarioLogado((Usu_usuarioT) getSession().getAttribute(USER_PRINCIPAL));
    }
    
    public String getParametro(String nomeParam) throws Exception {
        Par_parametroT parT = getParametro(SISTEMA_EASYPORTAL, nomeParam);
        return parT == null ? null : parT.getPar_tx_valor();
    }

    public Par_parametroT getParametro(int sisNrId, String nome) throws Exception {
//        Par_parametroT parT = new Par_parametroT();
//        parT.setSis_nr_id(sisNrId);
//        parT.setPar_tx_nome(nome);
//        return getPar_parametroDAO().getByPK(parT);
        /*
        Par_parametroT parT = null;
        //Primeiro verifica se já existe o objeto na memória
        
        String nomeParam = sisNrId + "_" + nome;
        if (parametroBDPortal.contains(nomeParam)) {
            parT = parametroBDPortal.get(nomeParam);
        } else {
            //Nao existe este parametro no objeto em memoria
            parT = new Par_parametroT();
            parT.setSis_nr_id(sisNrId);
            parT.setPar_tx_nome(nome);
            parT = getPar_parametroDAO().getByPK(parT);
            if (parT != null) {
                parametroBDPortal.put(nomeParam, parT);
            }
        }
        //Par_parametroT parT = super.getParametro(NOME_SISTEMA, nomeParam);
        return parT; 
        */
                return null;
    }

    public Par_parametroT getParametro(String nomeSistema, String nomeParametro) throws Exception {
        Sis_sistemaT sisT = null;
        /*
        if (sistemas.contains(nomeSistema)) {
            sisT = sistemas.get(nomeSistema);
        } else {
            sisT = new Sis_sistemaT();
            sisT.setSis_tx_nome(nomeSistema);
            sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
            sistemas.put(nomeSistema, sisT);
        }
        if (sisT == null) {
            return null;
        }
        return getParametro(sisT.getSis_nr_id(), nomeParametro);
        */
        return null;
    }    
    private void registreLog(boolean status) {
        /*
        try {
           
            String registrarLog = getParametro("REGISTRAR_LOG");
            boolean habilitarLog = true;
            if (registrarLog != null) {
                habilitarLog = Boolean.parseBoolean(registrarLog);
            }

            if (!(this instanceof INotLog  && habilitarLog)) {
                String op = getOp();
                if (op == null) {
                    op = "";
                }

                String classMetodo = getClass().getName();
                //               LogT logT = new LogT();
                //               logT.setLog_tx_classe(classMetodo);
                //               logT.setLog_tx_metodo(op);
                String usuario = "Anonimo";
                if (getUsuarioLogado() != null) {
                    usuario = getUsuarioLogado().getUsu_tx_nome();
//                logT.setLog_tx_usuario(usuario);
                }
                String sistema = getSistema(classMetodo);
                if (sistema == null) {
                    sistema = "Sistema não identificado!";
                }
                registroLog(application, sistema, classMetodo, op, usuario, request.getRemoteAddr(), status ? "S" : "N");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
*/
    }    

    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * @return the usuarioLogado
     */
    public Usu_usuarioT getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usu_usuarioT usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
