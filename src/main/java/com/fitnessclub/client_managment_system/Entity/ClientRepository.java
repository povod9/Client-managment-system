package com.fitnessclub.client_managment_system.Entity;

import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {


    @Modifying
    @Query("""
    update ClientEntity c
    set c.payment = :payment
    where c.id = :id
    """)
    void setPayment(
            @Param("id") Long id,
            @Param("payment") SubscriptionPayment payment
    );

}
