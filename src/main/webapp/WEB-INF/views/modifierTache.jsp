<%@ page import="com.example.javaproject.entity.TacheEntity" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <%@include file="head.jsp"%>

    <body>
        <%@include file="menu.jsp"%>
        <h2>Modification de la tache n°${tache.id}</h2>
        <div class="container">
            <%--@elvariable id="tache" type="com.example.javaproject.entity.TacheEntity"--%>
            <form:form action="/modiftache/${tache.id}" method="post" modelAttribute="tache">
                <div class="mb-3">
                    <label for="titre" class="form-label">Titre: </label>
                    <form:input path="titre" required="true" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description: </label>
                    <form:input path="description" required="true" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label for="importance" class="form-label">Importance: </label>
                    <form:select path="importance" class="form-control">
                        <option value="Haute">Haute</option>
                        <option value="Moyenne">Moyenne</option>
                        <option value="Basse">Basse</option>
                    </form:select>
                </div>
                <div class="mb-3">
                    <label for="etat" class="form-label">Etat: </label>
                    <form:select path="etat" class="form-control">
                        <option value="En cours">En cours</option>
                        <option value="En pause">En pause</option>
                        <option value="Reportée">Reportée</option>
                        <option value="Reportée">Terminée</option>
                        <option value="Reportée">Vérification</option>
                        <option value="Reportée">Archivée</option>
                    </form:select>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" id="nouveauMembreCheckbox" name="nouveauMembreCheckbox" onchange="afficherFormNouveauMembre()" class="form-check-input"/>
                    <label for="nouveauMembreCheckbox" class="form-check-label" checked=''>Nouveau Membre</label>
                </div>
                <div class="mb-3">
                    <div id="membreExistantChamps">
                        <label class="form-label">Membre:</label>
                        <%--@elvariable id="membres" type="java.util.List"--%>
                        <form:select class="form-select" path="membre.idMembre">
                            <c:forEach var="membre" items="${membres}" varStatus="loop">
                                <form:option value="${membre.idMembre}" label="${membre.prenom} ${membre.nom}" />
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
                <form:hidden path="date_debut" required="true" class="form-control"/>
                <input type="submit" value="Modifier la tâche" class="btn btn-primary">
            </form:form>
                <a href="/taches"><button type="button" class="btn btn-secondary">Retour à la liste</button></a>
        </div>
    </body>

</html>