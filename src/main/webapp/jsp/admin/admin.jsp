<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cst" uri="custom" %>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="page_content" var="lang"/>

<html>
<head>
    <title><fmt:message key="header.administration" bundle="${lang}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<main>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="sign_up.username" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="sign_up.email" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="profile.role" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="profile.status" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="profile.status.deleted" bundle="${lang}"/></th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">

            <tr class="<c:if test="${user.id == sessionScope.user.id}">table-primary</c:if>">


                <th scope="row">${user.id}</th>

                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=show_profile&user=${user.id}">
                            ${user.username}
                    </a>
                </td>
                <td>${user.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.id != sessionScope.user.id}">
                            <form method="post" action="${pageContext.request.contextPath}/controller"
                                  onchange="this.submit()">
                                <input type="hidden" name="command" value="change_account_role">
                                <input type="hidden" name="user" value="${user.id}">
                                <select class="form-select" required name="role">
                                    <option
                                            <c:if test="${user.role == 'ADMIN'}">selected</c:if> value="admin">
                                        <fmt:message key="profile.role.admin" bundle="${lang}"/>
                                    </option>
                                    <option
                                            <c:if test="${user.role == 'BARTENDER'}">selected</c:if> value="bartender">
                                        <fmt:message key="profile.role.bartender" bundle="${lang}"/>
                                    </option>
                                    <option
                                            <c:if test="${user.role == 'CLIENT'}">selected</c:if> value="client">
                                        <fmt:message key="profile.role.client" bundle="${lang}"/>
                                    </option>
                                </select>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${user.role == 'ADMIN'}">
                                    <fmt:message key="profile.role.admin" bundle="${lang}"/>
                                </c:when>
                                <c:when test="${user.role == 'BARTENDER'}">
                                    <fmt:message key="profile.role.bartender" bundle="${lang}"/>
                                </c:when>
                                <c:when test="${user.role == 'CLIENT'}">
                                    <fmt:message key="profile.role.client" bundle="${lang}"/>
                                </c:when>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>


                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.status == 'WORKING'}">
                            <span>
                                <fmt:message key="status.ok" bundle="${lang}"/>
                            </span>
                            <c:if test="${user.id != sessionScope.user.id}">
                                <form method="post" class="d-inline"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_account_status">
                                    <input type="hidden" name="status" value="banned">
                                    <input type="hidden" name="user" value="${user.id}">
                                    <button type="submit" class="btn btn-outline-warning ">
                                        <i class="bi bi-person-x-fill"></i>
                                        <fmt:message key="profile.action.ban" bundle="${lang}"/>
                                    </button>
                                </form>
                            </c:if>

                        </c:when>
                        <c:when test="${user.status == 'BANNED'}">
                                <span>
                                    <fmt:message key="status.ban" bundle="${lang}"/>
                                </span>
                            <c:if test="${user.id != sessionScope.user.id}">
                                <form method="post" class="d-inline"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_account_status">
                                    <input type="hidden" name="status" value="working">
                                    <input type="hidden" name="user" value="${user.id}">
                                    <button type="submit" class="btn btn-outline-success ">
                                        <i class="bi bi-person-check-fill"></i>
                                        <fmt:message key="profile.action.unban" bundle="${lang}"/>
                                    </button>
                                </form>
                            </c:if>
                        </c:when>

                    </c:choose>

                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.deleted}">
                            <fmt:message key="deleted.yes" bundle="${lang}"/>
                        </c:when>
                        <c:when test="${not user.deleted}">
                            <fmt:message key="deleted.no" bundle="${lang}"/>
                        </c:when>
                    </c:choose>
                </td>


            </tr>

        </c:forEach>

        </tbody>
    </table>

    <cst:pagination currentPage="${param.page}"
                    itemsSize="${requestScope.users_size}"
                    command="show_administration"
                    search=""/>

    <jsp:include page="../include/footer.jsp"/>

</main>

</body>
</html>
