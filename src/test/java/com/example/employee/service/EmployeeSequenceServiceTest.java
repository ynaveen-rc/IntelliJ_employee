package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeSequence;
import com.example.employee.repository.EmployeeRepo;
import com.example.employee.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@SpringBootTest
class EmployeeSequenceServiceTest {
    @Autowired
    EmployeeSequenceService employeeSequenceService;
    @MockBean
    EmployeeSequence employeeSequence;
    @MockBean
    GridFsTemplate gridFsTemplate;
    @MockBean
    UserRepo userRepo;
    @MockBean
    EmployeeRepo employeeRepo;
    @MockBean
    EmployeeServiceImpl employeeServiceImpl;
    @MockBean
    MongoOperations mongoOperations;

    @Test
    public void getSequenceNumberTest() {
        Query query = new Query(Criteria.where("id").is(Employee.SEQUENCE_NAME));
        Update update = new Update().inc("seq", 1);
        employeeSequence.setSeq(1);
        employeeSequence.setId(Employee.SEQUENCE_NAME);
        when(mongoOperations.findAndModify(query, update,
                options().returnNew(true).upsert(true),
                EmployeeSequence.class)).thenReturn(employeeSequence);
        assertEquals(1, employeeSequenceService.getSequenceNumber(Employee.SEQUENCE_NAME));
    }
}