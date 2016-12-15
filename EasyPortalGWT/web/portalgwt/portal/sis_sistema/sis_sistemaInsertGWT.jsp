<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="sis_sistemaJB" class="br.com.i9.portal.jb.Sis_sistemaInsertJB" scope="request"/>
<jsp:setProperty name="sis_sistemaJB" property="*"/>
<jsp:setProperty name="sis_sistemaJB" property="page" value="${pageContext}"/>
${sis_sistemaJB.execute}
{"resultado":"${sis_sistemaJB.msg}"}
