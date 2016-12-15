<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="res_recall_senhaJB" class="br.com.i9.portal.jb.Res_recall_senhaInsertJB" scope="request"/>
<jsp:setProperty name="res_recall_senhaJB" property="*"/>
<jsp:setProperty name="res_recall_senhaJB" property="page" value="${pageContext}"/>
${res_recall_senhaJB.execute}
{"resultado":"${res_recall_senhaJB.msg}"}
