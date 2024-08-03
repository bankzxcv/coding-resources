<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<main class="container d-flex flex-column align-items-center pt-5">
    <h1>${topicName} Resources</h1>

    <c:forEach items="${resources}" var="resource">
        <c:set var="resource" value="${resource}" scope="request"/>
        <jsp:include page="../components/resource-card.jsp" />
    </c:forEach>
</main>

<jsp:include page="../include/footer.jsp"/>