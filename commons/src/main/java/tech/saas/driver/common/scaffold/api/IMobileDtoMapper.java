package tech.saas.driver.common.scaffold.api;

import tech.saas.driver.common.scaffold.IDomain;

public interface IMobileDtoMapper<D extends IDomain, R extends IMobileDto> {

    R toDto(D userDomain);

    D fromDto(R userDomain);

    Iterable<R> toDtoList(Iterable<D> userDomain);

    Iterable<D> fromDtoList(Iterable<R> userDomain);
}
