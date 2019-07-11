<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="op" uri="http://www.osivia.org/jsp/taglib/osivia-portal" %>

<%@ page isELIgnored="false" %>


<portlet:actionURL name="submitPassword" var="submitUrl"/>

<c:set var="namespace"><portlet:namespace/></c:set>

change mail

<div class="ml-3">
    <form:form id="${namespace}-form" action="${submitUrl}" method="post" modelAttribute="form">
        <div class="form-group mb-0">
            <form:label path="password1" cssClass="sr-only"><op:translate key="SAMPLE_INIT_NEW_PASSWORD_LABEL"/></form:label>
            <div class="input-group border rounded">
                 <form:input path="password1" type="password" cssClass="form-control border-0"></form:input>
            </div>
        </div>
        <div class="form-group mb-0">
            <form:label path="password2" cssClass="sr-only"><op:translate key="SAMPLE_INIT_CONFIRM_PASSWORD_LABEL"/></form:label>
            <div class="input-group border rounded">
                 <form:input path="password2" type="password" cssClass="form-control border-0"></form:input>
            </div>
        </div>        
	<input type="submit" />        
    </form:form>
</div>
