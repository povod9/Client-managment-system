package com.fitnessclub.client_managment_system.Entity;

import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionPayment;
import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record Client(
        @Null
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        SubscriptionType subscription,
        SubscriptionPayment payment
) {
}
