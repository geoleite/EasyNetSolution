<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="pu_per_usuJB" class="br.com.jb.Pu_per_usuConsultJB" scope="request"/>                                         
<jsp:setProperty name="pu_per_usuJB" property="*"/>                                         
<jsp:setProperty name="pu_per_usuJB" property="page" value="${pageContext}"/>                                         
${pu_per_usuJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${pu_per_usuJB.list}" var="item">
     ,{	"per_nr_id":"${item.per_nr_id}"
 ,	"usu_nr_id":"${item.usu_nr_id}"
  }
</c:forEach>
]}                                                                                
   
