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

    <title><fmt:message key="sign_up.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="include/header.jsp"/>

<main>
    <form class="container g-3 needs-validation justify-content-center " novalidate
          action="${pageContext.request.contextPath}/controller" METHOD="post">
        <input type="hidden" name="command" value="sign_up">

        <div class="row text-center">
            <div class="col-md-12">
                <h3><fmt:message key="sign_up.title" bundle="${lang}"/></h3>
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
                       value="${requestScope.parameters.get("first_name")}"
                       pattern="[А-ЯA-Zа-яa-z]{2,30}">
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
                       value="${requestScope.parameters.get("last_name")}"
                       pattern="[А-ЯA-Zа-яa-z]{2,30}">

                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.last_name" bundle="${lang}"/>
                </div>
            </div>
        </div>

        <div class="row justify-content-center">

            <div class="col-md-8">
                <label for="email" class="form-label">
                    <fmt:message key="sign_up.email" bundle="${lang}"/>
                </label>
                <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend1">
                    <i class="bi bi-envelope"></i>
                </span>
                    <input type="email"
                           class="form-control
                           <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${requestScope.unique_email_error or empty requestScope.parameters.email}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                           </c:choose>"
                           id="email" required name="email"
                           minlength="6"
                           maxlength="40"
                           value="${requestScope.parameters.get("email")}"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                    >
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.email" bundle="${lang}"/>
                        <c:if test="${requestScope.unique_email_error}">
                            <span class="signup-unique">
                                <fmt:message key="sign_up.error.unique_email" bundle="${lang}"/>
                            </span>
                        </c:if>
                    </div>

                </div>

            </div>

        </div>
        <div class="row justify-content-center">

            <div class="col-md-8">
                <label for="validationCustomUsername" class="form-label">
                    <fmt:message key="sign_up.username" bundle="${lang}"/>
                </label>
                <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend2">
                    <i class="bi bi-person"></i>
                </span>
                    <input type="text" class="form-control
                    <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${requestScope.unique_username_error or empty requestScope.parameters.username}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                    </c:choose>"
                           id="validationCustomUsername"
                           aria-describedby="inputGroupPrepend2" required minlength="4" maxlength="30"
                           name="username" pattern="[A-Za-z\d\.\-_&%$#@!*,]{4,30}"
                           value="${requestScope.parameters.get('username')}">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.username" bundle="${lang}"/>
                        <c:if test="${requestScope.unique_email_error}">
                        <span class="signup-unique">
                            <fmt:message key="sign_up.error.unique_user" bundle="${lang}"/>
                        </span>
                        </c:if>
                    </div>


                </div>

            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4">
                <label for="validationPass" class="form-label">
                    <fmt:message key="sign_up.password" bundle="${lang}"/></label>
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

        <div class="d-grid row justify-content-center">
            <div class="col-md-12 mt-2">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                    <label class="form-check-label" for="invalidCheck">
                        <fmt:message key="sign_up.terms" bundle="${lang}"/>
                    </label>
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.terms.invalid" bundle="${lang}"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="sign_up.submit" bundle="${lang}"/>
                </button>
            </div>
        </div>

    </form>
</main>

<jsp:include page="include/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/signUpFormValidation.js"></script>
</body>
</html>
