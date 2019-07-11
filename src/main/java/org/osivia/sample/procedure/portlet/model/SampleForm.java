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

    private String mail;
    
    
    /**
     * Getter for mail.
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    
    /**
     * Setter for mail.
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    
    /**
     * Getter for password1.
     * @return the password1
     */
    public String getPassword1() {
        return password1;
    }

    
    /**
     * Setter for password1.
     * @param password1 the password1 to set
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    
    /**
     * Getter for password2.
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    
    /**
     * Setter for password2.
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    private String password1;
    
    private String password2;  
  
    public SampleView getView() {
        return view;
    }

    public void setView(SampleView view) {
        this.view = view;
    }

}
