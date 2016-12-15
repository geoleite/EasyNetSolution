<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="pi_per_intJB" class="br.com.easynet.easyportal.jb.Pi_per_intConsultJB" scope="request"/>                                         
<jsp:setProperty name="pi_per_intJB" property="*"/>                                         
<jsp:setProperty name="pi_per_intJB" property="page" value="${pageContext}"/>                                         
${pi_per_intJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${pi_per_intJB.list}" var="item">
     ,{	"int_nr_id":"${item.int_nr_id}"
 ,	"per_nr_id":"${item.per_nr_id}"
  }
</c:forEach>
]}                                                                                
   
