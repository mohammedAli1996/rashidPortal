$(document).ready(function () {
    getLckState();
})


function _(id) {
    return document.getElementById(id);
}


function getLckState() {
    $.ajax({
        url: '/api/form-packages/lckState',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            if (!response.canRegister) {
                document.getElementById("pckl1").href = "/locked" ; 
                document.getElementById("pckl2").href = "/locked" ; 
                document.getElementById("pckl3").href = "/locked" ; 
            }
        },
        error: function (error) {
            // showAlert("Error!", "Failed to add question!", "error");
        }
    });
}