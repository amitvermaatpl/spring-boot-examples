package com.spring.jpa.demojdbc.optimisticlock;

/**
 * Represents an entity that is eligible for optimistic locking.
 */
public interface VersionedEntity {
    public Long getId();

    public Long getVersion();

    public void setVersion(Long version);

    public String getTableName();
}
