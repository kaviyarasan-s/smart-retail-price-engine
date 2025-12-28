function showLoader() {
    $(".loader-overlay").removeClass("d-none");
}

function hideLoader() {
    $(".loader-overlay").addClass("d-none");
}

$.ajaxSetup({
    error: function (jqXHR, status, error) {
        alert("Error: " + jqXHR.responseJSON.message);
    },
});
