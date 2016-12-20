package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Par_parametroBL  extends SystemBusinessBase {

    public List<Par_parametroTGWT> consultBySistema(Sis_sistemaTGWT sis_sistemaTGWT) throws Exception {
        try {
            Par_parametroTGWT  par_parametroT = new Par_parametroTGWT();
            par_parametroT.setSis_nr_id(sis_sistemaTGWT.getSis_nr_id());
            List<Par_parametroTGWT> list  = getPar_parametroDAO().getBySis_nr_id(par_parametroT);
            return list;
            //return encodeParametros(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
//private List<Par_parametroTGWT> encodeParametros(List<Par_parametroTGWT> listTemp) {
//        
//    if (list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                Par_parametroT parT = list.get(i);
//                parT.setPar_tx_valor(encodeHtml(parT.getPar_tx_valor()));
//            }
//        }
//        return listTemp;
//    }
  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Par_parametroTGWT par_parametroT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Falha na seguranca !");
      }
      par_parametroT.setPar_nr_id(getIncParametro());
      getPar_parametroDAO().insert(par_parametroT);
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
  public List<Par_parametroTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Falha na seguranca !");
      }
      
      return getPar_parametroDAO().getAll();
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
  public boolean delete(Par_parametroTGWT par_parametroT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(par_parametroT)) {
        getPar_parametroDAO().delete(par_parametroT);
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
  public boolean exist(Par_parametroTGWT par_parametroT) throws Exception {
   try {
	
      List<Par_parametroTGWT> listTemp  = getPar_parametroDAO().getByPK(par_parametroT);

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
  public boolean update(Par_parametroTGWT par_parametroT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Falha na seguranca !");
      }

      if (exist(par_parametroT)) {
        getPar_parametroDAO().update(par_parametroT);
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
  public Par_parametroTGWT findbyid(Par_parametroTGWT par_parametroT) throws Exception {
    try {
      List<Par_parametroTGWT> listTemp  = getPar_parametroDAO().getByPK( par_parametroT);

      return listTemp.size()>0?listTemp.get(0):new Par_parametroTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
