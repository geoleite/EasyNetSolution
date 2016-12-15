<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="opm_met_ope_perJB" class="br.com.i9.portal.jb.Opm_met_ope_perInsertJB" scope="request"/>
<jsp:setProperty name="opm_met_ope_perJB" property="*"/>
<jsp:setProperty name="opm_met_ope_perJB" property="page" value="${pageContext}"/>
${opm_met_ope_perJB.execute}
{"resultado":"${opm_met_ope_perJB.msg}"}
