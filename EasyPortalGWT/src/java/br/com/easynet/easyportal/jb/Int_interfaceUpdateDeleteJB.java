package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Int_interfaceUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Int_interfaceT int_interfaceT = new Int_interfaceT();

  public void setInt_interfaceT(Int_interfaceT int_interfaceT) {
    this.int_interfaceT = int_interfaceT;
  }

  public Int_interfaceT getInt_interfaceT() {	
    return int_interfaceT;
  }

	
  private List<Int_interfaceT> list;

  public List<Int_interfaceT> getList() {
    return list;
  }
  
  public void setList(List<Int_interfaceT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
	//
  }

  public void clear() throws Exception {
    
      int_interfaceT = new Int_interfaceT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
        int_interfaceDAO.delete(int_interfaceT);	 
        setMsg("Exclusao efetuada com sucesso!");
        clear();
      } else {  
	setMsg(ERROR, "Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar exclusao!");	
    } finally {
	close();
    }
  } 
  public boolean exist() throws Exception {
   try {
      IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
      List<Int_interfaceT> listTemp  = int_interfaceDAO.getByPK( int_interfaceT);	 

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
        IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
        int_interfaceDAO.update(int_interfaceT);	 
        setMsg("Alteracao efetuada com sucesso!");	
      } else {  
	setMsg(ERROR,"Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar alteracao!");	
    } finally {
	close();
    }
  }   

// Method de lookup
// 


  //Method Download Image ? montando se houver algum campo do tipo bin?rio
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
      List<Int_interfaceT> listTemp  = int_interfaceDAO.getByPK( int_interfaceT);	 

      int_interfaceT= listTemp.size()>0?listTemp.get(0):new Int_interfaceT();
      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  /**
   * Volta para a p?gina de consulta
   */	
  public void consult() throws Exception {
	// TODO Consult
	try {
	  String page = "int_interfaceConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "int_interfaceConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
