<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="per_perfilJB" class="br.com.i9.portal.jb.Per_perfilConsultJB" scope="request"/>                                         
<jsp:setProperty name="per_perfilJB" property="*"/>                                         
<jsp:setProperty name="per_perfilJB" property="page" value="${pageContext}"/>                                         
${per_perfilJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${per_perfilJB.list}" var="item">
     ,{	"per_nr_id":"${item.per_nr_id}"
 ,	"per_tx_nome":"${item.per_tx_nome}"
 ,	"per_tx_status":"${item.per_tx_status}"
 ,	"per_tx_class":"${item.per_tx_class}"
  }
</c:forEach>
]}                                                                                
   
