<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <title>
        <fmt:message key="addNewIngredient.title" bundle="${lang}"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css">

</head>
<body>
<jsp:include page="../include/header.jsp"/>
<main>

    <form class="container g-3 needs-validation justify-content-center " novalidate
          action="${pageContext.request.contextPath}/controller" METHOD="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="create_ingredient">


        <div class="row text-center">
            <div class="col-md-12">
                <h3><fmt:message key="addNewIngredient.title" bundle="${lang}"/></h3>
            </div>
        </div>


        <div class="row justify-content-center">
            <input type="file" class="form-control" style="display:none;" name="image" id="file"
                   accept=".png, .jpg, .jpeg" required>
            <div class="col-md-4 d-flex justify-content-center">

                    <span id="output" class="d-flex justify-content-center mt-md-4"
                          onclick="document.getElementById('file').click();">
                        <svg class=" bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
                             width="200" height="200" xmlns="http://www.w3.org/2000/svg" role="img"
                             aria-label="Placeholder: 200x200" preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title><fmt:message key="addNewIngredient.imageName" bundle="${lang}"/></title>
                            <rect width="100%" height="100%" fill="#eee"></rect>
                            <text x="30%" y="50%" fill="#aaa" dy=".5em" dx=".5em">
                                <fmt:message key="addNewIngredient.imageName" bundle="${lang}"/>
                            </text>
                        </svg>
                    </span>

            </div>
            <div class="col-md-4 ">
                <div class="row row-cols-1">

                    <div class="col">
                        <label for="measure" class="form-label">
                            <fmt:message key="addNewIngredient.measure" bundle="${lang}"/>
                        </label>
                        <select id="measure" class="form-select form-select-sm form-control"
                                aria-label=".form-select-sm example" required name="measure">
                            <option></option>
                            <c:forEach items="${requestScope.measures}" var="measure">
                                <option value="${measure.id}"
                                        <c:if test="${measure.id eq sessionScope.parameters.measure}">selected</c:if>>
                                        ${measure.name}</option>

                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            <fmt:message key="addNewIngredient.measure.error" bundle="${lang}"/>
                        </div>
                    </div>

                    <div class="col ">
                        <label for="price" class="form-label">
                            <fmt:message key="addNewIngredient.price" bundle="${lang}"/>
                        </label>
                        <div class="input-group">

                            <input id="price" type="number" name="price" class="form-control" required min="0"
                                   max="1000" step="0.01"
                                   <c:if test="${not empty sessionScope.parameters.price}">value="${sessionScope.parameters.price}" </c:if>>
                            <span class="input-group-text">$</span>

                            <div class="invalid-feedback">
                                <fmt:message key="addNewIngredient.price.error" bundle="${lang}"/>
                            </div>
                        </div>


                    </div>
                    <div class="col ">
                        <label for="calorie" class="form-label">
                            <fmt:message key="addNewIngredient.calorie" bundle="${lang}"/>
                        </label>
                        <input id="calorie" type="number" name="calorie" class="form-control" required min="1" step="1"
                               max="2000"
                               <c:if test="${not empty sessionScope.parameters.calorie}">value="${sessionScope.parameters.calorie}" </c:if>>

                        <div class="invalid-feedback">
                            <fmt:message key="addNewIngredient.calorie.error" bundle="${lang}"/>
                        </div>

                    </div>


                </div>


            </div>


        </div>


        <div class="row justify-content-center">
            <div class="col-md-8">
                <label for="name" class="form-label">
                    <fmt:message key="addNewIngredient.ingredient.name" bundle="${lang}"/>
                </label>
                <input id="name" type="text" name="name" class="form-control" required
                       value="${sessionScope.parameters.name}"
                    pattern="^([A-ZА-Я][a-zа-я]+)+(?=( ?))(?:\2[a-zа-я]+)+$"
                       maxlength="40">

                <div class="invalid-feedback">
                    <fmt:message key="addNewIngredient.ingredient.name.error" bundle="${lang}"/>
                </div>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8 ">
                <label for="text_area" class="form-label">
                    <fmt:message key="addNewIngredient.description" bundle="${lang}"/>
                </label>

                <textarea class="form-control" data-mdb-showcounter="true"
                          id="text_area" rows="3" minlength="10" maxlength="255" name="description"
                          required>${sessionScope.parameters.description}</textarea>
                <span id="review_message"></span>
                <fmt:message key="rating.textarea.char_remaining" bundle="${lang}"/>
            </div>
        </div>


        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="addNewIngredient.submit" bundle="${lang}"/>
                </button>
            </div>
        </div>
        <c:if test="${not empty sessionScope.message}">
            <c:choose>
                <c:when test="${sessionScope.message == 1}">
                    <div class="row justify-content-center mt-2">
                        <div class="col-8">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <fmt:message key="addNewIngredient.alert.invalid" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </div>

                    </div>
                </c:when>
                <c:when test="${sessionScope.message == 2}">
                    <div class="row justify-content-center mt-2">
                        <div class="col-8">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <fmt:message key="addNewIngredient.alert.unique" bundle="${lang}"/>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </div>

                    </div>
                </c:when>

            </c:choose>

        </c:if>

    </form>

    <jsp:include page="../include/footer.jsp"/>
</main>
<script src="${pageContext.request.contextPath}/js/addIngredient.js"></script>

</body>
</html>
