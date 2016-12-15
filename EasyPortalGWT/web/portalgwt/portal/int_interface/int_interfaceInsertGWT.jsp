<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="int_interfaceJB" class="br.com.easynet.easyportal.jb.Int_interfaceInsertJB" scope="request"/>
<jsp:setProperty name="int_interfaceJB" property="*"/>
<jsp:setProperty name="int_interfaceJB" property="page" value="${pageContext}"/>
${int_interfaceJB.execute}
{"resultado":"${int_interfaceJB.msg}"}
