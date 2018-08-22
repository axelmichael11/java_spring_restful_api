package com.maxhax.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.lang.Long;


@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
