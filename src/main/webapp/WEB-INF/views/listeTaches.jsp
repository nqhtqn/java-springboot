<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Liste des tâches par priorité</h1>
        <table class="table table-bordered table-striped">
            <thead class="table-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tâches</th>
                <th scope="col">Priorité</th>
                <th scope="col">Actions</th>
                <th scope="col">Etat</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tache" items="${taches}" varStatus="loop">
                <tr>
                    <td>
                        <a href="/detail/${tache.id}"><button type="button" class="btn btn-outline-dark">${tache.id}</button></a>
                    </td>
                    <td>
                        <a href="/detail/${tache.id}"><button type="button" class="btn btn-outline-dark">${tache.getTitre()}</button></a>
                    </td>
                    <td>
                            ${tache.getImportance()}
                    </td>
                    <td>
                        <a href="/modiftache/${tache.id}"><button type="button" class="btn btn-outline-primary">Modifier</button></a>
                        <c:if test="${tache.getEtat() == 'A lancer'}">
                            <a href="/cours/${tache.id}"><button type="button" class="btn btn-outline-info">Lancer</button></a>
                        </c:if>
                        <c:choose>
                            <c:when test="${tache.getEtat() != 'En pause'}">
                                <c:choose>
                                    <c:when test="${tache.getEtat() == 'Terminée'}">
                                        <a href="/reprendre/${tache.id}"><button type="button" class="btn btn-outline-warning">Reprendre</button></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/pause/${tache.id}"><button type="button" class="btn btn-outline-warning">Pause</button></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a href="/reprendre/${tache.id}"><button type="button" class="btn btn-outline-warning">Reprendre</button></a>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${tache.getEtat() == 'En cours'}">
                            <a href="/terminer/${tache.id}"><button type="button" class="btn btn-outline-success">Terminer</button></a>
                        </c:if>
                        <c:if test="${tache.getEtat() == 'Terminée'}">
                            <a href="/archiver/${tache.id}"><button type="button" class="btn btn-light">Archiver</button></a>
                        </c:if>
                        <a href="/supptache/${tache.id}" style="padding-right: 20px;"><button type="button" class="btn btn-outline-danger">Supprimer</button></a>
                    </td>
                    <td>
                            ${tache.getEtat()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
