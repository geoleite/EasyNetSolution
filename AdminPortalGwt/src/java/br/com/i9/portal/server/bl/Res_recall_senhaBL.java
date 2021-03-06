package br.com.i9.portal.server.bl	;

import java.util.List;
import br.com.jdragon.dao.DAOFactory;
import br.com.i9.portal.server.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.easynet.bl.BusinessException;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Res_recall_senhaBL  extends SystemBusinessBase {


  /**
   * Insere um registro na tabela
   */	  
  public boolean insert(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    
    try {
      if (!valide("insert")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("insert"));
      }
      Res_recall_senhaDAO res_recall_senhaDAO =  getRes_recall_senhaDAO();
      res_recall_senhaDAO.insert(res_recall_senhaT);	 
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("Problema ao inserir ".concat("Res_recall_senha"));
    } finally {
	close();
    }
  } 

  /**
   * Consulta todos os registros da tabela
   */	
  public List<Res_recall_senhaTGWT> consult() throws Exception {
    try {
      if (!valide("consult")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("consult"));
      }
      Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
      return res_recall_senhaDAO.getAll();	 
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
  public boolean delete(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    try {
      if (!valide("delete")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("delete"));
      }
      if (exist(res_recall_senhaT)) {
        Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
        res_recall_senhaDAO.delete(res_recall_senhaT);	 
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
   * Limpar o contador de Tentativas de acesso do usuário
   * @param res_recall_senhaT
   * @return
   * @throws Exception
   */
  public boolean zerarContadorTentativas(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    try {
      if (!valide("zerarContadorTentativas")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("zerarContadorTentativas"));
      }
      if (exist(res_recall_senhaT)) {
        Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
        res_recall_senhaDAO.zerarContadorTentativas(res_recall_senhaT);
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

  public boolean registrarTentativas(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    try {
      if (!valide("registrarTentativas")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("registrarTentativas"));
      }
      if (exist(res_recall_senhaT)) {
        Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
        res_recall_senhaDAO.registrarTentativa(res_recall_senhaT);
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
  public boolean exist(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
   try {
      Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
      List<Res_recall_senhaTGWT> listTemp  = res_recall_senhaDAO.getByPK(res_recall_senhaT);	 

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
  public boolean update(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    try {
      if (!valide("update")) {
	throw new BusinessException("Tentativa de execucao indevida da operacao".concat("update"));
      }
      if (exist(res_recall_senhaT)) {
        Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
        res_recall_senhaDAO.update(res_recall_senhaT);	 
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
  public Res_recall_senhaTGWT findbyid(Res_recall_senhaTGWT res_recall_senhaT) throws Exception {
    try {
      Res_recall_senhaDAO res_recall_senhaDAO = getRes_recall_senhaDAO();
      List<Res_recall_senhaTGWT> listTemp  = res_recall_senhaDAO.getByPK( res_recall_senhaT);	 
      return listTemp.size()>0?listTemp.get(0):new Res_recall_senhaTGWT();      
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
	close();
    }
  }    

}
