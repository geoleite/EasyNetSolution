<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="men_menuJB" class="br.com.i9.portal.jb.Men_menuInsertJB" scope="request"/>
<jsp:setProperty name="men_menuJB" property="*"/>
<jsp:setProperty name="men_menuJB" property="page" value="${pageContext}"/>
${men_menuJB.execute}
{"resultado":"${men_menuJB.msg}"}
