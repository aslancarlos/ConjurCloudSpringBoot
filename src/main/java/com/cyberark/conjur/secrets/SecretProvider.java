package com.cyberark.conjur.secrets;

public interface SecretProvider {
    String getSecret(String key);
}