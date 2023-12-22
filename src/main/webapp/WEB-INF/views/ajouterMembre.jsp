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
        <h1>Ajouter un membre dans une équipe</h1>

        <%--@elvariable id="nouveauMembre" type="com.example.javaproject.entity.MembreEntity"--%>
        <form:form action="/ajoutmembre" method="post" modelAttribute="nouveauMembre">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom: </label>
                <form:input path="nom" required="true" class="form-control"/>
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">Prénom: </label>
                <form:input path="prenom" required="true" class="form-control"/>
            </div>
            <div class="mb-3">
                <label for="equipe" class="form-label">Equipe: </label>
                <form:input path="equipe" required="true" class="form-control"/>
            </div>
            <input type="submit" value="Ajouter le nouveau Membre" class="btn btn-primary">
        </form:form>
        <a href="/taches"><button type="button" class="btn btn-secondary">Retour à la liste</button></a>
    </div>
</body>

</html>