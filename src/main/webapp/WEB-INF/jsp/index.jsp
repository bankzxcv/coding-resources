<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"/>

<h1>Feature Resources</h1>

<c:forEach items="${resources}" var="resource">
    <c:set var="resource" value="${resource}" scope="request"/>
    <jsp:include page="components/resource-card.jsp"/>
</c:forEach>

<jsp:include page="include/footer.jsp"/>