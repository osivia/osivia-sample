package org.osivia.sample.procedure.portlet.model;

import org.apache.commons.lang.StringUtils;

/**
 * Search views enumeration.
 *
 * @author Jean-Sébastien Steux
 */
public enum SampleView {

    INIT,
    SEND,
    PASSWORD,
    CONFIRM,
    END;
 
    /** Default view. */
    public static final SampleView DEFAULT = INIT;


    /**
     * Identifier.
     */
    private final String id;
    /**
     * Internationalization key.
     */
    private final String key;


    /**
     * Constructor.
     */
    SampleView() {
        this.id = StringUtils.lowerCase(this.name());
        this.key = "SAMPLE_VIEW_" + StringUtils.upperCase(this.name());
    }


    /**
     * Get search view from identifier.
     *
     * @param id identifier
     * @return view
     */
    public static SampleView fromId(String id) {
        SampleView result = DEFAULT;

        for (SampleView value : SampleView.values()) {
            if (StringUtils.equals(id, value.id)) {
                result = value;
            }
        }

        return result;
    }


    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
