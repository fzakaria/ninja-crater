package models;

import org.joda.time.DateTime;
import javax.persistence.*;
import org.hibernate.annotations.Type;

/***
 * Abstract base class for all models that provide two basic functionality:
 * 1) Creation/Update time of the model
 * 2) Optimistic locking via a record version column
 */
@MappedSuperclass
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name = "created", nullable = false)
    private DateTime created;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name = "updated", nullable = false)
    private DateTime updated;

    @Version
    @Column(name = "rvn", nullable = false)
    public Long Version;

    @PrePersist
    protected void onCreate() {
        updated = created = new DateTime();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new DateTime();
    }
}