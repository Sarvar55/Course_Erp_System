package com.erp.erpbackend.services.student;

import com.erp.erpbackend.models.payload.student.StudentPayload;

public interface StudentBusinessService {
    void addStudent(StudentPayload studentPayload);

    void addStudentToGroup(long studentId, long groupId);
}
