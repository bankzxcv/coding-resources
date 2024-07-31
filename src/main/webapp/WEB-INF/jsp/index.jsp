<jsp:include page="include/header.jsp"/>

<main class="container d-flex flex-column align-items-center pt-5">
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
                        src="/assets/img/resources/FreeCodeCamp.webp"
                        class="rounded-start"
                        alt="..."
                />
            </div>

            <div class="col-md-8">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <h2 class="card-title">FreeCodeCamp</h2>
                        <a href="#" class="btn btn-primary">+ ADD TO LIST</a>
                    </div>
                    <p class="card-text">
                        FreeCodeCamp offers a free, extensive curriculum for learning
                        web development and programming, including HTML, CSS,
                        JavaScript, and various other technologies. It provides hands-on
                        coding exercises, real-world projects, and certifications upon
                        completion. The platform also features a supportive community
                        and resources to help learners build practical skills and
                        prepare for tech careers.
                    </p>
                    <div class="card-text gap-1 d-flex justify-content-end">
                        <a
                                href="https://www.freecodecamp.org/learn/"
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
                        <a href="#" class="text-muted chip">HTML</a>
                        <a href="#" class="text-muted chip">CSS</a>
                        <a href="#" class="text-muted chip">JavaScript</a>
                        <a href="#" class="text-muted chip">Data Visualization</a>
                        <a href="#" class="text-muted chip"
                        >Data Analysis with Python</a
                        >
                    </p>
                </div>
            </div>
        </div>


    </div>
    <div class="card my-2">
        <div class="row g-0">
            <div
                    class="col-md-4 d-flex justify-content-center align-items-center"
            >
                <img
                        class="resource-img"
                        src="/assets/img/resources/HackerRank.png"
                        class="rounded-start"
                        alt="..."
                />
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <h2 class="card-title">HackerRank</h2>
                        <a href="#" class="btn btn-primary">+ ADD TO LIST</a>
                    </div>
                    <p class="card-text">
                        HackerRank offers coding challenges and competitions to improve
                        their skills and provides structured learning paths with
                        problem-solving exercises. It also connects developers with
                        potential employers through company-sponsored challenges and
                        assessments.
                    </p>
                    <div class="card-text gap-1 d-flex justify-content-end">
                        <a href="#" class="btn btn-secondary">START LEARNING</a>
                        <a href="#" class="btn btn-outline-secondary">MORE INFO</a>
                    </div>
                    <p class="card-text mt-3 gap-1 d-flex justify-content-end">
                        <a href="#" class="text-muted chip">Interview Prep</a>
                        <a href="#" class="text-muted chip">Algorithms</a>
                        <a href="#" class="text-muted chip">Data Structure</a>
                        <a href="#" class="text-muted chip">Java</a>
                        <a href="#" class="text-muted chip">Python</a>
                        <a href="#" class="text-muted chip">SQL</a>
                        <a href="#" class="text-muted chip">JavaScript</a>
                        <a href="#" class="text-muted chip">React</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="card my-2">
        <div class="row g-0">
            <div
                    class="col-md-4 d-flex justify-content-center align-items-center"
            >
                <img
                        class="resource-img"
                        src="/assets/img/resources/TheOdinProject.png"
                        class="rounded-start"
                        alt="..."
                />
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <h2 class="card-title">The Odin Project</h2>
                        <a href="#" class="btn btn-primary">+ ADD TO LIST</a>
                    </div>
                    <p class="card-text">
                        The Odin Project offers a comprehensive, free curriculum for
                        learning web development, covering HTML, CSS, JavaScript, and
                        Ruby on Rails. It provides hands-on projects, real-world
                        exercises, and a supportive community to help learners build
                        practical skills. The platform is designed for self-paced
                        learning, making it accessible for beginners and those looking
                        to enhance their programming knowledge.
                    </p>
                    <div class="card-text gap-1 d-flex justify-content-end">
                        <a href="#" class="btn btn-secondary">START LEARNING</a>
                        <a href="#" class="btn btn-outline-secondary">MORE INFO</a>
                    </div>
                    <p class="card-text mt-3 gap-1 d-flex justify-content-end">
                        <a href="#" class="text-muted chip">HTML</a>
                        <a href="#" class="text-muted chip">CSS</a>
                        <a href="#" class="text-muted chip">Git</a>
                        <a href="#" class="text-muted chip">Ruby</a>
                        <a href="#" class="text-muted chip">Ruby on Rails</a>
                        <a href="#" class="text-muted chip">Node.js</a>
                        <a href="#" class="text-muted chip">React</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="include/footer.jsp"/>