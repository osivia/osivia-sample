package org.osivia.sample.procedure.portlet.controller;

import org.apache.commons.lang.StringUtils;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.sample.procedure.portlet.model.SampleForm;
import org.osivia.sample.procedure.portlet.model.SampleView;
import org.osivia.sample.procedure.portlet.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.*;
import java.io.IOException;

/**
 * Search portlet controller.
 *
 * @author Cédric Krommenhoek
 */
@Controller
@RequestMapping("VIEW")
public class SampleController {

    /**
     * Portlet context.
     */
    @Autowired
    private PortletContext portletContext;

    /**
     * Portlet service.
     */
    @Autowired
    private SampleService service;


    /**
     * View render mapping.
     *
     * @param request  render request
     * @param response render response
     * @return view path
     */
    @RenderMapping
    public String view(RenderRequest request, RenderResponse response, @ModelAttribute("form") SampleForm form) throws PortletException {
        // Portal Controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);
  
        return this.service.getViewPath(portalControllerContext, form);
    }


    /**
     * Search action mapping.
     *
     * @param request  action request
     * @param response action response
     * @param form     search form model attribute
     */
    @ActionMapping("sendMail")
    public void sendMail(ActionRequest request, ActionResponse response, @ModelAttribute("form") SampleForm form) throws PortletException, IOException {
        // Portal Controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);
        
        this.service.proceedInit(portalControllerContext, form);
        
    }

    
    

    /**
     * Search action mapping.
     *
     * @param request  action request
     * @param response action response
     * @param form     search form model attribute
     */
    @ActionMapping("submitPassword")
    public void submitPassword(ActionRequest request, ActionResponse response, @ModelAttribute("form") SampleForm form) throws PortletException, IOException {
        // Portal Controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);
        

        this.service.proceedPassword(portalControllerContext, form);        
    }

    

    /**
     * Get search form model attribute.
     *
     * @param request  portlet request
     * @param response portlet response
     * @return form
     */
    @ModelAttribute("form")
    public SampleForm getForm(PortletRequest request, PortletResponse response) throws PortletException {
        // Portal Controller context
        PortalControllerContext portalControllerContext = new PortalControllerContext(this.portletContext, request, response);

        return this.service.getForm(portalControllerContext);
    }


    /**
     * Form init binder.
     *
     * @param binder data binder
     */
    @InitBinder("form")
    public void formInitBinder(PortletRequestDataBinder binder) {
        binder.setDisallowedFields("view");
    }


}
