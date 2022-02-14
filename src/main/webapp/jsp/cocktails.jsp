<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cst" uri="custom" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <title><fmt:message key="header.cocktails" bundle="${lang}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<main>
    <div class="row justify-content-center mb-2 ms-2 me-2">
        <form method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="show_cocktails">
            <input type="search" class="form-control fs-3"
                   placeholder="<fmt:message key="cocktail.search" bundle="${lang}"/>"
                   name="search" value="${param.search}">
        </form>
    </div>
    <c:if test="${sessionScope.user.role eq 'BARTENDER'}">
        <div class="row justify-content-center me-2 mb-2">
            <a class="btn btn-success "
               href="${pageContext.request.contextPath}/controller?command=go_to_add_cocktail"
               role="button">
                <fmt:message key="ingredients.new" bundle="${lang}"/>
            </a>
        </div>
    </c:if>


    <div class="container">
        <div class="row justify-content-center m-2 fs-2 text-center">
            <p>
                <fmt:message key="header.cocktails" bundle="${lang}"/> - <span
                    id="cocktails_size">${requestScope.cocktails_size}</span>
            </p>
        </div>

        <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-3" id="ingredients">
            <div class="col">
                <div class="card text-center">
                    <div class="card-body justify-content-center">
                        <img src="${ingredient.photo.data}"
                             class="card-img-top"
                             alt="${ingredient.photo.name}"
                             style="width: 200px; height: 200px;">
                    </div>
                    <div class="card-body">
                        <p class="card-text m-0">${ingredient.name}</p>
                        <small class="card-text fw-light m-0">${ingredient.description}</small>
                        <a class="stretched-link"
                           href="${pageContext.request.contextPath}/controller?command=show_ingredient&ingredient=${ingredient.id}">
                        </a>
                    </div>
                </div>
            </div>
        </div>





        <div class=" mt-2 row">
            <cst:pagination currentPage="${param.page}"
                            itemsSize="${requestScope.cocktails_size}"
                            command="show_cocktails"
                            search="${param.search}"/>

        </div>


    </div>
    <jsp:include page="include/footer.jsp"/>
</main>
</body>
</html>
