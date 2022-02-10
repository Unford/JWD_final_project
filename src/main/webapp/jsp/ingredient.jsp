<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>
<html>
<head>
    <title>${requestScope.ingredient.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="include/header.jsp"/>
<main>

    <div class="container g-3 justify-content-center ">
        <div class="row  ">
            <div class="col-4 text-start fs-3">
                ${requestScope.ingredient.name}
            </div>
            <div class="col-4 d-flex justify-content-center fs-4">
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_ingredient&ingredient=${requestScope.ingredient.id}">
                        <button type="button" class="btn btn-outline-success ">
                            <i class="bi bi-pencil-fill"></i>
                        </button>
                    </a>
                    <c:choose>
                        <c:when test="${requestScope.ingredient.verified}">
                            <form class="d-inline" method="post"
                                  action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command"
                                       value="change_ingredient_status">
                                <input type="hidden" name="ingredient"
                                       value="${requestScope.ingredient.id}">
                                <input type="hidden" name="value"
                                       value="false">
                                <button type="submit" class="ms-1 btn btn-outline-primary">
                                    <i class="bi bi-x-circle"></i>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form class="d-inline" method="post"
                                  action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command"
                                       value="change_ingredient_status">
                                <input type="hidden" name="ingredient"
                                       value="${requestScope.ingredient.id}">
                                <input type="hidden" name="value"
                                       value="true">
                                <button type="submit" class="ms-1 btn btn-outline-primary">
                                    <i class="bi bi-check-circle"></i>
                                </button>
                            </form>

                        </c:otherwise>
                    </c:choose>
                </c:if>

            </div>
            <div class="col-4 text-end">
                <c:if test="${not requestScope.ingredient.verified}">
                    <fmt:message key="ingredient.unverified" bundle="${lang}"/>
                </c:if>
            </div>
        </div>
        <div class="row justify-content-center border-top mt-2 py-2">
            <img src="${requestScope.ingredient.photo.data}" alt="${requestScope.ingredient.photo.name}"
                 style="max-width: 540px; ">
        </div>
        <div class="row justify-content-center  fs-5 border-top mt-2">
            <div class="col-4 text-center">
                <fmt:message key="addNewIngredient.price" bundle="${lang}"/> - ${requestScope.ingredient.price}
            </div>
            <div class="col-4 text-center">
                <fmt:message key="addNewIngredient.calorie" bundle="${lang}"/> - ${requestScope.ingredient.calorie}
            </div>
        </div>
        <div class="row text-start fs-3 border-top mt-2">
            <fmt:message key="addNewIngredient.description" bundle="${lang}"/>
        </div>
        <div class="row text-start  ">
            ${requestScope.ingredient.description}
        </div>
    </div>


</main>
<jsp:include page="include/footer.jsp"/>

</body>
</html>
