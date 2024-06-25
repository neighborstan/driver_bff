package tech.saas.driver.common.scaffold.api;

import tech.saas.driver.common.scaffold.IDomain;

public interface IMobileDtoMapper<D extends IDomain, R extends IMobileDto> {

    R toDto(D userDomain);

    D fromDto(R userDto);

    Iterable<R> toDtos(Iterable<D> userDomains);

    Iterable<D> fromDtos(Iterable<R> userDtos);
}
