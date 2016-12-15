package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Por_usuUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Por_usuT por_usuT = new Por_usuT();

  public void setPor_usuT(Por_usuT por_usuT) {
    this.por_usuT = por_usuT;
  }

  public Por_usuT getPor_usuT() {	
    return por_usuT;
  }

	
  private List<Por_usuT> list;

  public List<Por_usuT> getList() {
    return list;
  }
  
  public void setList(List<Por_usuT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      por_usuT = new Por_usuT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IPor_usuDAO por_usuDAO = getPor_usuDAO();
        por_usuDAO.delete(por_usuT);	 
        setMsg("Exclusao efetuada com sucesso!");
        clear();
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclusao!");
    } finally {
	close();
    }
  } 
  public boolean exist() throws Exception {
   try {
      IPor_usuDAO por_usuDAO = getPor_usuDAO();
      List<Por_usuT> listTemp  = por_usuDAO.getById( por_usuT);	 

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
        IPor_usuDAO por_usuDAO = getPor_usuDAO();
        por_usuDAO.update(por_usuT);	 
        setMsg("Alteracao efetuada com sucesso!");
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar alteracao!");
    } finally {
	close();
    }
  }   

  //Method Download Image   montando se houver algum campo do tipo binario
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      IPor_usuDAO por_usuDAO = getPor_usuDAO();
      List<Por_usuT> listTemp  = por_usuDAO.getById( por_usuT);	 

      por_usuT= listTemp.size()>0?listTemp.get(0):new Por_usuT();
      
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
	  String page = "por_usuConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "por_usuConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
