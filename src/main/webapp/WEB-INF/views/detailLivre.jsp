<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <!-- Apple du fichier head qui est commun à toutes les pages-->
    <%@include file="head.jsp"%>


<body>

    <!-- Apple du menu qui est commun à toutes les pages-->
    <%@include file="menu.jsp"%>


    <div class="container">
        <h1>Détails</h1>
        <a href="/supplivres/${livre.id}" style="padding-right: 50px;">Supprimer</a>
        <a href="/modiflivres/${livre.id}">Modifier</a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Label</th>
                <th scope="col">Valeur</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">Titre</th>
                <td>${livre.titre}</td>
            </tr>
            <tr>
                <th scope="row">Auteur</th>
                <td>${livre.auteur.nom} ${livre.auteur.prenom}</td>
            </tr>
            <tr>
                <th scope="row">Genre</th>
                <td>${livre.genre}</td>
            </tr>
            <tr>
                <th scope="row">Année</th>
                <td>${livre.annee}</td>
            </tr>
            </tbody>
        </table>

        <a href="/livres">Retourner à la Liste</a>
    </div>
</body>
</html>