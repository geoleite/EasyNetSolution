<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="pi_per_intJB" class="br.com.easynet.easyportal.jb.Pi_per_intUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="pi_per_intJB" property="*"/>                                         
<jsp:setProperty name="pi_per_intJB" property="page" value="${pageContext}"/>                                         
${pi_per_intJB.execute}                                         
{"resultado":
{"msg":"${pi_per_intJB.msg}",
     "pi_per_int":{	"int_nr_id":"${pi_per_intJB.pi_per_intT.int_nr_id}"
 ,	"per_nr_id":"${pi_per_intJB.pi_per_intT.per_nr_id}"
  }
    }
     
}                                                                                
   
