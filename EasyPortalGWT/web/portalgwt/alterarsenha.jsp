<jsp:useBean id="usu_usuarioJB" class="br.com.easynet.easyportal.jb.AlterarSenhaJB" scope="request"/>
<jsp:setProperty name="usu_usuarioJB" property="*"/>
<jsp:setProperty name="usu_usuarioJB" property="page" value="${pageContext}"/>
${usu_usuarioJB.execute}
{"resultado":{"mensagem":"${usu_usuarioJB.msg}","result":"${usu_usuarioJB.result}"}}