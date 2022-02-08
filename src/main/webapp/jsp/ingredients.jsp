
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <title><fmt:message key="header.ingredients" bundle="${lang}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet" type="text/css">

</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="include/header.jsp"/>
<main>
    <main>

        <div class="row justify-content-center mb-4 ms-2 me-2">
            <form >
                <input type="search" class="form-control fs-3" placeholder="Ingredient" aria-label="Search">
            </form>
        </div>

        <div class="container">
            <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-3">
                <div class="col">
                    <div class="card text-center" >
                        <div class="card-body justify-content-center">
                            <img src="/final_project/res/ma.png" class="card-img-top" alt="..." style="width: 200px; height: 200px;">

                        </div>
                        <div class="card-body">
                            <p class="card-text fw-light">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-center" >
                        <div class="card-body justify-content-center">
                            <img src="/final_project/res/ma.png" class="card-img-top" alt="..." style="width: 200px; height: 200px;">

                        </div>
                        <div class="card-body">
                            <p class="card-text fw-light">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-center" >
                        <div class="card-body justify-content-center">
                            <img src="/final_project/res/ma.png" class="card-img-top" alt="..." style="width: 200px; height: 200px;">

                        </div>
                        <div class="card-body">
                            <p class="card-text fw-light">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-center" >
                        <div class="card-body justify-content-center">
                            <img src="/final_project/res/ma.png" class="card-img-top" alt="..." style="width: 200px; height: 200px;">

                        </div>
                        <div class="card-body">
                            <p class="card-text fw-light">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-center" >
                        <div class="card-body justify-content-center">
                            <img src="/final_project/res/ma.png" class="card-img-top" alt="..." style="width: 200px; height: 200px;">

                        </div>
                        <div class="card-body">
                            <p class="card-text fw-light">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>



            </div>
        </div>
        <div class="row">
            <div class=" mt-2">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

        </div>
    </main>
    <jsp:include page="include/footer.jsp"/>
</main>
</body>
</html>
