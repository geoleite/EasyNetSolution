package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Usu_por_canInsertJB extends SystemBase {

  // Atributos e propriedades
    private Usu_por_canT usu_por_canT = new Usu_por_canT();

  public void setUsu_por_canT(Usu_por_canT usu_por_canT) {
    this.usu_por_canT = usu_por_canT;
  }

  public Usu_por_canT getUsu_por_canT() {	
    return usu_por_canT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IUsu_por_canDAO usu_por_canDAO =  getUsu_por_canDAO();
      usu_por_canDAO.insert(usu_por_canT);	 
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
    
      usu_por_canT = new Usu_por_canT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "usu_por_canConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
