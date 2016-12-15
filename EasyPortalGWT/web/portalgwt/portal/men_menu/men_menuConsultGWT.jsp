<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="men_menuJB" class="br.com.i9.portal.jb.Men_menuConsultJB" scope="request"/>                                         
<jsp:setProperty name="men_menuJB" property="*"/>                                         
<jsp:setProperty name="men_menuJB" property="page" value="${pageContext}"/>                                         
${men_menuJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${men_menuJB.list}" var="item">
     ,{	"men_nr_id":"${item.men_nr_id}"
 ,	"supermenu_nr_id":"${item.supermenu_nr_id}"
 ,	"men_tx_status":"${item.men_tx_status}"
 ,	"men_tx_nome":"${item.men_tx_nome}"
 ,	"men_nr_ordem":"${item.men_nr_ordem}"
 ,	"men_tx_url":"${item.men_tx_url}"
 ,	"men_tx_acao":"${item.men_tx_acao}"
 ,	"sis_nr_id":"${item.sis_nr_id}"
 ,	"men_tx_icone":"${item.men_tx_icone}"
  }
</c:forEach>
]}                                                                                
   
