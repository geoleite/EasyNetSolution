/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.bl;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import static br.com.easynet.easyportal.jb.EasySecurityJB.USER_PRINCIPAL;
import br.com.easynet.easyportal.jb.SystemBase;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author georgejunior
 */
public class UsuUsuarioBL extends EasyPortalBusinessBase {
    
    public Usu_usuarioT autentication(Usu_usuarioT usuT) {
        try {            
            // Criptografando a Senha do usuário com o Algoritmo MD5
            usuT.setUsu_tx_senha(MD5.criptografar(usuT.getUsu_tx_senha()));
            List<Usu_usuarioT> list = getUsu_usuarioDAO().getByAutentication(usuT);
            close();
            if (list.size() == 0) {
                easyLogger.debug("Usuário ou senha inválidos!");
                registroLog("EasyPortal", this.getClass().getName(), "autentication", usuT.getUsu_tx_login(), "N");
                registrarTentativa(usuT);
                return null;
            } else {
                usuT = list.get(0);
                registroLog("EasyPortal", this.getClass().getName(), "autentication", usuT.getUsu_tx_login(), "S");
                zerarContadorTentativas(usuT);                
                return usuT;
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }
    
    public Res_recall_senhaT getRecallSenha(Usu_usuarioT usuT) {
        try {
            Res_recall_senhaT resT = new Res_recall_senhaT();
            resT.setUsu_nr_id(usuT.getUsu_nr_id());
            List<Res_recall_senhaT> list = getRes_recall_senhaDAO().getByPK(resT);
            return list.size() >0?list.get(0):null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }
    
    public void registrarTentativa(Usu_usuarioT usuT) {
        try {
            List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getByUsu_tx_login(usuT);
            if (listUsu.size() > 0) {
                usuT = listUsu.get(0);
                Res_recall_senhaT resT = new Res_recall_senhaT();
                resT.setUsu_nr_id(usuT.getUsu_nr_id());
                getRes_recall_senhaDAO().registrarTentativa(resT);
                checaNumeroTentativasAcesso(usuT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    
    public void zerarContadorTentativas(Usu_usuarioT usuT) {
        try {
            List<Usu_usuarioT> listUsu = getUsu_usuarioDAO().getByUsu_tx_login(usuT);
            if (listUsu.size() > 0) {
                usuT = listUsu.get(0);
                Res_recall_senhaT resT = new Res_recall_senhaT();
                resT.setUsu_nr_id(usuT.getUsu_nr_id());
                getRes_recall_senhaDAO().zerarContadorTentativas(resT);
                getRes_recall_senhaDAO().registrarAcesso(resT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    
    private void checaNumeroTentativasAcesso(Usu_usuarioT usuT) {
        try {
            if (usuT != null) {
                
                Par_parametroT parT = getParametro(SystemBase.SISTEMA_EASYPORTAL, "Numero_Tentativas");
                if (parT != null && !"0".equals(parT.getPar_tx_valor().trim())) {
                    Res_recall_senhaT resT = new Res_recall_senhaT();
                    resT.setUsu_nr_id(usuT.getUsu_nr_id());
                    List<Res_recall_senhaT> listRes = getRes_recall_senhaDAO().getByPK(resT);
                    if (listRes.size() > 0) {
                        resT = listRes.get(0);
                        int nrMaxTentativas = Integer.parseInt(parT.getPar_tx_valor());
                        if (resT.getRes_nr_tentativas() >= nrMaxTentativas) {
                            //Inativa o usuário
                            usuT.setUsu_tx_status("I");
                            getUsu_usuarioDAO().update(usuT);
                            easyLogger.debug("Numero de tentativas excedeu o limite para usuario: " + usuT.getUsu_tx_login());
                        }
                    }
                }
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void changePassword(Usu_usuarioT usu_usuarioT, String senhaAtual, 
                               String novaSenha, String confNovaSenha) 
                                throws Exception{
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            // Valida se a senha é diferente de null, no mínimo com 6 caracteres e igual a senha
            if (confNovaSenha == null) {
                throw new Exception("Senha nao pode ser nula!");
            } else if (confNovaSenha.trim().length() < 6) {
                throw new Exception("Senha deve conter 6 ou mais caracteres!");
            } else if (!confNovaSenha.equals(novaSenha)) {
                throw new Exception("Senha nao confirmada!");
            } else if (senhaAtual == null) {
                throw new Exception("Senha atual invalida!");
            } else if (senhaAtual.equals(confNovaSenha)) {
                throw new Exception("Nova senha deve ser diferente da senha atual!");
            } 
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(senhaAtual));
            
            if (usu_usuarioDAO.getByAutentication(usu_usuarioT).size() == 0) {
                throw new Exception("Senha atual nao confere!");
            }
            // Criptografando a Senha do usuário com o Algoritmo MD5  
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(novaSenha));
            usu_usuarioDAO.updateSenha(usu_usuarioT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            throw e;
        } finally {
            close();
        }
    }

    public void insertRecallPassword(Res_recall_senhaT resT) throws Exception {
        try {
            //res_recall_senhaT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            resT.setRes_dt_ultimoacesso(new Timestamp(System.currentTimeMillis()));
            resT.setRes_nr_tentativas(0);
            getRes_recall_senhaDAO().insert(resT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            throw e;
        } finally {
            close();
        }
    }

    public void updateRecallPassword(Res_recall_senhaT resT) throws Exception {
        try {
            //res_recall_senhaT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            resT.setRes_dt_ultimoacesso(new Timestamp(System.currentTimeMillis()));
            resT.setRes_nr_tentativas(0);
            getRes_recall_senhaDAO().update(resT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            throw e;
        } finally {
            close();
        }
    }
    
}
