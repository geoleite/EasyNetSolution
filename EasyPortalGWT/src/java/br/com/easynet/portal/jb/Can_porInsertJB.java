package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Can_porInsertJB extends SystemBase {

  // Atributos e propriedades
    private Can_porT can_porT = new Can_porT();

  public void setCan_porT(Can_porT can_porT) {
    this.can_porT = can_porT;
  }

  public Can_porT getCan_porT() {	
    return can_porT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      ICan_porDAO can_porDAO =  getCan_porDAO();
      can_porDAO.insert(can_porT);	 
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
    
      can_porT = new Can_porT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "can_porConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
