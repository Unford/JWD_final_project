var area = document.getElementById("description");
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

    let oldParameters = {
        firstName: document.getElementById("first_name").value,
        lastName: document.getElementById("last_name").value,
        description: document.getElementById("description").value
    }

    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {

                let actual_firstname = document.getElementById("first_name").value
                let actual_lastname = document.getElementById("last_name").value
                let actual_desc = document.getElementById("description").value
                let fileInput = document.getElementById("file").value;

                var bool = oldParameters.firstName === actual_firstname
                    && oldParameters.lastName === actual_lastname
                    && oldParameters.description === actual_desc
                    && fileInput === "";

                if (!form.checkValidity() && bool) {
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
    reader.onload = (function (theFile) {
        return function (e) {
            var span = document.getElementById('output');
            span.innerHTML = ['<img class="thumb" title="', escape(theFile.name), '" src="', e.target.result, '" />'].join('');
        };
    })(f);
    reader.readAsDataURL(f);
}

document.getElementById('file').addEventListener('change', handleFileSelect, false);


