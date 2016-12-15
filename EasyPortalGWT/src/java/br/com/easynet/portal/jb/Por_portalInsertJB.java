package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Por_portalInsertJB extends SystemBase {

  // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();

  public void setPor_portalT(Por_portalT por_portalT) {
    this.por_portalT = por_portalT;
  }

  public Por_portalT getPor_portalT() {	
    return por_portalT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IPor_portalDAO por_portalDAO =  getPor_portalDAO();
      por_portalDAO.insert(por_portalT);	 
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
    
      por_portalT = new Por_portalT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "por_portalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
