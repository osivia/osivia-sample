package org.osivia.sample.procedure.portlet.service;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.PortalException;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.notifications.INotificationsService;
import org.osivia.portal.api.notifications.NotificationsType;
import org.osivia.portal.api.urls.IPortalUrlFactory;
import org.osivia.portal.api.windows.PortalWindow;
import org.osivia.portal.api.windows.WindowFactory;
import org.osivia.sample.procedure.portlet.model.SampleForm;
import org.osivia.sample.procedure.portlet.model.SampleView;
import org.osivia.sample.procedure.portlet.model.SampleWindowProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.forms.FormFilterException;
import fr.toutatice.portail.cms.nuxeo.api.forms.IFormsService;

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

    private static String USER_MAIL = "mail";

    /** Sample model identifier. */
    private static String SAMPLE_MODEL_ID = "sample-pwd-change";

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


    /** Forms service. */
    @Autowired
    private IFormsService formsService;

    /** Bundle factory. */
    @Autowired
    private IBundleFactory bundleFactory;

    /** Notifications service. */
    @Autowired
    private INotificationsService notificationsService;

    /** Log. */
    private final Log log;


    /**
     * Constructor.
     */
    public SampleServiceImpl() {
        super();
        this.log = LogFactory.getLog(this.getClass());
    }


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



    /**
     * {@inheritDoc}
     */

    public void proceedInit(PortalControllerContext portalControllerContext, SampleForm form) throws PortletException {
        // Internationalization bundle
        Bundle bundle = this.bundleFactory.getBundle(portalControllerContext.getRequest().getLocale());

        try {

            // Variables
            Map<String, String> variables = new HashMap<>();

            variables.put(USER_MAIL, form.getMail());

            // Start
            String modelWebId = IFormsService.FORMS_WEB_ID_PREFIX + SAMPLE_MODEL_ID;
            this.formsService.start(portalControllerContext, modelWebId, variables);

            // Notification
            String message = bundle.getString("MESSAGE_SAMPLE_SEND_SUCCESS");
            this.notificationsService.addSimpleNotification(portalControllerContext, message, NotificationsType.SUCCESS);
            
            form.setView(SampleView.SEND);
            
            
        } catch (PortalException | FormFilterException e) {
            // Error notification
            String message = bundle.getString("MESSAGE_SAMPLE_SEND_ERROR");
            this.notificationsService.addSimpleNotification(portalControllerContext, message, NotificationsType.ERROR);

            this.log.error(message, e);
        }
    }


    
    /**
     * {@inheritDoc}
     */

    public void proceedPassword(PortalControllerContext portalControllerContext, SampleForm form) throws PortletException {
        // Internationalization bundle
        Bundle bundle = this.bundleFactory.getBundle(portalControllerContext.getRequest().getLocale());
        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(portalControllerContext);

        try {

            // Variables
            Map<String, String> variables = new HashMap<>();

            variables.put("password", form.getPassword1());
            variables.put("confirmpassword", form.getPassword2());            

            Document task = nuxeoController.getDocumentContext( nuxeoController.getContentPath()).getDenormalizedDocument();
            this.formsService.proceed(portalControllerContext, task, variables);
            
            form.setView(SampleView.CONFIRM);
            

        } catch (Exception  e) {
            // Error notification
            String message = bundle.getString("MESSAGE_SAMPLE_SEND_ERROR");
            this.notificationsService.addSimpleNotification(portalControllerContext, message, NotificationsType.ERROR);

            this.log.error(message, e);
        }
    }


    
    
}
