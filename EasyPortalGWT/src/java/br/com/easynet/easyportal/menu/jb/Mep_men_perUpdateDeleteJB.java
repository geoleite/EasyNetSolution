package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Mep_men_perUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Mep_men_perT mep_men_perT = new Mep_men_perT();

  public void setMep_men_perT(Mep_men_perT mep_men_perT) {
    this.mep_men_perT = mep_men_perT;
  }

  public Mep_men_perT getMep_men_perT() {	
    return mep_men_perT;
  }

	
  private List<Mep_men_perT> list;

  public List<Mep_men_perT> getList() {
    return list;
  }
  
  public void setList(List<Mep_men_perT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  public void clear() throws Exception {
    
      mep_men_perT = new Mep_men_perT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();
        mep_men_perDAO.delete(mep_men_perT);	 
        setMsg("Exclus o efetuada com sucesso!");
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
      IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();
      List<Mep_men_perT> listTemp  = mep_men_perDAO.getById( mep_men_perT);	 

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
        IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();
        mep_men_perDAO.update(mep_men_perT);	 
        setMsg("Altera  o efetuada com sucesso!");	
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar altera  o!");	
    } finally {
	close();
    }
  }   

  //Method Download Image   montando se houver algum campo do tipo binario
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();
      List<Mep_men_perT> listTemp  = mep_men_perDAO.getById( mep_men_perT);	 

      mep_men_perT= listTemp.size()>0?listTemp.get(0):new Mep_men_perT();
      
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
	  String page = "mep_men_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "mep_men_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
