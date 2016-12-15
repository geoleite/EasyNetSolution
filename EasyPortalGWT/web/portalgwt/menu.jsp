<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="mep_men_perJB" class="br.com.easynet.easyportal.gwt.jb.MenuJB" scope="request"/>
<jsp:setProperty name="mep_men_perJB" property="*"/>
<jsp:setProperty name="mep_men_perJB" property="page" value="${pageContext}"/>
${mep_men_perJB.execute}
{"resultado":{
"menu":[{"nome":"root","acao":"sem acao", "icone":"sem icone","status":"A","sistema":"sis", "pagina":"local" }
<c:forEach var="menu" items="${mep_men_perJB.menu.subMenu}">
 ,{"nome":"${menu.nome}","acao":"${menu.acao}","icone":"${menu.icone}","status":"A","sistema":"${menu.sistema}","pagina":"${menu.pagina}",
    "submenu":[{"nome":"rootsubmenu","acao":"sem acao","icone":"sem icone","status":"A","sistema":"sis"}
    <c:forEach var="submenu1" items="${menu.subMenu}">
      ,{"nome":"${submenu1.nome}","acao":"${submenu1.acao}","icone":"${submenu1.icone}","status":"A","sistema":"${submenu1.sistema}","pagina":"${submenu1.pagina}",
          "submenu":[{"nome":"rootsubmenu","acao":"sem acao","icone":"sem icone","status":"A"}
          <c:forEach var="submenu2" items="${submenu1.subMenu}">
              ,{"nome":"${submenu2.nome}","acao":"${submenu2.acao}","icone":"${submenu2.icone}","status":"A","sistema":"${submenu2.sistema}","pagina":"${submenu2.pagina}",
                  "submenu":[{"nome":"rootsubmenu","acao":"sem acao","status":"A","sistema":"sistema"}
                  <c:forEach var="submenu3" items="${submenu2.subMenu}">
                      ,{"nome":"${submenu3.nome}","acao":"${submenu3.acao}","icone":"${submenu3.icone}","status":"A","sistema":"${submenu3.sistema}","pagina":"${submenu3.pagina}", "submenu":[{"nome":"rootsubmenu","acao":"sem acao","status":"A","sistema":"sistema","pagina":"${menu.pagina}"}]}
                  </c:forEach>
                  ]}
          </c:forEach>
      ]}
    </c:forEach>
   ]}
</c:forEach>
]}
}