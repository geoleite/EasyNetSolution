package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Per_perfilInsertJB extends EasySecurityJB {

  // Atributos e propriedades
    private Per_perfilT per_perfilT = new Per_perfilT();

  public void setPer_perfilT(Per_perfilT per_perfilT) {
    this.per_perfilT = per_perfilT;
  }

  public Per_perfilT getPer_perfilT() {	
    return per_perfilT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IPer_perfilDAO per_perfilDAO =  getPer_perfilDAO();
      per_perfilT.setPer_nr_id(getIncPerfil());
      per_perfilDAO.insert(per_perfilT);	 
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
    
      per_perfilT = new Per_perfilT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "per_perfilConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
