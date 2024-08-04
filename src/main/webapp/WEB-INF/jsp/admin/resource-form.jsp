<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<!-- Resource Form -->
<form id="resource-form" action="/admin/submit-new-resource" method="post" enctype="multipart/form-data"
      style="width: 90%; max-width: 650px"
      class="border p-4 rounded-2">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h1 class="text-center mb-4">Resource</h1>

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input value="${form.name}" type="text"
               class="form-control <c:if test="${bindingResult.hasFieldErrors('name')}">is-invalid</c:if>" id="name"
               name="name"/>
        <c:if test="${bindingResult.hasFieldErrors('name')}">
            <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="url" class="form-label">URL</label>
        <input value="${form.url}" type="text"
               class="form-control <c:if test="${bindingResult.hasFieldErrors('url')}">is-invalid</c:if>" id="url"
               name="url"/>
        <c:if test="${bindingResult.hasFieldErrors('url')}">
            <c:forEach items="${bindingResult.getFieldErrors('url')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea value="${form.description}" name="description"
                  class="form-control <c:if test="${bindingResult.hasFieldErrors('description')}">is-invalid</c:if>"
                  id="description" rows="5"></textarea>
        <c:if test="${bindingResult.hasFieldErrors('description')}">
            <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <div class="mb-3">
        <label for="imageFile" class="form-label">Image</label>
        <input value="${form.imageFile}" type="file" id="imageFile" name="imageFile"
               class="form-control <c:if test="${bindingResult.hasFieldErrors('imageFile')}">is-invalid</c:if>"
        >
        <c:if test="${bindingResult.hasFieldErrors('imageFile')}">
            <c:forEach items="${bindingResult.getFieldErrors('imageFile')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>
    <div>
        <label for="description" class="form-label">Topics</label>
        <c:forEach items="${topics}" var="topic">
            <div class="form-check <c:if test="${bindingResult.hasFieldErrors('topics')}">is-invalid</c:if>">
                <input
                        class="form-check-input"
                        type="checkbox"
                        name="topics"
                        value="${topic.id}"
                        id="${topic.name.toLowerCase()}"
                />
                <label class="form-check-label" for="${topic.name.toLowerCase()}"> ${topic.name} </label>
            </div>
        </c:forEach>
        <c:if test="${bindingResult.hasFieldErrors('topics')}">
            <c:forEach items="${bindingResult.getFieldErrors('topics')}" var="error">
                <div class="text-danger">${error.defaultMessage}</div>
            </c:forEach>
        </c:if>
    </div>

    <button type="submit" class="w-100 btn btn-primary mt-3">ADD</button>
</form>

<jsp:include page="../include/footer.jsp"/>
