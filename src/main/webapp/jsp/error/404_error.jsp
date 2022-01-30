<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <title><fmt:message key="error.title" bundle="${lang}"/> 404</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="../include/header.jsp"/>

<main class="text-center ">
    <h1 class="fw-bold display-2 ">404 - <fmt:message key="error.404.message" bundle="${lang}"/></h1>
    <div class="border border-2 pb-2">
        <p class="lh-lg"><fmt:message key="error.404.description" bundle="${lang}"/>
            <a href="mailto:vladimir0feshenko@gmail.com"><fmt:message key="error.help" bundle="${lang}"/></a>
        </p>
        <a href="${pageContext.request.contextPath}">
            <button class="btn-primary">
                <fmt:message key="error.back" bundle="${lang}"/>
            </button>
        </a>
    </div>
</main>

<jsp:include page="../include/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>
