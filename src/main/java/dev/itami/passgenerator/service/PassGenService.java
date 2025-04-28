package dev.itami.passgenerator.service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PassGenService {
    public String generatePassword(int length, boolean useNumbers, boolean useUppercase, boolean useSymbols) {
        if (length < 1) {
            throw new IllegalArgumentException("La longitud debe ser al menos 1.");
        }

        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbolChars = "!@#$%^&*()-_=+<>?/";

        StringBuilder availableChars = new StringBuilder(lowercaseChars);
        SecureRandom random = new SecureRandom();
        List<Character> password = new ArrayList<>();

        // Garantizar al menos un carácter de cada tipo seleccionado
        if (useNumbers) {
            availableChars.append(numberChars);
            password.add(numberChars.charAt(random.nextInt(numberChars.length())));
        }
        if (useUppercase) {
            availableChars.append(uppercaseChars);
            password.add(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        }
        if (useSymbols) {
            availableChars.append(symbolChars);
            password.add(symbolChars.charAt(random.nextInt(symbolChars.length())));
        }

        // Llenar el resto de la contraseña
        for (int i = password.size(); i < length; i++) {
            password.add(availableChars.charAt(random.nextInt(availableChars.length())));
        }

        // Mezclar los caracteres para evitar patrones predecibles
        Collections.shuffle(password, random);

        // Convertir la lista en una cadena
        StringBuilder finalPassword = new StringBuilder();
        for (char c : password) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }
}
