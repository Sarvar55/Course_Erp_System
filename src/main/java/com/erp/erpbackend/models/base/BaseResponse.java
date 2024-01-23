package com.erp.erpbackend.models.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    T data;
    String message;
    HttpStatus status;

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder().status(HttpStatus.OK)
                .message("Success").data(data).build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }
}
