<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%@include file="head.jsp"%>

<body>
<%@include file="menu.jsp"%>

<div class="container">
    <h1>Rapport sur un membre</h1><br>
    <ul class="list-group list-group-flush">
        <c:forEach var="membre" items="${membres}" varStatus="loop">
            <a href="/rapport/${membre.idMembre}"><li class="list-group-item">${membre.getPrenom()} ${membre.getNom()}</li></a>
        </c:forEach>
    </ul>
</div>
</body>
</html>
