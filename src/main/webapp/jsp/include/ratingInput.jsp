<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset class="rating">
    <input type="radio" id="star5" name="rating" value="5" <c:if test="${requestScope.my_review.score == 5.0}">checked</c:if>/>
    <label class="full" for="star5"></label>
    <input type="radio" id="star4half" name="rating" value="4.5" <c:if test="${requestScope.my_review.score == 4.5}">checked</c:if>/>
    <label class="half" for="star4half"></label>
    <input type="radio" id="star4" name="rating" value="4" <c:if test="${requestScope.my_review.score == 4.0}">checked</c:if>/>
    <label class="full" for="star4"></label>
    <input type="radio" id="star3half" name="rating" value="3.5" <c:if test="${requestScope.my_review.score == 3.5}">checked</c:if>/>
    <label class="half" for="star3half"></label>
    <input type="radio" id="star3" name="rating" value="3" required <c:if test="${requestScope.my_review.score == 3.0}">checked</c:if>/>
    <label class="full" for="star3"></label>
    <input type="radio" id="star2half" name="rating" value="2.5" <c:if test="${requestScope.my_review.score == 2.5}">checked</c:if>/>
    <label class="half" for="star2half"></label>
    <input type="radio" id="star2" name="rating" value="2" <c:if test="${requestScope.my_review.score == 2.0}">checked</c:if>/>
    <label class="full" for="star2"></label>
    <input type="radio" id="star1half" name="rating" value="1.5" <c:if test="${requestScope.my_review.score == 1.5}">checked</c:if>/>
    <label class="half" for="star1half"></label>
    <input type="radio" id="star1" name="rating" value="1" <c:if test="${requestScope.my_review.score == 1.0}">checked</c:if>/>
    <label class="full" for="star1"></label>
    <input type="radio" id="starhalf" name="rating" value="0.5" <c:if test="${requestScope.my_review.score == 0.5}">checked</c:if>/>
    <label class="half" for="starhalf"></label>
</fieldset>