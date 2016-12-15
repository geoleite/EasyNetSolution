package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Mod_moduloInsertJB extends SystemBase {
  // Atributos e propriedades
  private Mod_moduloT mod_moduloT = new Mod_moduloT();

  public void setMod_moduloT(Mod_moduloT mod_moduloT) {
    this.mod_moduloT = mod_moduloT;
  }

  public Mod_moduloT getMod_moduloT() {	
    return mod_moduloT;
  }

	
  public void pageLoad() throws Exception {
    super.pageLoad();
  }

  // Metodos de Eventos
  public void insert() throws Exception {
    try {
      getMod_moduloDAO().insert(mod_moduloT);
      setMsg(INFO,"Cadastro efetuado com sucesso!");
    } catch (Exception e) {
      setMsg(ERROR,"Falha: ".concat(e.getMessage()) );	
    } finally {
      close();
    }
  } 
}
