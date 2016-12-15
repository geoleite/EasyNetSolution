<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="sis_sistemaJB" class="br.com.i9.portal.jb.Sis_sistemaConsultJB" scope="request"/>                                         
<jsp:setProperty name="sis_sistemaJB" property="*"/>                                         
<jsp:setProperty name="sis_sistemaJB" property="page" value="${pageContext}"/>                                         
${sis_sistemaJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${sis_sistemaJB.list}" var="item">
     ,{	"sis_nr_id":"${item.sis_nr_id}"
 ,	"sis_tx_nome":"${item.sis_tx_nome}"
 ,	"sis_tx_descricao":"${item.sis_tx_descricao}"
 ,	"sis_tx_status":"${item.sis_tx_status}"
  }
</c:forEach>
]}                                                                                
   
