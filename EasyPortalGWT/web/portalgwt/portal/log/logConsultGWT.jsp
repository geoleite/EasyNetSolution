<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="logJB" class="br.com.i9.portal.jb.LogConsultJB" scope="request"/>                                         
<jsp:setProperty name="logJB" property="*"/>                                         
<jsp:setProperty name="logJB" property="page" value="${pageContext}"/>                                         
${logJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${logJB.list}" var="item">
     ,{	"log_nr_id":"${item.log_nr_id}"
 ,	"log_tx_sistema":"${item.log_tx_sistema}"
 ,	"log_tx_classe":"${item.log_tx_classe}"
 ,	"log_tx_metodo":"${item.log_tx_metodo}"
 ,	"log_tx_usuario":"${item.log_tx_usuario}"
 ,	"log_dt_datahora":"<fmt:formatDate value="${item.log_dt_datahora}" pattern="dd/MM/yyyy HH:mm"/>"
 ,	"log_tx_status":"${item.log_tx_status}"
 ,	"log_tx_ip":"${item.log_tx_ip}"
 ,	"log_tx_parametros":"${item.log_tx_parametros}"
  }
</c:forEach>
]}                                                                                
   
