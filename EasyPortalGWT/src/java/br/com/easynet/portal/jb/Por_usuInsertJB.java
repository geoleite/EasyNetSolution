package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Por_usuInsertJB extends SystemBase {

  // Atributos e propriedades
    private Por_usuT por_usuT = new Por_usuT();

  public void setPor_usuT(Por_usuT por_usuT) {
    this.por_usuT = por_usuT;
  }

  public Por_usuT getPor_usuT() {	
    return por_usuT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IPor_usuDAO por_usuDAO =  getPor_usuDAO();
      por_usuDAO.insert(por_usuT);	 
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
    
      por_usuT = new Por_usuT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "por_usuConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
