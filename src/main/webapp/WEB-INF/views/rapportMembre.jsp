<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Rapport sur un membre</h1><br>
        <h4>Liste de ses ${nombre} tâches :</h4>
        <table class="table table-bordered table-striped">
            <thead class="table-light">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tâches</th>
                <th scope="col">Priorité</th>
                <th scope="col">Date</th>
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
                            ${tache.getDate_debut()}
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
