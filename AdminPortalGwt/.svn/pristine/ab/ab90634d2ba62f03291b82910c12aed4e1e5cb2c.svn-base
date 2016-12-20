package br.com.i9.portal.server.bl;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.easynet.bl.BusinessException;
import static br.com.easynet.jb.BeanBase.ERROR;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.server.dao.*;

/**
 * Classe Criada Automaticamente pelo "EasyNet Generate JDragon"
 */
public class LogBL extends SystemBusinessBase {

    /**
     * Insere um registro na tabela
     */
    public boolean insert(LogTGWT logT) throws Exception {

        try {
            if (!valide("insert")) {
                throw new BusinessException("Falha na seguranca !");
            }

            getLogDAO().insert(logT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    /**
     * Consulta todos os registros da tabela
     */
    public List<LogTGWT> consult(LogTGWT logT, String dtInicio, String dtFinal) throws Exception {
        try {
            return getLogDAO().getFilterGenerico(logT, dtInicio, dtFinal);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
        return null;
    }

    /**
     * Deletar um registro
     */
    public boolean delete(LogTGWT logT) throws Exception {
        try {
            if (!valide("delete")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(logT)) {

                getLogDAO().delete(logT);
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
    public boolean exist(LogTGWT logT) throws Exception {
        try {

            List<LogTGWT> listTemp = getLogDAO().getByPK(logT);

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
    public boolean update(LogTGWT logT) throws Exception {
        try {
            if (!valide("update")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(logT)) {
                getLogDAO().update(logT);
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
     * Obt?m os dados de um registro
     */
    public LogTGWT findbyid(LogTGWT logT) throws Exception {
        try {
            List<LogTGWT> listTemp = getLogDAO().getByPK(logT);

            return listTemp.size() > 0 ? listTemp.get(0) : new LogTGWT();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

}
