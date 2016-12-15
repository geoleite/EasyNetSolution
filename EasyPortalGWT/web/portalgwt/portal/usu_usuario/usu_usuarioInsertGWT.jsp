<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="usu_usuarioJB" class="br.com.i9.portal.jb.Usu_usuarioInsertJB" scope="request"/>
<jsp:setProperty name="usu_usuarioJB" property="*"/>
<jsp:setProperty name="usu_usuarioJB" property="page" value="${pageContext}"/>
${usu_usuarioJB.execute}
{"resultado":"${usu_usuarioJB.msg}"}
