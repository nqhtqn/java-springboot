<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <%@include file="head.jsp"%>

    <body>
        <%@include file="menu.jsp"%>
        <div class="container">
            <%--@elvariable id="tacheAModifier" type="com.example.javaproject.entity.TacheEntity"--%>
            <form:form action="/modiftache/${tacheAModifier.id}" method="post" modelAttribute="tacheAModifier">
                <div class="mb-3">
                    <label for="titre" class="form-label">Title: </label>
                    <form:input path="titre" required="true" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description: </label>
                    <form:input path="description" required="true" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="importance" class="form-label">Importance: </label>
                    <form:input path="importance" required="true" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="etat" class="form-label">Etat: </label>
                    <form:input path="etat" required="true" class="form-control"/>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" id="nouveauMembreCheckbox" name="nouveauMembreCheckbox" onchange="afficherFormNouveauMembre()" class="form-check-input"/>
                    <label for="nouveauMembreCheckbox" class="form-check-label" checked=''>Nouveau Membre</label>
                </div>
                <div class="mb-3">
                    <div id="membreExistantChamps">
                        <label class="form-label">Author:</label>
                        <%--@elvariable id="membres" type="java.util.List"--%>
                        <form:select class="form-select" path="membre.idMembre">
                            <c:forEach var="membre" items="${membres}" varStatus="loop">
                                <form:option value="${membre.idMembre}" label="${membre.nom} ${membre.prenom}" />
                            </c:forEach>
                        </form:select>
                    </div>
                </div>

                <div id="nouveauMembreChamps" style="display: none">
                    <%@include file="formAjoutMembre.jsp"%>
                </div>

                <script>
                    function uncheckCheckbox() {
                        var myCheckbox = document.getElementById("nouveauMembreCheckbox");
                        myCheckbox.checked = false;
                    }
                    window.onload = uncheckCheckbox;
                    function afficherFormNouveauMembre() {
                        var nouveauMembreChamps = document.getElementById("nouveauMembreChamps");
                        var membreExistantChamps = document.getElementById("membreExistantChamps");
                        var prenom = document.getElementById("prenom");
                        var nom = document.getElementById("nom");


                        if (document.getElementById("nouveauMembreCheckbox").checked) {
                            nouveauMembreChamps.style.display = "block";
                            membreExistantChamps.style.display = "none";
                            prenom.classList.add("required");
                            nom.classList.add("required");

                        } else {
                            nouveauMembreChamps.style.display = "none";
                            membreExistantChamps.style.display = "block";
                            prenom.classList.remove("required");
                            nom.classList.remove("required");
                        }
                    }
                </script>
                <input type="submit" value="Modifier la tâche" class="btn btn-primary">
            </form:form>
            <a href="/livres">Retourner à la Liste de tâches</a>
        </div>
    </body>

</html>