<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="opm_met_ope_perJB" class="br.com.i9.portal.jb.Opm_met_ope_perUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="opm_met_ope_perJB" property="*"/>                                         
<jsp:setProperty name="opm_met_ope_perJB" property="page" value="${pageContext}"/>                                         
${opm_met_ope_perJB.execute}                                         
{"resultado":
{"msg":"${opm_met_ope_perJB.msg}",
     "opm_met_ope_per":{	"per_nr_id":"${opm_met_ope_perJB.opm_met_ope_perT.per_nr_id}"
 ,	"ope_nr_id":"${opm_met_ope_perJB.opm_met_ope_perT.ope_nr_id}"
 ,	"sis_nr_id":"${opm_met_ope_perJB.opm_met_ope_perT.sis_nr_id}"
 ,	"met_nr_id":"${opm_met_ope_perJB.opm_met_ope_perT.met_nr_id}"
  }
    }
     
}                                                                                
   
