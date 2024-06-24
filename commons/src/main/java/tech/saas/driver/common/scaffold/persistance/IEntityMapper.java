package tech.saas.driver.common.scaffold.persistance;

import tech.saas.driver.common.scaffold.IDomain;

public interface IEntityMapper<D extends IDomain, R extends IEntity> {

    R toEntity(D userDomain);

    D fromEntity(R userDomain);

    Iterable<R> toEntityList(Iterable<D> userDomain);

    Iterable<D> fromEntityList(Iterable<R> userDomain);
}
