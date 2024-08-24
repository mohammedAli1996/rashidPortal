const url = window.location.href;
const urlObj = new URL(url);
const formId = urlObj.searchParams.get('formId');


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
                response.questions.forEach(element => {
                    var activeAddon = "";
                    var cnt = "";
                    if (itr === 0) {
                        activeAddon = "active";
                    }
                    if (element.type === "TEXT") {
                        cnt = `<div class="question-container `+activeAddon+`">
                                    <div class="question">`+element.question+` :</div>
                                    <div class="options">
                                        <input type="text" id="name" />
                                        <a class="btn btn-secondary" onclick="previousQuestion()">السابق</a>
                                        <a class="btn" onclick="nextQuestion()">التالي</a>

                                    </div>
                                </div>` ;
                    } else if (element.type === "RADIO") {
                        var optionsTxt = `` ; 
                        element.options.forEach(qOption => {
                            optionsTxt += `<button onclick="nextQuestion()">`+qOption+`</button>` ; 
                        });
                        cnt = `<div class="question-container `+activeAddon+`">
                                    <div class="question">`+element.question+` :</div>
                                    <div class="options">
                                        `+optionsTxt+`
                                    </div>
                                </div>
                                `;
                    } else if (element.type === "MULTI") {
                        var optionsTxt = `` ; 
                        element.options.forEach(qOption => {
                            optionsTxt += `<label class="custom-checkbox"><input type="checkbox" name="hobbies" value="`+qOption+`"><span></span>
                                        `+qOption+`</label>` ; 
                        });
                        cnt = `<div class="question-container `+activeAddon+`">
                                <div class="question">`+element.question+` :</div>
                                <div class="options">
                                    `+optionsTxt+`
                                    <a class="btn btn-secondary" onclick="previousQuestion()">السابق</a>

                                    <a class="btn" onclick="nextQuestion()">التالي</a>
                                </div>
                            </div>`;
                    } else if (element.type === "FILE") {
                        cnt = `<div class="question-container `+activeAddon+`">
                                    <div class="question">`+element.question+` :</div>
                                    <div class="options">
                                        <div class="row">
                                            <div class="file-upload-wrapper">
                                                <button class="file-upload-button">اختر ملف</button>
                                                <input type="file" id="fileUploader" class="file-upload-input" />
                                            </div>
                                            <span class="file-upload-text" id="file-upload-text">لم يتم اختيار ملف</span>
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
