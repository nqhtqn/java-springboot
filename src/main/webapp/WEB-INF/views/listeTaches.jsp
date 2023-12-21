<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Liste des tâches</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Tâches</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="tache" items="${taches}" varStatus="loop">
                    <tr>
                        <td>
                            <a href="/detail/${tache.id}"> ${tache.getTitre()} - ${tache.getDate_debut()}</a>
                        </td>
                        <td>
                            ${tache.getImportance()}
                        </td>
                        <td>
                            <a href="/modiftache/${tache.id}"><img src="images/edit.png" alt="Modifier" style="padding-right: 20px;"></a>
                            <a href="/supptache/${tache.id}" style="padding-right: 20px;"><img src="images/trash.png" alt="Supprimer" style="padding-right: 20px;"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
