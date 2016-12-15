package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Mep_men_perInsertJB extends SystemBase {

  // Atributos e propriedades
    private Mep_men_perT mep_men_perT = new Mep_men_perT();

  public void setMep_men_perT(Mep_men_perT mep_men_perT) {
    this.mep_men_perT = mep_men_perT;
  }

  public Mep_men_perT getMep_men_perT() {	
    return mep_men_perT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IMep_men_perDAO mep_men_perDAO =  getMep_men_perDAO();
      mep_men_perDAO.insert(mep_men_perT);	 
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
    
      mep_men_perT = new Mep_men_perT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "mep_men_perConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
