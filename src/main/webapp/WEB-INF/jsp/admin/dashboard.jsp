<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../include/header.jsp"/>

<h1>Admin Dashboard</h1>

<form id="filter-by-status-form" action="" class="d-flex justify-content-between align-items-center w-100">
    <div>
        <strong>Status:</strong>

        <input type="radio" id="all" name="status" value="All" class="filter-status-radio-button"
               <c:if test="${param.status == null}">checked</c:if>>
        <label for="all">All</label>

        <input type="radio" id="publish" name="status" value="Publish" class="filter-status-radio-button"
               <c:if test="${param.status == 'Publish'}">checked</c:if>>
        <label for="publish">Publish</label>

        <input type="radio" id="pending" name="status" value="Pending" class="filter-status-radio-button"
               <c:if test="${param.status == 'Pending'}">checked</c:if>>
        <label for="pending">Pending</label>

        <input type="radio" id="archive" name="status" value="Archive" class="filter-status-radio-button"
               <c:if test="${param.status == 'Archive'}">checked</c:if>>
        <label for="archive">Archive</label>
    </div>

    <a class="btn btn-primary mt-3 mb-4" href="/admin/add-new-resource">+ Add New Resource</a>
</form>

<c:if test="${resources.size() == 0}"><p class="fs-2 mt-4">No resource with status ${param.status}</p></c:if>

<c:forEach items="${resources}" var="resource">
    <c:set var="resource" value="${resource}" scope="request"/>
    <jsp:include page="../components/resource-card.jsp"/>
</c:forEach>

<jsp:include page="../include/footer.jsp"/>