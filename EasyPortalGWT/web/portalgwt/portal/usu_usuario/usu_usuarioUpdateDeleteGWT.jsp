<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="usu_usuarioJB" class="br.com.i9.portal.jb.Usu_usuarioUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="usu_usuarioJB" property="*"/>                                         
<jsp:setProperty name="usu_usuarioJB" property="page" value="${pageContext}"/>                                         
${usu_usuarioJB.execute}                                         
{"resultado":
{"msg":"${usu_usuarioJB.msg}",
     "usu_usuario":{	"usu_nr_id":"${usu_usuarioJB.usu_usuarioT.usu_nr_id}"
 ,	"usu_tx_nome":"${usu_usuarioJB.usu_usuarioT.usu_tx_nome}"
 ,	"usu_tx_login":"${usu_usuarioJB.usu_usuarioT.usu_tx_login}"
 ,	"usu_tx_senha":"${usu_usuarioJB.usu_usuarioT.usu_tx_senha}"
 ,	"usu_tx_status":"${usu_usuarioJB.usu_usuarioT.usu_tx_status}"
 ,	"usu_tx_email":"${usu_usuarioJB.usu_usuarioT.usu_tx_email}"
  }
    }
     
}                                                                                
   
