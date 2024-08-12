const radioButtons = document.querySelectorAll(".filter-status-radio-button");


radioButtons.forEach(radioButton => {
    radioButton.addEventListener("change", function (e) {
        const status = e.target.value;

        let url = "/admin/dashboard";

        if (!!status && status !== "All") {
            url = url + "?status=" + status;
        }

        window.location.href = url;
    })
})