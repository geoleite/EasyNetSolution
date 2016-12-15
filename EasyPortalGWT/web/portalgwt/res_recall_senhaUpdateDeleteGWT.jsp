<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="res_recall_senhaJB" class="br.com.easynet.easyportal.jb.Res_recall_senhaUpdateDeleteJB" scope="request"/>
<jsp:setProperty name="res_recall_senhaJB" property="*"/>                                         
<jsp:setProperty name="res_recall_senhaJB" property="page" value="${pageContext}"/>                                         
${res_recall_senhaJB.execute}                                         
{"resultado":
{"msg":"${res_recall_senhaJB.msg}",
     "res_recall_senha":{	"usu_nr_id":"${res_recall_senhaJB.res_recall_senhaT.usu_nr_id}"
 ,	"res_tx_pergunta":"${res_recall_senhaJB.res_recall_senhaT.res_tx_pergunta}"
 ,	"res_tx_resposta":""
 ,	"res_dt_ultimoacesso":"<fmt:formatDate value="${res_recall_senhaJB.res_recall_senhaT.res_dt_ultimoacesso}" pattern="dd/MM/yyyy HH:mm"/>"
 ,	"res_nr_tentativas":"${res_recall_senhaJB.res_recall_senhaT.res_nr_tentativas}"
  }
    }
     
}                                                                                
   
