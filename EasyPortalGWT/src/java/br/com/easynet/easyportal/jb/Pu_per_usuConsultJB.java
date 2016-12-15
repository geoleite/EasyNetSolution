package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Pu_per_usuConsultJB extends SystemBase {

  // Atributos e propriedades
    private Pu_per_usuT pu_per_usuT = new Pu_per_usuT();

  public void setPu_per_usuT(Pu_per_usuT pu_per_usuT) {
    this.pu_per_usuT = pu_per_usuT;
    
  }

  public Pu_per_usuT getPu_per_usuT() {	
    return pu_per_usuT;
  }


	
  private List<Pu_per_usuT> list;

  public List<Pu_per_usuT> getList() {
    return list;
  }
  
  public void setList(List<Pu_per_usuT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
        consult();
  }

  public void consult() throws Exception {
    try {
      IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
      list = pu_per_usuDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
      pu_per_usuDAO.delete(pu_per_usuT);	 
      setMsg("Exclusao efetuada com sucesso!");
      pu_per_usuT = new Pu_per_usuT();
      consult();	  	
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclusao!");
    } finally {
	close();
    }
  }  

 public void insert() throws Exception {
	// TODO Insert
	try {
	  String page = "pu_per_usuInsert.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){}   } 

 public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  }

}
