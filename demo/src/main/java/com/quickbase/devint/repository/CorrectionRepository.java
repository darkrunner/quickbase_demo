package com.quickbase.devint.repository;

import com.quickbase.devint.model.Correction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionRepository extends CrudRepository<Correction, String> {
}
