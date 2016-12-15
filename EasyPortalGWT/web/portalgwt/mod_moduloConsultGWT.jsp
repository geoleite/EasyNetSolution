<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="mod_moduloJB" class="br.com.easynet.easyportal.jb.Mod_moduloConsultJB" scope="request"/>                                         
<jsp:setProperty name="mod_moduloJB" property="*"/>                                         
<jsp:setProperty name="mod_moduloJB" property="page" value="${pageContext}"/>                                         
${mod_moduloJB.execute}                                         
{"resultado":[
   {"registro":"marcacao"}
<c:forEach items="${mod_moduloJB.list}" var="item">
     ,{	"mod_nr_id":"${item.mod_nr_id}"
 ,	"mod_tx_nome":"${item.mod_tx_nome}"
 ,	"mod_tx_status":"${item.mod_tx_status}"
 ,	"mod_nr_ordem":"${item.mod_nr_ordem}"
 ,	"mod_tx_acao":"${item.mod_tx_acao}"
 ,	"mod_tx_icone":"${item.mod_tx_icone}"
 ,	"mod_tx_autologin":"${item.mod_tx_autologin}"
 ,	"mod_tx_urllogin":"${item.mod_tx_urllogin}"

  }
</c:forEach>
]}                                                                                
   
