package br.com.easynet.easylog.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easylog.dao.*;
import br.com.easynet.easylog.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class LogInsertJB extends SystemBase {

  // Atributos e propriedades
    private LogT logT = new LogT();

  public void setLogT(LogT logT) {
    this.logT = logT;
  }

  public LogT getLogT() {	
    return logT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
	//
  }

  // M?todos de Eventos
  public void insert() throws Exception {
    
    try {
      
      getLogDAO().insert(logT);
      setMsg("Cadastro efetuado com sucesso!");
      clear();
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar cadastro!");	
    } finally {
	close();
    }
  } 

// Method de lookup
// 

  
  public void clear() throws Exception {
    
      logT = new LogT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "logConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
