package com.fitnessclub.client_managment_system.Entity;

public record Client(
        Long id,
        String firstName,
        String lastName,
        String email,
        SubscriptionType subscription,
        SubscriptionPayment payment
) {
}
