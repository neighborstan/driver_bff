package tech.saas.driver.common.scaffold.persistence;

import tech.saas.driver.common.scaffold.IDomain;

public interface IEntityMapper<D extends IDomain, R extends IEntity> {

    R toEntity(D domain);

    D fromEntity(R entity);

    Iterable<R> toEntities(Iterable<D> domains);

    Iterable<D> fromEntities(Iterable<R> entities);
}
