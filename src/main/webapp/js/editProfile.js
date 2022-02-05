let oldParameters = {
    firstName: document.getElementById("first_name").value,
    lastName: document.getElementById("last_name").value,
    description: document.getElementById("description").innerHTML
}


let form = document.getElementById("profile_info_form")

form.addEventListener('submit', function (e) {
    let actual_firstname = document.getElementById("first_name").value
    let actual_lastname = document.getElementById("last_name").value
    let actual_desc = document.getElementById("description").innerHTML
    let fileInput = document.getElementById("file").value;

    if (oldParameters.firstName === actual_firstname && oldParameters.lastName === actual_lastname
    && oldParameters.description === actual_desc && fileInput === "") {
        e.preventDefault()
        event.stopPropagation()
    }
        })

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


