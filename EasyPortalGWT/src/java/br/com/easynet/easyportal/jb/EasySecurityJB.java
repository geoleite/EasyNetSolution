/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.database.DataSet;
import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easylog.transfer.LogT;
import br.com.easynet.easyportal.dao.IPer_perfilDAO;
import br.com.easynet.easyportal.dao.Per_perfilDAO;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.jb.BeanBase;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;

/**
 *
 * @author geoleite
 */
public class EasySecurityJB extends SystemBase {

    public static final String USER_PRINCIPAL = "user_principal";
    private Usu_usuarioT usuarioLogado;
    public static final String PAGE_INDEX = "../autentication.jsp";
    public EasySecurityJB esjb = null;

    /**
     * Faz a seguranca de todas as aplicações
     *
     * @return
     * @throws java.lang.Exception
     */
    public String getExecute() throws Exception {
        try {
            if (esjb == null) {
                esjb = new EasySecurityJB();
                esjb.setApplication(application);
                esjb.setPage(page);
                esjb.pageLoad();
            }

            setUsuarioLogado((Usu_usuarioT) getSession().getAttribute(USER_PRINCIPAL));
            if (this instanceof INotSecurity) {
                registreLog(true);
                return super.getExecute();
            }

            if (getUsuarioLogado() == null) {
                getSession().invalidate();
                getResponse().sendRedirect(PAGE_INDEX + "?msg=Usuario não logado ou sessão expirada!");
            } else {
                // Verificando a permissão
                if (permissaoExecucao()) {
                    if (getOp() == null || "null".equals(getOp())) {
                        setOp("");
                    }
                    return super.getExecute();
                } else {
                    // Problema de permissão
                    setMsg(ERROR, "Falha: Voce nao possui permissao para executar esta operacao!");
                }
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            response.sendRedirect(PAGE_INDEX + "?msg=Usuario nao logado ou sessão expirada!");
        } finally {
            try {
                esjb.close();
            } catch (Exception e) {
            }
        }
        return "";
    }

    /**
     * Obtém o nome do sistema que está sendo executado
     *
     * @param esjb
     * @param classe
     * @return
     */
    private String getSistema(String classe) {
        try {
            DataSet ds = esjb.getSis_sistemaDAO().getSistemaByOperacao(classe);
            if (ds.getList().size() > 0) {
                return (String) ds.getList().get(0).getColumn("sis_tx_nome");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
        }
        return null;
    }

    private String readRequest() {
        if (this instanceof AutenticationJB) {
            return " ";
        }
        StringBuffer sb = new StringBuffer("");
        Map map = request.getParameterMap();
        Set keys = map.keySet();
        Iterator iter = keys.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            sb.append(key).append("=");
            try {
                String[] values = (String[]) map.get(key);
                for (int i = 0; i < values.length; i++) {
                    sb.append(values[i]).append(",");
                }
            } catch (Exception e) {
                sb.append("binario");
            }
            sb.append("&");
        }
        return sb.toString();
    }

    /**
     * Registra o log através de outra classe
     *
     * @param application
     * @param sistema
     * @param classe
     * @param method
     * @param usuario
     * @param ip
     * @param status
     */
    protected void registroLog(ServletContext application, String sistema,
            String classe, String method, String usuario,
            String ip, String status) {
        try {
            String registrarLog = getParametro("REGISTRAR_LOG");
            boolean habilitarLog = true;
            if (registrarLog != null) {
                habilitarLog = Boolean.parseBoolean(registrarLog);
            }
            if (habilitarLog) {
                String op = getOp();
                if (op == null) {
                    op = "";
                }

                //EasySecurityJB esjb = new EasySecurityJB();
                //esjb.setApplication(application);
                LogT logT = new LogT();
                logT.setLog_tx_classe(classe);
                logT.setLog_tx_metodo(op);
                logT.setLog_tx_usuario(usuario);
                logT.setLog_tx_sistema(sistema);
                logT.setLog_tx_ip(ip);
                logT.setLog_tx_status(status);
                logT.setLog_tx_parametro(readRequest());
                logT.setLog_nr_id(esjb.getAutoIncrementoLong("EasyPortal", "Log", "log_nr_id"));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                logT.setLog_dt_datahora(ts);
                esjb.getLogDAO().insert(logT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            try {
                esjb.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Registra o log na tabela de LOG
     *
     * @param status
     */
    private void registreLog(boolean status) {
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

    }

    /**
     * Valida a permissão da operação no momento da execução
     *
     * @return
     * @throws java.lang.Exception
     */
    private boolean permissaoExecucao() throws Exception {
        try {
            String op = getOp();

            if (op == null) {
                op = "";
            }

            String classMetodo = getClass().getName();
            DataSet ds = null;
            if (op == null || op.trim().length() == 0) {
                //String sql = "select pu.usu_nr_id from usr_portal.pu_per_usu pu, usr_portal.opm_met_ope_per op , usr_portal.ope_operacao ope where pu.usu_nr_id=? and pu.per_nr_id=op.per_nr_id and op.ope_nr_id=ope.ope_nr_id and ope.ope_tx_classe=? and ope.ope_tx_status='A'";
                //Object[] param = {usuarioLogado.getUsu_nr_id(), classMetodo};
                ds = esjb.getUsu_usuarioDAO().checaPermissao(usuarioLogado.getUsu_nr_id(), classMetodo);
            } else {
                //Object[] param = {usuarioLogado.getUsu_nr_id(), op, classMetodo};
                //String sql = "select met.met_nr_id from usr_portal.pu_per_usu pu, usr_portal.opm_met_ope_per op, usr_portal.met_metodo met, usr_portal.ope_operacao ope where pu.usu_nr_id=? and pu.per_nr_id=op.per_nr_id and op.sis_nr_id=met.sis_nr_id and op.met_nr_id=met.met_nr_id and met.met_tx_metodo=? and ope.sis_nr_id=met.sis_nr_id and ope.ope_nr_id=met.ope_nr_id and ope.ope_tx_classe=? and ope.ope_tx_status='A' and met.met_tx_status='A'";
                ds = esjb.getUsu_usuarioDAO().checaPermissao(usuarioLogado.getUsu_nr_id(), op, classMetodo);
            }
            registreLog(ds.size() > 0);
            return ds.size() > 0;
        } catch (Exception e) {
            setMsg("Falha: O usuário não possui permissão para executar esta operação!");
            easyLogger.error(e.getMessage(), e);
        } finally {
            try {
                esjb.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * Retorna os Perfis do usuário Logadoe
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Per_perfilT> getPerfilUser() throws Exception {
        List<Per_perfilT> list = null;
        try {
            IPer_perfilDAO perDao = getPer_perfilDAO();
            list = perDao.getByUsuario(usuarioLogado);
            perDao.close();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return list;
    }

    public Usu_usuarioT getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usu_usuarioT usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
