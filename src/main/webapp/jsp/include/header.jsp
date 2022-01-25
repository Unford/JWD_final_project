<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>


<header class="p-3 mb-2 border-bottom bg-dark">
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 ms-lg-0 text-white text-decoration-none">
                <svg style="width:32px;height:32px" viewBox="0 0 24 24">
                    <path fill="currentColor" d="M7.5,7L5.5,5H18.5L16.5,7M11,13V19H6V21H18V19H13V13L21,5V3H3V5L11,13Z" />
                </svg>
               <h3><fmt:message key="header.title" bundle="${lang}"/></h3>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li class="nav-item"><a href="#" class="nav-link px-2 "><fmt:message key="header.cocktails" bundle="${lang}"/></a></li>
                <li class="nav-item"><a href="#" class="nav-link px-2 "><fmt:message key="header.ingredients" bundle="${lang}"/></a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " href="#" id="dropdown08" data-bs-toggle="dropdown" aria-expanded="false"><fmt:message key="header.language" bundle="${lang}"/></a>
                    <ul class="dropdown-menu" aria-labelledby="dropdown08">
                        <li><a class="dropdown-item" href="#"><span class="flag-icon flag-icon-us me-2"></span><fmt:message key="header.language.eng" bundle="${lang}"/></a></li>
                        <li><a class="dropdown-item" href="#"><span class="flag-icon flag-icon-ru me-2"></span><fmt:message key="header.language.ru" bundle="${lang}"/></a></li>
                    </ul>
                </li>
            </ul>



            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <input type="search" class="form-control" placeholder="<fmt:message key="header.search" bundle="${lang}"/>..." aria-label="Search">
            </form>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-light text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                    <li><a class="dropdown-item" href="#"><fmt:message key="header.profile" bundle="${lang}"/></a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#"><fmt:message key="header.sign_out" bundle="${lang}"/></a></li>
                </ul>
            </div>
            <div class="dropdown text-end " >
                <a href="#" class="d-block link-light text-decoration-none dropdown-toggle" id="dropdownUser12" data-bs-toggle="dropdown" aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                    </svg>
                </a>

                <ul class="dropdown-menu " aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="#"><fmt:message key="header.log_in" bundle="${lang}"/></a></li>
                    <li><hr class="dropdown-divider"></li>

                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message key="header.log_up" bundle="${lang}"/></a></li>
                </ul>
            </div>
        </div>

    </div>
</header>
