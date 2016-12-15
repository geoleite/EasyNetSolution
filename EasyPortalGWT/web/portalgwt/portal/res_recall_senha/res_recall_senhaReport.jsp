<jsp:useBean id="res_recall_senha" class="br.com.i9.portal.relatorios.res_recall_senha.Res_recall_senhaReport" scope="request"/>
<jsp:setProperty name="res_recall_senha" property="*"/>
<jsp:setProperty name="res_recall_senha" property="page" value="${pageContext}"/>
${res_recall_senha.execute}
