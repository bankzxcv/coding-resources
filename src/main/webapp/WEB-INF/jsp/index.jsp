<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"/>

<main class="container d-flex flex-column align-items-center pt-5">
    <c:forEach items="${resources}" var="resource">
        <div
                role="link"
                target="_blank"
                onclick=" window.open('./resource.html', '_blank').focus();"
                class="card my-2"
        >
            <div class="row g-0">
                <div
                        class="col-md-4 d-flex justify-content-center align-items-center"
                >
                    <img
                            class="resource-img"
                            src="${resource.imageUrl}"
                            class="rounded-start"
                            alt="..."
                    />
                </div>

                <div class="col-md-8">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2 class="card-title">${resource.name}</h2>
                            <a href="#" class="btn btn-primary">+ ADD TO LIST</a>
                        </div>
                        <p class="card-text">
                                ${resource.description}
                        </p>
                        <div class="card-text gap-1 d-flex justify-content-end">
                            <a
                                    href="${resource.url}"
                                    target="_blank"
                                    class="btn btn-secondary"
                                    onclick="event.stopPropagation();"
                            >START LEARNING</a
                            >
                            <a
                                    href="./resource.html"
                                    target="_blank"
                                    class="btn btn-outline-secondary"
                                    onclick="event.stopPropagation();"
                            >MORE INFO</a
                            >
                        </div>

                        <p class="card-text mt-3 gap-1 d-flex justify-content-end">
                            <c:forEach items="${resource.resourceTopics}" var="resourceTopic">
                                <a href="#" class="text-muted chip">${resourceTopic.topic.name}</a>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </div>


        </div>
    </c:forEach>
</main>

<jsp:include page="include/footer.jsp"/>