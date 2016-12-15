package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Op_ope_perUpdateDeleteJB extends SystemBase {

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
  }

  public void clear() throws Exception {
    
      op_ope_perT = new Op_ope_perT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        IOp_ope_perDAO op_ope_perDAO = getOp_ope_perDAO();
        op_ope_perDAO.delete(op_ope_perT);	 
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
      IOp_ope_perDAO op_ope_perDAO = getOp_ope_perDAO();
      List<Op_ope_perT> listTemp  = op_ope_perDAO.getById( op_ope_perT);	 

      return listTemp.size()>0;      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
    return false;
	
  }


  //Method Download Image   montando se houver algum campo do tipo binario
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      IOp_ope_perDAO op_ope_perDAO = getOp_ope_perDAO();
      List<Op_ope_perT> listTemp  = op_ope_perDAO.getById( op_ope_perT);	 

      op_ope_perT= listTemp.size()>0?listTemp.get(0):new Op_ope_perT();
      
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
	  String page = "op_ope_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "op_ope_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
