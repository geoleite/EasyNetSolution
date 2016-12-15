<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="con_componente_negadoJB" class="br.com.easynet.easyportal.jb.Con_componente_negadoInsertJB" scope="request"/>
<jsp:setProperty name="con_componente_negadoJB" property="*"/>
<jsp:setProperty name="con_componente_negadoJB" property="page" value="${pageContext}"/>
${con_componente_negadoJB.execute}
{"resultado":"${con_componente_negadoJB.msg}"}
