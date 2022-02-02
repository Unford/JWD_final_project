
    var area = document.getElementById("text_area");
    var message = document.getElementById("review_message");
    var maxLength = area.getAttribute("maxlength");


    var checkLength = function () {
    if (area.value.length < maxLength) {
    message.innerHTML = (maxLength - area.value.length) + " characters remaining";
} else {

    message.innerHTML = ""

}
}
    setInterval(checkLength, 300);

    (function () {
    'use strict'


    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
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