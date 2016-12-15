package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Op_ope_perInsertJB extends SystemBase {

  // Atributos e propriedades
    private Op_ope_perT op_ope_perT = new Op_ope_perT();

  public void setOp_ope_perT(Op_ope_perT op_ope_perT) {
    this.op_ope_perT = op_ope_perT;
  }

  public Op_ope_perT getOp_ope_perT() {	
    return op_ope_perT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IOp_ope_perDAO op_ope_perDAO =  getOp_ope_perDAO();
      op_ope_perDAO.insert(op_ope_perT);	 
      setMsg("Cadastro efetuado com sucesso!");
      clear();
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar cadastro!");	
    } finally {
	close();
    }
  } 
  public void clear() throws Exception {
    
      op_ope_perT = new Op_ope_perT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "op_ope_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
