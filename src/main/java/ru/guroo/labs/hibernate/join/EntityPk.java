package ru.guroo.labs.hibernate.join;

import java.io.Serializable;

/**
 * @author Guram Savinov
 */
public class EntityPk implements Serializable {

    private RelatedEntity1 relatedEntity1;
    private RelatedEntity2 relatedEntity2;

    public RelatedEntity1 getRelatedEntity1() {
        return relatedEntity1;
    }

    public void setRelatedEntity1(RelatedEntity1 relatedEntity1) {
        this.relatedEntity1 = relatedEntity1;
    }

    public RelatedEntity2 getRelatedEntity2() {
        return relatedEntity2;
    }

    public void setRelatedEntity2(RelatedEntity2 relatedEntity2) {
        this.relatedEntity2 = relatedEntity2;
    }
}
