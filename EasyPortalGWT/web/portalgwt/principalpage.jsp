<jsp:useBean id="principalPageJB" class="br.com.easynet.easyportal.jb.PrincipalPageJB" scope="request"/>
<jsp:setProperty name="principalPageJB" property="*"/> 
<jsp:setProperty name="principalPageJB" property="page" value="${pageContext}"/>
<jsp:setProperty name="principalPageJB" property="gwt" value="true"/>
${principalPageJB.execute}
{"resultado":"true"}