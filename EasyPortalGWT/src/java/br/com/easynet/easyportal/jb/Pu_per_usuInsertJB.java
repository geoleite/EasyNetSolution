package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Pu_per_usuInsertJB extends SystemBase {

  // Atributos e propriedades
    private Pu_per_usuT pu_per_usuT = new Pu_per_usuT();

  public void setPu_per_usuT(Pu_per_usuT pu_per_usuT) {
    this.pu_per_usuT = pu_per_usuT;
  }

  public Pu_per_usuT getPu_per_usuT() {	
    return pu_per_usuT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IPu_per_usuDAO pu_per_usuDAO =  getPu_per_usuDAO();
      pu_per_usuDAO.insert(pu_per_usuT);	 
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
    
      pu_per_usuT = new Pu_per_usuT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "pu_per_usuConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
