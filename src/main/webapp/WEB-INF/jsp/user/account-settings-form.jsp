<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<h1>Account Settings</h1>

<form action="/account/settings/update/submit" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="id" value="${form.id}"/>
    <div class="mb-3">
        <p>Email: ${form.email}</p>
    </div>
    <div class="mb-3">
        <label for="usernameId" class="form-label">Username</label>
        <input
                type="text"
                class="form-control <c:if test="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>"
                id="usernameId"
                name="username"
                value="${form.username}"
        />
        <c:if test="${bindingResult.hasFieldErrors('username')}">
            <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="passwordId" class="form-label">Password</label>
        <input
                type="password"
                class="form-control <c:if test="${bindingResult.hasFieldErrors('password')}">is-invalid</c:if>"
                id="passwordId"
                name="password"
        />
        <c:if test="${bindingResult.hasFieldErrors('password')}">
            <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <a class="btn btn-outline-primary" href="/account/settings">CANCEL</a>
    <button type="submit" class="btn btn-primary">UPDATE</button>
</form>

<jsp:include page="../include/footer.jsp"/>