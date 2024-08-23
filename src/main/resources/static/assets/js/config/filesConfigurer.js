var uploadEp , downloadEp ; 
var filesMap = new Map(); 

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/api/fileSystem/upload-end-point',
        success: function (response) {
            uploadEp = response ; 
        },
        error: function (error) {
            console.error("Error loading files uploader end point")
        }
    });

    $.ajax({
        type: 'GET',
        url: '/api/fileSystem/download-end-point',
        success: function (response) {
            downloadEp = response ; 
        },
        error: function (error) {
            console.error("Error loading files uploader end point")
        }
    });
})


function uploadFile(key, inputId) {
    return new Promise(function(resolve, reject) {
        var form = new FormData();
        form.append("file", document.getElementById(inputId).files[0]);
        var settings = {
            "url": uploadEp,
            "method": "POST",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form
        };

        $.ajax(settings).done(function(response) {
            filesMap.set(key, response);
            resolve(response);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            reject(new Error("Upload failed: " + textStatus + " - " + errorThrown));
        });
    });
}