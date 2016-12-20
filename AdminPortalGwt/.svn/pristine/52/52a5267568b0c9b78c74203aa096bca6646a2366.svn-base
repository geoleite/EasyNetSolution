package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Pu_per_usuBL  extends SystemBusinessBase {


  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Pu_per_usuTGWT pu_per_usuT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      getPu_per_usuDAO().insert(pu_per_usuT);
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
  public List<Pu_per_usuTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
      return getPu_per_usuDAO().getAll();
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
  public boolean delete(Pu_per_usuTGWT pu_per_usuT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(pu_per_usuT)) {
        getPu_per_usuDAO().delete(pu_per_usuT);
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
  public boolean exist(Pu_per_usuTGWT pu_per_usuT) throws Exception {
   try {
	
      List<Pu_per_usuTGWT> listTemp  = getPu_per_usuDAO().getByPK(pu_per_usuT);

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
  public boolean update(Pu_per_usuTGWT pu_per_usuT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(pu_per_usuT)) {
        getPu_per_usuDAO().update(pu_per_usuT);
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
  public Pu_per_usuTGWT findbyid(Pu_per_usuTGWT pu_per_usuT) throws Exception {
    try {
      List<Pu_per_usuTGWT> listTemp  = getPu_per_usuDAO().getByPK( pu_per_usuT);

      return listTemp.size()>0?listTemp.get(0):new Pu_per_usuTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
