<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="met_metodoJB" class="br.com.i9.portal.jb.Met_metodoInsertJB" scope="request"/>
<jsp:setProperty name="met_metodoJB" property="*"/>
<jsp:setProperty name="met_metodoJB" property="page" value="${pageContext}"/>
${met_metodoJB.execute}
{"resultado":"${met_metodoJB.msg}"}
