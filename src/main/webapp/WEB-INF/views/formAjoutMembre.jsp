<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mb-3">
    <label for="nom" class="form-label">Nom du nouveau membre:</label>
    <input type="text" id="nom" name="nom" class="form-control" <c:if test='${empty membres}'>required="true"</c:if>/>
</div>

<div class="mb-3">
    <label for="prenom" class="form-label">Prénom du nouveau membre:</label>
    <input type="text" id="prenom" name="prenom" class="form-control" <c:if test='${empty membres}'>required="true"</c:if>/>
</div>