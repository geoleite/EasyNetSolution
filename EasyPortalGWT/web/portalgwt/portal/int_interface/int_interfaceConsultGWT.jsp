<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="int_interfaceJB" class="br.com.easynet.easyportal.jb.Int_interfaceConsultJB" scope="request"/>                                         
<jsp:setProperty name="int_interfaceJB" property="*"/>                                         
<jsp:setProperty name="int_interfaceJB" property="page" value="${pageContext}"/>                                         
${int_interfaceJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${int_interfaceJB.list}" var="item">
     ,{	"int_nr_id":"${item.int_nr_id}"
 ,	"int_tx_nome":"${item.int_tx_nome}"
  }
</c:forEach>
]}                                                                                
   
