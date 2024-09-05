const url = window.location.href;
const urlObj = new URL(url);
const id = urlObj.searchParams.get('id');



var optIndex = 1;

function showModal() {
    $('#otpModal').modal('show');
}

$(document).ready(function () {
    getFormDetails();
    // Show options fields if RADIO or MULTI is selected
    $('#questionType').change(function () {
        var selectedType = $(this).val();
        if (selectedType === 'RADIO' || selectedType === 'MULTI') {
            optIndex = 1;
            document.getElementById("optionsContainer").innerHTML = ` <button type="button" class="btn btn-warning mb-3" onclick="addNewOption()">إضافة خيار</button> <br>
                            <label class="form-label">الخيارات</label>
                            <span id="optionsPLH">
                                <input type="text" id="opt1" class="form-control mb-2" placeholder="الخيار 1">
                            </span>`;
            $('#optionsContainer').show();
        } else {
            $('#optionsContainer').hide();
        }
    });


    // Handle Save Question button click
    $('#saveQuestionButton').click(function () {
        var question = $('#questionText').val();
        var type = $('#questionType').val();
        var options = [];

        if (type === 'RADIO' || type === 'MULTI') {
            $('#optionsContainer input').each(function () {
                var optionValue = $(this).val();
                if (optionValue) {
                    options.push(optionValue);
                }
            });
        }

        var formQuestion = {
            parentFormId: id,
            question: question,
            type: type,
            options: options,
            requiredQ:_("requiredQ").checked
        };

        $.ajax({
            url: '/api/subscription-form/addQuestion',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formQuestion),
            success: function (response) {
                $('#otpModal').modal('hide');
                showAlert("تم بنجاح!", "تم إضافة السؤال بنجاح!", "success");

                getFormDetails();
            },
            error: function (error) {
                showAlert("خطأ!", "فشل إضافة السؤال!", "error");
            }
        });
    });

})




function addNewOption() {
    var valsMap = new Map();
    for (var i = 1; i <= optIndex; i++) {
        valsMap.set("opt" + i, document.getElementById("opt" + i).value);
    }
    optIndex++;
    document.getElementById("optionsPLH").innerHTML += `<input type="text" id="opt` + optIndex + `" class="form-control mb-2" placeholder="الخيار ` + optIndex + `">`;
    for (var i = 1; i < optIndex; i++) {
        document.getElementById("opt" + i).value = valsMap.get("opt" + i);
    }
}


var questionsMap = new Map();

