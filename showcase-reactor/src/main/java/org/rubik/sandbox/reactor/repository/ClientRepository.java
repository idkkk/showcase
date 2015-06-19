package org.rubik.sandbox.reactor.repository;

import org.rubik.sandbox.reactor.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
