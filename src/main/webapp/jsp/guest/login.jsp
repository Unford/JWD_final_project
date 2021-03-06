
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

    <title><fmt:message key="logIn.page_title" bundle="${lang}"/></title>
</head>

<body class="d-flex flex-column min-vh-100">
<main class="form-signin text-center">
    <form method="post" action="${pageContext.request.contextPath}/controller" class="needs-validation" novalidate>
        <input type="hidden" name="command" value="log_in">
        <a href="${pageContext.request.contextPath}/controller?command=go_to_main" class="mb-2  text-decoration-none fs-2">
            <fmt:message key="header.title" bundle="${lang}"/>
            <svg style="width:calc(1.325rem + .9vw);height: calc(1.325rem + .9vw);" viewBox="0 0 24 24">
                <path fill="currentColor" d="M7.5,7L5.5,5H18.5L16.5,7M11,13V19H6V21H18V19H13V13L21,5V3H3V5L11,13Z" />
            </svg>
        </a>
        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="logIn.form_title" bundle="${lang}"/>
        </h1>

        <div class="form-floating mb-2">
            <input type="text" class="form-control" id="floatingInput"
                   placeholder="name@example.com"
                   required maxlength="40"
                   minlength="4"
                   pattern="([A-Za-z\d\.\-_&%$#@!*,]{4,30})|([a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$)"
                   name="login"
                   value=<c:out value="${param.login}"/>

            >
            <label for="floatingInput">
                <fmt:message key="logIn.login" bundle="${lang}"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-person" viewBox="0 0 16 16">
                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                </svg>
            </label>
            <div class="invalid-feedback">
                <fmt:message key="logIn.error.login" bundle="${lang}"/>
            </div>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword"
                   name="password"
                   placeholder="Password"
                   required
                   maxlength="30"
                   minlength="8"
                   pattern="[A-Za-z\d\.\-_&%$#@!*,]{8,30}"
            >
            <label for="floatingPassword">
                <fmt:message key="logIn.password" bundle="${lang}"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-key" viewBox="0 0 16 16">
                    <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                    <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                </svg>
            </label>
            <div class="invalid-feedback">
                <fmt:message key="logIn.error.password" bundle="${lang}"/>
            </div>
        </div>


        <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">
            <fmt:message key="logIn.submit" bundle="${lang}"/>
        </button>

        <hr class="my-3">
        <div class="d-grid  d-md-flex justify-content-md-center">
            <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up">

            <button class="w-100 btn btn-lg rounded-4 btn-success" type="button">
                <fmt:message key="logIn.sign_up" bundle="${lang}"/>
            </button>
            </a>
        </div>

    </form>
    <c:choose>
        <c:when test="${param.message == 1}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="logIn.error.auth" bundle="${lang}"/>
            </div>
        </c:when>
        <c:when test="${param.message == 2}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="logIn.error.ban" bundle="${lang}"/>
            </div>
        </c:when>
    </c:choose>

</main>


<jsp:include page="../include/footer.jsp"/>


<script src="${pageContext.request.contextPath}/js/logInFormValidation.js"></script>

</body>
</html>
