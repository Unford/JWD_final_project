<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cst" uri="custom" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>

<head>


    <title><fmt:message key="profile.title" bundle="${lang}"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/rating.css" rel="stylesheet">

</head>

<body class="d-flex flex-column min-vh-100">
<jsp:include page="../include/header.jsp"/>
<span id="contextPath" style="display: none">${pageContext.request.contextPath}</span>

<main >
    <div class="container">
        <div class="d-flex">
            <div class="nav flex-column nav-pills me-lg-3 " id="v-pills-tab" role="tablist"
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
                    <span class="badge bg-secondary">${requestScope.user.cocktails.size()}</span>

                </button>
                <button class="nav-link" id="v-pills-reviews-tab" data-bs-toggle="pill"
                        data-bs-target="#v-pills-reviews" type="button" role="tab" aria-controls="v-pills-reviews"
                        aria-selected="false">
                    <fmt:message key="profile.reviews" bundle="${lang}"/>
                    <span class="badge bg-secondary">${requestScope.user.reviews.size()}</span>

                </button>

            </div>
            <div class="tab-content w-100 h-100 ms-2   border-left shadow" id="v-pills-tabContent">
                <div class="tab-pane fade active show " id="v-pills-profile" role="tabpanel"
                     aria-labelledby="v-pills-profile-tab">

                    <div class="container-fluid ">
                        <div class="row bg-dark text-white text-start">
                            <div class="col-4">
                                <p class="h1">
                                    <c:choose>
                                        <c:when test="${requestScope.user.role == 'ADMIN'}">
                                            <span class="text-success">
                                                <fmt:message key="profile.role.admin" bundle="${lang}"/>
                                            </span>
                                        </c:when>
                                        <c:when test="${requestScope.user.role == 'BARTENDER'}">
                                            <span class="text-warning">
                                                <fmt:message key="profile.role.bartender" bundle="${lang}"/>
                                            </span>
                                        </c:when>
                                        <c:when test="${requestScope.user.role == 'CLIENT'}">
                                            <fmt:message key="profile.role.client" bundle="${lang}"/>
                                        </c:when>
                                    </c:choose>
                                </p>

                            </div>
                            <div class="col text-center">
                                <p class="h1"><c:out value="${requestScope.user.username}"/></p>
                                <c:if test="${requestScope.user.deleted}">
                                    <p class="text-info">
                                        <fmt:message key="profile.status.deleted" bundle="${lang}"/>
                                    </p>
                                </c:if>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-4 h-100 text-center align-self-center border-end ">
                                <c:choose>
                                    <c:when test="${not empty requestScope.user.photo.data}">
                                        <img src="${requestScope.user.photo.data}" alt="${requestScope.user.photo.name}"
                                             class="rounded-circle my-2"
                                             style="width: 100%; max-width: 415px; min-width: 200px;">
                                    </c:when>
                                    <c:otherwise>
                                        <svg xmlns="http://www.w3.org/2000/svg"
                                             style="width: 100%; max-width: 415px; " fill="blue"
                                             class="bi bi-person-circle my-2" viewBox="0 0 16 16">
                                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                            <path fill-rule="evenodd"
                                                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-8 align-self-start">
                                <div class="container">
                                    <div class="row text-center " style="letter-spacing: 3px">
                                    </div>
                                    <div class="row text-center">
                                        <h1>
                                            <c:out value="${requestScope.user.firstName} "/>
                                            <c:out value="${requestScope.user.lastName}"/>
                                        </h1>

                                    </div>
                                    <div class=" row row-cols-2 text-center lead">
                                        <div class="col">
                                            <fmt:message key="profile.cocktail.rating" bundle="${lang}"/>
                                        </div>
                                        <div class="col">
                                            <fmt:message key="profile.user.rating" bundle="${lang}"/>
                                        </div>
                                        <div class="col">
                                            <cst:rating value="4.5"/>

                                        </div>
                                        <div class="col">
                                            <cst:rating value="4.5"/>

                                        </div>
                                    </div>
                                    </h3>
                                    <p>
                                    <div class="row  mt-2 mt-lg-5 ">
                                        <div class="col align-self-center">
                                            <h3><fmt:message key="profile.description" bundle="${lang}"/>
                                            </h3>
                                            <p>
                                                <c:if test="${empty requestScope.user.description}"><fmt:message
                                                        key="profile.empty" bundle="${lang}"/> </c:if>
                                                <c:out value="${requestScope.user.description}"/>

                                            </p>
                                        </div>


                                    </div>


                                </div>

                            </div>

                        </div>
                        <c:if test="${requestScope.user.role != 'GUEST'}">
                            <div class="row mb-2 mt-2 justify-content-center text-center">
                                <c:if test="${requestScope.user eq sessionScope.user}">
                                    <div class="col">
                                        <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_profile">
                                            <button type="button" class="btn btn-outline-success w-100">
                                                <i class="bi bi-pencil-fill"></i>
                                                <fmt:message key="profile.action.edit" bundle="${lang}"/>
                                            </button>
                                        </a>
                                    </div>

                                    <div class="col">
                                        <form method="post" action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="delete_account">
                                            <input type="hidden" name="user" value="${requestScope.user.id}">
                                            <button type="submit" class="btn btn-outline-danger w-100">
                                                <i class="bi bi-trash-fill"></i>
                                                <fmt:message key="profile.action.delete" bundle="${lang}"/>

                                            </button>
                                        </form>
                                    </div>


                                </c:if>
                                <c:if test="${sessionScope.user.role == 'ADMIN' and sessionScope.user != requestScope.user}">
                                    <c:choose>
                                        <c:when test="${requestScope.user.status eq 'BANNED'}">
                                            <div class="col">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="change_account_status">
                                                    <input type="hidden" name="status" value="working">
                                                    <input type="hidden" name="user" value="${requestScope.user.id}">
                                                    <button type="submit" class="btn btn-outline-success w-100">
                                                        <i class="bi bi-person-check-fill"></i>
                                                        <fmt:message key="profile.action.unban" bundle="${lang}"/>
                                                    </button>
                                                </form>
                                            </div>


                                        </c:when>
                                        <c:otherwise>
                                            <div class="col text-center">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="change_account_status">
                                                    <input type="hidden" name="status" value="banned">
                                                    <input type="hidden" name="user" value="${requestScope.user.id}">
                                                    <button type="submit" class="btn btn-outline-warning w-100">
                                                        <i class="bi bi-person-x-fill"></i>
                                                        <fmt:message key="profile.action.ban" bundle="${lang}"/>
                                                    </button>
                                                </form>
                                            </div>


                                        </c:otherwise>
                                    </c:choose>
                                </c:if>


                            </div>
                        </c:if>


                    </div>


                </div>
                <div class="tab-pane fade py-2" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab">
                    <div class="container">
                        <div class="row bg-dark text-white text-center">
                            <h4><fmt:message key="header.cocktails" bundle="${lang}"/></h4>
                        </div>
                        <div class="row">
                            <div class="list-group">
                                <a href="#" class="list-group-item list-group-item-action" aria-current="true">
                                    <div class="d-flex w-100 ">
                                        <div class="row">
                                            <div class="col-4 col-md-3 col-lg-2">
                                                <img src="https://github.com/mdo.png" alt="mdo" width="100%"
                                                     style="max-width: 240px;"
                                                     class="rounded float-left">
                                            </div>
                                            <div class="col-8">
                                                <div class="row">
                                                <span class="mb-1 h3">
                                                    Кровавая мэри
                                                </span>
                                                </div>
                                                <div class="row">
                                                    <small>Lorem ipsum, dolor sit amet consectetur adipisicing elit. At illum
                                                        minima magni
                                                        quam placeat ipsa earum beatae nobis animi et. Aut fugiat fuga minus
                                                        atque ab
                                                        eveniet asperiores reiciendis earum...</small>
                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                </a>

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
                    </div>



                </div>
                <div class="tab-pane fade" id="v-pills-reviews" role="tabpanel"
                     aria-labelledby="v-pills-reviews-tab">
                    <div class="container">
                        <div class="row bg-dark text-white text-center">
                            <div>
                                <h4><fmt:message key="profile.reviews" bundle="${lang}"/></h4>
                            </div>
                        </div>
                        <div id="users_reviews" class="row">
                            <div class="card mb-3">
                                <div class="container-fluid">
                                    <div class="row g-0 ">
                                        <div class="col-2 col-md-1 align-self-center ">


                                            <c:choose>
                                                <c:when test="${not empty requestScope.user.photo.data}">
                                                    <img src="${requestScope.user.photo.data}" class="img-thumbnail"
                                                         alt="${requestScope.user.photo.name}"
                                                         style="height: 80px; width: 80px; "> <!--todo!-->
                                                </c:when>
                                                <c:otherwise>
                                                    <svg xmlns="http://www.w3.org/2000/svg"
                                                         style="height: 80px; width: 80px; " fill="black"
                                                         class="bi bi-person-circle mb-1 img-thumbnail" viewBox="0 0 16 16">
                                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                        <path fill-rule="evenodd"
                                                              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                    </svg>
                                                </c:otherwise>
                                            </c:choose>


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

                        </div>
                        <div class=" mt-2 row">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center" id="review_pagination">
                                    <li class="page-item prev">
                                        <a class="page-link prev" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item active first"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                                    <li class="page-item"><a class="page-link" href="#">5</a></li>

                                    <li class="page-item">
                                        <a class="page-link next" href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>

                    </div>






                    <c:if test="${sessionScope.user.id != requestScope.user.id}">
                        <div class="container">
                            <c:choose>
                                <c:when test="${sessionScope.user.role == 'GUEST'}">
                                    <div class="row text-center border">
                                        <p><fmt:message key="rating.logIn.message" bundle="${lang}"/>
                                            <a href="${pageContext.request.contextPath}/controller?command=go_to_login">
                                                <fmt:message key="rating.logIn.ref" bundle="${lang}"/>
                                            </a>
                                        </p>
                                    </div>
                                </c:when>
                                <c:when test="${not empty requestScope.my_review}">


                                    <div class="card mb-3 ">
                                        <div class="container-fluid">
                                            <div class="row g-0 ">
                                                <div class="col-2 col-md-1 align-self-center ">


                                                    <c:choose>
                                                        <c:when test="${not empty sessionScope.user.photo.data}">
                                                            <img src="${sessionScope.user.photo.data}"
                                                                 class="img-thumbnail"
                                                                 alt="${sessionScope.user.photo.name}"
                                                                 style="height: 80px; width: 80px; "> <!--todo!-->
                                                        </c:when>
                                                        <c:otherwise>
                                                            <svg xmlns="http://www.w3.org/2000/svg"
                                                                 style="height: 80px; width: 80px; " fill="blue"
                                                                 class="bi bi-person-circle mb-1 img-thumbnail"
                                                                 viewBox="0 0 16 16">
                                                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                                <path fill-rule="evenodd"
                                                                      d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                            </svg>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                                <div class="col-10 ">
                                                    <div class="card-body bg-light">


                                                        <h5 class="card-title">
                                                            ${sessionScope.user.username}
                                                            <cst:rating value="${requestScope.my_review.score}"/>

                                                        </h5>
                                                        <p class="card-text">
                                                                ${requestScope.my_review.message}
                                                        </p>
                                                        <p class="card-text">
                                                            <small class="text-muted">
                                                            </small>
                                                            <cst:dateformat value="${requestScope.my_review.timestamp}"/>
                                                            </small>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>


                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not requestScope.user.deleted}">
                                        <form class=" g-3 needs-validation justify-content-center " novalidate
                                              action="${pageContext.request.contextPath}/controller" method="post">
                                            <input name="command" type="hidden" value="send_review">
                                            <input name="user" type="hidden" value="${requestScope.user.id}">
                                            <div class="row ">
                                                <div class="col text-end">
                                                    <fieldset class="rating">

                                                        <input type="radio" id="star5" name="rating" value="5"/>
                                                        <label class="full" for="star5"></label>
                                                        <input type="radio" id="star4half" name="rating" value="4.5"/>
                                                        <label class="half" for="star4half"></label>
                                                        <input type="radio" id="star4" name="rating" value="4"/>
                                                        <label class="full" for="star4"></label>
                                                        <input type="radio" id="star3half" name="rating" value="3.5"/>
                                                        <label class="half" for="star3half"></label>
                                                        <input type="radio" id="star3" name="rating" value="3"
                                                               required/>
                                                        <label class="full" for="star3"></label>
                                                        <input type="radio" id="star2half" name="rating" value="2.5"/>
                                                        <label class="half" for="star2half"></label>
                                                        <input type="radio" id="star2" name="rating" value="2"/>
                                                        <label class="full" for="star2"></label>
                                                        <input type="radio" id="star1half" name="rating" value="1.5"/>
                                                        <label class="half" for="star1half"></label>
                                                        <input type="radio" id="star1" name="rating" value="1"/>
                                                        <label class="full" for="star1"></label>
                                                        <input type="radio" id="starhalf" name="rating" value="0.5"/>
                                                        <label class="half" for="starhalf"></label>
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
                                                    <textarea class="form-control" id="text_area" rows="3"
                                                              maxlength="255" minlength="10"
                                                              required name="review_message"></textarea>
                                                    <span id="review_message"></span>
                                                    <fmt:message key="rating.textarea.char_remaining" bundle="${lang}"/>
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

                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:if>


                </div>

            </div>
        </div>

    </div>

    </div>
    <jsp:include page="../include/footer.jsp"/>

</main>




<script src="${pageContext.request.contextPath}/js/profile.js"></script>
<c:if test="${sessionScope.user.id != requestScope.user.id and sessionScope.user.role != 'GUEST' and empty requestScope.my_review and not requestScope.user.deleted}">
    <script src="${pageContext.request.contextPath}/js/messageCharacterCounter.js"></script>
</c:if>
</body>

</html>
