package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Usu_por_canConsultJB extends SystemBase {

  // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();
    private Usu_por_canT usu_por_canT = new Usu_por_canT();

  public void setUsu_por_canT(Usu_por_canT usu_por_canT) {
    this.usu_por_canT = usu_por_canT;
  }

  public Usu_por_canT getUsu_por_canT() {	
    return usu_por_canT;
  }


	
  private List<Usu_por_canT> list;

  public List<Usu_por_canT> getList() {
    return list;
  }
  
  public void setList(List<Usu_por_canT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
        consult();
  }

  public void consult() throws Exception {
    try {
      IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
      list = usu_por_canDAO.getAll();	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IUsu_por_canDAO usu_por_canDAO = getUsu_por_canDAO();
      usu_por_canDAO.delete(usu_por_canT);	 
      setMsg("Exclusao efetuada com sucesso!");
      usu_por_canT = new Usu_por_canT();
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
	  String page = "usu_por_canInsert.jsp";// defina aqui a pagina que deve ser chamada  
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
