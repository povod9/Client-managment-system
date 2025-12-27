package com.fitnessclub.client_managment_system.Entity;

import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionPayment;
import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionType;

public record Client(
        Long id,
        String firstName,
        String lastName,
        String email,
        SubscriptionType subscription,
        SubscriptionPayment payment
) {
}
