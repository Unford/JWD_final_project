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

var span = document.getElementById('output');

(function () {
    'use strict'


    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (document.getElementById("file").checkValidity()){
                    span.classList.remove("border", "border-danger")
                }else {
                    span.classList.add("border", "border-danger")
                }

                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()


function handleFileSelect(evt) {
    var file = evt.target.files;
    var f = file[0];
    var reader = new FileReader();
    span.classList.remove("border", "border-danger")
    reader.onload = (function (theFile) {
        return function (e) {
            span.innerHTML = ['<img class="thumb" title="', escape(theFile.name), '" src="', e.target.result, '" />'].join('');
        };
    })(f);
    reader.readAsDataURL(f);
}

document.getElementById('file').addEventListener('change', handleFileSelect, false);