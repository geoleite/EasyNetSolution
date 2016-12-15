package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Por_portalUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();

  public void setPor_portalT(Por_portalT por_portalT) {
    this.por_portalT = por_portalT;
  }

  public Por_portalT getPor_portalT() {	
    return por_portalT;
  }

	
  private List<Por_portalT> list;

  public List<Por_portalT> getList() {
    return list;
  }
  
  public void setList(List<Por_portalT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      por_portalT = new Por_portalT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IPor_portalDAO por_portalDAO = getPor_portalDAO();
        por_portalDAO.delete(por_portalT);	 
        setMsg("Exclusão efetuada com sucesso!");
        clear();
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclusão!");	
    } finally {
	close();
    }
  } 
  public boolean exist() throws Exception {
   try {
      IPor_portalDAO por_portalDAO = getPor_portalDAO();
      List<Por_portalT> listTemp  = por_portalDAO.getById( por_portalT);	 

      return listTemp.size()>0;      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
    return false;
	
  }

  public void update() throws Exception {
    try {
      if (exist()) {
        IPor_portalDAO por_portalDAO = getPor_portalDAO();
        por_portalDAO.update(por_portalT);	 
        setMsg("Alteracão efetuada com sucesso!");	
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar alteracão!");	
    } finally {
	close();
    }
  }   

  //Method Download Image   montando se houver algum campo do tipo binario
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      IPor_portalDAO por_portalDAO = getPor_portalDAO();
      List<Por_portalT> listTemp  = por_portalDAO.getById( por_portalT);	 

      por_portalT= listTemp.size()>0?listTemp.get(0):new Por_portalT();
      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  /**
   * Volta para a pagina de consulta
   */	
  public void consult() throws Exception {
	// TODO Consult
	try {
	  String page = "por_portalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "por_portalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
