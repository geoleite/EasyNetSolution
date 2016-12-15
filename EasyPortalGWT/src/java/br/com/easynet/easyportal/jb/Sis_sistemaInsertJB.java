package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Sis_sistemaInsertJB extends EasySecurityJB {

  // Atributos e propriedades
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

  public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
    this.sis_sistemaT = sis_sistemaT;
  }

  public Sis_sistemaT getSis_sistemaT() {	
    return sis_sistemaT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      ISis_sistemaDAO sis_sistemaDAO =  getSis_sistemaDAO();
      sis_sistemaT.setSis_nr_id(getIncSistema());
      sis_sistemaDAO.insert(sis_sistemaT);	 
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
    
      sis_sistemaT = new Sis_sistemaT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "sis_sistemaConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
