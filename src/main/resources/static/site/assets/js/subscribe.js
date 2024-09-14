var student = false;
var selectedPackage = '1', selectedPackageName = 'VIP';
var packageAmount = 0;
var formId;

$(document).ready(function () {
    const queryParams = new URLSearchParams(window.location.search);

    selectedPackage = queryParams.get('packageType');
    selectedPackageName = queryParams.get('packageName');

    $.ajax({
        url: '/api/subscription-form/mainForm',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            formId = response.id;
            getFormData();
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });


})


var questions;
var progressBar;
let currentQuestionIndex = 0;

var subscriptionFormQuestionsOrigin;

function getFormData() {
    $.ajax({
        url: '/api/subscription-form/' + formId,
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            subscriptionFormQuestionsOrigin = response.questions;
            var itr = 0;
            _("qstns").innerHTML = "";
            if (response.questions != null) {
                // _("qstns").innerHTML += `<div class="question-container active">
                //                             <div class="question">هل انت طالب ؟ ( خصم 20% للطلاب ) :</div>
                //                             <div class="options">
                //                                 <button onclick="callStnt()">أنا طالب</button>
                //                                 <button onclick="nextQuestion()">أنا موظف</button>
                //                             </div>
                //                         </div>`;
                response.questions.forEach(element => {
                    var activeAddon = "";
                    var cnt = "";

                    if(itr == 0 ){
                        activeAddon = "active"; 
                    }
                    if (itr == 2) {
                        _("qstns").innerHTML += `<div class="question-container">
                                            <div class="question">هل انت طالب ؟ ( خصم 20% للطلاب ) :</div>
                                            <div class="options">
                                                <button onclick="callStnt()">أنا طالب</button>
                                                <button onclick="nextQuestion()">أنا موظف</button>
                                            </div>
                                        </div>`
                    }


                    var requiredAddon = ``, requiredAddonTwo = ``;
                    if (element.requiredQ) {
                        requiredAddon = `style="display:none;"`;
                        requiredAddonTwo = ` *`;
                    }

                    if (element.type === "TEXT") {
                        cnt = `<div class="question-container ` + activeAddon + `">
                                    <div class="question">`+ element.question + requiredAddonTwo + ` :</div>
                                    <div class="options">
                                        <input type="text" id="txtqstn`+ element.id + `" onkeyup="addAnswerText('` + element.id + `','txtqstn` + element.id + `','nxtBtn` + element.id + `')"/>
                                        <a class="btn" onclick="previousQuestion()">السابق</a>
                                        <a class="btn" id="nxtBtn`+ element.id + `" ` + requiredAddon + ` onclick="nextQuestion()">التالي</a>

                                    </div>
                                </div>` ;
                    } else if (element.type === "RADIO") {
                        var optionsTxt = ``;
                        element.options.forEach(qOption => {
                            optionsTxt += `<button onclick="addAnswerRadio('` + element.id + `' , '` + qOption + `' )">` + qOption + `</button>`;
                        });
                        cnt = `<div class="question-container ` + activeAddon + `">
                                    <div class="question">`+ element.question + ` :</div>
                                    <div class="options">
                                        `+ optionsTxt + `
                                    </div>
                                </div>
                                `;
                    } else if (element.type === "MULTI") {
                        var optionsTxt = ``;
                        element.options.forEach(qOption => {
                            optionsTxt += `<label  class="custom-checkbox"><input onclick="multiChoiceCheck('` + element.id + `','` + qOption + `','nxtBtn` + element.id + `')" type="checkbox" name="hobbies" value="` + qOption + `"><span></span>
                                        `+ qOption + `</label>`;
                        });
                        cnt = `<div class="question-container ` + activeAddon + `">
                                <div class="question">`+ element.question + ` :</div>
                                <div class="options">
                                    `+ optionsTxt + `
                                    <a class="btn" onclick="previousQuestion()">السابق</a>

                                    <a class="btn" style="display:none;" id="nxtBtn`+ element.id + `" onclick="nextQuestion()">التالي</a>
                                </div>
                            </div>`;
                    } else if (element.type === "FILE") {
                        cnt = `<div class="question-container ` + activeAddon + `">
                                    <div class="question">`+ element.question + requiredAddonTwo + ` :</div>
                                    <div class="options">
                                        <div class="row">
                                            <div class="file-upload-wrapper">
                                                <button class="file-upload-button">اختر ملف</button>
                                                <input type="file" oninput="whenFilesSelected('fileUploaderText`+ element.id + `' , 'fileUploader` + element.id + `' , '` + element.id + `','nxtBtn` + element.id + `','loaderId` + element.id + `')" id="fileUploader` + element.id + `" class="file-upload-input" accept="image/*, .pdf, .txt" multiple/>
                                            </div>
                                            <span class="file-upload-text" id="fileUploaderText`+ element.id + `">لم يتم اختيار ملف</span>
                                        </div>
                                        <a class="btn" onclick="previousQuestion()">السابق</a>
                                        <div class="loader" style="display:none;" id="loaderId`+ element.id + `"></div>
                                        <a class="btn" `+ requiredAddon + ` id="nxtBtn` + element.id + `" onclick="nextQuestion()">التالي</a>
                                    </div>
                                </div>`;
                    }
                    _("qstns").innerHTML += cnt;
                    itr++;
                });
                questions = document.querySelectorAll('.question-container');
                progressBar = document.getElementById('progress-bar');
                currentQuestionIndex = 0;
            }
        },
        error: function (error) {
            showAlert("Error!", "Failed to add question!", "error");
        }
    });
}

