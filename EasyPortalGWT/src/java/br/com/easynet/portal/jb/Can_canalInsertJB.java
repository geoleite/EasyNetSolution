package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Can_canalInsertJB extends SystemBase {

  // Atributos e propriedades
    private Can_canalT can_canalT = new Can_canalT();

  public void setCan_canalT(Can_canalT can_canalT) {
    this.can_canalT = can_canalT;
  }

  public Can_canalT getCan_canalT() {	
    return can_canalT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      ICan_canalDAO can_canalDAO =  getCan_canalDAO();
      can_canalDAO.insert(can_canalT);	 
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
    
      can_canalT = new Can_canalT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "can_canalConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
