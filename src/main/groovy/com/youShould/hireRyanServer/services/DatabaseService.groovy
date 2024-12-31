package com.youShould.hireRyanServer.services

import com.youShould.hireRyanServer.model.DatabaseTest
import com.youShould.hireRyanServer.model.DatabaseTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DatabaseService {

    @Autowired
    private DatabaseTestRepository databaseTestRepository

    List<DatabaseTest> getAllDatabaseTestByCookie(String cookie) {
        List<DatabaseTest> allDatabaseTests = databaseTestRepository.findAll().sort { it.id }
        return allDatabaseTests.findAll { !it.isDeleted && it.browserCookie == cookie }
    }

    DatabaseTest createOrUpdateDatabaseTest(DatabaseTest databaseTest) {
        if (databaseTest.id == 0) { // create flow
            databaseTest.id = null // null it so we can generate a new serial record
            databaseTestRepository.save(databaseTest)
        } else { // update flow
            DatabaseTest existingDatabaseTest = databaseTestRepository.findById(databaseTest.id).orElse(null)
            if (existingDatabaseTest == null || existingDatabaseTest.browserCookie != databaseTest.browserCookie) {
                return null
            } else {
                existingDatabaseTest.value = databaseTest.value
                existingDatabaseTest.dateUpdated = new Date()
                databaseTestRepository.save(existingDatabaseTest)
            }
        }
        return databaseTest
    }

    boolean deleteDatabaseTestByIdAndCookie(String id, String cookie) {
        DatabaseTest existingDatabaseTest = databaseTestRepository.findById(id.toInteger()).orElse(null)
        if (existingDatabaseTest == null || existingDatabaseTest.browserCookie != cookie) {
            return false
        } else {
            existingDatabaseTest.isDeleted = true
            databaseTestRepository.save(existingDatabaseTest)
        }
        return true
    }
}
