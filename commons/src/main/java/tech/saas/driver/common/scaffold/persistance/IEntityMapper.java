package tech.saas.driver.common.scaffold.persistance;

import tech.saas.driver.common.scaffold.IDomain;

public interface IEntityMapper<D extends IDomain, R extends IEntity> {

    R toEntity(D userDomain);

    D fromEntity(R userEntity);

    Iterable<R> toEntities(Iterable<D> userDomains);

    Iterable<D> fromEntities(Iterable<R> userEntities);
}
