<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card my-2">
    <div class="row g-0">
        <div class="col-md-4 d-flex justify-content-center align-items-center">
            <a
                    href="/resources/detail/${resource.id}"
                    target="_blank"
                    class="card-image-link"
            >

                <img
                        class="resource-img"
                        src="${requestScope.resource.imageUrl}"
                        class="rounded-start"
                        alt="..."
                />
            </a>
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <a href="/resources/detail/${resource.id}" target="_blank">
                        <h2 class="card-title">${requestScope.resource.name}</h2>
                    </a>
                    <c:if test="${isAdmin == true}">
                        <a href="/resources/edit&resourceId=${resource.id}"
                           class="btn btn-primary">EDIT</a>
                    </c:if>
                    <c:if test="${isAdmin != true}">
                        <div class="alert alert-info d-inline w-40 mt-3" role="alert">Search: "${searchTerm}"</div>
                        <c:choose>
                            <c:when test="${resource.isAdded == 1}">
                                <a href="/user-list/remove-resource?userListId=${sessionScope.userListId}&resourceId=${resource.id}"
                                   class="btn btn-danger">REMOVE FROM LIST</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/user-list/add-resource?userListId=${sessionScope.userListId}&resourceId=${resource.id}"
                                   class="btn btn-primary">+ ADD TO LIST</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>

                <p class="card-text card-description">
                    ${requestScope.resource.description}
                </p>
                <div class="card-text gap-1 d-flex justify-content-end">
                    <a
                            href="${requestScope.resource.url}"
                            target="_blank"
                            class="btn btn-secondary"
                    >START LEARNING</a>
                    <a
                            href="/resources/detail/${resource.id}"
                            target="_blank"
                            class="btn btn-outline-secondary"
                    >MORE INFO</a>
                </div>

                <p class="card-text mt-3 gap-1 d-flex justify-content-end">
                    <c:forEach items="${requestScope.resource.resourceTopics}" var="resourceTopic">
                        <c:set var="resourceTopic" value="${resourceTopic}" scope="request"/>
                        <jsp:include page="topic-chip.jsp"/>
                    </c:forEach>
                </p>
            </div>
        </div>
    </div>
</div>