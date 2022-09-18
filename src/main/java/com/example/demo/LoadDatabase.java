package com.example.demo;

import com.example.demo.model.Chambre;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ChambreRepository;
import com.example.demo.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ChambreRepository repository, ReservationRepository rRepository) {
        return args -> {
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));
            log.info("Donnée : " + repository.save(new Chambre("standard", 60, 2)));

            log.info("Donnée : " + repository.save(new Chambre("king size", 100, 4)));
            log.info("Donnée : " + repository.save(new Chambre("king size", 100, 4)));
            log.info("Donnée : " + repository.save(new Chambre("king size", 100, 4)));
            log.info("Donnée : " + repository.save(new Chambre("king size", 100, 4)));

            log.info("Donnée : " + repository.save(new Chambre("suite de luxe", 200, 6)));
        };
    }
}
