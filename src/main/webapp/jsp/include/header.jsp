<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>


<header class="p-3 mb-2 border-bottom bg-dark ">
<div class="container-fluid">
<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
<a href="${pageContext.request.contextPath}/controller?command=go_to_main"
   class="d-flex align-items-center  text-white text-decoration-none">
    <svg style="width:calc(1.325rem + .9vw);height: calc(1.325rem + .9vw);" viewBox="0 0 24 24">
        <path fill="currentColor" d="M7.5,7L5.5,5H18.5L16.5,7M11,13V19H6V21H18V19H13V13L21,5V3H3V5L11,13Z"/>
    </svg>
    <h3><fmt:message key="header.title" bundle="${lang}"/></h3>
</a>

<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
    <li class="nav-item">
        <a href="${pageContext.request.contextPath}/controller?command=show_cocktails"
           class="nav-link px-2 ">
            <fmt:message key="header.cocktails" bundle="${lang}"/></a></li>
    <li class="nav-item"><a href="${pageContext.request.contextPath}/controller?command=show_ingredients"
                            class="nav-link px-2 ">
        <fmt:message key="header.ingredients" bundle="${lang}"/></a></li>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/controller?command=show_administration"
                                class="nav-link px-2 ">
            <fmt:message key="header.administration" bundle="${lang}"/></a></li>
    </c:if>
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle " href="#" id="dropdownLangHeader" data-bs-toggle="dropdown"
           aria-expanded="false">
            <c:choose>
                <c:when test="${sessionScope.locale eq 'en'}">
                    <span class="flag-icon flag-icon-us me-2"></span>
                </c:when>
                <c:when test="${sessionScope.locale eq 'ru'}">
                    <span class="flag-icon flag-icon-ru me-2"></span>
                </c:when>
                <c:otherwise><fmt:message key="header.language" bundle="${lang}"/></c:otherwise>
            </c:choose>
        </a>
        <ul class="dropdown-menu" aria-labelledby="dropdownLangHeader">
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


    <c:choose>
        <c:when test="${sessionScope.user.role != 'GUEST'}">
            <div class="dropdown text-end">
                <a href="#" class="d-block link-light text-decoration-none dropdown-toggle" id="dropdownUser1"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user.photo.data}">
                            <img src="${sessionScope.user.photo.data}" alt="mdo" width="32" height="32" class="rounded-circle">
                        </c:when>
                        <c:otherwise>
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="blue"
                                 class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd"
                                      d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </c:otherwise>
                    </c:choose>

                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=show_profile">
                        <fmt:message key="header.profile" bundle="${lang}"/></a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=sign_out">
                            <fmt:message key="header.sign_out" bundle="${lang}"/></a>
                    </li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div class="dropdown text-end ">
                <a href="#" class="d-block link-light text-decoration-none dropdown-toggle" id="dropdownUser12"
                   data-bs-toggle="dropdown" aria-expanded="false">

                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                         class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        <path fill-rule="evenodd"
                              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                    </svg>
                </a>

                <ul class="dropdown-menu " aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="" data-bs-toggle="modal" data-bs-target="#modalSignIn">
                        <fmt:message key="header.log_in" bundle="${lang}"/>
                    </a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li><a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=go_to_sign_up">
                        <fmt:message key="header.sign_up" bundle="${lang}"/></a></li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>

    </div>

    </div>
    </header>
    <c:if test="${sessionScope.user.role eq 'GUEST'}">
        <div class="modal fade" tabindex="-1" role="dialog" id="modalSignIn">
            <div class="modal-dialog" role="document">
                <div class="modal-content rounded-5 shadow">
                    <div class="modal-header  border-bottom-0 bg-dark">
                        <h3 class="modal-title ms-4 text-white"><fmt:message key="header.title" bundle="${lang}"/></h3>

                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>

                    <div class="modal-body p-5 pt-2 pb-0">
                        <form method="post" action="${pageContext.request.contextPath}/controller"
                              class="container g-3 needs-validation justify-content-center " novalidate>
                            <input type="hidden" name="command" value="log_in">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control rounded-4" id="floatingInput"
                                       placeholder="name@example.com"
                                       required maxlength="40"
                                       minlength="4"
                                       pattern="([A-Za-z\d\.\-_&%$#@!*,]{4,30})|([a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$)"
                                       name="login"
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

                                <input type="password" class="form-control rounded-4" id="floatingPassword"
                                       name="password"
                                       placeholder="Password"
                                       required
                                       maxlength="30"
                                       minlength="8"
                                       pattern="[A-Za-z\d\.\-_&%$#@!*,]{8,30}">
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
                            <button class="w-100 mt-2 mb-2 btn btn-lg rounded-4 btn-primary mt-2" type="submit">
                                <fmt:message key="logIn.submit" bundle="${lang}"/>
                            </button>
                            <hr class="my-4">
                            <div class="d-grid  d-md-flex justify-content-md-center">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up">
                                    <button class=" btn btn-lg rounded-4 btn-success" type="button">
                                        <fmt:message key="logIn.sign_up" bundle="${lang}"/>
                                    </button>
                                </a>

                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/logInFormValidation.js"></script>
    </c:if>

