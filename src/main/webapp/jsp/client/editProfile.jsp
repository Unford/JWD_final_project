<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

    <title><fmt:message key="edit_profile.title" bundle="${lang}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css">

</head>
<body>
<jsp:include page="../include/header.jsp"/>


<main>
    <form id="profile_info_form" class="container g-3 needs-validation justify-content-center border" novalidate
          action="${pageContext.request.contextPath}/controller" METHOD="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="edit_profile">


        <div class="row text-center">
            <div class="col-md-12">
                <h3><fmt:message key="edit_profile.title" bundle="${lang}"/></h3>
            </div>
        </div>


        <div class="row justify-content-center">
            <input type="file" class="form-control" style="display:none;" name="avatar" id="file" accept=".png, .jpg, .jpeg" required>
            <div class="col-md-4 d-flex justify-content-center">

                    <span id="output" class="d-flex justify-content-center mt-md-4" onclick="document.getElementById('file').click();">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.photo.data}">
                                <img class="thumb" title="${sessionScope.user.photo.name}"
                                     src="${sessionScope.user.photo.data}"/>
                            </c:when>
                            <c:otherwise>
                                <svg xmlns="http://www.w3.org/2000/svg" style="width: 200px; max-width: 200px; " fill="blue"
                                     class="bi bi-person-circle my-2" viewBox="0 0 16 16">
                                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                            <path fill-rule="evenodd"
                                                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                        </svg>
                            </c:otherwise>
                        </c:choose>
                    </span>

            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-4 ">
                <label for="first_name" class="form-label">
                    <fmt:message key="sign_up.first_name" bundle="${lang}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.first_name}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="first_name" required name="first_name" minlength="2" maxlength="30"
                       value="${sessionScope.user.firstName}"
                       pattern="[А-ЯA-Z][а-яa-z]{1,29}">
                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.first_name" bundle="${lang}"/>
                </div>

            </div>

            <div class="col-md-4 ">
                <label for="last_name" class="form-label">
                    <fmt:message key="sign_up.last_name" bundle="${lang}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.last_name}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="last_name" required name="last_name" minlength="2" maxlength="30"
                       value="${sessionScope.user.lastName}"
                       pattern="[А-ЯA-Z][а-яa-z]{1,29}">

                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.last_name" bundle="${lang}"/>
                </div>
            </div>
        </div>


        <div class="row justify-content-center">
            <div class="col-md-8 ">
                <label for="text_area" class="form-label">
                    <fmt:message key="edit_profile.description" bundle="${lang}"/>
                </label>
                <textarea id="description" class="form-control" id="text_area" rows="3" maxlength="255"
                          name="about_me">${sessionScope.user.description}</textarea>
                <span id="review_message"></span>
                <fmt:message key="rating.textarea.char_remaining" bundle="${lang}"/>
                <div class="invalid-feedback">
                    <fmt:message key="edit_profile.error.description" bundle="${lang}"/>
                </div>

            </div>
        </div>


        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3 mb-2">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="edit_profile.submit" bundle="${lang}"/>
                </button>
            </div>
        </div>

        <c:if test="${not empty requestScope.m}">
        <div class="row justify-content-center mt-2">
            <div class="col-8">
                    <c:choose>
                        <c:when test="${requestScope.m == 3}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <fmt:message key="edit_profile.info.invalid_dat" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.m == 4}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <fmt:message key="edit_profile.info.success" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:when>

                    </c:choose>

            </div>
        </div>
        </c:if>

    </form>


    <form class="container g-3 needs-validation justify-content-center border" novalidate
          action="${pageContext.request.contextPath}/controller" METHOD="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="change_password">


        <div class="row text-center">
            <div class="col-md-12">
                <h3>
                    <fmt:message key="edit_profile.password.title" bundle="${lang}"/>
                </h3>
            </div>
        </div>


        <input name="command" type="hidden" value="change_password">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <label for="validationPass" class="form-label">
                    <fmt:message key="edit_profile.password.new" bundle="${lang}"/>
                </label>
                <div class="input-group has-validation">
                            <span class="input-group-text" id="inputGroupPrepend3">
                                <i class="bi bi-key"></i>
                            </span>
                    <input type="password"
                           class="form-control"
                           id="validationPass" required
                           aria-describedby="passwordHelpBlock" minlength="8" maxlength="30" name="password"
                           pattern="[A-Za-z\d\.\-_&%$#@!*,]{8,30}">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.password" bundle="${lang}"/>
                    </div>
                </div>

            </div>
            <div class="col-md-4">
                <label for="validationRepeatPass" class="form-label">
                    <fmt:message key="sign_up.rep_password" bundle="${lang}"/>
                </label>
                <div class="input-group has-validation">
                    <span class="input-group-text" id="inputGroupPrepend4">
                    <i class="bi bi-key-fill"></i>
                      </span>
                    <input type="password" class="form-control" id="validationRepeatPass" required minlength="8"
                           maxlength="30">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.confirm_pass" bundle="${lang}"/>
                    </div>
                </div>

            </div>
            <div id="passwordHelpBlock" class="form-text text-center">
                <fmt:message key="sign_up.password.help" bundle="${lang}"/>
            </div>
        </div>


        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3 mb-2">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="edit_profile.submit" bundle="${lang}"/>
                </button>
            </div>
        </div>
        <c:if test="${not empty requestScope.m}">
            <div class="row justify-content-center mt-2">
                <div class="col-8">
                    <c:choose>
                        <c:when test="${requestScope.m == 1}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <fmt:message key="edit_profile.password.invalid_dat" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.m == 2}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <fmt:message key="edit_profile.password.success" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:when>

                    </c:choose>
                </div>
            </div>
        </c:if>
    </form>


</main>

<jsp:include page="../include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/editProfile.js"></script>
</body>
</html>
