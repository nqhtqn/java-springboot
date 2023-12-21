<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <!-- Apple du fichier head qui est commun à toutes les pages-->
    <%@include file="head.jsp"%>

<body>

    <!-- Apple du menu qui est commun à toutes les pages-->
    <%@include file="menu.jsp"%>


    <div class="container">
        <h1>Ajouter une tache au calendrier</h1>

        <%--@elvariable id="nouvelleTache" type="com.example.javaproject.entity.TacheEntity"--%>
        <form:form action="/ajouttache" method="post" modelAttribute="nouvelleTache">
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
                <form:select path="importance" class="form-control">
                    <option value="Haute">Haute</option>
                    <option value="Moyenne">Moyenne</option>
                    <option value="Basse">Basse</option>
                </form:select>
            </div>

            <c:choose>
                <%--@elvariable id="membres" type="java.util.List"--%>
                <c:when test="${empty membres}">
                    <%@include file="formAjoutMembre.jsp"%>
                </c:when>
                <c:otherwise>
                    <div class="mb-3 form-check">
                        <input type="checkbox" id="nouveauMembreCheckbox" name="nouveauMembreCheckbox" onchange="afficherFormNouveauMembre()" class="form-check-input"/>
                        <label for="nouveauMembreCheckbox" class="form-check-label" checked=''>Création d'un nouveau membre</label>
                    </div>
                    <div class="mb-3">
                        <div id=membreExistantChamps">
                            <label class="form-label">Membre:</label>
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
                            var nom = document.getElementById("nom");
                            var prenom = document.getElementById("prenom");


                            if (document.getElementById("nouveauMembreCheckbox").checked) {
                                nouveauMembreChamps.style.display = "block";
                                membreExistantChamps.style.display = "none";
                                nom.classList.add("required");
                                prenom.classList.add("required");

                            } else {
                                nouveauMembreChamps.style.display = "none";
                                membreExistantChamps.style.display = "block";
                                prenom.classList.remove("required");
                                nom.classList.remove("required");
                            }
                        }
                    </script>
                </c:otherwise>
            </c:choose>

            <input type="submit" value="Ajouter une tâche à la liste" class="btn btn-primary">
        </form:form>


        <a href="/livres">Retourner à la Liste de tâches</a>
    </div>
</body>

</html>