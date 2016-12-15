<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="men_menuJB" class="br.com.i9.portal.jb.Men_menuUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="men_menuJB" property="*"/>                                         
<jsp:setProperty name="men_menuJB" property="page" value="${pageContext}"/>                                         
${men_menuJB.execute}                                         
{"resultado":
{"msg":"${men_menuJB.msg}",
     "men_menu":{	"men_nr_id":"${men_menuJB.men_menuT.men_nr_id}"
 ,	"supermenu_nr_id":"${men_menuJB.men_menuT.supermenu_nr_id}"
 ,	"men_tx_status":"${men_menuJB.men_menuT.men_tx_status}"
 ,	"men_tx_nome":"${men_menuJB.men_menuT.men_tx_nome}"
 ,	"men_nr_ordem":"${men_menuJB.men_menuT.men_nr_ordem}"
 ,	"men_tx_url":"${men_menuJB.men_menuT.men_tx_url}"
 ,	"men_tx_acao":"${men_menuJB.men_menuT.men_tx_acao}"
 ,	"sis_nr_id":"${men_menuJB.men_menuT.sis_nr_id}"
 ,	"men_tx_icone":"${men_menuJB.men_menuT.men_tx_icone}"
  }
    }
     
}                                                                                
   
