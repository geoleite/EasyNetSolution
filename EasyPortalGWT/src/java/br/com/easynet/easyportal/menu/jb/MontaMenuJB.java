/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.database.DataSet;
import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.INotSecurity;
import br.com.easynet.easyportal.jb.SystemBase;
import br.com.easynet.easyportal.menu.dao.IMen_menuDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.transfer.Ime_item_menuT;
import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.jdragon.dao.DAOFactory;
import br.com.jdragon.dao.ObjectDAO;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class MontaMenuJB extends EasySecurityJB implements INotSecurity, INotLog {

    private static final String MENU_MOTADO = "menu_usuario_logado";
    private static String folderfiles = "";
    private static final String fonte1 = "\"bold 9pt Verdana\"";
    private static final String fonte2 = "\"bold 9pt Verdana\"";
    private static final String fonte3 = "\"9pt 'Arial','Verdana'\"";
    private static final String color1 = "\"#FFFFFF\"";
    private static final String color2 = "\"#330033\"";
    private static final String color3 = "\"#CCCCCC\"";
    private static final String color4 = "\"#330033\"";
    private static final String color5 = "\"#000000\"";
    private static final String color6 = "\"#330033\"";
    private static final String color7 = "\"#330033\"";
    private static final String color8 = "\"#330033\"";
    private static final String color9 = "\"#330033\"";
    private static final String gif1 = "\"0506-arrow.gif\"";
    private static final String gif2 = "\"0506-arrow1.gif\"";
    private static final String gif3 = "\"060420arrow2.gif\"";
    private static final String gif4 = "\"060420arrow2.gif\"";
    private static final String gif5 = "\"newline.gif\"";
    private static final String fimMenu = "stm_ep();";
    private static final String layout = "p1";
    private boolean bpx = false;
    private boolean aix = false;
    private int contaix = 1;
    private int cont = 1;
    private static final String openScript = "<script type=" + "\"text/javascript\"" + ">" + " <!--\n";
    private static final String closeScript = "//--> \n" + "</script>";
    private static String firstScript;
    private static String script;
    private IMen_menuDAO menuDao;
    private StringBuffer ep = new StringBuffer();
    private boolean jaCriado = false;
    private int nivel = 0;
    private int contBpx1 = 1;
    private int contBpx2 = 4;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
    private String scriptHead;

    public String getScriptHead() {
        return scriptHead;
    }

    public void setScriptHead(String scriptHead) {
        this.scriptHead = scriptHead;
    }
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();

    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }
    private List<Ime_item_menuT> listaMenu;

    public List<Ime_item_menuT> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Ime_item_menuT> listaMenu) {
        this.listaMenu = listaMenu;
    }

    // Monta os itens e submenu
    private void menu(StringBuffer menuScript, Men_menuT menT, int cont,
            int cont2, int paix1, int paix2) throws Exception {

        StringBuffer temp = new StringBuffer();
        List<Men_menuT> subMenu = menuDao.getSubMenu(menT);
        StringBuffer abre = new StringBuffer();
        StringBuffer fecha = new StringBuffer();
        StringBuffer sbEp = new StringBuffer();
        if (subMenu.size() > 0) {
            if (!jaCriado) {
                if (nivel == 0) {
                    abre.append("\nstm_bpx('p").append(cont).append("','p").append(cont2).append("',[").append(contBpx1).append(",").append(contBpx2).append(",0,0,2,3,0,0]);");
                } else {
                    abre.append("\nstm_bpx('p").append(cont).append("','p").append(cont2).append("',[").append(contBpx1).append(",").append(contBpx2).append("]);");
                }
            } else {
                abre.append("\nstm_bpx('p").append(cont).append("','p").append(cont - 1).append("',[]);");
            }
            fecha.append("\nstm_ep();");
        }
        for (int i = 0; i < subMenu.size(); i++) {
            Men_menuT men_menuT = subMenu.get(i);
            //String url = men_menuT.getMen_tx_url();
            String url = getPathBase(getRequest().getRequestURL().toString(), men_menuT.getMen_tx_url());
            if (nivel == 0) {
                if (!jaCriado) {
                    //stm_aix("p1i0","p0i0",[0,                                     "Novo","","",-1,-1,0,"1","_self","","","","",0,0,0,"","",0,0]);
                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i0',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self','','','','',0,0,0,'','',0,0]);");
//                temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i0',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("']);");
                    jaCriado = true;
                } else {//stm_aix("p1i1","p1i0",[0,"Menu Item 2"]);

                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p").append(cont).append("i").append(0).append("',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self']);");
                }
            } else if (nivel == 1) {
                if (!jaCriado) {
                    //stm_aix("p1i0","p0i0",[0,                                     "Novo","","",-1,-1,0,"1","_self","","","","",0,0,0,"","",0,0]);
                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i0',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self','','','','',0,0,0,'','',0,0]);");
//                temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i0',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("']);");
                    jaCriado = true;
                } else {//stm_aix("p1i1","p1i0",[0,"Menu Item 2"]);

                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p").append(0).append("i").append(2).append("',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self']);");
                }
            } else if (nivel >= 2) {
                if (!jaCriado) {
                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i2',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self','','','','',0,0,0,'','',0,0]);");
