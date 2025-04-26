package com.app.Model;

/**
 * Represents a sample model with an id and text.
 */
public class Sample {
    private Long id;
    private String text;

    /**
     * Constructs a new Sample with the specified id and text.
     *
     * @param id the unique identifier
     * @param text the associated text
     */
    public Sample(Long id, String text){
        this.id = id;
        this.text = text;
    }

    /**
     * Default constructor for Sample.
     */
    public Sample() { }

    /**
     * Sets the id of the Sample.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the id of the Sample.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the text of the Sample.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the Sample.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }
}