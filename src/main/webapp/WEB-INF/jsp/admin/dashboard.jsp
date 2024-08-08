<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../include/header.jsp"/>


<h1>Admin Dashboard</h1>
<a class="btn btn-primary mt-3 mb-4" href="/admin/add-new-resource">+ Add New Resource</a>


<c:forEach items="${resources}" var="resource">
    <c:set var="resource" value="${resource}" scope="request"/>
    <jsp:include page="../components/resource-card.jsp"/>
</c:forEach>

<jsp:include page="../include/footer.jsp"/>