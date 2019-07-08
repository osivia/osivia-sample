package org.osivia.sample.procedure.portlet.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Search window properties.
 *
 * @author Jean-Sébastien Steux
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SampleWindowProperties {

    /**
     * Search view.
     */
    private SampleView view;


    public SampleView getView() {
        return view;
    }

    public void setView(SampleView view) {
        this.view = view;
    }
}
