package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Can_canalConsultJB extends SystemBase {

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
        consult();
  }

  public void consult() throws Exception {
    try {
      ICan_canalDAO can_canalDAO = getCan_canalDAO();
      list = can_canalDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      ICan_canalDAO can_canalDAO = getCan_canalDAO();
      can_canalDAO.delete(can_canalT);	 
      setMsg("Exclusão efetuada com sucesso!"); 
      can_canalT = new Can_canalT();
      consult();	  	
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclusão!");	
    } finally {
	close();
    }
  }  

 public void insert() throws Exception {
	// TODO Insert
	try {
	  String page = "portal.jsp?urlCanalAtual=can_canalConsult.jsp&urlCanal=can_canalInsert.jsp";// defina aqui a pagina que deve ser chamada  
	  //getResponse().sendRedirect(page);
          
	} catch (Exception e){}   } 

 public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  }

}
