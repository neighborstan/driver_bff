package tech.saas.driver.common.scaffold.api;

import tech.saas.driver.common.scaffold.IDomain;

public interface IMobileDtoMapper<D extends IDomain, R extends IMobileDto> {

    R toDto(D domain);

    D fromDto(R dto);

    Iterable<R> toDtos(Iterable<D> domains);

    Iterable<D> fromDtos(Iterable<R> dtos);
}
