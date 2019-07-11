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

import java.util.List;
import java.util.Map;

import javax.portlet.PortletContext;

import org.osivia.portal.api.cms.DocumentType;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.Plugin;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.api.menubar.MenubarModule;
import org.osivia.portal.api.player.IPlayerModule;
import org.osivia.portal.api.taskbar.TaskbarFactory;
import org.osivia.portal.api.taskbar.TaskbarItem;
import org.osivia.portal.api.taskbar.TaskbarItems;


import fr.toutatice.portail.cms.nuxeo.api.domain.AbstractPluginPortlet;
import fr.toutatice.portail.cms.nuxeo.api.domain.ListTemplate;


/**
 * Technical portlet for attributes bundles customization.
 *
 * @author Jean-Sébastien steux
 * @see AbstractPluginPortlet
 */
@Plugin("sample.plugin")
public class SamplePlugin extends AbstractPluginPortlet {


    /** Plugin name. */
    private static final String PLUGIN_NAME = "procedure.sample.plugin";


    /* (non-Javadoc)
     * @see fr.toutatice.portail.cms.nuxeo.api.domain.AbstractPluginPortlet#getOrder()
     */
    @Override
    public int getOrder() {
        // After procedures
        return DEFAULT_DEPLOYMENT_ORDER + 10;
    }



    /** Bundle factory. */
    private final IBundleFactory bundleFactory;


    /**
     * Constructor.
     */
    public SamplePlugin() {
        super();

        // Bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeCMSProperties(CustomizationContext context) {

        // Players
        this.customizePlayers(context);

    }


   

    /**
     * Customize players.
     *
     * @param context customize players
     */
    @SuppressWarnings("rawtypes")
    private void customizePlayers(CustomizationContext context) {
        // Players
        List<IPlayerModule> players = this.getPlayers(context);

        // Add before procedures ...
        SamplePlayer sp = new SamplePlayer(this.getPortletContext());
        players.add(0, sp);
    }


   
    /**
     * {@inheritDoc}
     */
	@Override
	protected String getPluginName() {
		return PLUGIN_NAME;
	}

}
