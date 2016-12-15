<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="per_perfilJB" class="br.com.i9.portal.jb.Per_perfilInsertJB" scope="request"/>
<jsp:setProperty name="per_perfilJB" property="*"/>
<jsp:setProperty name="per_perfilJB" property="page" value="${pageContext}"/>
${per_perfilJB.execute}
{"resultado":"${per_perfilJB.msg}"}
