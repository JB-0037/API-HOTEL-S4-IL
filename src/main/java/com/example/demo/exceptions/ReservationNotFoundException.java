package com.example.demo.exceptions;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("La reservation " + id + " n'a pas été trouvée !");
    }
}
