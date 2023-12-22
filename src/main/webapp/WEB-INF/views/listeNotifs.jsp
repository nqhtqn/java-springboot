<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Liste des notifications</h1><br><br>
        <ul class="list-group list-group-flush">
            <c:forEach var="notif" items="${notifs}" varStatus="loop">
                <c:if test="${notif.getVu() == 'Non'}">
                    <a href="/vu/${notif.idNotif}" class="list-group-item list-group-item-action list-group-item-info"  aria-disabled="true">${notif.getType()} -  ${notif.getTexte()}, ${notif.tache}</a>
                </c:if>
                <c:if test="${notif.getVu() == 'Oui'}">
                    <li class="list-group-item" aria-disabled="true">${notif.getType()} -  ${notif.getTexte()}, ${notif.tache}</li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
