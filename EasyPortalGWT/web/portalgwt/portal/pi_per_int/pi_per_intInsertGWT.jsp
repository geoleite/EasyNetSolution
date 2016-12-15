<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="pi_per_intJB" class="br.com.easynet.easyportal.jb.Pi_per_intInsertJB" scope="request"/>
<jsp:setProperty name="pi_per_intJB" property="*"/>
<jsp:setProperty name="pi_per_intJB" property="page" value="${pageContext}"/>
${pi_per_intJB.execute}
{"resultado":"${pi_per_intJB.msg}"}
