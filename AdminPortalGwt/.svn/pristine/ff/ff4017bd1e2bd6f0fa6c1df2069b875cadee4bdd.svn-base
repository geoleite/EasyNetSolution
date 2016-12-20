package br.com.i9.portal.server.bl;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;
import static br.com.easynet.jb.BeanBase.ERROR;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Men_menuBL  extends SystemBusinessBase {

private List<Men_menuTGWT> list;
  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Men_menuTGWT men_menuT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      men_menuT.setMen_nr_id(getIncMenu());
      getMen_menuDAO().insert(men_menuT);
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
  public List<Men_menuTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
      return getMen_menuDAO().getAll();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }

  }  

  public List<Men_menuTGWT> consultByPerfil(Per_perfilTGWT per_perfilT) throws Exception {
        try {
            
            return  getMen_menuDAO().getByPerfil(per_perfilT);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            close();
        }
        return null;
    }

    public List<Men_menuTGWT> consultByNotPerfil(Per_perfilTGWT per_perfilT) throws Exception {
        try {
            return getMen_menuDAO().getByNotPerfil(per_perfilT);
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
  public boolean delete(Men_menuTGWT men_menuT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(men_menuT)) {
        getMen_menuDAO().delete(men_menuT);
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
  public boolean exist(Men_menuTGWT men_menuT) throws Exception {
   try {
	
      List<Men_menuTGWT> listTemp  = getMen_menuDAO().getByPK(men_menuT);

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
  public boolean update(Men_menuTGWT men_menuT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(men_menuT)) {
        getMen_menuDAO().update(men_menuT);
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
  public Men_menuTGWT findbyid(Men_menuTGWT men_menuT) throws Exception {
    try {
      List<Men_menuTGWT> listTemp  = getMen_menuDAO().getByPK( men_menuT);

      return listTemp.size()>0?listTemp.get(0):new Men_menuTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
