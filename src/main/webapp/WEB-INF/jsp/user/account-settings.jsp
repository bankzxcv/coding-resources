<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>

<h1>Account Settings</h1>

<p>Email: ${user.email}</p>
<p>Username: ${user.username}</p>

<a class="btn btn-primary" href="/account/settings/update">Edit Account Settings</a>

<jsp:include page="../include/footer.jsp"/>