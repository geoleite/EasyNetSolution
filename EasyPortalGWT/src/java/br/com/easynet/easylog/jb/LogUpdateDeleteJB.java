package br.com.easynet.easylog.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easylog.dao.*;
import br.com.easynet.easylog.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class LogUpdateDeleteJB extends SystemBase implements INotLog {

  // Atributos e propriedades
    private LogT logT = new LogT();

  public void setLogT(LogT logT) {
    this.logT = logT;
  }

  public LogT getLogT() {	
    return logT;
  }

	
  private List<LogT> list;

  public List<LogT> getList() {
    return list;
  }
  
  public void setList(List<LogT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
	//
  }

  public void clear() throws Exception {
    
      logT = new LogT();	
  } 

  public void delete() throws Exception {
    try {
      if (exist()) {
        
        getLogDAO().delete(logT);
        setMsg("Exclus?o efetuada com sucesso!");
        clear();
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclus?o!");	
    } finally {
	close();
    }
  } 
  public boolean exist() throws Exception {
   try {
      
      List<LogT> listTemp  = getLogDAO().getById( logT);

      return listTemp.size()>0;      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
    return false;
	
  }

  public void update() throws Exception {
    try {
      if (exist()) {
        
        getLogDAO().update(logT);
        setMsg("Altera??o efetuada com sucesso!");	
      } else {  
	setMsg("Error: Registro inexistente!");
      } 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar altera??o!");	
    } finally {
	close();
    }
  }   

// Method de lookup
// 


  //Method Download Image ? montando se houver algum campo do tipo bin?rio
  //|DOWNLOADIMAGE| 


  public void findbyid() throws Exception {
    try {
      
      List<LogT> listTemp  = getLogDAO().getById( logT);

      logT= listTemp.size()>0?listTemp.get(0):new LogT();
      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  /**
   * Volta para a p?gina de consulta
   */	
  public void consult() throws Exception {
	// TODO Consult
	try {
	  String page = "logConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 
  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "logConsult.jsp";// defina aqui a p?gina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  } 

}
