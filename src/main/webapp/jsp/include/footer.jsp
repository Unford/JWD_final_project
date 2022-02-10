<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>


<footer class="mt-auto">
    <div class="container">
        <ul class="nav justify-content-center border-bottom pb-1 mb-1">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_cocktails" class="nav-link px-2 text-muted">
                    <fmt:message key="header.cocktails" bundle="${lang}"/></a></li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_ingredients"
                   class="nav-link px-2 text-muted">
                    <fmt:message key="header.ingredients" bundle="${lang}"/></a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-muted" href="#" id="dropdownLangFooter"
                   data-bs-toggle="dropdown"
                   aria-expanded="false"><fmt:message key="header.language" bundle="${lang}"/></a>
                <ul class="dropdown-menu" aria-labelledby="dropdownLangFooter">
                    <li><a class="dropdown-item <c:if test="${sessionScope.locale eq 'en'}">active</c:if>"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en">
                        <span class="flag-icon flag-icon-us me-2"></span>
                        <fmt:message key="header.language.eng" bundle="${lang}"/></a></li>
                    <li><a class="dropdown-item <c:if test="${sessionScope.locale eq 'ru'}">active</c:if>"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru">
                        <span class="flag-icon flag-icon-ru me-2"></span>
                        <fmt:message key="header.language.ru" bundle="${lang}"/></a></li>
                </ul>
            </li>
        </ul>


        <p class="text-center text-muted">© 2022 Vladimir Feshchenko, Inc</p>
    </div>
</footer>
<c:remove var="message" scope="session"/>
<c:remove var="parameters" scope="session"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>