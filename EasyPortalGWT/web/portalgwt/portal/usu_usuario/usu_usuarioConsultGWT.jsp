<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="usu_usuarioJB" class="br.com.i9.portal.jb.Usu_usuarioConsultJB" scope="request"/>                                         
<jsp:setProperty name="usu_usuarioJB" property="*"/>                                         
<jsp:setProperty name="usu_usuarioJB" property="page" value="${pageContext}"/>                                         
${usu_usuarioJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${usu_usuarioJB.list}" var="item">
     ,{	"usu_nr_id":"${item.usu_nr_id}"
 ,	"usu_tx_nome":"${item.usu_tx_nome}"
 ,	"usu_tx_login":"${item.usu_tx_login}"
 ,	"usu_tx_status":"${item.usu_tx_status}"
 ,	"usu_tx_email":"${item.usu_tx_email}"
  }
</c:forEach>
]}                                                                                
   
