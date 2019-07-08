package org.osivia.sample.procedure.portlet.service;

import fr.toutatice.portail.cms.nuxeo.api.PageSelectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.PropertyList;
import org.osivia.portal.api.Constants;
import org.osivia.portal.api.PortalException;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.page.PageParametersEncoder;
import org.osivia.portal.api.urls.IPortalUrlFactory;
import org.osivia.portal.api.urls.PortalUrlType;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.api.windows.WindowFactory;
import org.osivia.portal.core.page.PageProperties;
import org.osivia.sample.procedure.options.portlet.service.SearchOptionsService;
import org.osivia.sample.procedure.portlet.model.SampleForm;
import org.osivia.sample.procedure.portlet.model.SampleView;
import org.osivia.sample.procedure.portlet.model.SampleWindowProperties;
import org.osivia.sample.procedure.portlet.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sample portlet service implementation.
 *
 * @author Jean-Sébastien Steux
 * @see SampleService
 */
@Service
public class SampleServiceImpl implements SampleService {



    /**
     * Search view prefix.
     */
    private static final String VIEW_PREFIX = "view-";
    
    
 
    /**
     * Application context.
     */
    @Autowired
    private ApplicationContext applicationContext;



    /**
     * Portal URL factory.
     */
    @Autowired
    private IPortalUrlFactory portalUrlFactory;


    @Override
    public SampleWindowProperties getWindowProperties(PortalControllerContext portalControllerContext) {
        // Window
        PortalWindow window = WindowFactory.getWindow(portalControllerContext.getRequest());

        // Window properties
        SampleWindowProperties windowProperties = this.applicationContext.getBean(SampleWindowProperties.class);

        // Search view
        SampleView view = SampleView.fromId(window.getProperty(VIEW_WINDOW_PROPERTY));
        windowProperties.setView(view);

        return windowProperties;
    }


    @Override
    public void setWindowProperties(PortalControllerContext portalControllerContext, SampleWindowProperties windowProperties) {
        // Window
        PortalWindow window = WindowFactory.getWindow(portalControllerContext.getRequest());

        // Search view
        SampleView view = windowProperties.getView();
        if (view == null) {
            view = SampleView.DEFAULT;
        }
        window.setProperty(VIEW_WINDOW_PROPERTY, view.getId());
    }


    @Override
    public SampleForm getForm(PortalControllerContext portalControllerContext) {
        // Window properties
        SampleWindowProperties windowProperties = this.getWindowProperties(portalControllerContext);

        // Portlet request
        PortletRequest request = portalControllerContext.getRequest();

        // Search form
        SampleForm form = this.applicationContext.getBean(SampleForm.class);

        // Search view
        SampleView view = windowProperties.getView();
        form.setView(view);


        return form;
    }



    @Override
    public String getViewPath(PortalControllerContext portalControllerContext) throws PortletException {
        // Window properties
        SampleWindowProperties windowProperties = this.getWindowProperties(portalControllerContext);

        return VIEW_PREFIX + windowProperties.getView().getId();
    }




}
