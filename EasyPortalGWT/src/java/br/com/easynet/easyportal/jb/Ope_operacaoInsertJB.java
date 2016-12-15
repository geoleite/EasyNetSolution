package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Ope_operacaoInsertJB extends EasySecurityJB {

  // Atributos e propriedades
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

  public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
    this.ope_operacaoT = ope_operacaoT;
  }

  public Ope_operacaoT getOpe_operacaoT() {	
    return ope_operacaoT;
  }

	
  public void pageLoad() throws Exception {
	super.pageLoad();
        findbyid();
  }

  public void findbyid() throws Exception {
    try {
      ISis_sistemaDAO sisDao = getSis_sistemaDAO();
      sis_sistemaT.setSis_nr_id(ope_operacaoT.getSis_nr_id());
      List<Sis_sistemaT> list = sisDao.getById(getSis_sistemaT());
      if (list.size() > 0)
          sis_sistemaT = list.get(0);
      
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  
  // Metodos de Eventos
  public void insert() throws Exception {
    
    try {
      IOpe_operacaoDAO ope_operacaoDAO =  getOpe_operacaoDAO();
      ope_operacaoT.setOpe_nr_id(getIncOperacao());
      ope_operacaoDAO.insert(ope_operacaoT);	 
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
    
      ope_operacaoT = new Ope_operacaoT();	
  } 


  public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "ope_operacaoConsult.jsp";// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){} 
  }

    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }

}
