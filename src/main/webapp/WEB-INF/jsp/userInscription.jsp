<%@ include file="include/importTags.jsp" %>

<h2>Inscription utilisateur</h2>

<form:form method="post" modelAttribute="currentUser" action="/firstSpring/inscription/submit">
    <p>
        <form:label path="name">Nom :</form:label>
        <form:input path="name" />
        <form:errors path="name" cssClass="error" />
    </p>

    <p>
        <form:label path="age">Âge :</form:label>
        <form:input path="age" type="number" />
        <form:errors path="age" cssClass="error" />
    </p>


    <p>Sexe :</p>
    <p>
        <form:radiobutton path="male" value="true" label="Boy" />
        <form:radiobutton path="male" value="false" label="Girl" />
    </p>

    <p>
        <form:label path="hobby">Loisir préféré :</form:label>
        <form:select path="hobby">
            <form:options items="${hobbies}" itemValue="id" itemLabel="name" />
        </form:select>
    </p>


    <p>
        <form:button>S'inscrire</form:button>
    </p>


</form:form>
<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
