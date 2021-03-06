package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Opm_met_ope_perBL  extends SystemBusinessBase {


  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      getOpm_met_ope_perDAO().insert(opm_met_ope_perT);
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
  public List<Opm_met_ope_perTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
      return getOpm_met_ope_perDAO().getAll();
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
  public boolean delete(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(opm_met_ope_perT)) {
        getOpm_met_ope_perDAO().delete(opm_met_ope_perT);
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
  public boolean exist(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
   try {
	
      List<Opm_met_ope_perTGWT> listTemp  = getOpm_met_ope_perDAO().getByPK(opm_met_ope_perT);

      return listTemp.size()>0;      
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
  public boolean update(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(opm_met_ope_perT)) {
        getOpm_met_ope_perDAO().update(opm_met_ope_perT);
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
  public Opm_met_ope_perTGWT findbyid(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
    try {
      List<Opm_met_ope_perTGWT> listTemp  = getOpm_met_ope_perDAO().getByPK( opm_met_ope_perT);

      return listTemp.size()>0?listTemp.get(0):new Opm_met_ope_perTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
