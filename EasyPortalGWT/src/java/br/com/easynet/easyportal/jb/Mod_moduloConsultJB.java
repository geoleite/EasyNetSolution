package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;



/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */

public class Mod_moduloConsultJB extends EasySecurityJB implements INotSecurity {

  // Atributos e propriedades
  private Mod_moduloT mod_moduloT = new Mod_moduloT();

  public void setMod_moduloT(Mod_moduloT mod_moduloT) {
    this.mod_moduloT = mod_moduloT;
  }

  public Mod_moduloT getMod_moduloT() {	
    return mod_moduloT;
  }

	
  private List<Mod_moduloT> list;

  public List<Mod_moduloT> getList() {
    return list;
  }
  
  public void setList(List<Mod_moduloT> list) {
    this.list = list;
  }

  public void pageLoad() throws Exception {
    super.pageLoad();
  }

  public void consult() throws Exception {
    try {      
      list = getMod_moduloDAO().getAll();
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar consulta!");	
    }
  }  

  /**
   * Consulta os módulos que o perfil do usuário deve ter acesso
   * @throws Exception
   */
  public void consultByPerfil() throws Exception {
    try {
        List<Per_perfilT> listPer = getPerfilUser();
        StringBuffer sb = new StringBuffer();
        sb.append("0");
        for (int i = 0; i < listPer.size(); i++) {
            Per_perfilT perT = listPer.get(i);
            sb.append(", ").append(perT.getPer_nr_id());
        }
      list = getMod_moduloDAO().getByPerfis(sb.toString());
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar consulta!");
    }
  }

  public void delete() throws Exception {
    try {
        getMod_moduloDAO().delete(mod_moduloT);
        setMsg("Exclusao efetuada com sucesso!");
      consult();	  	
    } catch (Exception e) {
      easyLogger.error(e.getMessage(), e);
      setMsg(ERROR,"Falha ao realizar exclusao!");	
    } finally {
	close();
    }
  }  
}