function _(id) {
    return document.getElementById(id);
}



var answersMap = new Map();
var multiChoiceMap = new Map();
var filesMap = new Map();

function addAnswerRadio(questionId, answer) {
    answersMap.set(questionId, answer);
    nextQuestion();
}


function addAnswerText(questionId, answerInputId, nextButtonId) {
    answersMap.set(questionId, _(answerInputId).value);
    if (_(answerInputId).value != null && _(answerInputId).value.trim() != "") {
        _(nextButtonId).style.display = "inline-block";
    } else {
        _(nextButtonId).style.display = "none";
    }
}


function multiChoiceCheck(questionId, value, nextBtnId) {
    if (multiChoiceMap.has(questionId)) {
        var currElementsArr = multiChoiceMap.get(questionId);
        if (currElementsArr.includes(value)) {

            if (currElementsArr.length === 1) {
                currElementsArr = [];
            } else {
                var indexOfElement = currElementsArr.indexOf(value);
                currElementsArr.splice(indexOfElement, indexOfElement);
            }
        } else {
            currElementsArr.push(value);
        }

        if (currElementsArr.length === 0) {
            _(nextBtnId).style.display = "none";
        } else {
            _(nextBtnId).style.display = "inline-block";
        }
        multiChoiceMap.set(questionId, currElementsArr);
    } else {
        var elmArray = [];
        elmArray.push(value);
        multiChoiceMap.set(questionId, elmArray);
        _(nextBtnId).style.display = "inline-block";
    }
}


function callStnt() {
    student = true;
    nextQuestion();
}

function choosePkg(packageIndex, packageName) {
    selectedPackage = packageIndex;
    selectedPackageName = packageName;
    nextQuestion();
}

function getPaymentLink() {

    if (!student) {
        if (selectedPackage === '1') {
            packageAmount = 180;
            return "https://portal.myfatoorah.com/KWT/la/05064177365884058";
        } else if (selectedPackage === '2') {
            packageAmount = 150;
            return "https://portal.myfatoorah.com/KWT/la/05064177365883958";
        } else {
            packageAmount = 210;
            return "https://portal.myfatoorah.com/KWT/la/05064177365884358";
        }
    } else {
        if (selectedPackage === '1') {
            packageAmount = 144;
            return "https://portal.myfatoorah.com/KWT/la/05064177365884558";
        } else if (selectedPackage === '2') {
            packageAmount = 120;
            return "https://portal.myfatoorah.com/KWT/la/05064177370487458";
        } else {
            packageAmount = 168;
            return "https://portal.myfatoorah.com/KWT/la/05064177365884758";
        }
    }

}



