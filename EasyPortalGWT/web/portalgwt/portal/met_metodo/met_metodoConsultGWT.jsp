<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="met_metodoJB" class="br.com.i9.portal.jb.Met_metodoConsultJB" scope="request"/>                                         
<jsp:setProperty name="met_metodoJB" property="*"/>                                         
<jsp:setProperty name="met_metodoJB" property="page" value="${pageContext}"/>                                         
${met_metodoJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${met_metodoJB.list}" var="item">
     ,{	"ope_nr_id":"${item.ope_nr_id}"
 ,	"met_nr_id":"${item.met_nr_id}"
 ,	"met_tx_metodo":"${item.met_tx_metodo}"
 ,	"met_tx_status":"${item.met_tx_status}"
 ,	"sis_nr_id":"${item.sis_nr_id}"
 ,	"met_tx_descricao":"${item.met_tx_descricao}"
  }
</c:forEach>
]}                                                                                
   
