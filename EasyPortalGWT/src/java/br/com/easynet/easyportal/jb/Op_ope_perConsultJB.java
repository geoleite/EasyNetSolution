package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Op_ope_perConsultJB extends SystemBase {

  // Atributos e propriedades
    private Op_ope_perT op_ope_perT = new Op_ope_perT();

  public void setOp_ope_perT(Op_ope_perT op_ope_perT) {
    this.op_ope_perT = op_ope_perT;
  }

  public Op_ope_perT getOp_ope_perT() {	
    return op_ope_perT;
  }


	
  private List<Op_ope_perT> list;

  public List<Op_ope_perT> getList() {
    return list;
  }
  
  public void setList(List<Op_ope_perT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
        consult();
  }

  public void consult() throws Exception {
    try {
      IOp_ope_perDAO op_ope_perDAO = getOp_ope_perDAO();
      list = op_ope_perDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IOp_ope_perDAO op_ope_perDAO = getOp_ope_perDAO();
      op_ope_perDAO.delete(op_ope_perT);	 
      setMsg("Exclusao efetuada com sucesso!");
      op_ope_perT = new Op_ope_perT();
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
	  String page = "op_ope_perInsert.jsp";// defina aqui a pagina que deve ser chamada  
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
