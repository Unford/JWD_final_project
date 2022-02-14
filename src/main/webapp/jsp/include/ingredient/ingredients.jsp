<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cst" uri="custom" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<c:if test="${empty requestScope.ingredients}">
    <span class="text-center">
        <fmt:message key="profile.empty" bundle="${lang}"/>
    </span>

</c:if>

<c:forEach items="${requestScope.ingredients}" var="ingredient">
    <div class="col">
        <div class="card text-center">
            <div class="card-body justify-content-center">
                <img src="${ingredient.photo.data}"
                     class="card-img-top"
                     alt="${ingredient.photo.name}"
                     style="width: 200px; height: 200px;">
            </div>
            <div class="card-body <c:if test="${not ingredient.verified}">bg-secondary</c:if>">
                <p class="card-text m-0">${ingredient.name}</p>
                <small class="card-text fw-light m-0">${ingredient.description}</small>
                <a class="stretched-link"
                   href="${pageContext.request.contextPath}/controller?command=show_ingredient&ingredient=${ingredient.id}">
                </a>
            </div>
        </div>
    </div>
</c:forEach>