function getFormDetails() {
    $.ajax({
        type: 'GET',
        url: '/api/subscription-form/' + id,
        success: function (response) {
            _("formtitle").value = response.formtitle;
            _("formDescription").value = response.formDescription;
            document.getElementById('packagesTableBody').innerHTML = ``;
            var cnt = `<table id="datatable" class="table table-bordered dt-responsive nowrap"
                                        style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>السؤال</th>
                                                <th>نوع السؤال</th>
                                                <th>الخيارات</th>
                                                <th>السؤال مطلوب ؟</th>
                                                <th>تعديل</th>
                                                <th>حذف</th>
                                            </tr>
                                        </thead>
                                        <tbody>`;
            if (response.questions != null && response.questions.length > 0) {
                response.questions.forEach(question => {
                    questionsMap.set(question.id, question);
                    cnt += addQuestionToTable(question);
                });
            }

            cnt += ` </tbody>
                                    </table>` ;

            document.getElementById("packagesTableBody").innerHTML = cnt;

            $('#datatable').DataTable({
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
            console.error("Error subscriptions")
        }
    });
}


function addQuestionToTable(question) {
    var optionsAddon = "";
    if (question.options != null && question.options.length > 0) {
        question.options.forEach(element => {
            optionsAddon += element + " - "
        });
    }

    var qType = "" ; 

    if(question.type === "TEXT" ){
        qType = "نص";
    }else if(question.type === "RADIO" ){
        qType = "اختيار من متعدد (خيار واحد)";
    }else if(question.type === "MULTI" ){
        qType = "اختيار من متعدد (عدة خيارات)";
    }else if(question.type === "FILE" ){
        qType = "ملف";
    }

    var requiredAddontext = "" ;
    if(question.requiredQ){
        requiredAddontext = "نعم";
    }else{
        requiredAddontext = "لا";
    }

    var res = `
    <tr>
        <td>${question.question}</td>
        <td>`+qType+`</td>
        <td>${optionsAddon}</td>
        <td>`+requiredAddontext+`</td>
        <td><button class="btn btn-secondary" onclick="editQuestion('${question.id}')">تعديل</button></td>
         <td><button class="btn btn-danger" onclick="deleteQuestion('${question.id}')">حذف</button></td>
        </tr>
    `;
    return res;
}


function _(rid) {
    return document.getElementById(rid);
}

function deleteQuestion(qid) {
    $.ajax({
        type: 'DELETE',
        url: '/api/subscription-form/removeQuestion/' + qid,
        success: function (response) {
            getFormDetails();
        },
        error: function (error) {
            console.error("Error subscriptions")
        }
    });
}


var edtIndex = 0;
var currQID;

function editQuestion(qid) {
    currQID = qid;
    var qstn = questionsMap.get(qid);
    _("edtquestionText").value = qstn.question;
    _("edtquestionType").value = qstn.type;

    _("eRequiredQ").checked = qstn.requiredQ ; 

    document.getElementById("edtOptionsPLH").innerHTML = ``;
    var qOptions = qstn.options;
    if (qstn.type === 'RADIO' || qstn.type === 'MULTI') {
        $('#edtoptionsContainer').show();
        edtIndex = 0;
        qOptions.forEach(element => {
            edtIndex++;
            document.getElementById("edtOptionsPLH").innerHTML += ` <input type="text" id="edtopt` + edtIndex + `" class="form-control mb-2" value="` + element + `">`;
        });
    } else {
        $('#edtoptionsContainer').hide();
    }
    $('#edtModal').modal('show');
}


function addNewEdtOption() {
    var valsMap = new Map();
    for (var i = 1; i <= edtIndex; i++) {
        valsMap.set("edtopt" + i, document.getElementById("edtopt" + i).value);
    }
    edtIndex++;
    document.getElementById("edtOptionsPLH").innerHTML += `<input type="text" id="edtopt` + edtIndex + `" class="form-control mb-2" placeholder="الخيار ` + edtIndex + `">`;
    for (var i = 1; i < edtIndex; i++) {
        document.getElementById("edtopt" + i).value = valsMap.get("edtopt" + i);
    }
}


function changeQType() {
    var selectedType = _("edtquestionType").value;

    if (selectedType === 'RADIO' || selectedType === 'MULTI') {
        edtIndex = 1;
        document.getElementById("edtoptionsContainer").innerHTML = ` <button type="button" class="btn btn-warning mb-3" onclick="addNewEdtOption()">إضافة خيار</button> <br>
                            <label class="form-label">الخيارات</label>
                            <span id="edtOptionsPLH">
                                <input type="text" id="edtopt1" class="form-control mb-2" placeholder="الخيار 1">
                            </span>`;
        $('#edtoptionsContainer').show();
    } else {
        $('#edtoptionsContainer').hide();
    }

}

function updateQuestion() {
    var question = $('#edtquestionText').val();
    var requiredQ = document.getElementById("eRequiredQ").checked ; 
    var type = $('#edtquestionType').val();
    var options = [];

    if (type === 'RADIO' || type === 'MULTI') {
        $('#edtoptionsContainer input').each(function () {
            var optionValue = $(this).val();
            if (optionValue) {
                options.push(optionValue);
            }
        });
    }

    var formQuestion = {
        id: currQID,
        question: question,
        type: type,
        options: options,
        requiredQ:requiredQ
    };

    $.ajax({
        url: '/api/subscription-form/updateQuestion',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formQuestion),
        success: function (response) {
            $('#edtModal').modal('hide');
            showAlert("تم بنجاح!", "تم تعديل السؤال بنجاح!", "success");
            getFormDetails();
        },
        error: function (error) {
            showAlert("خطأ!", "فشل تعديل السؤال!", "error");

        }
    });


}
