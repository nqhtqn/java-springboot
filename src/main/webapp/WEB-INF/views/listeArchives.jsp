<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Liste des tâches archivées</h1>
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
                        <button type="button" class="btn btn-outline-dark">${tache.id}</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-outline-dark">${tache.getTitre()}</button>
                    </td>
                    <td>
                            ${tache.getImportance()}
                    </td>
                    <td>
                        <a href="/detail/${tache.id}"><button type="button" class="btn btn-outline-primary">Details</button></a>
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
