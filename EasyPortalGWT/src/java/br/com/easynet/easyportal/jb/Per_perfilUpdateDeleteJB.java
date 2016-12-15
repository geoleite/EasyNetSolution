package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Per_perfilUpdateDeleteJB extends EasySecurityJB {

  // Atributos e propriedades
    private Per_perfilT per_perfilT = new Per_perfilT();

  public void setPer_perfilT(Per_perfilT per_perfilT) {
    this.per_perfilT = per_perfilT;
  }

  public Per_perfilT getPer_perfilT() {	
    return per_perfilT;
  }

	
  private List<Per_perfilT> list;

  public List<Per_perfilT> getList() {
    return list;
  }
  
  public void setList(List<Per_perfilT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      per_perfilT = new Per_perfilT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
        per_perfilDAO.delete(per_perfilT);	 
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
      IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
      List<Per_perfilT> listTemp  = per_perfilDAO.getById( per_perfilT);	 

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
        IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
        per_perfilDAO.update(per_perfilT);	 
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
      IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
      List<Per_perfilT> listTemp  = per_perfilDAO.getById( per_perfilT);	 

      per_perfilT= listTemp.size()>0?listTemp.get(0):new Per_perfilT();
      
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
	  String page = "per_perfilConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "per_perfilConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
