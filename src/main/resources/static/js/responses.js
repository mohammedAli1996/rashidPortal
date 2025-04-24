

var responsesMap = new Map();

$(document).ready(function () {
    getData();
    getLockState();
})


function getLockState() {
    $.ajax({
        url: '/adminstration/regLock',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            if (response.canRegister) {
                document.getElementById("currState").value = "مفتوح";
            } else {
                document.getElementById("currState").value = "مغلق";
            }
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });
}

function flipState() {
    $.ajax({
        url: '/adminstration/regLock',
        type: 'POST',
        contentType: 'application/json',
        success: function (response) {
            if (response.canRegister) {
                document.getElementById("currState").value = "مفتوح";
            } else {
                document.getElementById("currState").value = "مغلق";
            }
            showAlert("تم بنجاح!", "تم تغيير حالة التسجيل بنجاح", "success");
        },
        error: function (error) {
            showAlert("Error!", "حدث خطأ ما يرجى المحاولة مرة ثانية", "error");
        }
    });
}


function getData() {
    $.ajax({
        url: '/api/form-responses',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var cnt = ` <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                        style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>التاريخ</th>
                                                <th>الباقة</th>
                                                <th>حالة الدفع</th>
                                                <th>مدفوع</th>
                                                <th>غير مدفوع</th>
                                                <th>عرض</th>
                                                <th>حذف</th>
                                            </tr>
                                        </thead>
                                        <tbody id="packagesTableBody">`;

            response.forEach(element => {
                responsesMap.set(element.id, element);
                cnt += ` 
                <tr>
                                                <td>`+ formatDateToDDMMYYYY(element.submittedAt) + `</td>
                                                <td>`+ element.packageName + `</td>
                                                <td>` + (element.paid ? 'مدفوع' : 'غير مدفوع') + `</td>
                                                <td><button onclick="setpaid('`+ element.id + `')" class="btn btn-success">وضع علامة مدفوع</button></td>
                                                <td><button onclick="setNotpaid('`+ element.id + `')" class="btn btn-danger">وضع علامة غير مدفوع</button></td>
                                                <td><button onclick="viewResponse('`+ element.id + `')" class="btn btn-primary">عرض</button></td>
                                                <td><button onclick="deleteResponse('`+ element.id + `')" class="btn btn-outline-danger">حذف</button></td>
                                            </tr>` ;


            });
            cnt += `</tbody>
                                    </table>`;


            document.getElementById("dtblD").innerHTML = cnt;


            $('#datatable').DataTable({
                "ordering": false, 
                "language": {
                    "paginate": {
                        "previous": "<i class='mdi mdi-chevron-left'>",
                        "next": "<i class='mdi mdi-chevron-right'>"
                    }
                },
                "drawCallback": function () {
                    $('.dataTables_paginate > .pagination').addClass('pagination-rounded');
                }
            });



        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });

}

function viewResponse(resId) {
    var response = responsesMap.get(resId);
    document.getElementById("modalBdy").innerHTML = ``;
    response.responses.forEach(element => {
        if (element.questionType === "TEXT" || element.questionType === "RADIO") {
            document.getElementById("modalBdy").innerHTML += `<label class="form-label">` + element.question + `:</label>
                    <p>`+ element.answers + `</p>
                    <hr>`;
        } else if (element.questionType === "MULTI") {
            if (element.answersList != null) {
                document.getElementById("modalBdy").innerHTML += `<label class="form-label">` + element.question + `:</label><p>`;
                element.answersList.forEach(subAnswer => {
                    document.getElementById("modalBdy").innerHTML += `
                    `+ subAnswer + ` - `;
                });
                document.getElementById("modalBdy").innerHTML += `</p>
                    <hr>`;
            }

        } else if (element.questionType === "FILE") {
            if (element.answersList != null) {
                document.getElementById("modalBdy").innerHTML += `<label class="form-label">` + element.question + `:</label>  <br>`;
                if (element.answersList.length === 0) {
                    document.getElementById("modalBdy").innerHTML += `لا يوجد`;
                } else {
                    element.answersList.forEach(subAnswer => {
                        document.getElementById("modalBdy").innerHTML += `
                        <a href="/api/dfs/getLocalFile/`+ subAnswer + `">تحميل المرفق </a> <br>`;
                    });
                }
                document.getElementById("modalBdy").innerHTML += `
                    <hr>`;
            }
        }
    });
    $('#myModal').modal('show');
}

function deleteResponse(resId) {
    $.ajax({
        url: '/api/form-responses/' + resId,
        type: 'DELETE',
        contentType: 'application/json',
        success: function (response) {
            showAlert("تم بنجاح!", "تم الحذف  بنجاح!", "success");
            getData();
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });
}

function setpaid(resId) {
    $.ajax({
        url: '/api/form-responses/markAsPaid?id=' + resId + '&state=true',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            showAlert("تم بنجاح!", "تم التعديل  بنجاح!", "success");
            getData();
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });
}

function setNotpaid(resId) {
    $.ajax({
        url: '/api/form-responses/markAsPaid?id=' + resId + '&state=false',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            showAlert("تم بنجاح!", "تم التعديل  بنجاح!", "success");
            getData();
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });
}




function formatDateToDDMMYYYY(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const year = date.getFullYear();

    return `${day}-${month}-${year}`;
}


