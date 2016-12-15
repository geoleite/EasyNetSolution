<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="ope_operacaoJB" class="br.com.i9.portal.jb.Ope_operacaoUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="ope_operacaoJB" property="*"/>                                         
<jsp:setProperty name="ope_operacaoJB" property="page" value="${pageContext}"/>                                         
${ope_operacaoJB.execute}                                         
{"resultado":
{"msg":"${ope_operacaoJB.msg}",
     "ope_operacao":{	"ope_nr_id":"${ope_operacaoJB.ope_operacaoT.ope_nr_id}"
 ,	"sis_nr_id":"${ope_operacaoJB.ope_operacaoT.sis_nr_id}"
 ,	"ope_tx_nome":"${ope_operacaoJB.ope_operacaoT.ope_tx_nome}"
 ,	"ope_tx_status":"${ope_operacaoJB.ope_operacaoT.ope_tx_status}"
 ,	"ope_tx_url":"${ope_operacaoJB.ope_operacaoT.ope_tx_url}"
 ,	"ope_tx_descricao":"${ope_operacaoJB.ope_operacaoT.ope_tx_descricao}"
 ,	"ope_tx_classe":"${ope_operacaoJB.ope_operacaoT.ope_tx_classe}"
  }
    }
     
}                                                                                
   
