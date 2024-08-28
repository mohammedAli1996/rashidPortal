const url = window.location.href;
const urlObj = new URL(url);
const formId = urlObj.searchParams.get('formId');

var student = false;

$(document).ready(function () {
    getFormData();
})


var questions;
var progressBar;
let currentQuestionIndex = 0;

function getFormData() {
    $.ajax({
        url: '/api/subscription-form/' + formId,
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var itr = 0;
            _("qstns").innerHTML = "";
            if (response.questions != null) {
                _("qstns").innerHTML += `<div class="question-container active">
                <div class="question">هل انت طالب ؟ ( خصم 20% للطلاب ) :</div>
                <div class="options">
                    <button onclick="callStnt()">أنا طالب</button>
                    <button onclick="nextQuestion()">أنا موظف</button>
                </div>
            </div>`;
                response.questions.forEach(element => {
                    var activeAddon = "";
                    var cnt = "";
                    if (element.type === "TEXT") {
                        cnt = `<div class="question-container ` + activeAddon + `">
                                    <div class="question">`+ element.question + ` :</div>
                                    <div class="options">
                                        <input type="text" id="txtqstn`+ element.id + `" onkeyup="addAnswerText('` + element.id + `','txtqstn` + element.id + `','nxtBtn` + element.id + `')"/>
                                        <a class="btn btn-secondary" onclick="previousQuestion()">السابق</a>
                                        <a class="btn" id="nxtBtn`+ element.id + `" style="display:none;" onclick="nextQuestion()">التالي</a>

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
                            optionsTxt += `<label  class="custom-checkbox"><input onclick="multiChoiceCheck('` + element.id + `','` + qOption + `')" type="checkbox" name="hobbies" value="` + qOption + `"><span></span>
                                        `+ qOption + `</label>`;
                        });
                        cnt = `<div class="question-container ` + activeAddon + `">
                                <div class="question">`+ element.question + ` :</div>
                                <div class="options">
                                    `+ optionsTxt + `
                                    <a class="btn btn-secondary" onclick="previousQuestion()">السابق</a>

                                    <a class="btn" onclick="nextQuestion()">التالي</a>
                                </div>
                            </div>`;
                    } else if (element.type === "FILE") {
                        cnt = `<div class="question-container ` + activeAddon + `">
                                    <div class="question">`+ element.question + ` :</div>
                                    <div class="options">
                                        <div class="row">
                                            <div class="file-upload-wrapper">
                                                <button class="file-upload-button">اختر ملف</button>
                                                <input type="file" oninput="whenFilesSelected('fileUploaderText`+ element.id + `' , 'fileUploader` + element.id + `' , '` + element.id + `')" id="fileUploader` + element.id + `" class="file-upload-input" accept="image/*, .pdf, .txt" multiple/>
                                            </div>
                                            <span class="file-upload-text" id="fileUploaderText`+ element.id + `">لم يتم اختيار ملف</span>
                                        </div>
                                        <a class="btn btn-secondary" onclick="previousQuestion()">السابق</a>
                                        <a class="btn" onclick="nextQuestion()">التالي</a>
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
            console.log(response);
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

function addAnswerRadio(questionId, answer) {
    answersMap.set(questionId, answer);
    console.log(answersMap);
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


var multiChoiceMap = new Map();

function multiChoiceCheck(questionId, value) {
    if (multiChoiceMap.has(questionId)) {
        var currElementsArr = multiChoiceMap.get(questionId);
        if (currElementsArr.includes(value)) {

            if (currElementsArr.length === 1) {
                currElementsArr = [] ; 
            } else {
                var indexOfElement = currElementsArr.indexOf(value);
                currElementsArr.splice(indexOfElement, indexOfElement);
            }
        } else {
            currElementsArr.push(value);
        }
        multiChoiceMap.set(questionId, currElementsArr);
    } else {
        var elmArray = [];
        elmArray.push(value);
        multiChoiceMap.set(questionId, elmArray);
    }
    answersMap.set(questionId, multiChoiceMap);

    console.log("multiChoiceMap:");
    console.log(multiChoiceMap);
    console.log("answersMap");
    console.log(answersMap);
}


function callStnt() {
    student = true;
    nextQuestion();
}



var filesMap = new Map();


function uploadFiles(inputId, key) {
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
                throw new Error(`Failed to upload files: ${response.statusText}`);
            }
            //hide loader 
            return response.json();
        })
        .then(responseData => {
            responseArray.push(...responseData);
            filesMap.set(key, responseArray);
            //hide loader 
        })
        .catch(error => {
            console.error(`Error uploading files: ${error}`);
            //hide loader 
        });
}





function whenFilesSelected(fileTextElementId, fileInputId, questionId) {
    _(fileTextElementId).innerHTML = _(fileInputId).files.length + "ملفات تم اختيارها";

    uploadFiles(fileInputId, questionId);


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
