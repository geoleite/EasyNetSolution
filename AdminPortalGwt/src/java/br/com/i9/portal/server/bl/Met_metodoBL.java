package br.com.i9.portal.server.bl;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;
import static br.com.easynet.jb.BeanBase.ERROR;

/**
 * Classe Criada Automaticamente pelo "EasyNet Generate JDragon"
 */
public class Met_metodoBL extends SystemBusinessBase {

    /**
     * Insere um registro na tabela
     */
    public boolean insert(Met_metodoTGWT met_metodoT) throws Exception {

        try {
            if (!valide("insert")) {
                throw new BusinessException("Falha na seguranca !");
            }
            met_metodoT.setMet_nr_id(getIncMetodo());
            getMet_metodoDAO().insert(met_metodoT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

    public List<Met_metodoTGWT> consultByOperacao(Met_metodoTGWT met_metodoT) {
        try {
            IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();
            return met_metodoDAO.getByOpe_nr_id(met_metodoT);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            close();
        }
        return null;
    }

    public List<Met_metodoTGWT> consultByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) {
        try {
            return getMet_metodoDAO().getByOperacaoPerfil(met_metodoT, per_perfilT);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
        return null;
    }

    public List<Met_metodoTGWT> consultByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT) {
        try {
            return getMet_metodoDAO().getByOperacaoNaoPerfil(met_metodoT, per_perfilT);
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
    public List<Met_metodoTGWT> consult() throws Exception {
        try {
            if (!valide("consult")) {
                throw new BusinessException("Falha na seguranca !");
            }
            return getMet_metodoDAO().getAll();
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
    public boolean delete(Met_metodoTGWT met_metodoT) throws Exception {
        try {
            if (!valide("delete")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(met_metodoT)) {
                getMet_metodoDAO().delete(met_metodoT);
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
    public boolean exist(Met_metodoTGWT met_metodoT) throws Exception {
        try {

            List<Met_metodoTGWT> listTemp = getMet_metodoDAO().getByPK(met_metodoT);

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
    public boolean update(Met_metodoTGWT met_metodoT) throws Exception {
        try {
            if (!valide("update")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(met_metodoT)) {
                getMet_metodoDAO().update(met_metodoT);
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
    public Met_metodoTGWT findbyid(Met_metodoTGWT met_metodoT) throws Exception {
        try {
            List<Met_metodoTGWT> listTemp = getMet_metodoDAO().getByPK(met_metodoT);

            return listTemp.size() > 0 ? listTemp.get(0) : new Met_metodoTGWT();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

}
