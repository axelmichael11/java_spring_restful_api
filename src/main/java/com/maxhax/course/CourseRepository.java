package com.maxhax.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Id;
import java.lang.Long;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @RestResource(rel = "title-contains", path = "containsTitle")
    Page<Course> findByTitleContaining(@Param("title") String title, Pageable page);


}
