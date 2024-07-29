<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<main class="container d-flex flex-column align-items-center pt-5">
    <form class="border p-4 rounded-2">
        <div class="mb-3">
            <label for="emailId" class="form-label">Email address</label>
            <input type="email"
                   class="form-control <c:if test="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>"
                   id="emailId" name="email" value="${form.email}"/>
            <c:if test="${bindingResult.hasFieldErrors('email')}">
                <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                    <div class="text-danger">${error.defaultMessage}</div>
                </c:forEach>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="passwordId"
                   class="form-label">Password</label>
            <input
                    type="password"
                    class="form-control <c:if test="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>"
                    id="passwordId"
                    name="password"
                    value="${form.password}"
            />
            <c:if test="${bindingResult.hasFieldErrors('password')}">
                <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                    <div class="text-danger">${error.defaultMessage}</div>
                </c:forEach>
            </c:if>
        </div>
        <p>
            Don't have account?
            <a class="py-2" href="/user/signup">Sign Up</a>
        </p>
        <button type="submit" class="btn btn-primary">Log In</button>
    </form>
</main>

<jsp:include page="../include/footer.jsp"/>