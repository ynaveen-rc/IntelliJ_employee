package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeSequence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@SpringBootTest
class EmployeeSequenceServiceTest {
    @Autowired
    EmployeeSequenceService employeeSequenceService;
    @MockBean
    EmployeeSequence employeeSequence;
    @MockBean
    MongoOperations mongoOperations;

    @Test
    public void getSequenceNumberTest() {
//        Query query = new Query(Criteria.where("id").is(Employee.SEQUENCE_NAME));
//        Update update = new Update().inc("seq", 1);
//        when(mongoOperations.findAndModify(query, update,
//                options().returnNew(true).upsert(true),
//                EmployeeSequence.class)).thenReturn(new EmployeeSequence());
//        assertEquals(employeeSequence.getSeq(), employeeSequenceService.getSequenceNumber(Employee.SEQUENCE_NAME));
//        assertThat(employeeSequence.equals(employeeSequenceService.getSequenceNumber(Employee.SEQUENCE_NAME)));
    }
}