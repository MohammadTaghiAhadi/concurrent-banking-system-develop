package com.ahadi.concurrent_banking_system.config.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=ApiValidationError.class)
public abstract class ApiSubError {
}
