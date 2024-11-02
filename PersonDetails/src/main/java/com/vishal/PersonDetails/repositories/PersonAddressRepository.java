package com.vishal.PersonDetails.repositories;

import com.vishal.PersonDetails.entities.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Integer> {
}
