<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<!-- Resource Form -->
<form id="resource-form" action="/admin/submit-new-resource" method="post" style="width: 90%; max-width: 650px"
      class="border p-4 rounded-2">
    <h1 class="text-center mb-4">Resource</h1>

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name"/>
    </div>
    <div class="mb-3">
        <label for="url" class="form-label">URL</label>
        <input type="text" class="form-control" id="url" name="url"/>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea class="form-control" id="description" rows="5"></textarea>
    </div>
    <div class="mb-3">
        <label for="image" class="form-label">Image</label>
        <input type="file" id="image" name="image"
               class="form-control"
        >
    </div>
    <div>
        <label for="description" class="form-label">Topics</label>
        <c:forEach items="${topics}" var="topic">
            <div class="form-check">
                <input
                        class="form-check-input"
                        type="checkbox"
                        name="topics"
                        value="1"
                        id="${topic.name.toLowerCase()}"
                />
                <label class="form-check-label" for="${topic.name.toLowerCase()}"> ${topic.name} </label>
            </div>
        </c:forEach>

    </div>

    <button type="submit" class="w-100 btn btn-primary mt-3">ADD</button>
</form>

<jsp:include page="../include/footer.jsp"/>
