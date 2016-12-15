package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Ope_operacaoConsultJB extends EasySecurityJB {

  // Atributos e propriedades
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

  public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
    this.ope_operacaoT = ope_operacaoT;
  }

  public Ope_operacaoT getOpe_operacaoT() {	
    return ope_operacaoT;
  }


	
  private List<Ope_operacaoT> list;

  public List<Ope_operacaoT> getList() {
    return list;
  }
  
  public void setList(List<Ope_operacaoT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
	super.pageLoad();
        findbyid();
        consult();
  }

  public void findbyid() throws Exception {
    try {
      sis_sistemaT.setSis_nr_id(ope_operacaoT.getSis_nr_id());
      List<Sis_sistemaT>list = getSis_sistemaDAO().getById(sis_sistemaT);
      sis_sistemaT = list.size()>0?list.get(0):new Sis_sistemaT();
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }
  public void consult() throws Exception {
    try {
      IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
      list = ope_operacaoDAO.getBySis_nr_id(ope_operacaoT);	 
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar consulta!");	
    } finally {
	close();
    }
  }  

  public void delete() throws Exception {
    try {
      IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
      ope_operacaoDAO.delete(ope_operacaoT);	 
      setMsg("Exclusao efetuada com sucesso!");
      ope_operacaoT = new Ope_operacaoT();
      consult();	  	
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg("Falha ao realizar exclusao!");
    } finally {
	close();
    }
  }  

 public void insert() throws Exception {
	// TODO Insert
	try {
	  String page = "ope_operacaoInsert.jsp?ope_operacaoT.sis_nr_id=" + 
                  ope_operacaoT.getSis_nr_id();// defina aqui a pagina que deve ser chamada  
	  getResponse().sendRedirect(page); 
	} catch (Exception e){}   } 

 public void cancel() throws Exception {
	// TODO Cancel
	try {
	  String page = "";// defina aqui a pagina que deve ser chamada  
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
