package com.youShould.hireRyanServer.dto

import org.springframework.http.HttpStatus;

class ClientPayloadDTO<T> {
    HttpStatus status
    String note
    T payload

    ClientPayloadDTO() {}

    ClientPayloadDTO(HttpStatus status, T payload) {
        this.status = status
        this.payload = payload
    }
}