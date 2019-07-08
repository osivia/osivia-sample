<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="op" uri="http://www.osivia.org/jsp/taglib/osivia-portal" %>

<%@ page isELIgnored="false" %>


<portlet:actionURL name="init" var="initUrl"/>

<c:set var="namespace"><portlet:namespace/></c:set>


<div class="ml-3">
    <form:form id="${namespace}-form" action="${initUrl}" method="post" modelAttribute="form">
        <div class="form-group mb-0">
            <form:label path="value" cssClass="sr-only"><op:translate key="SAMPLE_INIT_VALUE_LABEL"/></form:label>
            <div class="input-group border rounded">
            

                 <form:input path="value" type="search" placeholder="${placeholder} ${form.folderName}"
                            cssClass="form-control border-0"></form:input>
                <input type="submit" class="d-none"/>
            </div>
        </div>
    </form:form>
</div>
