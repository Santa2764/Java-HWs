package com.example.CoffeeShop_Spring.exception;

import java.io.IOException;

public class PropertyFileException extends IOException {
    public PropertyFileException(String errorMessage) {
        super(errorMessage);
    }
}