function uploadFiles(inputId, key, loaderId, nextBtnId) {
    _(loaderId).style.display = "inline-block";
    const inputElement = document.getElementById(inputId);
    const files = inputElement.files;
    const responseArray = [];

    //display loader

    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
        formData.append('files', files[i]);
    }

    fetch('/api/fs/multiFilesUploader', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                _(loaderId).style.display = "none";
                throw new Error(`Failed to upload files: ${response.statusText}`);
            }
            _(loaderId).style.display = "none";
            return response.json();
        })
        .then(responseData => {
            responseArray.push(...responseData);
            filesMap.set(key, responseArray);
            _(loaderId).style.display = "none";
            _(nextBtnId).style.display = "inline-block";

        })
        .catch(error => {
            _(loaderId).style.display = "none";
            console.error(`Error uploading files: ${error}`);
        });
}




function whenFilesSelected(fileTextElementId, fileInputId, questionId, nextBtnId, loaderId) {
    _(fileTextElementId).innerHTML = _(fileInputId).files.length + "ملفات تم اختيارها";
    uploadFiles(fileInputId, questionId, loaderId, nextBtnId);
}



function submitResponse() {
    _("submitBtn").disabled = true;
    _("sucMsg").style.display = "none";
    _("errMsg").style.display = "none";
    var responses = [];
    if (student) {
        var answer = {
            questionId: "",
            question: "هل انت طالب ؟",
            questionType: "TEXT",
            answers: "أنا طالب"
        }
        responses.push(answer);
    } else {
        var answer = {
            questionId: "",
            question: "هل انت طالب ؟",
            questionType: "TEXT",
            answers: "أنا موظف"
        }
        responses.push(answer);
    }

    subscriptionFormQuestionsOrigin.forEach(question => {
        if (question.type === "TEXT" || question.type === "RADIO") {
            var answer = {
                questionId: question.id,
                question: question.question,
                questionType: question.type,
                answers: answersMap.get(question.id)
            }
            responses.push(answer);
        }
        else if (question.type === "MULTI") {
            var answer = {
                questionId: question.id,
                question: question.question,
                questionType: question.type,
                answersList: multiChoiceMap.get(question.id)
            }
            responses.push(answer);
        }
        else if (question.type === "FILE") {
            var fileValue = [];
            if (filesMap.has(question.id)) {
                fileValue = filesMap.get(question.id);
            }
            var answer = {
                questionId: question.id,
                question: question.question,
                questionType: question.type,
                answersList: fileValue
            }
            responses.push(answer);
        }
    });

    var formData = {
        packageName: selectedPackageName,
        paymentAmount: packageAmount,
        responses: responses
    };


    $.ajax({
        url: '/api/form-responses',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function (response) {
            _("sucMsg").style.display = "block";
            setTimeout(function () {
                window.location.href = getPaymentLink();
            }, 2000);
        },
        error: function (xhr, status, error) {
            _("errMsg").style.display = "block";
            _("submitBtn").disabled = false;
        }
    });

}


function nextQuestion() {
    if (currentQuestionIndex < questions.length - 1) {
        questions[currentQuestionIndex].classList.remove('active');
        currentQuestionIndex++;
        questions[currentQuestionIndex].classList.add('active');
        updateProgressBar();
    }
}

function previousQuestion() {
    if (currentQuestionIndex > 0) {
        questions[currentQuestionIndex].classList.remove('active');
        currentQuestionIndex--;
        questions[currentQuestionIndex].classList.add('active');
        updateProgressBar();
    }
}

function updateProgressBar() {
    const progress = ((currentQuestionIndex + 1) / questions.length) * 100;
    progressBar.style.width = progress + '%';
}
