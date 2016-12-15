<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="logJB" class="br.com.i9.portal.jb.LogUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="logJB" property="*"/>                                         
<jsp:setProperty name="logJB" property="page" value="${pageContext}"/>                                         
${logJB.execute}                                         
{"resultado":
{"msg":"${logJB.msg}",
     "log":{	"log_nr_id":"${logJB.logT.log_nr_id}"
 ,	"log_tx_sistema":"${logJB.logT.log_tx_sistema}"
 ,	"log_tx_classe":"${logJB.logT.log_tx_classe}"
 ,	"log_tx_metodo":"${logJB.logT.log_tx_metodo}"
 ,	"log_tx_usuario":"${logJB.logT.log_tx_usuario}"
 ,	"log_dt_datahora":"<fmt:formatDate value="${logJB.logT.log_dt_datahora}" pattern="dd/MM/yyyy HH:mm"/>"
 ,	"log_tx_status":"${logJB.logT.log_tx_status}"
 ,	"log_tx_ip":"${logJB.logT.log_tx_ip}"
 ,	"log_tx_parametros":"${logJB.logT.log_tx_parametros}"
  }
    }
     
}                                                                                
   
