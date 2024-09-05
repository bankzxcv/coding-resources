<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <!-- Google tag (gtag.js) -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-HWE927D7H4"></script>
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }

        gtag('js', new Date());

        gtag('config', 'G-HWE927D7H4');
    </script>
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
    <script
            src="https://kit.fontawesome.com/da84e60c83.js"
            crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="/assets/css/styles.css"/>
    <title>Learn Coding Resources</title>
</head>
<body>
<!-- Nav Bar -->
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="/"
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
                    <a id="nav-link-home" class="nav-link" aria-current="page" href="/"
                    >Home</a
                    >
                </li>
                <%--                <li class="nav-item dropdown">--%>
                <%--                    <a--%>
                <%--                            class="nav-link dropdown-toggle"--%>
                <%--                            href="#"--%>
                <%--                            role="button"--%>
                <%--                            data-bs-toggle="dropdown"--%>
                <%--                            aria-expanded="false"--%>
                <%--                    >--%>
                <%--                        Categories--%>
                <%--                    </a>--%>
                <%--                    <ul class="dropdown-menu">--%>
                <%--                        <li><a class="dropdown-item" href="#">Computer Science</a></li>--%>
                <%--                        <li><a class="dropdown-item" href="#">Web Development</a></li>--%>
                <%--                        <li><a class="dropdown-item" href="#">Data Science</a></li>--%>
                <%--                        <li><a class="dropdown-item" href="#">Databases</a></li>--%>
                <%--                        <li><a class="dropdown-item" href="#">CI/DC</a></li>--%>
                <%--                        <li><a class="dropdown-item" href="#">Cloud Computing</a></li>--%>
                <%--                        <li>--%>
                <%--                            <a class="dropdown-item" href="#">Interview Preps</a>--%>
                <%--                        </li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <li class="nav-item dropdown">
                    <a id="nav-link-topics"
                       class="nav-link dropdown-toggle"
                       href="#"
                       role="button"
                       data-bs-toggle="dropdown"
                       aria-expanded="false"
                    >
                        Topics
                    </a>
                    <ul class="dropdown-menu">
                        <%--                        <c:forEach items="${sessionScope.topics}" var="topic">--%>
                        <%--                            <li><a class="dropdown-item" href="/resources/topics/${topic.id}">${topic.name}</a></li>--%>
                        <%--                        </c:forEach>--%>
                        <li><a class="dropdown-item" href="/resources/topics/7">Algorithms</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/2">CSS</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/5">Data Analysis</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/8">Data Structures</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/4">Data Visualization</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/13">Git</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/1">HTML</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/6">Interview Prep</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/9">Java</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/3">JavaScript</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/16">Node.js</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/10">Python</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/12">React.js</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/14">Ruby</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/15">Ruby on Rails</a></li>
                        <li><a class="dropdown-item" href="/resources/topics/11">SQL</a></li>

                    </ul>
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
                    <li class="nav-item">
                        <a id="nav-link-my-resources" class="nav-link" href="/user-list/all">My Resources</a>
                    </li>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item mx-2">
                            <a id="nav-link-admin-dashboard" class="nav-link px-3 text-center btn btn-outline-primary"
                               href="/admin/dashboard">Admin Dashboard</a>
                        </li>
                    </sec:authorize>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button"
                           data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <sec:authentication property="name"/>
                                <%--                                Roles: <sec:authentication property="principal.authorities"/>--%>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item p-2 px-4" href="/user-list/all">My Resources</a>
                            </li>
                            <li>
                                <a class="dropdown-item p-2 px-4" href="/account/settings">My Account</a>
                            </li>
                            <li role="button" onclick="logoutForm.submit();">
                                <form id="logoutForm" class="dropdown-item p-2 px-4" action="/auth/logout"
                                      method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input class="border-0 bg-transparent p-0" type="submit"
                                           value="Log Out"/>
                                </form>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<main class="container d-flex flex-column align-items-center pt-5">
