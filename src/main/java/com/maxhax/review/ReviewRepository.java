package com.maxhax.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.Id;
import java.lang.Long;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>{
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.reviewer?.username == authentication.name")
    void deleteById(@Param("id") Long Id);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #review.reviewer?.username == authentication.name")
    void delete(@Param("review") Review entity);
}
