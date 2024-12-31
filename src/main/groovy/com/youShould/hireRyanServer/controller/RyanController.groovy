package com.youShould.hireRyanServer.controller

import com.youShould.hireRyanServer.dto.ClientPayload
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

    @GetMapping("/getAllDatabaseTests")
    ClientPayload<List<DatabaseTest>> getAllDatabaseTests() {
        List<DatabaseTest> databaseTests = databaseService.getAllDatabaseTest()
        ClientPayload<List<DatabaseTest>> payload = new ClientPayload<>(HttpStatus.OK, databaseTests)
        return payload
    }

    @PostMapping("/createOrUpdateDatabaseTest")
    ClientPayload<DatabaseTest> createOrUpdateDatabaseTest(@RequestBody DatabaseTest databaseTest) {
        DatabaseTest newDatabaseTest = databaseService.createOrUpdateDatabaseTest(databaseTest)
        ClientPayload<DatabaseTest> payload = new ClientPayload<>()
        payload.setPayload(newDatabaseTest)
        if (newDatabaseTest != null) {
            payload.setStatus(HttpStatus.OK)
        } else {
            payload.setStatus(HttpStatus.NOT_FOUND)
        }
        return payload
    }

    @DeleteMapping("/deleteDatabaseTestById/{id}")
    ClientPayload<Boolean> deleteDatabaseTestById(@PathVariable("id") Long id) {
        Boolean isDeleted = databaseService.deleteDatabaseTest(id)
        ClientPayload<Boolean> payload = new ClientPayload<>()
        payload.setPayload(isDeleted)
        if (isDeleted) {
            payload.setStatus(HttpStatus.OK)
        } else {
            payload.setStatus(HttpStatus.NOT_FOUND)
        }
        return payload
    }
}