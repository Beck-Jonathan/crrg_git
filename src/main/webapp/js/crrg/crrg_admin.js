$(document).ready(function() {

    const path = window.location.pathname.toLowerCase();

    $(".nav-btn").each(function () {
        if (this.href.toLowerCase().includes(path)) {
            $(this).addClass("active");
        }
    });


});