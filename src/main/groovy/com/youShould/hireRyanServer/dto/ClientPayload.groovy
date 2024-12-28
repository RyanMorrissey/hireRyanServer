package com.youShould.hireRyanServer.dto

import org.springframework.http.HttpStatus;

class ClientPayload<T> {
    HttpStatus status
    String note
    T payload

    ClientPayload() {}

    ClientPayload(HttpStatus status, T payload) {
        this.status = status
        this.payload = payload
    }
}