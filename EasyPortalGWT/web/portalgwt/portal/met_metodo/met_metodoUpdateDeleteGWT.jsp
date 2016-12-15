<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="met_metodoJB" class="br.com.i9.portal.jb.Met_metodoUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="met_metodoJB" property="*"/>                                         
<jsp:setProperty name="met_metodoJB" property="page" value="${pageContext}"/>                                         
${met_metodoJB.execute}                                         
{"resultado":
{"msg":"${met_metodoJB.msg}",
     "met_metodo":{	"ope_nr_id":"${met_metodoJB.met_metodoT.ope_nr_id}"
 ,	"met_nr_id":"${met_metodoJB.met_metodoT.met_nr_id}"
 ,	"met_tx_metodo":"${met_metodoJB.met_metodoT.met_tx_metodo}"
 ,	"met_tx_status":"${met_metodoJB.met_metodoT.met_tx_status}"
 ,	"sis_nr_id":"${met_metodoJB.met_metodoT.sis_nr_id}"
 ,	"met_tx_descricao":"${met_metodoJB.met_metodoT.met_tx_descricao}"
  }
    }
     
}                                                                                
   
