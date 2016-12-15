package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Mop_mod_perInsertJB extends SystemBase {
  // Atributos e propriedades
  private Mop_mod_perT mop_mod_perT = new Mop_mod_perT();

  public void setMop_mod_perT(Mop_mod_perT mop_mod_perT) {
    this.mop_mod_perT = mop_mod_perT;
  }

  public Mop_mod_perT getMop_mod_perT() {	
    return mop_mod_perT;
  }

	
  public void pageLoad() throws Exception {
    super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    try {
      getMop_mod_perDAO().insert(mop_mod_perT);
      setMsg(INFO,"Cadastro efetuado com sucesso!");
    } catch (Exception e) {
      setMsg(ERROR,"Falha: ".concat(e.getMessage()) );	
    } finally {
      close();
    }
  } 
}
