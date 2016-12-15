package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Can_porUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Can_porT can_porT = new Can_porT();

  public void setCan_porT(Can_porT can_porT) {
    this.can_porT = can_porT;
  }

  public Can_porT getCan_porT() {	
    return can_porT;
  }

	
  private List<Can_porT> list;

  public List<Can_porT> getList() {
    return list;
  }
  
  public void setList(List<Can_porT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      can_porT = new Can_porT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        ICan_porDAO can_porDAO = getCan_porDAO();
        can_porDAO.delete(can_porT);	 
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
      ICan_porDAO can_porDAO = getCan_porDAO();
      List<Can_porT> listTemp  = can_porDAO.getById( can_porT);	 

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
        ICan_porDAO can_porDAO = getCan_porDAO();
        can_porDAO.update(can_porT);	 
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
      ICan_porDAO can_porDAO = getCan_porDAO();
      List<Can_porT> listTemp  = can_porDAO.getById( can_porT);	 

      can_porT= listTemp.size()>0?listTemp.get(0):new Can_porT();
      
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
	  String page = "can_porConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "can_porConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
