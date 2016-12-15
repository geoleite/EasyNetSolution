<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="ope_operacaoJB" class="br.com.i9.portal.jb.Ope_operacaoConsultJB" scope="request"/>                                         
<jsp:setProperty name="ope_operacaoJB" property="*"/>                                         
<jsp:setProperty name="ope_operacaoJB" property="page" value="${pageContext}"/>                                         
${ope_operacaoJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${ope_operacaoJB.list}" var="item">
     ,{	"ope_nr_id":"${item.ope_nr_id}"
 ,	"sis_nr_id":"${item.sis_nr_id}"
 ,	"ope_tx_nome":"${item.ope_tx_nome}"
 ,	"ope_tx_status":"${item.ope_tx_status}"
 ,	"ope_tx_url":"${item.ope_tx_url}"
 ,	"ope_tx_descricao":"${item.ope_tx_descricao}"
 ,	"ope_tx_classe":"${item.ope_tx_classe}"
  }
</c:forEach>
]}                                                                                
   
