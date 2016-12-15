<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="int_interfaceJB" class="br.com.easynet.easyportal.jb.Int_interfaceUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="int_interfaceJB" property="*"/>                                         
<jsp:setProperty name="int_interfaceJB" property="page" value="${pageContext}"/>                                         
${int_interfaceJB.execute}                                         
{"resultado":
{"msg":"${int_interfaceJB.msg}",
     "int_interface":{	"int_nr_id":"${int_interfaceJB.int_interfaceT.int_nr_id}"
 ,	"int_tx_nome":"${int_interfaceJB.int_interfaceT.int_tx_nome}"
  }
    }
     
}                                                                                
   
