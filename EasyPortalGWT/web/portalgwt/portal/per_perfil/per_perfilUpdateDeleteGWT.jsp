<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="per_perfilJB" class="br.com.i9.portal.jb.Per_perfilUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="per_perfilJB" property="*"/>                                         
<jsp:setProperty name="per_perfilJB" property="page" value="${pageContext}"/>                                         
${per_perfilJB.execute}                                         
{"resultado":
{"msg":"${per_perfilJB.msg}",
     "per_perfil":{	"per_nr_id":"${per_perfilJB.per_perfilT.per_nr_id}"
 ,	"per_tx_nome":"${per_perfilJB.per_perfilT.per_tx_nome}"
 ,	"per_tx_status":"${per_perfilJB.per_perfilT.per_tx_status}"
 ,	"per_tx_class":"${per_perfilJB.per_perfilT.per_tx_class}"
  }
    }
     
}                                                                                
   
