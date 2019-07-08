package org.osivia.sample.procedure.portlet.service;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.sample.procedure.portlet.model.SampleForm;
import org.osivia.sample.procedure.portlet.model.SampleWindowProperties;

import javax.portlet.PortletException;

/**
 * Sample portlet service interface.
 *
 * @author Jean-Sébastien Steux
 */
public interface SampleService {

    /**
     * Search view window property.
     */
    String VIEW_WINDOW_PROPERTY = "osivia.search.view";


    /**
     * Get search window properties.
     *
     * @param portalControllerContext portal controller context
     * @return window properties
     */
    SampleWindowProperties getWindowProperties(PortalControllerContext portalControllerContext) throws PortletException;


    /**
     * Set search window properties.
     *
     * @param portalControllerContext portal controller context
     * @param windowProperties        search window properties
     */
    void setWindowProperties(PortalControllerContext portalControllerContext, SampleWindowProperties windowProperties) throws PortletException;


    /**
     * Get search form.
     *
     * @param portalControllerContext portal controller context
     * @return form
     */
    SampleForm getForm(PortalControllerContext portalControllerContext) throws PortletException;


    /**
     * Get view path.
     *
     * @param portalControllerContext portal controller context
     * @return path
     */
    String getViewPath(PortalControllerContext portalControllerContext) throws PortletException;


}
