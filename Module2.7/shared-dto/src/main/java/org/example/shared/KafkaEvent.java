package org.example.shared;

/**
 * @param operation Операция, например "CREATE" или "DELETE".
 * @param email Электронная почта, связанная с событием.
 */
public record KafkaEvent(String operation, String email) {}