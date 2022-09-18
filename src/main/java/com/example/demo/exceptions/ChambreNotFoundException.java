package com.example.demo.exceptions;

public class ChambreNotFoundException extends RuntimeException {

    public ChambreNotFoundException(Long id) {
        super("La chambre " + id + " n'a pas été trouvée ou n'est pas réservable");
    }
}
