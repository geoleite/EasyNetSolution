<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="par_parametroJB" class="br.com.easynet.easyportal.jb.Par_parametroUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="par_parametroJB" property="*"/>                                         
<jsp:setProperty name="par_parametroJB" property="page" value="${pageContext}"/>                                         
${par_parametroJB.execute}                                         
{"resultado":
{"msg":"${par_parametroJB.msg}",
     "par_parametro":{	"sis_nr_id":"${par_parametroJB.par_parametroT.sis_nr_id}"
 ,	"par_tx_nome":"${par_parametroJB.par_parametroT.par_tx_nome}"
 ,	"par_tx_valor":"${par_parametroJB.par_parametroT.par_tx_valor}"
  }
    }
     
}                                                                                
   
