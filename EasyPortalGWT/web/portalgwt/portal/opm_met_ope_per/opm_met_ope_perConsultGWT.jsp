<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="opm_met_ope_perJB" class="br.com.i9.portal.jb.Opm_met_ope_perConsultJB" scope="request"/>                                         
<jsp:setProperty name="opm_met_ope_perJB" property="*"/>                                         
<jsp:setProperty name="opm_met_ope_perJB" property="page" value="${pageContext}"/>                                         
${opm_met_ope_perJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${opm_met_ope_perJB.list}" var="item">
     ,{	"per_nr_id":"${item.per_nr_id}"
 ,	"ope_nr_id":"${item.ope_nr_id}"
 ,	"sis_nr_id":"${item.sis_nr_id}"
 ,	"met_nr_id":"${item.met_nr_id}"
  }
</c:forEach>
]}                                                                                
   
