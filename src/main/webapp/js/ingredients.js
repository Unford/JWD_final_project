function pagination(
    totalItems,
    currentPage,
    pageSize,
    maxPages
) {
    let totalPages = Math.ceil(totalItems / pageSize);

    if (currentPage < 1) {
        currentPage = 1;
    } else if (currentPage > totalPages) {
        currentPage = totalPages
    }

    let startPage;
    let endPage;
    if (totalPages <= maxPages) {
        startPage = 2;
        endPage = totalPages - 1;
    } else {
        let maxPagesBeforeCurrentPage = Math.floor(maxPages / 2);
        let maxPagesAfterCurrentPage = Math.floor(maxPages / 2) - 1;
        if (currentPage <= maxPagesBeforeCurrentPage) {
            startPage = 2;
            endPage = maxPages;
        } else if (currentPage + maxPagesAfterCurrentPage >= totalPages) {
            startPage = totalPages - maxPages + 1;
            endPage = totalPages - 1
        } else {
            startPage = currentPage - maxPagesBeforeCurrentPage + 1;
            endPage = currentPage + maxPagesAfterCurrentPage;
        }
    }
    let startIndex = (currentPage - 1) * pageSize
    let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

    let pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);

    return {
        totalItems: totalItems,
        currentPage: currentPage,
        pageSize: pageSize,
        totalPages: totalPages,
        startPage: startPage,
        endPage: endPage,
        startIndex: startIndex,
        endIndex: endIndex,
        pages: pages
    };
}

let contextPath = document.getElementById("contextPath").innerText;
let ingredients = document.getElementById("ingredients");
let pagination = document.getElementById("pagination")

let ingredients_size = parseInt(document.getElementById("ingredients_size").innerText);

let current_page_html = pagination.querySelector(".active");
let current_page = parseInt(current_page_html.querySelector("a").innerHTML);

let last_page = parseInt(pagination.querySelector(".last a").innerHTML)

let pagination_items = pagination.querySelectorAll(".page-link")

pagination_items.forEach(function (page) {
    page.addEventListener("click", (e) => {
        var pagenum = parseInt(page.textContent);
        if (page.classList.contains('next')) {
            pagenum = current_page != last_page ? current_page + 1 : last_page;
        }
        if (page.classList.contains('prev')) {
            pagenum = current_page != 1 ? current_page - 1 : 1;
        }
        console.log("pagenum = " +pagenum)
        if (pagenum != current_page) {

            fetch(contextPath + "/controller?command=show_ingredients&user=" + "&page=" + pagenum, {
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

                    let update_pagination = pagination(ingredients_size, pagenum, 8, 6)
                    var nextPage = e.target.parentElement;
                    if (e.target.classList.contains("next")){
                        var nextId = parseInt(current_page_html.id.replaceAll("ingredientPage", "")) + 1;
                        nextPage = pagination.querySelector("#ingredientPage" + nextId);
                    }else if (e.target.classList.contains("prev")){
                        var prevId = parseInt(current_page_html.id.replaceAll("ingredientPage", "")) - 1;
                        nextPage = pagination.querySelector("#ingredientPage" + prevId);
                    }

                    for (let i = 0; i < update_pagination.pages.length; i++) {
                        pagination.querySelector("#ingredientPage" + (i + 2) + " a")
                            .textContent = update_pagination.pages[i]
                        if (update_pagination.pages[i] === update_pagination.currentPage){
                            nextPage = pagination.querySelector("#ingredientPage" + (i + 2));
                        }
                    }

                    current_page_html.classList.remove("active");
                    nextPage.classList.add("active")
                    current_page_html = nextPage
                    current_page = pagenum;
                    ingredients.innerHTML = data.body.innerHTML

                })
                .catch(function (error) {
                    console.log(error)
                })
        }

    })
})