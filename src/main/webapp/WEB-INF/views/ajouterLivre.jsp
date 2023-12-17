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
        <h1>Ajout de livre</h1>
        <!-- J'utiliser le formulaire de spring boot qui va créer un objet livreEntity pour moi-->
        <!-- modelAttribute est associé à l'objet vide que j'ai envoyé depuis le controller-->

        <%--@elvariable id="nouveauLivre" type="com.example.livrespirngboot.entity.LivreEntity"--%>
        <form:form action="/ajoutlivre" method="post" modelAttribute="nouveauLivre">
            <div class="mb-3">
                <label for="titre" class="form-label">Title: </label>

                <!-- J'utilises le path au lieu de name parce qu'il va l'associer à l'objet qu'il va créer-->
                <!-- La valeur du path doit être le même que le nom de l'attribut dans la classe LivreEntity-->
                <form:input path="titre" required="true" class="form-control"/>
            </div>

            <div class="mb-3">
                <label for="genre" class="form-label">Genre: </label>
                <form:input path="genre" required="true" class="form-control"/>
            </div>

            <div class="mb-3">
                <label for="annee" class="form-label">Année: </label>
                <form:input path="annee" required="true" class="form-control"/>
            </div>

            <!-- Je fais plusieurs Tests-->
            <c:choose>

                <c:when test="${empty auteurs}">
                    <!-- S'il n'y a pas d'auteur dans la base de données alors
                    il n'y aura pas de menu select mais juste 2 input pour ajouter un
                    nouvel auteur -->

                    <!-- appel du champ d'ajout d'auteur-->
                    <%@include file="formAjoutAuteur.jsp"%>
                </c:when>
                <c:otherwise>
                    <!-- s'il des auteurs dans la base-->

                    <!-- C'est un check box qui permet à l'utilisateur de soit utiliser un utilisateur
                    dans la base ou d'en ajouter un nouveau

                    Si l'utilisateur coche cette case alors il va ajouter un nouvel auteur: le menu
                    select disparait et 2 champs vont apparaitre-->
                    <div class="mb-3 form-check">
                        <input type="checkbox" id="nouvelAuteurCheckbox" name="nouvelAuteurCheckbox" onchange="afficherFormNouvelAuteur()" class="form-check-input"/>
                        <label for="nouvelAuteurCheckbox" class="form-check-label" checked=''>Nouvel Auteur</label>
                    </div>


                    <!-- un menu select qui liste tous les auteurs dans la base de données
                    L'utilisateur peut choisir parmi les auteurs s'il le veut
                    S'il veut ajouter un nouve auteur alors il doit juste chocher le checkbox-->
                    <div class="mb-3">
                        <div id="auteurExistantChamp">
                            <label class="form-label">Author:</label>
                            <%--@elvariable id="auteurs" type="java.util.List"--%>
                            <form:select class="form-select" path="auteur.idAuteur">
                                <c:forEach var="auteur" items="${auteurs}" varStatus="loop">

                                    <form:option value="${auteur.idAuteur}" label="${auteur.nom} ${auteur.prenom}" />
                                </c:forEach>
                            </form:select>
                        </div>

                    </div>

                    <!-- 2 champs pour ajouter un nouvel utilisateur
                    par défaut ça ne s'affiche pas. Pour l'afficher il faut cocher le checkbox
                    et ça fait disparaitre le menu select-->
                    <div id="nouvelAuteurChamp" style="display: none">
                        <!-- Appel à nouveau du champ d'ajout d'auteur-->
                        <%@include file="formAjoutAuteur.jsp"%>
                    </div>


                    <!-- Code javascript qui me permet mettre le checkbox non cocher par défaut
                    à chaque fois que la page est loadée.
                    Permet aussi d'afficher ou non le champ d'ajout de nouvel utilisateur en
                    fonction de si le checkbox est cocher ou non. Ça permet aussi de mettre
                    les champs nom et prenom en required ou non. S'il s'affiche, ils sont
                    required.-->
                    <script>

                        function uncheckCheckbox() {
                            var myCheckbox = document.getElementById("nouvelAuteurCheckbox");
                            myCheckbox.checked = false;
                        }

                        // Appeler la fonction lors du chargement de la page
                        window.onload = uncheckCheckbox;


                        function afficherFormNouvelAuteur() {
                            var nouvelAuteurChamps = document.getElementById("nouvelAuteurChamp");
                            var auteurExistantChamps = document.getElementById("auteurExistantChamp");
                            var nomAuteur = document.getElementById("nomAuteur");
                            var prenomAuteur = document.getElementById("prenomAuteur");


                            if (document.getElementById("nouvelAuteurCheckbox").checked) {
                                nouvelAuteurChamps.style.display = "block";
                                auteurExistantChamps.style.display = "none";
                                prenomAuteur.classList.add("required");
                                nomAuteur.classList.add("required");

                            } else {
                                nouvelAuteurChamps.style.display = "none";
                                auteurExistantChamps.style.display = "block";
                                prenomAuteur.classList.remove("required");
                                nomAuteur.classList.remove("required");
                            }
                        }
                    </script>


                </c:otherwise>
            </c:choose>

            <input type="submit" value="Ajouter un Livre" class="btn btn-primary">
        </form:form>


        <a href="/livres">Retourner à la Liste</a>
    </div>
</body>

</html>