<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>


<footer class="mt-auto">
    <div class="container">
        <ul class="nav justify-content-center border-bottom pb-1 mb-1">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/jsp/cocktails.jsp" class="nav-link px-2 text-muted">
                    <fmt:message key="header.cocktails" bundle="${lang}"/></a></li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/jsp/ingredients.jsp"
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
