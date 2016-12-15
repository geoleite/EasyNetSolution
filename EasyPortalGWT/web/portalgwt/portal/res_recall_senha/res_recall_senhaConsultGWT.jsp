<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="res_recall_senhaJB" class="br.com.i9.portal.jb.Res_recall_senhaConsultJB" scope="request"/>                                         
<jsp:setProperty name="res_recall_senhaJB" property="*"/>                                         
<jsp:setProperty name="res_recall_senhaJB" property="page" value="${pageContext}"/>                                         
${res_recall_senhaJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${res_recall_senhaJB.list}" var="item">
     ,{	"usu_nr_id":"${item.usu_nr_id}"
 ,	"res_tx_pergunta":"${item.res_tx_pergunta}"
 ,	"res_tx_resposta":"${item.res_tx_resposta}"
 ,	"res_dt_ultimoacesso":"<fmt:formatDate value="${item.res_dt_ultimoacesso}" pattern="dd/MM/yyyy HH:mm"/>"
 ,	"res_nr_tentativas":"${item.res_nr_tentativas}"
  }
</c:forEach>
]}                                                                                
   
