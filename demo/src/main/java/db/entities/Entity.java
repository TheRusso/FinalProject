package db.entities;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author R.Humeniuk
 *
 */
public abstract class Entity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

