<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@include file="head.jsp"%>

<body>
    <%@include file="menu.jsp"%>

    <div class="container">
        <h1>Détails de la tâche</h1>
        <a href="/supptache/${tache.id}" style="padding-right: 50px;">Supprimer</a>
        <a href="/modiftache/${tache.id}">Modifier</a>
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
                <td>${tache.titre}</td>
            </tr>
            <tr>
                <th scope="row">Membre</th>
                <td>${tache.membre.nom} ${tache.membre.prenom}</td>
            </tr>
            <tr>
                <th scope="row">Description</th>
                <td>${tache.description}</td>
            </tr>
            <tr>
                <th scope="row">Importance</th>
                <td>${tache.importance}</td>
            </tr>
            <tr>
                <th scope="row">Date de début</th>
                <td>${tache.date_debut}</td>
            </tr>
            </tbody>
        </table>
        <a href="/taches">Retourner à la Liste</a>
    </div>
</body>
</html>