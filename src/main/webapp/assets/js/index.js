// console.log(window.location.pathname);

const navLinkHome = document.getElementById("nav-link-home")
const navLinkTopics = document.getElementById("nav-link-topics")
const navLinkMyResources = document.getElementById("nav-link-my-resources")
const navLinkAdminDashboard = document.getElementById("nav-link-admin-dashboard")

const {pathname} = window.location;

if (pathname === "/") {
    navLinkHome.classList.add("active");
} else if (pathname === "/user-list/all") {
    navLinkMyResources.classList.add("active")
} else if (pathname.startsWith("/resources/topics")) {
    navLinkTopics.classList.add("active")
} else if (pathname === "/admin/dashboard") {
    navLinkAdminDashboard.classList.add("active")
}




