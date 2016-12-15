package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Con_componente_negadoUpdateDeleteJB extends SystemBase {

  // Atributos e propriedades
    private Con_componente_negadoT con_componente_negadoT = new Con_componente_negadoT();

  public void setCon_componente_negadoT(Con_componente_negadoT con_componente_negadoT) {
    this.con_componente_negadoT = con_componente_negadoT;
  }

  public Con_componente_negadoT getCon_componente_negadoT() {	
    return con_componente_negadoT;
  }

	
  private List<Con_componente_negadoT> list;

  public List<Con_componente_negadoT> getList() {
    return list;
  }
  
  public void setList(List<Con_componente_negadoT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
	//
		consultPi_per_int();

  }

  public void clear() throws Exception {
    
      con_componente_negadoT = new Con_componente_negadoT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
        con_componente_negadoDAO.delete(con_componente_negadoT);	 
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
      ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
      List<Con_componente_negadoT> listTemp  = con_componente_negadoDAO.getByPK( con_componente_negadoT);	 

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
        ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
        con_componente_negadoDAO.update(con_componente_negadoT);	 
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
	private List<Pi_per_intT> listpi_per_int;
	public List<Pi_per_intT> getListpi_per_int() {
		return listpi_per_int;
	}

	 public void setListpi_per_int(List<Pi_per_intT> list) {
		this.listpi_per_int=list;
	}
	public void consultPi_per_int() throws Exception {
		try {
			IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();
			listpi_per_int=pi_per_intDAO.getAll();
		} catch(Exception e) {
			easyLogger.error(e.getMessage(), e);
		}
		finally {
			close();
		}
	}



  //Method Download Image ? montando se houver algum campo do tipo bin?rio
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
      List<Con_componente_negadoT> listTemp  = con_componente_negadoDAO.getByPK( con_componente_negadoT);	 

      con_componente_negadoT= listTemp.size()>0?listTemp.get(0):new Con_componente_negadoT();
      
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
	  String page = "con_componente_negadoConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "con_componente_negadoConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
