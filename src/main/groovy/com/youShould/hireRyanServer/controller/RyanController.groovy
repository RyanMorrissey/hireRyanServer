package com.youShould.hireRyanServer.controller

import com.youShould.hireRyanServer.dto.ClientPayloadDTO
import com.youShould.hireRyanServer.dto.DatabaseTestDTO
import com.youShould.hireRyanServer.model.DatabaseTest
import com.youShould.hireRyanServer.services.DatabaseService
import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RyanController {

    private final AtomicLong counter = new AtomicLong();
    private final static Logger logger = LoggerFactory.getLogger(RyanController.class);

    @Autowired
    private DatabaseService databaseService

    RyanController( ) {
    }

    @GetMapping("/")
    public String testRoot() {
        return "yup it works";
    }

    @GetMapping("/health")
    public String healthCheck() {
        logger.info("Health checked")
        return "Healthy";
    }

    @GetMapping("/getAllDatabaseTestsByCookie/{browserCookie}")
    ClientPayloadDTO<List<DatabaseTestDTO>> getAllDatabaseTestsByCookie(@PathVariable("browserCookie") String browserCookie) {
        List<DatabaseTest> databaseTests = databaseService.getAllDatabaseTestByCookie(browserCookie)
        List<DatabaseTestDTO> databaseTestDTOs = databaseTests.collect { databaseService.convertDatabaseTestToDTO(it) }
        ClientPayloadDTO<List<DatabaseTestDTO>> payload = new ClientPayloadDTO<>(HttpStatus.OK, databaseTestDTOs)
        return payload
    }

    @PostMapping("/createOrUpdateDatabaseTest")
    ClientPayloadDTO<Boolean> createOrUpdateDatabaseTest(@RequestBody DatabaseTestDTO databaseTestDTO) {
        boolean wasCreatedOrUpdated = databaseService.createOrUpdateDatabaseTest(databaseTestDTO)
        ClientPayloadDTO<Boolean> payload = new ClientPayloadDTO<>()
        payload.setPayload(wasCreatedOrUpdated)
        if (wasCreatedOrUpdated) {
            payload.setStatus(HttpStatus.OK)
        } else {
            payload.setStatus(HttpStatus.NOT_FOUND)
        }
        return payload
    }

    @DeleteMapping("/deleteDatabaseTestByIdAndCookie/{id}/{cookie}")
    ClientPayloadDTO<Boolean> deleteDatabaseTestById(
            @PathVariable("id") String id,
            @PathVariable("cookie") String cookie
    ) {
        Boolean isDeleted = databaseService.deleteDatabaseTestByIdAndCookie(id, cookie)
        ClientPayloadDTO<Boolean> payload = new ClientPayloadDTO<>()
        payload.setPayload(isDeleted)
        if (isDeleted) {
            payload.setStatus(HttpStatus.OK)
        } else {
            payload.setStatus(HttpStatus.NOT_FOUND)
        }
        return payload
    }
}