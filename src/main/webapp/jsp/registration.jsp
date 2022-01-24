<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="page_content" var="lang"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">

    <title><fmt:message key="sign_up.title" bundle="${lang}"/></title>
</head>
<body>
<jsp:include page="include/header.jsp"/>

    <main>
        <form class="container g-3 needs-validation justify-content-center " novalidate action="${pageContext.request.contextPath}/controller" METHOD="post">
            <input type="hidden" name="command" value="sign_up">
            <div class="row text-center">
                <div class="col-md-12">
                    <h3><fmt:message key="sign_up.title" bundle="${lang}"/></h3>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-md-4 ">
                    <label for="first_name" class="form-label"><fmt:message key="sign_up.first_name" bundle="${lang}"/></label>
                    <input type="text" class="form-control" id="first_name" required name="first_name">
                </div>
                <div class="col-md-4 ">
                    <label for="last_name" class="form-label"><fmt:message key="sign_up.last_name" bundle="${lang}"/></label>
                    <input type="text" class="form-control" id="last_name" required name="last_name">
                </div>
            </div>
            <div class="row justify-content-center">

                <div class="col-md-8">
                    <label for="email" class="form-label"><fmt:message key="sign_up.email" bundle="${lang}"/></label>
                    <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend1">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-envelope" viewBox="0 0 16 16">
                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
                      </svg>
                </span>
                        <input type="email" class="form-control" id="email" required name="email">
                    </div>
                </div>

            </div>
            <div class="row justify-content-center">

                <div class="col-md-8">
                    <label for="validationCustomUsername" class="form-label"><fmt:message key="sign_up.username" bundle="${lang}"/></label>
                    <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend2"><svg xmlns="http://www.w3.org/2000/svg"
                                                                            width="16" height="16" fill="currentColor"
                                                                            class="bi bi-person" viewBox="0 0 16 16">
                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                  </svg></span>
                        <input type="text" class="form-control" id="validationCustomUsername"
                               aria-describedby="inputGroupPrepend2" required min="4" max="30" name="username">
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <label for="validationPass" class="form-label"><fmt:message key="sign_up.password" bundle="${lang}"/></label>
                    <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-key" viewBox="0 0 16 16">
                                <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                                <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                              </svg>
                        </span>
                        <input type="password" class="form-control" id="validationPass" required
                               aria-describedby="passwordHelpBlock" min="8" max="30" name="password">
                    </div>

                </div>
                <div class="col-md-4">
                    <label for="validationRepeatPass" class="form-label"><fmt:message key="sign_up.rep_password" bundle="${lang}"/></label>
                    <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend4">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-key-fill" viewBox="0 0 16 16">
                    <path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2zM2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                  </svg>
                  </span>
                        <input type="password" class="form-control" id="validationRepeatPass" required minlength="8" max="30">
                    </div>

                </div>
                <div id="passwordHelpBlock" class="form-text text-center">
                    <fmt:message key="sign_up.password.help" bundle="${lang}"/>
                </div>
            </div>

            <div class="d-grid row justify-content-center">
                <div class="col-md-12 mt-2">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                        <label class="form-check-label" for="invalidCheck">
                            <fmt:message key="sign_up.terms" bundle="${lang}"/>
                        </label>
                        <div class="invalid-feedback">
                            <fmt:message key="sign_up.terms.invalid" bundle="${lang}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-grid row justify-content-center mx-auto">
                <div class="col-12 mt-3">
                    <button class="btn btn-primary" type="submit"><fmt:message key="sign_up.submit" bundle="${lang}"/>
                    </button>
                </div>
            </div>

        </form>
    </main>

<jsp:include page="include/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="../js/formValidation.js"></script>
</body>
</html>
