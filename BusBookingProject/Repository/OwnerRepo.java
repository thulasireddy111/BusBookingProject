package com.bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.entities.Owner;
@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long>{
	@Query("select o from Owner o left join fetch o.bus where o.id=:id")
	Owner getOwnerBusDetails(Long id);
//	
//	@Query("select o from Owner o left join fetch o.driver where o.id=:id")
//	Owner getOwnerAndDriver(Long id);
}
