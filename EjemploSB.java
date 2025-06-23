// Ejemplo completo de integración con CyberArk Conjur Cloud usando Spring Boot

// 1. Interfaz estándar para inyección de secretos
package com.example.secrets;

public interface SecretProvider {
    String getSecret(String key);
}

// 2. Implementación utilizando el SDK de Conjur
package com.example.secrets;

import com.cyberark.conjur.api.Conjur;

public class ConjurSecretProvider implements SecretProvider {
    private final Conjur conjur;

    public ConjurSecretProvider() {
        this.conjur = new Conjur();
    }

    @Override
    public String getSecret(String key) {
        return conjur.variables().retrieveSecret(key);
    }
}

// 3. Configuración del bean en Spring para inyección de dependencias
package com.example.config;

import com.example.secrets.ConjurSecretProvider;
import com.example.secrets.SecretProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretConfig {

    @Bean
    public SecretProvider secretProvider() {
        return new ConjurSecretProvider();
    }
}

// 4. Servicio que utiliza el proveedor de secretos
package com.example.service;

import com.example.secrets.SecretProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final SecretProvider secretProvider;

    @Autowired
    public DatabaseService(SecretProvider secretProvider) {
        this.secretProvider = secretProvider;
    }

    public void conectar() {
        // Obtener secretos desde Conjur
        String usuario = secretProvider.getSecret("dev/db/username");
        String clave = secretProvider.getSecret("dev/db/password");

        // Simulación de uso del secreto (por ejemplo, para conectarse a una base de datos)
        System.out.println("Conectando con usuario: " + usuario);
    }
}

// 5. Controlador REST para probar la integración
package com.example.controller;

import com.example.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/test")
    public String testConnection() {
        databaseService.conectar();
        return "OK";
    }
}

// 6. Clase principal de la aplicación Spring Boot
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConjurApp {
    public static void main(String[] args) {
        SpringApplication.run(ConjurApp.class, args);
    }
}
