<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>

<head>


    <title><fmt:message key="profile.title" bundle="${lang}"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/rating.css" rel="stylesheet">

</head>

<body class="d-flex flex-column min-vh-100">
<jsp:include page="../include/header.jsp"/>

<main>
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="nav flex-column nav-pills me-lg-3" id="v-pills-tab" role="tablist"
                 aria-orientation="vertical">
                <button class="nav-link active" id="v-pills-profile-tab" data-bs-toggle="pill"
                        data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile"
                        aria-selected="false">
                    <fmt:message key="profile.title" bundle="${lang}"/>
                </button>
                <button class="nav-link" id="v-pills-messages-tab" data-bs-toggle="pill"
                        data-bs-target="#v-pills-messages" type="button" role="tab" aria-controls="v-pills-messages"
                        aria-selected="false">
                    <fmt:message key="header.cocktails" bundle="${lang}"/>

                    <span class="badge bg-secondary">5</span>

                </button>
                <button class="nav-link" id="v-pills-reviews-tab" data-bs-toggle="pill"
                        data-bs-target="#v-pills-reviews" type="button" role="tab" aria-controls="v-pills-reviews"
                        aria-selected="false">
                    <fmt:message key="profile.reviews" bundle="${lang}"/>
                    <span class="badge bg-secondary">4</span>

                </button>

            </div>
            <div class="tab-content w-100 h-100 ms-2   border-left shadow" id="v-pills-tabContent">
                <div class="tab-pane fade active show " id="v-pills-profile" role="tabpanel"
                     aria-labelledby="v-pills-profile-tab">
                    <div class="container-fluid ">
                        <div class="row">
                            <div class="col-4 text-center align-self-center">
                                <h4>
                                    <c:choose>
                                        <c:when test="${requestScope.user.role == 'ADMIN'}">
                                            <fmt:message key="profile.role.admin" bundle="${lang}"/>
                                        </c:when>
                                        <c:when test="${requestScope.user.role == 'BARTENDER'}">
                                            <fmt:message key="profile.role.bartender" bundle="${lang}"/>
                                        </c:when>
                                        <c:when test="${requestScope.user.role == 'CLIENT'}">
                                            <fmt:message key="profile.role.client" bundle="${lang}"/>
                                        </c:when>
                                    </c:choose>
                                </h4>
                                <c:choose>
                                    <c:when test="${not empty requestScope.user.photo.data}">
                                        <img src="${requestScope.user.photo.data}" alt="${requestScope.user.photo.name}"
                                             class="rounded-circle float-md-"
                                             style="width: 100%; max-width: 415px; min-width: 200px;">
                                    </c:when>
                                    <c:otherwise>
                                        <svg xmlns="http://www.w3.org/2000/svg"
                                             style="width: 100%; max-width: 415px; min-width: 200px;" fill="orange"
                                             class="bi bi-person-circle" viewBox="0 0 16 16">
                                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                            <path fill-rule="evenodd"
                                                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="col-8 align-self-start">
                                <div class="container-fluid">
                                    <div class="col text-center " style="letter-spacing: 3px">
                                        <p class="h1">
                                            <c:out value="${requestScope.user.username}"/>
                                        </p>
                                    </div>
                                    <div class="col text-center">
                                        <h1>
                                            <c:out value="${requestScope.user.firstName} "/>
                                            <c:out value="${requestScope.user.lastName}"/>
                                        </h1>
                                    </div>
                                    <div class="row mt-2 mt-lg-4 text-center lead">
                                        <div class="row">
                                            <div class="col">
                                                <fmt:message key="profile.cocktail.rating" bundle="${lang}"/>
                                            </div>
                                            <div class="col">
                                                <fmt:message key="profile.user.rating" bundle="${lang}"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                            </div>

                                            <div class="col">
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>
                                                <i class="fa fa-star rating-color"></i>

                                            </div>

                                        </div>

                                    </div>
                                    <div class="row  mt-2 mt-lg-5 ">
                                        <div class="col align-self-center">
                                            <h3>
                                                <fmt:message key="profile.description" bundle="${lang}"/>
                                            </h3>
                                            <p>
                                                <c:out value="${requestScope.user.description}"/>
                                            </p>
                                        </div>


                                    </div>
                                </div>


                            </div>
                        </div>


                    </div>

                </div>
                <div class="tab-pane fade py-2" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab">

                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action" aria-current="true">
                            <div class="d-flex w-100 justify-content-between">

                                    <span class="mb-1 h3">
                                        <img src="https://github.com/mdo.png" alt="mdo" width="48" height="48"
                                             class="rounded-circle">
                                        Кровавая мэри</span>
                            </div>
                            <small>
                                Lorem ipsum, dolor sit amet consectetur adipisicing elit. At illum minima magni
                                quam placeat ipsa earum beatae nobis animi et. Aut fugiat fuga minus atque ab
                                eveniet asperiores reiciendis earum...
                            </small>
                        </a>

                    </div>

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
                <div class="tab-pane fade" id="v-pills-reviews" role="tabpanel"
                     aria-labelledby="v-pills-reviews-tab">
                    <div class="card mb-3">
                        <div class="container-fluid">
                            <div class="row g-0 ">
                                <div class="col-2 col-md-1 align-self-center ">
                                    <img src="res/ma.png" class="img-thumbnail" alt="..."
                                         style="height: 80px; width: 80px; ">
                                </div>
                                <div class="col-10 ">
                                    <div class="card-body">
                                        <h5 class="card-title">

                                            <i class="fa fa-star rating-color"></i>
                                            <i class="fa fa-star rating-color"></i>
                                            <i class="fa fa-star rating-color"></i>
                                            <i class="fa fa-star rating-color"></i>
                                            <i class="fa fa-star rating-color"></i>


                                        </h5>
                                        <p class="card-text">This is a wider card with supporting text below as a
                                            natural lead-in to additional content. This content is a little bit
                                            longer.</p>
                                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

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

                    <form class="container g-3 needs-validation justify-content-center " novalidate action="#"
                          method="get">

                        <div class="row ">
                            <div class="col text-end">
                                <fieldset class="rating">

                                    <input type="radio" id="star5" name="rating" value="5"/><label class="full"
                                                                                                   for="star5"></label>
                                    <input type="radio" id="star4half" name="rating" value="4.5"/><label class="half"
                                                                                                         for="star4half"></label>
                                    <input type="radio" id="star4" name="rating" value="4"/><label class="full"
                                                                                                   for="star4"></label>
                                    <input type="radio" id="star3half" name="rating" value="3.5"/><label class="half"
                                                                                                         for="star3half"></label>
                                    <input type="radio" id="star3" name="rating" value="3" required/><label class="full"
                                                                                                            for="star3"></label>
                                    <input type="radio" id="star2half" name="rating" value="2.5"/><label class="half"
                                                                                                         for="star2half"></label>
                                    <input type="radio" id="star2" name="rating" value="2"/><label class="full"
                                                                                                   for="star2"></label>
                                    <input type="radio" id="star1half" name="rating" value="1.5"/><label class="half"
                                                                                                         for="star1half"></label>
                                    <input type="radio" id="star1" name="rating" value="1"/><label class="full"
                                                                                                   for="star1"></label>
                                    <input type="radio" id="starhalf" name="rating" value="0.5"/><label class="half"
                                                                                                        for="starhalf"></label>


                                </fieldset>
                                <div class="valid-feedback">
                                    <fmt:message key="rating.valid.feedback" bundle="${lang}"/>

                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="rating.invalid.feedback" bundle="${lang}"/>

                                </div>
                            </div>


                        </div>

                        <div class="row justify-content-center">
                            <div class="col ">
                                <label for="text_area" class="form-label">
                                    <fmt:message key="rating.textarea.title" bundle="${lang}"/>
                                </label>
                                <textarea class="form-control" id="text_area" rows="3" maxlength="255" minlength="10"
                                          required></textarea>
                                <div id="review_message">
                                </div>
                            </div>

                        </div>


                        <div class="d-grid row justify-content-center mx-auto">
                            <div class="col-12 mt-3">
                                <button class="btn btn-primary" type="submit">
                                    <fmt:message key="rating.form.submit" bundle="${lang}"/>
                                </button>
                            </div>
                        </div>

                    </form>


                </div>

            </div>
        </div>

    </div>

    </div>
</main>


<jsp:include page="../include/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>

</body>

</html>
