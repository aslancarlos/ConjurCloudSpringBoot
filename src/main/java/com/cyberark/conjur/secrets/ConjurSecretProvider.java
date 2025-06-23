package com.cyberark.conjur.secrets;

public class ConjurSecretProvider implements SecretProvider {
    public String getSecret(String key) {
        return "secret";
    }
}