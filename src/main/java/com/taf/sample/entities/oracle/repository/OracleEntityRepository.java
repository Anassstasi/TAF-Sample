package com.taf.sample.entities.oracle.repository;

import com.taf.sample.entities.oracle.entity.OracleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OracleEntityRepository extends JpaRepository<OracleEntity, String> {

    @Query(value = "SELECT NAME FROM TABLE_NAME WHERE number = :number", nativeQuery = true)
    String getNameByNumber(@Param("number") String number);
}
