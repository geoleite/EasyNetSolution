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
public class Ope_operacaoBL extends SystemBusinessBase {

    /**
     * Insere um registro na tabela
     */
    public boolean insert(Ope_operacaoTGWT ope_operacaoT) throws Exception {

        try {
            if (!valide("insert")) {
                throw new BusinessException("Falha na seguranca !");
            }
            ope_operacaoT.setOpe_nr_id(getIncOperacao());
            getOpe_operacaoDAO().insert(ope_operacaoT);
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
    public List<Ope_operacaoTGWT> consult() throws Exception {
        try {
            if (!valide("consult")) {
                throw new BusinessException("Falha na seguranca !");
            }
            return getOpe_operacaoDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }

    }

    public List<Ope_operacaoTGWT> consultBySistema(Ope_operacaoTGWT ope_operacaoT) {
        try {
            return getOpe_operacaoDAO().getBySis_nr_id(ope_operacaoT);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
        return null;
    }

    public List<Ope_operacaoTGWT> consultByPerfil(Per_perfilTGWT per_perfilT) {
        try {
            IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
            return ope_operacaoDAO.getByPerfil(per_perfilT);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            close();
        }
        return null;
    }

    public List<Ope_operacaoTGWT> consultByNotPerfil(Per_perfilTGWT per_perfilT) {
        try {

            return getOpe_operacaoDAO().getByNotPerfil(per_perfilT);
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
    public boolean delete(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        try {
            if (!valide("delete")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(ope_operacaoT)) {
                getOpe_operacaoDAO().delete(ope_operacaoT);
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
    public boolean exist(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        try {

            List<Ope_operacaoTGWT> listTemp = getOpe_operacaoDAO().getByPK(ope_operacaoT);

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
    public boolean update(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        try {
            if (!valide("update")) {
                throw new BusinessException("Falha na seguranca !");
            }

            if (exist(ope_operacaoT)) {
                getOpe_operacaoDAO().update(ope_operacaoT);
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
    public Ope_operacaoTGWT findbyid(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        try {
            List<Ope_operacaoTGWT> listTemp = getOpe_operacaoDAO().getByPK(ope_operacaoT);

            return listTemp.size() > 0 ? listTemp.get(0) : new Ope_operacaoTGWT();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close();
        }
    }

}
