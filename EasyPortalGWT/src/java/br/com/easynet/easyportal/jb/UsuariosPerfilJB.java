/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.jb;

import br.com.easynet.easyportal.dao.IPer_perfilDAO;
import br.com.easynet.easyportal.dao.IPu_per_usuDAO;
import br.com.easynet.easyportal.dao.IUsu_usuarioDAO;
import br.com.easynet.easyportal.dao.Per_perfilDAO;
import br.com.easynet.easyportal.dao.Pu_per_usuDAO;
import br.com.easynet.easyportal.dao.Usu_usuarioDAO;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Pu_per_usuT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author geoleite
 */
public class UsuariosPerfilJB extends EasySecurityJB {

    private Per_perfilT per_perfilT = new Per_perfilT();
    private String[] naoUsuarios;
    private String[] usuarios;
    private List<Usu_usuarioT> usuariosPerfil;
    private List<Usu_usuarioT> usuariosNaoPerfil;

    public void pageLoad() throws Exception {
        findbyid();
        consultUsuariosPerfil();
    }

    public void consultUsuariosPerfil() throws Exception {
        try {
            IUsu_usuarioDAO usuDao = getUsu_usuarioDAO();
            usuariosPerfil = usuDao.getUsuariosPerfil(per_perfilT);
            usuariosNaoPerfil = usuDao.getUsuariosNaoPerfil(per_perfilT);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }

    public void findbyid() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            List<Per_perfilT> listTemp = per_perfilDAO.getById(per_perfilT);

            per_perfilT = listTemp.size() > 0 ? listTemp.get(0) : new Per_perfilT();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    /**
     * Adiciona operações no perfil
     * @throws java.lang.Exception
     */
    public void adicionar() throws Exception {
        try {
            IPu_per_usuDAO puDao = getPu_per_usuDAO();
            Pu_per_usuT puT = new Pu_per_usuT();
            puT.setPer_nr_id(per_perfilT.getPer_nr_id());
            if (naoUsuarios != null) {
                for (int i = 0; i < naoUsuarios.length; i++) {
                    int usuNrId = Integer.parseInt(naoUsuarios[i]);
                    puT.setUsu_nr_id(usuNrId);
                    puDao.insert(puT);
                }
            }
            
            consultUsuariosPerfil();


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }

    }

    /**
     * Remover operações do perfil
     * @throws java.lang.Exception
     */
    public void remover() throws Exception {
        try {
            IPu_per_usuDAO puDao = getPu_per_usuDAO();
            Pu_per_usuT puT = new Pu_per_usuT();
            puT.setPer_nr_id(per_perfilT.getPer_nr_id());
            for (int i = 0; i < usuarios.length; i++) {
                int usuNrId = Integer.parseInt(usuarios[i]);
                puT.setUsu_nr_id(usuNrId);
                puDao.delete(puT);
            }
            consultUsuariosPerfil();


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    public Per_perfilT getPer_perfilT() {
        return per_perfilT;
    }

    public void setPer_perfilT(Per_perfilT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }

    public String[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String[] usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usu_usuarioT> getUsuariosPerfil() {
        return usuariosPerfil;
    }

    public void setUsuariosPerfil(List<Usu_usuarioT> usuariosPerfil) {
        this.usuariosPerfil = usuariosPerfil;
    }

    public List<Usu_usuarioT> getUsuariosNaoPerfil() {
        return usuariosNaoPerfil;
    }

    public void setUsuariosNaoPerfil(List<Usu_usuarioT> usuariosNaoPerfil) {
        this.usuariosNaoPerfil = usuariosNaoPerfil;
    }

    public String[] getNaoUsuarios() {
        return naoUsuarios;
    }

    public void setNaoUsuarios(String[] naoUsuarios) {
        this.naoUsuarios = naoUsuarios;
    }
}