//                temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p0i0',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("']);");
                    jaCriado = true;
                } else {//stm_aix("p1i1","p1i0",[0,"Menu Item 2"]);

                    temp.append("\nstm_aix('p").append(cont).append("i").append(i).append("','p").append(0).append("i").append(2).append("',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self']);");
                }
            }
            List<Men_menuT> subMenuTemp = menuDao.getSubMenu(men_menuT);
            if (subMenuTemp.size() > 0) {
                cont = 2;
                jaCriado = false;
                cont2 = 1;
                nivel++;
                contBpx1 = 1;
                contBpx2 = 2;
            }
            menu(temp, men_menuT, cont + 1, cont2, paix1, paix2);
        }
        menuScript.append(abre);
        menuScript.append(temp);
        menuScript.append(fecha);

    }

    private String getPathBase(String path, String urlCanal) {
        String[] pastas = path.split("/");
        //int pos = path.lastIndexOf("/");
        StringBuffer newPath = new StringBuffer();
        /*        for (int i = 0; i < pastas.length-2; i++) {
        String string = pastas[i];
        //easyLogger.debug(string);
        newPath.append(string).append("/");
        }
         */
        String easyPortal = "easyportal/portal";
        int tam = easyPortal.length();
        int pos = path.indexOf(easyPortal) + tam;
//        easyLogger.debug(path + " " + path.substring(0, pos+1));
        newPath.append(path.substring(0, pos + 1));
//        newPath.append("http://www.mcconsultoriaesistemas.com.br/easyportal/portal/");
        newPath.append(urlCanal);

        return newPath.toString();
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        try {
            script = "";
            //usuT.setUsu_nr_id(1);
            usu_usuarioT = getUsuarioLogado();
            //usu_usuarioT = new Usu_usuarioT();
            //usu_usuarioT.setUsu_nr_id(16);
            String strMenu = "";
            if (usu_usuarioT != null) {
                if (getSession().getAttribute(MENU_MOTADO) != null) {
                    strMenu = (String) getSession().getAttribute(MENU_MOTADO);
                } else {
                    menuDao = getMen_menuDAO();

//                    String sql = "select men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and  men.supermenu_nr_id=0 order by men.men_nr_ordem";
                    String sql = "select men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' order by men.men_nr_ordem";
                    Object[] param = {usu_usuarioT.getUsu_nr_id()};
                    StringBuffer menuScript = new StringBuffer();
                    menuScript.append("<script type='text/javascript'> <!--\n");
                    menuScript.append("\nstm_bm(['menu0a10',820,'','Resource/blank.gif',0,'','',0,0,250,0,1000,1,0,0,'','',0,0,1,2,'default','hand',''],this);");
                    // Cor da borda -- FFFFF

                    String corContorno = getApplication().getInitParameter("corContorno");
                    if (corContorno == null) {
                        corContorno = "#000000";
                    }
                    String corPadrao = getApplication().getInitParameter("corPadrao");
                    if (corPadrao == null) {
                        corPadrao = "#FFFFFF";
                    }
                    String corSelecionado = getApplication().getInitParameter("corSelecionado");
                    if (corSelecionado == null) {
                        corSelecionado = "#FFFFFF";
                    }
                    String corTexto = getApplication().getInitParameter("corTexto");
                    if (corTexto == null) {
                        corTexto = "#000000";
                    }
                    menuScript.append("\nstm_bp('p0',[0,4,0,0,2,3,0,7,100,'',-2,'',-2,50,0,0,'#000000','").append(corPadrao).append("','',3,1,1,'").append(corContorno).append("']);");

                    List<Men_menuT> menuPrimario = menuDao.getMenuPrimario(usu_usuarioT);
                    int controle1 = 1;
                    int controle2 = 0;
                    int contP = 0;
                    int cont2 = 0;
                    int paix1 = 0;
                    int paix2 = 0;
                    // Perrcore cada menu trazendo seus itens e submenu
                    for (int i = 0; i < menuPrimario.size(); i++) {
                        contP = i;
                        Men_menuT men_menuT = menuPrimario.get(i);
//                    String url = men_menuT.getMen_tx_url();
                        String url = getPathBase(getRequest().getRequestURL().toString(), men_menuT.getMen_tx_url());

                        nivel = 0;
                        if (i == 0) {
                            menuScript.append("\nstm_ai('p0i").append(i).append("',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self','','','','',0,0,0,'Resource/arrow_r.gif','Resource/arrow_r.gif',7,7,0,0,1,'").append(corPadrao).append("',0,'").append(corSelecionado).append("',0,'','',3,3,1,1,'#330033','").append(corContorno).append("','").append(corTexto).append("','").append(corContorno).append("','8pt Verdana','8pt Verdana',0,0]);");
                        } else {
                            if (i > 2) {
                                controle2 = 2;
                                controle1 = 0;
                            }
                            List<Men_menuT> subMenu = menuDao.getSubMenu(men_menuT);
                            if (subMenu.size() > 0) {
                                nivel = 1;
                                controle2 = 0;
                                controle1 = 0;
                                contP = 1;
                                jaCriado = false;
                                cont2 = 0;
                                contBpx1 = 1;
                                contBpx2 = 4;
                            }
                            menuScript.append("\nstm_aix('p0i").append(i).append("','p").append(controle1).append("i").append(controle2).append("',[0,'").append(men_menuT.getMen_tx_nome()).append("','','',-1,-1,0,'").append(url).append("','_self']);");
                        }
                        menu(menuScript, men_menuT, contP + 1, cont2, paix1, paix2);
                        jaCriado = true;
                    //easyLogger.debug("MEnu : \n " + menuScript);
                    }
                    menuScript.append("\nstm_ep();");
                    menuScript.append("\nstm_em();");
                    menuScript.append("\n --> </script>");
                    strMenu = menuScript.toString().replaceAll("'", "\"");
                    getSession().setAttribute(MENU_MOTADO, strMenu);
                }
                out.print(strMenu);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] param) {
        try {
            //MontaMenuJB mm = new MontaMenuJB();
            //mm.pageLoad();
            //easyLogger.debug((byte)'h');
        } catch (Exception exception) {
        }

    }
}
