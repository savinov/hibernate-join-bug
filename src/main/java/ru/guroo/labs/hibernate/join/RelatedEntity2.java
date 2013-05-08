package ru.guroo.labs.hibernate.join;

/**
 * @author Guram Savinov
 */
public class RelatedEntity2 {

    private long id;
    private long property;

    public RelatedEntity2(long id, long property) {
        this.id = id;
        this.property = property;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProperty() {
        return property;
    }

    public void setProperty(long property) {
        this.property = property;
    }
}
