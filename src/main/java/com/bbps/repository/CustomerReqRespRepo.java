package com.bbps.repository;

import com.bbps.entity.CustomerRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerReqRespRepo extends JpaRepository<CustomerRequestResponse,Long> {

}
