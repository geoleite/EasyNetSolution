<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="jsonService" class="br.com.easynet.jb.JSONService" scope="request"/>
<jsp:setProperty name="jsonService" property="*"/>
<jsp:setProperty name="jsonService" property="page" value="${pageContext}"/>
${jsonService.execute}
${jsonService.result}