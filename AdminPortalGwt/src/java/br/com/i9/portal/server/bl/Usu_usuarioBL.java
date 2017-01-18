package br.com.i9.portal.server.bl;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;
import br.com.easynet.criptografia.MD5;
import br.com.easynet.criptografia.PasswordGenerate;
import br.com.easynet.email.SendMail;
import static br.com.easynet.jb.BeanBase.ERROR;
import java.util.ArrayList;

/**
 * Classe Criada Automaticamente pelo "EasyNet Generate JDragon"
 */
public class Usu_usuarioBL extends SystemBusinessBase {

    public List<Usu_usuarioTGWT> list = new ArrayList<Usu_usuarioTGWT>();

    /**
     * Insere um registro na tabela
     */
    public boolean insert(Usu_usuarioTGWT usu_usuarioT) throws Exception {

        try {
            if (!valide("insert")) {
                throw new BusinessException("Falha na seguranca !");
            }
            usu_usuarioT.setUsu_nr_id(getIncUsuario());
            getUsu_usuarioDAO().insert(usu_usuarioT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    public List<Usu_usuarioTGWT> consultByNomeLogin(String login) {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            return usu_usuarioDAO.getByNomeLogin(login);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
        return null;
    }

    public List<Usu_usuarioTGWT> consultByNotPerfil(Per_perfilTGWT per_perfilT) {
        try {
            return getUsu_usuarioDAO().getByNotPerfil(per_perfilT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public List<Usu_usuarioTGWT> consultByPerfil(Per_perfilTGWT per_perfilT) {
        try {
            return getUsu_usuarioDAO().getByPerfil(per_perfilT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    /**
     * Consulta todos os registros da tabela
     */
    public List<Usu_usuarioTGWT> consult() throws Exception {
        try {
            if (!valide("consult")) {
                throw new BusinessException("Falha na seguranca !");
            }

            return getUsu_usuarioDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }

    }

    /**
     * Deletar um registro
     */
    public boolean delete(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        try {
            if (!valide("delete")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(usu_usuarioT)) {
                getUsu_usuarioDAO().delete(usu_usuarioT);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * Verifica se o objeto existe na base
     */
    public boolean exist(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        try {

            List<Usu_usuarioTGWT> listTemp = getUsu_usuarioDAO().getByPK(usu_usuarioT);

            return listTemp.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }

    }

    /**
     * Realiza uma alterac?o na tabela
     */
    public boolean update(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        try {
            if (!valide("update")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(usu_usuarioT)) {
                getUsu_usuarioDAO().update(usu_usuarioT);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    public boolean updatePassword(Usu_usuarioTGWT usu_usuarioT, String novaSenha) throws Exception {
        try {
            if (!valide("updatePassword")) {
                throw new BusinessException("Falha na seguranca !");
            }

            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(novaSenha));
            getUsu_usuarioDAO().updateSenha(usu_usuarioT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    public String valideSenha(String senhaAtual, String novaSenha) {
        if (senhaAtual == null || senhaAtual.trim().length() == 0) {
            return "Senha atual nao pode ser vazia";
        } else if (senhaAtual.equalsIgnoreCase(novaSenha)) {
            return "A nova senha nao pode ser igual à senha atual";
        } else if (novaSenha == null || novaSenha.trim().length() < 6) {
            return "a nova senha nao pode ser vazia ou menor que 6";
        }
        return null;
    }

    public boolean updatePasswordByUsuario(Usu_usuarioTGWT usu_usuarioT, String novaSenha) throws Exception {
        try {
            if (!valide("updatePassword")) {
                throw new BusinessException("Falha na seguranca !");
            }
            String msg = valideSenha(usu_usuarioT.getUsu_tx_senha(), novaSenha);
            if (msg != null) {
                throw new Exception(msg); 
            } else {
                usu_usuarioT.setUsu_tx_senha(MD5.criptografar(usu_usuarioT.getUsu_tx_senha()));
                Usu_usuarioTGWT usuT = getUsu_usuarioDAO().getConfirmeSenha(usu_usuarioT);
                if (usuT == null) {
                    return false;
                } else {
                    usu_usuarioT.setUsu_tx_senha(MD5.criptografar(novaSenha));
                    getUsu_usuarioDAO().updateSenhaByUsuario(usu_usuarioT);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * Obt?m os dados de um registro
     */
    public Usu_usuarioTGWT findbyid(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        try {
            List<Usu_usuarioTGWT> listTemp = getUsu_usuarioDAO().getByPK(usu_usuarioT);

            return listTemp.size() > 0 ? listTemp.get(0) : new Usu_usuarioTGWT();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    public void enviarEmail(Usu_usuarioTGWT usu_usuarioT, String senha) {
        try {
            String emailServer = getParametro("emailServer");
            String sslServer = getParametro("sslServer");
            String userMail = getParametro("userMail");
            String portServer = getParametro("portServer");
            String fromName = getParametro("fromName");
            String fromEmail = getParametro("fromEmail");
            String fromPassword = getParametro("fromPassword");
            String subject = getParametro("subject");
            String toName = usu_usuarioT.getUsu_tx_nome();
            String toEmail = usu_usuarioT.getUsu_tx_email();
            String contentType = "text/html";
            String msg = getParametro("msg");
            msg = msg.replaceAll(";USUARIO;", usu_usuarioT.getUsu_tx_nome());
            msg = msg.replaceAll(";SENHA;", senha);
            SendMail sm = new SendMail(emailServer);
            sm.enviarEmailGmail(fromName, userMail, fromEmail, fromPassword, toName, toEmail, subject,
                    msg, contentType, portServer, sslServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarNovaSenha(Usu_usuarioTGWT usu_usuarioT) {
        try {
            List<Usu_usuarioTGWT> listTemp = getUsu_usuarioDAO().getByPK(usu_usuarioT);
            usu_usuarioT = listTemp.size() > 0 ? listTemp.get(0) : null;
            if (usu_usuarioT != null) {
                String novaSenha = PasswordGenerate.genereteStringNumber(8);
                novaSenha = novaSenha.toLowerCase().replace('l', 'i');
                usu_usuarioT.setUsu_tx_senha(MD5.criptografar(novaSenha));

                getUsu_usuarioDAO().updateSenha(usu_usuarioT);
                enviarEmail(usu_usuarioT, novaSenha);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
    }
}
