package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Usu_por_canUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Usu_por_canT usu_por_canT = new Usu_por_canT();

  public void setUsu_por_canT(Usu_por_canT usu_por_canT) {
    this.usu_por_canT = usu_por_canT;
  }

  public Usu_por_canT getUsu_por_canT() {	
    return usu_por_canT;
  }

	
  private List<Usu_por_canT> list;

  public List<Usu_por_canT> getList() {
    return list;
  }
  
  public void setList(List<Usu_por_canT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      usu_por_canT = new Usu_por_canT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
        usu_por_canDAO.delete(usu_por_canT);	 
        setMsg("Exclusao efetuada com sucesso!");
        clear();
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclus o!");	
    } finally {
	close();
    }
  } 
  public boolean exist() throws Exception {
   try {
      IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
      List<Usu_por_canT> listTemp  = usu_por_canDAO.getById( usu_por_canT);	 

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
        IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
        usu_por_canDAO.update(usu_por_canT);	 
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
      IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
      List<Usu_por_canT> listTemp  = usu_por_canDAO.getById( usu_por_canT);	 

      usu_por_canT= listTemp.size()>0?listTemp.get(0):new Usu_por_canT();
      
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
	  String page = "usu_por_canConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "usu_por_canConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
