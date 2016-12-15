<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="con_componente_negadoJB" class="br.com.easynet.easyportal.jb.Con_componente_negadoConsultJB" scope="request"/>                                         
<jsp:setProperty name="con_componente_negadoJB" property="*"/>                                         
<jsp:setProperty name="con_componente_negadoJB" property="page" value="${pageContext}"/>                                         
${con_componente_negadoJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${con_componente_negadoJB.list}" var="item">
     ,{	"con_nr_id":"${item.con_nr_id}"
 ,	"int_nr_id":"${item.int_nr_id}"
 ,	"per_nr_id":"${item.per_nr_id}"
 ,	"con_tx_visivel":"${item.con_tx_visivel}"
 ,	"con_tx_ativo":"${item.con_tx_ativo}"
 ,	"con_tx_nome":"${item.con_tx_nome}"
  }
</c:forEach>
]}                                                                                
   
