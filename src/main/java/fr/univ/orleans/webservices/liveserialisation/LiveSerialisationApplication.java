package fr.univ.orleans.webservices.liveserialisation;

import fr.univ.orleans.webservices.liveserialisation.modele.Message;
import fr.univ.orleans.webservices.liveserialisation.modele.Utilisateur;
import fr.univ.orleans.webservices.liveserialisation.service.Services;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiveSerialisationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveSerialisationApplication.class, args);
    }

    @Bean
    CommandLineRunner initialisation(Services services) {
        return args -> {
            Utilisateur fred = new Utilisateur("fred","fred", false);
            services.saveUtilisateur(fred);
            services.saveUtilisateur(new Utilisateur("admin","admin", true));
            services.saveMessage(new Message(null,"hello world !",fred));
        };
    }
}
