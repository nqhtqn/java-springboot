<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Period" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>
<br><br><br>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                <h1>Détails de la tâche</h1>
            </div>
            <div class="card-body">
                <h5 class="card-title">${tache.titre}</h5>
                <p class="card-text">${tache.description}</p>
                <p class="card-text">Importance : ${tache.importance}</p>
                <p class="card-text">Etat : ${tache.etat}</p>

                <a href="/modiftache/${tache.id}"><button type="button" class="btn btn-outline-primary">Modifier</button></a>
                <c:if test="${tache.getEtat() == 'A lancer'}">
                    <a href="/cours/${tache.id}"><button type="button" class="btn btn-outline-info">Lancer</button></a>
                </c:if>
                <c:if test="${tache.getEtat() != 'En pause'}">
                    <a href="/pause/${tache.id}"><button type="button" class="btn btn-outline-warning">Pause</button></a>
                </c:if>
                <c:if test="${tache.getEtat() == 'En pause'}">
                    <a href="/reprendre/${tache.id}"><button type="button" class="btn btn-outline-warning">Reprendre</button></a>
                </c:if>
                <c:if test="${tache.getEtat() == 'En cours'}">
                    <a href="/terminer/${tache.id}"><button type="button" class="btn btn-outline-success">Terminer</button></a>
                </c:if>
                <c:if test="${tache.getEtat() == 'Terminée'}">
                    <a href="/archiver/${tache.id}"><button type="button" class="btn btn-light">Archiver</button></a>
                </c:if>
                <a href="/supptache/${tache.id}" style="padding-right: 20px;"><button type="button" class="btn btn-outline-danger">Supprimer</button></a>

            </div>
            <div class="card-footer text-body-secondary">
                Date de début : ${tache.date_debut}
            </div>
        </div><br>
        <a href="/taches"><button type="button" class="btn btn-primary">Retour à la liste</button></a>
    </div>
</body>
</html>