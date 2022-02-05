var area = document.getElementById("text_area");
var message = document.getElementById("review_message");
var maxLength = area.getAttribute("maxlength");


var checkLength = function () {
    if (area.value.length < maxLength) {
        message.innerHTML = (maxLength - area.value.length);
    } else {

        message.innerHTML = ""

    }
}
setInterval(checkLength, 300);

(function () {
    'use strict'


    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()