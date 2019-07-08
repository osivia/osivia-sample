package org.osivia.sample.procedure.portlet.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Sample form java-bean.
 *
 * @author Jean-Sébastien Steux
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SampleForm {

    /**
     * Search view.
     */
    private SampleView view;

    /**
     * Input value.
     */
    private String value;
    
  
    public SampleView getView() {
        return view;
    }

    public void setView(SampleView view) {
        this.view = view;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
