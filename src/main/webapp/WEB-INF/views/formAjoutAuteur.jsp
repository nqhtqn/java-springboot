<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mb-3">
    <label for="nomAuteur" class="form-label">Nom du Nouvel Auteur:</label>

    <!-- La condition c:if permet en fait de mettre le champ en require ou pas
    S'il n'y a pas d'auteur dans la base, alors le champs est obligatoire (required="true")
    sinon le champ n'est pas obligatoire-->

    <input type="text" id="nomAuteur" name="nomAuteur" class="form-control" <c:if test='${empty auteurs}'>required="true"</c:if>/>
</div>

<div class="mb-3">
    <label for="prenomAuteur" class="form-label">Prénom du Nouvel Auteur:</label>
    <input type="text" id="prenomAuteur" name="prenomAuteur" class="form-control" <c:if test='${empty auteurs}'>required="true"</c:if>/>
</div>