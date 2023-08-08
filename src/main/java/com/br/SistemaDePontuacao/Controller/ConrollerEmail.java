package com.br.SistemaDePontuacao.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.SistemaDePontuacao.services.EmailService;
@RestController
@CrossOrigin("*")
public class ConrollerEmail {
        @Autowired
    private EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(ConrollerEmail.class);

@PostMapping("/email")
public ResponseEntity<String> enviarEmail(@RequestParam("destinatario") String destinatario,
                                           @RequestParam("assunto") String assunto,
                                           @RequestParam("corpo") String corpo) {
    try {
        emailService.enviarEmail(destinatario, assunto, corpo);
        return ResponseEntity.ok("Email enviado com sucesso!");
    } catch (Exception e) {
        // Log the error message
        logger.error("Error sending email: " + e.getMessage(), e);
        // Return an error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Erro ao enviar email: " + e.getMessage());
    }
}
}
