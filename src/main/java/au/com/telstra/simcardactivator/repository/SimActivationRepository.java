package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.model.SimActivationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for SIM card and activation information
 */
@Repository
public interface SimActivationRepository extends JpaRepository<SimActivationEntity, Long> { }
