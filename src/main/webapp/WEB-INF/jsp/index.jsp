<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"/>


<form id="search-form" action="/resources/search" class="w-50 d-flex mb-4" role="search">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Search</button>
</form>
<c:if test="${searchTerm != null}">
    <div class="d-flex justify-content-flex-end">
        <div class="alert alert-info d-inline" role="alert">Search: "${searchTerm}"</div>
        <a class="btn position-relative" style="top:-20px; right: 20px; height: fit-content" href="/">x</a>
    </div>
</c:if>

<c:forEach items="${resources}" var="resource">
    <c:set var="resource" value="${resource}" scope="request"/>
    <jsp:include page="components/resource-card.jsp"/>
</c:forEach>

<jsp:include page="include/footer.jsp"/>