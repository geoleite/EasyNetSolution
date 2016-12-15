package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Pi_per_intConsultJB extends SystemBase {

  // Atributos e propriedades
    private Pi_per_intT pi_per_intT = new Pi_per_intT();

  public void setPi_per_intT(Pi_per_intT pi_per_intT) {
    this.pi_per_intT = pi_per_intT;
  }

  public Pi_per_intT getPi_per_intT() {	
    return pi_per_intT;
  }


	
  private List<Pi_per_intT> list;

  public List<Pi_per_intT> getList() {
    return list;
  }
  
  public void setList(List<Pi_per_intT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
        consult();
  }

  public void consult() throws Exception {
    try {
      IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();
      list = pi_per_intDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();
      pi_per_intDAO.delete(pi_per_intT);	 
      setMsg("Exclusao efetuada com sucesso!");
      pi_per_intT = new Pi_per_intT();
      consult();	  	
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar exclusao!");	
    } finally {
	close();
    }
  }  

 public void insert() throws Exception {
	// TODO Insert
	try {
	  String page = "pi_per_intInsert.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){}   } 

 public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  }

}
