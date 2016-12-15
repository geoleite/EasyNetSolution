<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="sis_sistemaJB" class="br.com.i9.portal.jb.Sis_sistemaUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="sis_sistemaJB" property="*"/>                                         
<jsp:setProperty name="sis_sistemaJB" property="page" value="${pageContext}"/>                                         
${sis_sistemaJB.execute}                                         
{"resultado":
{"msg":"${sis_sistemaJB.msg}",
     "sis_sistema":{	"sis_nr_id":"${sis_sistemaJB.sis_sistemaT.sis_nr_id}"
 ,	"sis_tx_nome":"${sis_sistemaJB.sis_sistemaT.sis_tx_nome}"
 ,	"sis_tx_descricao":"${sis_sistemaJB.sis_sistemaT.sis_tx_descricao}"
 ,	"sis_tx_status":"${sis_sistemaJB.sis_sistemaT.sis_tx_status}"
  }
    }
     
}                                                                                
   
