package com.fitnessclub.client_managment_system.Entity;

import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionPayment;
import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionType;
import jakarta.persistence.*;

@Table(name = "clients")
@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "subscription")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscription;

    @Column(name = "payment")
    @Enumerated(EnumType.STRING)
    private SubscriptionPayment payment;


    public ClientEntity() {
    }

    public ClientEntity(Long id, String firstName, String lastName, String email, SubscriptionType subscription, SubscriptionPayment payment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subscription = subscription;
        this.payment = payment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubscriptionType getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionType subscription) {
        this.subscription = subscription;
    }

    public SubscriptionPayment getPayment() {
        return payment;
    }

    public void setPayment(SubscriptionPayment payment) {
        this.payment = payment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
