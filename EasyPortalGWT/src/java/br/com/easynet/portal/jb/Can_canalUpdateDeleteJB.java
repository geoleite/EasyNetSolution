package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Can_canalUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Can_canalT can_canalT = new Can_canalT();

  public void setCan_canalT(Can_canalT can_canalT) {
    this.can_canalT = can_canalT;
  }

  public Can_canalT getCan_canalT() {	
    return can_canalT;
  }

	
  private List<Can_canalT> list;

  public List<Can_canalT> getList() {
    return list;
  }
  
  public void setList(List<Can_canalT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      can_canalT = new Can_canalT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        ICan_canalDAO can_canalDAO = getCan_canalDAO();
        can_canalDAO.delete(can_canalT);	 
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
      ICan_canalDAO can_canalDAO = getCan_canalDAO();
      List<Can_canalT> listTemp  = can_canalDAO.getById( can_canalT);	 

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
        ICan_canalDAO can_canalDAO = getCan_canalDAO();
        can_canalDAO.update(can_canalT);	 
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
      ICan_canalDAO can_canalDAO = getCan_canalDAO();
      List<Can_canalT> listTemp  = can_canalDAO.getById( can_canalT);	 

      can_canalT= listTemp.size()>0?listTemp.get(0):new Can_canalT();
      
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
	  String page = "can_canalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "can_canalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
