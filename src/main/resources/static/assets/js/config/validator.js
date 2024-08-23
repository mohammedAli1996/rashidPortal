function validateFormData(formData) {
    for (const [key, value] of Object.entries(formData)) {
        if (!value) {
            console.log(key);
            return false;
        }
    }
    return true;
}


function showAlert(title, text, icon,callback) {
    Swal.fire({
        position: "center",
        icon: icon,
        title: title,
        showConfirmButton: false,
        timer: 1500
      })
    // Swal.fire({
    //     title: title,
    //     text: text,
    //     icon: icon,
    //     showCancelButton: false,
    //     confirmButtonColor: '#5664d2',
    //     cancelButtonColor: "#ff3d60"
    // })
    .then((result) => {
        if (result.isConfirmed && typeof callback === 'function') {
            callback();
        }
    });
}


function _(id){
    return document.getElementById(id);
}