package com.musula.drones.mapper;

import java.util.List;

public interface BaseMapper<E, D> {

  E toEntity(D d);

  D toDto(E e);

  List<E> toEntity(List<D> d);

  List<D> toDto(List<E> e);
}
