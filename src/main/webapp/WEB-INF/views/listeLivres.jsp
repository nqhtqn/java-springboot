<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <!-- Apple du fichier head qui est commun à toutes les pages-->
    <%@include file="head.jsp"%>

<body>

    <!-- Apple du menu qui est commun à toutes les pages-->
    <%@include file="menu.jsp"%>



    <div class="container">
        <h1>Liste de livres</h1>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Livre</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="livre" items="${livres}" varStatus="loop">
                    <tr>
                        <td>
                            <a href="/detail/${livre.id}"> ${livre.getTitre()} - ${livre.getAnnee()}</a>
                        </td>
                        <td>
                            <a href="/supplivres/${livre.id}" style="padding-right: 20px;">Supprimer</a>
                            <a href="/modiflivres/${livre.id}">Modifier</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
