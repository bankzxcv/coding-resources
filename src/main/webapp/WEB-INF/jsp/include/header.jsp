<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
            rel="stylesheet"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=DM+Serif+Display&family=Roboto+Mono:ital@0;1&display=swap"
            rel="stylesheet"
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />

    <link rel="stylesheet" href="/assets/css/styles.css"/>
    <title>Learn Coding Resources</title>
</head>
<body>
<!-- Nav Bar -->
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="#"
        ><img src="/assets/img/logo/logo.png"
        /></a>
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div
                class="collapse navbar-collapse flex-grow-0"
                id="navbarNavDropdown"
        >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/"
                    >Home</a
                    >
                </li>
                <li class="nav-item dropdown">
                    <a
                            class="nav-link dropdown-toggle"
                            href="#"
                            role="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                    >
                        Categories
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Computer Science</a></li>
                        <li><a class="dropdown-item" href="#">Web Development</a></li>
                        <li><a class="dropdown-item" href="#">Data Science</a></li>
                        <li><a class="dropdown-item" href="#">Databases</a></li>
                        <li><a class="dropdown-item" href="#">CI/DC</a></li>
                        <li><a class="dropdown-item" href="#">Cloud Computing</a></li>
                        <li>
                            <a class="dropdown-item" href="#">Interview Preps</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a
                            class="nav-link dropdown-toggle"
                            href="#"
                            role="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                    >
                        Topics
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">HTML</a></li>
                        <li><a class="dropdown-item" href="#">CSS</a></li>
                        <li><a class="dropdown-item" href="#">JavaScript</a></li>
                        <li><a class="dropdown-item" href="#">TypeScript</a></li>
                        <li><a class="dropdown-item" href="#">Python</a></li>
                        <li><a class="dropdown-item" href="#">Java</a></li>
                        <li><a class="dropdown-item" href="#">Ruby</a></li>
                        <li><a class="dropdown-item" href="#">MySQL</a></li>
                        <li><a class="dropdown-item" href="#">PostgreSQL</a></li>
                        <li><a class="dropdown-item" href="#">MongoDB</a></li>
                        <li>
                            <a class="dropdown-item" href="#">Interview Preps</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user-list/all">My Resources</a>
                </li>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link px-3 btn btn-primary text-white" href="/auth/login">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-3 btn btn-outline-secondary mx-2" href="/auth/signup">Sign Up</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item mx-2">
                            <a class="nav-link px-3 text-center btn btn-primary"
                               href="/admin/dashboard">Admin Dashboard</a>
                        </li>
                    </sec:authorize>
                    <li class="nav-item flex-row-center-center">
                        <span class="nav-link fs-6"><sec:authentication property="name"/></span>
                    </li>
                    <form class="flex-row-center-center" action="/auth/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input class="nav-item px-3 btn btn-outline-secondary" type="submit" value="Log Out"/>
                    </form>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<main class="container d-flex flex-column align-items-center pt-5">
