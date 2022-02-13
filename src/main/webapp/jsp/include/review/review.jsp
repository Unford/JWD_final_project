<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cst" uri="custom" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<c:if test="${empty requestScope.reviews}">
    <span class="text-center">
        <fmt:message key="profile.empty" bundle="${lang}"/>
    </span>
</c:if>

<c:forEach items="${requestScope.reviews}" var="review">
    <div class="card mb-3">
        <div class="container-fluid">
            <div class="row g-0 ">
                <div class="col-3 col-md-2 align-self-center ">

                    <a href="${pageContext.request.contextPath}/controller?command=show_profile&user=${review.username}">
                        <c:choose>
                            <c:when test="${not empty review.photoData}">
                                <img src="${review.photoData}" class="img-thumbnail"
                                     alt="${review.photoName}">
                            </c:when>

                            <c:otherwise>
                                <svg xmlns="http://www.w3.org/2000/svg"
                                      fill="black"
                                     class="bi bi-person-circle mb-1 img-thumbnail" viewBox="0 0 16 16">
                                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                    <path fill-rule="evenodd"
                                          d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                </svg>
                            </c:otherwise>
                        </c:choose>

                    </a>


                </div>
                <div class="col-10 ">
                    <div class="card-body">
                        <h5 class="card-title">
                                ${review.username}
                            <cst:rating value="${review.score}"/>


                        </h5>
                        <p class="card-text">${review.message}</p>
                        <p class="card-text"><small class="text-muted">
                            <cst:dateformat value="${review.timestamp}"/>
                        </small>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </div>

</c:forEach>
