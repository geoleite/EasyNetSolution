package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Sis_sistemaBL  extends SystemBusinessBase {


  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Sis_sistemaTGWT sis_sistemaT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      sis_sistemaT.setSis_nr_id(getIncSistema());
      getSis_sistemaDAO().insert(sis_sistemaT);
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
  public List<Sis_sistemaTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
        System.out.println("Consultando sistemas na base");
      return getSis_sistemaDAO().getAll();
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
  public boolean delete(Sis_sistemaTGWT sis_sistemaT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(sis_sistemaT)) {
        getSis_sistemaDAO().delete(sis_sistemaT);
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
  public boolean exist(Sis_sistemaTGWT sis_sistemaT) throws Exception {
   try {
	
      List<Sis_sistemaTGWT> listTemp  = getSis_sistemaDAO().getByPK(sis_sistemaT);

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
  public boolean update(Sis_sistemaTGWT sis_sistemaT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(sis_sistemaT)) {
        getSis_sistemaDAO().update(sis_sistemaT);
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
  public Sis_sistemaTGWT findbyid(Sis_sistemaTGWT sis_sistemaT) throws Exception {
    try {
      List<Sis_sistemaTGWT> listTemp  = getSis_sistemaDAO().getByPK( sis_sistemaT);

      return listTemp.size()>0?listTemp.get(0):new Sis_sistemaTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
