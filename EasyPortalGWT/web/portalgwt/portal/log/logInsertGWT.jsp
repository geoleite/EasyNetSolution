<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="logJB" class="br.com.i9.portal.jb.LogInsertJB" scope="request"/>
<jsp:setProperty name="logJB" property="*"/>
<jsp:setProperty name="logJB" property="page" value="${pageContext}"/>
${logJB.execute}
{"resultado":"${logJB.msg}"}
