let contextPath = document.getElementById("contextPath").innerText;
let users_reviews = document.getElementById("users_reviews");
let review_pagination = document.getElementById("review_pagination")

let current_page_html = review_pagination.querySelector(".active");
let current_page = parseInt(current_page_html.querySelector("a").innerHTML);

let last_page = parseInt(review_pagination.querySelector(".last a").innerHTML)
let userId = document.getElementById("userId").innerText;

review_pagination.querySelectorAll("li a").forEach(function (page) {
    page.addEventListener("click", (e) => {
        var pagenum = page.innerHTML;
        if (page.classList.contains('next')) {
            pagenum = current_page != last_page ? current_page + 1 : last_page;
        }
        if (page.classList.contains('prev')) {
            pagenum = current_page != 1 ? current_page - 1 : 1;

        }

        if (pagenum != current_page) {
            fetch(contextPath + "/controller?command=show_user_reviews&user=" + userId + "&page=" + pagenum, {
                headers: {'X-Requested-With': 'XMLHttpRequest'}
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(response.statusText);
                    }
                    return response.text()
                })
                .then(str => new window.DOMParser().parseFromString(str, "text/html"))
                .then(data => {
                    current_page_html.classList.remove("active");

                    console.log("Current page" + current_page)
                    console.log("new page" + pagenum)

                    var nextPage = e.target.parentElement;

                    if (nextPage.classList.contains('next')) {
                        var nextPageId = "reviewPage" +
                            (parseInt(current_page_html.id.replaceAll("reviewPage", "")) + 1)
                        console.log("nextpage next " + nextPageId)
                        nextPage = review_pagination.querySelector("#" + nextPageId)
                    }
                    if (nextPage.classList.contains('prev')) {
                        var nextPageId = "reviewPage" +
                            (parseInt(current_page_html.id.replaceAll("reviewPage", "")) - 1)
                        console.log("nextpage prev " + nextPageId)
                        nextPage = review_pagination.querySelector("#" + nextPageId)
                    }
                    console.log("next page" + nextPage)

                    nextPage.classList.add("active")
                    current_page_html = nextPage
                    current_page = pagenum;
                    users_reviews.innerHTML = data.body.innerHTML

                })
                .catch(function (error) {
                    console.log(error)
                })
        }

    })
})