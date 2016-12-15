package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Int_interfaceConsultJB extends SystemBase {

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
        consult();
  }

  public void consult() throws Exception {
    try {
      IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
      list = int_interfaceDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
      int_interfaceDAO.delete(int_interfaceT);	 
      setMsg("Exclusao efetuada com sucesso!");
      int_interfaceT = new Int_interfaceT();
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
	  String page = "int_interfaceInsert.jsp";// defina aqui a p?gina que deve ser chamada  
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
