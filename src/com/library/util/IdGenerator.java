package com.library.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private String prefix;
    private AtomicInteger counter;

    public IdGenerator(String prefix) {
        this.prefix = prefix;
        this.counter = new AtomicInteger(1);
    }

    public String generateId() {
        int id = counter.getAndIncrement();
        return prefix + String.format("%04d", id);
    }
}