
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet" type="text/css">

</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="include/header.jsp"/>
<main>
    <p>${requestScope.ingredient.id}</p>
    <p>${requestScope.ingredient.measure}</p>
    <p>${requestScope.ingredient.name}</p>
    <p>${requestScope.ingredient.description}</p>
    <p>${requestScope.ingredient.photo}</p>
    <p>${requestScope.ingredient.price}</p>
    <p>${requestScope.ingredient.calorie}</p>



    <jsp:include page="include/footer.jsp"/>

</main>
</body>
</html>
