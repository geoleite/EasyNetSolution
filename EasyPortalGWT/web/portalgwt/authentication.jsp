<%@page contentType="text/html" pageEncoding="latin1"%>
<jsp:useBean id="authentication" class="br.com.easynet.easyportal.gwt.jb.AuthenteicationJB" scope="request"/>
<jsp:setProperty name="authentication" property="*"/>
<jsp:setProperty name="authentication" property="page" value="${pageContext}"/>
${authentication.execute}
{"resultado":
{
"autenticacao":"${authentication.result}",
"usuario":"${authentication.usuarioLogado.usu_tx_nome}",
"trocarsenha":"${authentication.usuarioLogado.usu_tx_trocarsenha}",
"codigo":"${authentication.usuarioLogado.usu_nr_id}",
"login":"${authentication.usuarioLogado.usu_tx_login}",
"status":"${authentication.usuarioLogado.usu_tx_status}",
"email":"${authentication.usuarioLogado.usu_tx_email}"
}
}