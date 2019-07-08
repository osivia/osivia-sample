package org.osivia.sample.procedure.portlet.service;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;

import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.urls.IPortalUrlFactory;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.api.windows.WindowFactory;
import org.osivia.sample.procedure.portlet.model.SampleForm;
import org.osivia.sample.procedure.portlet.model.SampleView;
import org.osivia.sample.procedure.portlet.model.SampleWindowProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
    public String getViewPath(PortalControllerContext portalControllerContext, SampleForm form) throws PortletException {
         return VIEW_PREFIX + form.getView().getId();
    }


    @Override
    public void proceedNext(PortalControllerContext portalControllerContext, SampleForm form) throws PortletException {
        form.setView(SampleView.SEND);
    }




}
