package com.youShould.hireRyanServer.services

import com.youShould.hireRyanServer.model.DatabaseTest
import com.youShould.hireRyanServer.model.DatabaseTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DatabaseService {

    @Autowired
    private DatabaseTestRepository databaseTestRepository

    List<DatabaseTest> getAllDatabaseTest() {
        return databaseTestRepository.findAll()
    }

    DatabaseTest createOrUpdateDatabaseTest(DatabaseTest databaseTest) {
        if (databaseTest.id == 0) { // create flow
            databaseTest.id = null // null it so we can generate a new serial record
            databaseTestRepository.save(databaseTest)
        } else { // update flow
            DatabaseTest existingDatabaseTest = databaseTestRepository.findById(databaseTest.id).orElse(null)
            if (existingDatabaseTest == null) {
                return null
            } else {
                existingDatabaseTest.value = databaseTest.value
                existingDatabaseTest.dateUpdated = new Date()
                databaseTestRepository.save(existingDatabaseTest)
            }
        }
        return databaseTest
    }

    boolean deleteDatabaseTest(Long id) {
        DatabaseTest existingDatabaseTest = databaseTestRepository.findById(id).orElse(null)
        if (existingDatabaseTest == null) {
            return false
        } else {
            existingDatabaseTest.isDeleted = true
            // don't wanna push this until I test it, but ive held off pushing for a while now
            //databaseTestRepository.save(existingDatabaseTest)
        }
        return true
    }
}
