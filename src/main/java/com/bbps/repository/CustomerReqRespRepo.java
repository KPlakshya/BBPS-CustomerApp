package com.bbps.repository;

import com.bbps.entity.CustomerRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerReqRespRepo extends JpaRepository<CustomerRequestResponse,Long> {

    Optional<CustomerRequestResponse> findById(Long id);

}
