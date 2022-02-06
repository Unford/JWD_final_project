    let contextPath = document.getElementById("contextPath").innerText;
    let users_reviews = document.getElementById("users_reviews");
    let review_pagination = document.getElementById("review_pagination")
    let current_page_html = review_pagination.querySelector(".active");
    let current_page = parseInt(current_page_html.querySelector("a").innerHTML);

    review_pagination.querySelectorAll("li a").forEach(function (page){
        page.addEventListener("click", (e) =>{
            var pagenum = page.innerHTML;
            if (page.classList.contains('next')){
                pagenum =  1 + current_page;
            }
            if (page.classList.contains('prev')){
                pagenum = current_page != 1 ? current_page - 1 : 1;

            }
            if (pagenum != current_page){
                fetch(contextPath + "/controller?command=show_user_reviews&page=" + pagenum, {
                    d
                })

                    .then(response => response.text())
                    .then(str => new window.DOMParser().parseFromString(str, "text/html"))
                    .then(data => {
                        current_page_html.classList.remove("active");

                        console.log("Current page" + current_page)
                        console.log("new page" + pagenum)
                        console.log("target link" + e.target)

                        e.target.parentElement.classList.add("active")
                        if (page.classList.contains('next')){
                            pagenum =  1 + current_page;
                        }
                        if (page.classList.contains('prev')){
                            pagenum = current_page != 1 ? current_page - 1 : 1;

                        }
                        current_page_html = e.target.parentElement
                        current_page = pagenum;
                        users_reviews.innerHTML = data.body.innerHTML

                    })
                    .catch(function (error){
                        console.log(error)
                    })
            }

        })
    })