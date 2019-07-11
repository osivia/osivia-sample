/*
 * (C) Copyright 2014 Académie de Rennes (http://www.ac-rennes.fr/), OSIVIA (http://www.osivia.com) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 *
 *
 */
package org.osivia.sample.procedure.plugin;

import fr.toutatice.portail.cms.nuxeo.api.cms.NuxeoDocumentContext;
import fr.toutatice.portail.cms.nuxeo.api.cms.NuxeoPublicationInfos;
import fr.toutatice.portail.cms.nuxeo.api.player.INuxeoPlayerModule;
import fr.toutatice.portail.cms.nuxeo.api.portlet.ViewList;
import org.jboss.portal.theme.impl.render.dynamic.DynaRenderOptions;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.PropertyMap;
import org.osivia.portal.api.Constants;
import org.osivia.portal.api.player.Player;
import org.osivia.portal.core.constants.InternalConstants;
import org.osivia.sample.procedure.portlet.service.SampleService;

import javax.portlet.PortletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Forum player.
 *
 * @author Jean-Sébastien Steux
 * @see INuxeoPlayerModule
 */
public class SamplePlayer implements INuxeoPlayerModule {

    /** Forum portlet instance. */
    private static final String SAMPLE_PORTLET_INSTANCE = "osivia-sample-procedure-instance";
 

    /**
     * Constructor.
     * 
     * @param portletContext portlet context
     */
    public SamplePlayer(PortletContext portletContext) {
        super();
    }


    /**
     * Get forum player.
     *
     * @param documentContext Nuxeo document context
     * @return forum player
     */
    private Player getPortletPlayer(NuxeoDocumentContext documentContext) {
        Document document = documentContext.getDocument();

        // Window properties
        Map<String, String> properties = new HashMap<>();
        properties.put(Constants.WINDOW_PROP_URI, document.getPath());
        properties.put("osivia.hideDecorators", "1");
        properties.put(DynaRenderOptions.PARTIAL_REFRESH_ENABLED, String.valueOf(true));
        properties.put("osivia.ajaxLink", "1");
        properties.put(SampleService.VIEW_WINDOW_PROPERTY, "password");
        
        
 
        // Player
        Player player = new Player();
        player.setWindowProperties(properties);
        player.setPortletInstance(SAMPLE_PORTLET_INSTANCE);

        return player;
    }


  

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCMSPlayer(NuxeoDocumentContext documentContext) {
        Document doc = documentContext.getDocument();

        if ("TaskDoc".equals(doc.getType())) {
            doc = documentContext.getDenormalizedDocument();
            PropertyMap procMap = doc.getProperties().getMap("nt:pi");
            
            if( procMap!= null && "procedure_sample-pwd-change".equals(procMap.get("pi:procedureModelWebId")   )) {
                return this.getPortletPlayer(documentContext);
            }
        } 
        return null;
    }

}
