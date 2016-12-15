<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="mep_men_perJB" class="br.com.i9.portal.jb.Mep_men_perConsultJB" scope="request"/>                                         
<jsp:setProperty name="mep_men_perJB" property="*"/>                                         
<jsp:setProperty name="mep_men_perJB" property="page" value="${pageContext}"/>                                         
${mep_men_perJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${mep_men_perJB.list}" var="item">
     ,{	"per_nr_id":"${item.per_nr_id}"
 ,	"men_nr_id":"${item.men_nr_id}"
  }
</c:forEach>
]}                                                                                
   
