package fr.univ.orleans.webservices.liveserialisation.controller;

import fr.univ.orleans.webservices.liveserialisation.modele.Message;
import fr.univ.orleans.webservices.liveserialisation.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;

@RestController
public class ChatController {
    @Autowired
    private Services services;

    @PostMapping("/utilisateurs/{idUser}/messages")
    public ResponseEntity<Message> create(@PathVariable String idUser, @RequestBody Message message) {
        // il n'a pas d'id, juste un texte
        Message messageRec = services.saveMessage(message);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(messageRec.getId()).toUri();

        return ResponseEntity.created(location).body(messageRec);
    }

    @GetMapping("/utilisateurs/{idUser}/messages")
    public ResponseEntity<Collection<Message>>  getAll(@PathVariable String idUser) {
        return ResponseEntity.ok().body(services.findUtilisateurById(idUser).get().getMessages());
    }

}
