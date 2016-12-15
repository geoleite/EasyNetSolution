<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<jsp:useBean id="mep_men_perJB" class="br.com.i9.portal.jb.Mep_men_perUpdateDeleteJB" scope="request"/>                                         
<jsp:setProperty name="mep_men_perJB" property="*"/>                                         
<jsp:setProperty name="mep_men_perJB" property="page" value="${pageContext}"/>                                         
${mep_men_perJB.execute}                                         
{"resultado":
{"msg":"${mep_men_perJB.msg}",
     "mep_men_per":{	"per_nr_id":"${mep_men_perJB.mep_men_perT.per_nr_id}"
 ,	"men_nr_id":"${mep_men_perJB.mep_men_perT.men_nr_id}"
  }
    }
     
}                                                                                
   
