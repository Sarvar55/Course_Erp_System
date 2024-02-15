package com.erp.erpbackend.exception;


import com.erp.erpbackend.exception.types.NotFoundExceptionType;
import com.erp.erpbackend.models.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static com.erp.erpbackend.models.enums.response.ErrorResponseMessages.NOT_FOUND;
import static com.erp.erpbackend.models.enums.response.ErrorResponseMessages.UNEXPECTED;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {

    ResponseMessages responseMessage;
    NotFoundExceptionType notFoundData;

    @Override
    public String getMessage() {
        return responseMessage.message();
    }

    public static BaseException of(ResponseMessages messages) {
        return BaseException.builder().responseMessage(messages).build();
    }

    public static BaseException unexpected() {
        return of(UNEXPECTED);
    }

    public static BaseException notFound(String target, String field, String value) {
        return BaseException.builder().responseMessage(NOT_FOUND)
                .notFoundData(NotFoundExceptionType.of(target, Map.of(field, value)))
                .build();
    }


}
