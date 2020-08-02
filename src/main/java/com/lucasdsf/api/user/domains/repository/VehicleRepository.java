package com.lucasdsf.api.user.domains.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasdsf.api.user.domains.entity.User;
import com.lucasdsf.api.user.domains.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	Optional<Vehicle> findByLicensePlate(String licensePlate);
	List<Vehicle> findAllByUser(User user);
}
