<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="con_componente_negadoJB" class="br.com.easynet.easyportal.jb.Con_componente_negadoUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="con_componente_negadoJB" property="*"/>                                         
<jsp:setProperty name="con_componente_negadoJB" property="page" value="${pageContext}"/>                                         
${con_componente_negadoJB.execute}                                         
{"resultado":
{"msg":"${con_componente_negadoJB.msg}",
     "con_componente_negado":{	"con_nr_id":"${con_componente_negadoJB.con_componente_negadoT.con_nr_id}"
 ,	"int_nr_id":"${con_componente_negadoJB.con_componente_negadoT.int_nr_id}"
 ,	"per_nr_id":"${con_componente_negadoJB.con_componente_negadoT.per_nr_id}"
 ,	"con_tx_visivel":"${con_componente_negadoJB.con_componente_negadoT.con_tx_visivel}"
 ,	"con_tx_ativo":"${con_componente_negadoJB.con_componente_negadoT.con_tx_ativo}"
 ,	"con_tx_nome":"${con_componente_negadoJB.con_componente_negadoT.con_tx_ativo}"
  }
    }
     
}                                                                                
   
