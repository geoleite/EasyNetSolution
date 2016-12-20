package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Per_perfilBL  extends SystemBusinessBase {


  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Per_perfilTGWT per_perfilT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      per_perfilT.setPer_nr_id(getIncPerfil());
      
      getPer_perfilDAO().insert(per_perfilT);
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
  public List<Per_perfilTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
        System.out.println("******************************");
        List<Per_perfilTGWT> list = getPer_perfilDAO().getAll();
        System.out.println("lista bl "+list.size());
      return list;
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
  public boolean delete(Per_perfilTGWT per_perfilT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(per_perfilT)) {
        getPer_perfilDAO().delete(per_perfilT);
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
  public boolean exist(Per_perfilTGWT per_perfilT) throws Exception {
   try {
	 
      List<Per_perfilTGWT> listTemp  = getPer_perfilDAO().getByPK(per_perfilT);

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
  public boolean update(Per_perfilTGWT per_perfilT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(per_perfilT)) {

        getPer_perfilDAO().update(per_perfilT);
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
  public Per_perfilTGWT findbyid(Per_perfilTGWT per_perfilT) throws Exception {
    try {

      List<Per_perfilTGWT> listTemp  = getPer_perfilDAO().getByPK( per_perfilT);

      return listTemp.size()>0?listTemp.get(0):new Per_perfilTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
