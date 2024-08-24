// $(document).ready(function () {
//     getPackages();
// })


// function _(id) {
//     return document.getElementById(id);
// }


// function getPackages(){
//     $.ajax({
//         url: '/api/subscription-form/' + formId,
//         type: 'GET',
//         contentType: 'application/json',
//         success: function (response) {
//             var itr = 1 ; 
//             _("packagesDiv").innerHTML = ``;
//             response.forEach(element => {
//                 if(itr > 4 ){
//                     _("packagesDiv").innerHTML += ``;
//                 }
//                 itr ++ ; 
//             });
//             console.log(response);
//         },
//         error: function (error) {
//             showAlert("Error!", "Failed to add question!", "error");
//         }
//     });
// }