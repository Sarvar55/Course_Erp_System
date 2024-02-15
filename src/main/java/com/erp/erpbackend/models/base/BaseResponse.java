package com.erp.erpbackend.models.base;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.models.enums.exception.ExceptionType;

import com.erp.erpbackend.models.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import static com.erp.erpbackend.models.enums.response.SuccessResponseMessages.SUCCESS;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PROTECTED)
public class BaseResponse<T> {
    T data;
    Meta meta;
    HttpStatus status;

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static final class Meta {
        String key;
        String message;

        public static Meta of(String key, String message) {
            return Meta.builder()
                    .message(message)
                    .key(key)
                    .build();
        }

        public static Meta of(ResponseMessages responseMessages) {
            return of(responseMessages.key(), responseMessages.message());
        }

        public static Meta of(BaseException ex) {
            return ExceptionType.valueOf(ex.getResponseMessage().code()).handle(ex);
        }
    }

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of(SUCCESS))
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse<?> error(BaseException e) {
        return BaseResponse.builder()
                .meta(Meta.of(e))
                .status(e.getResponseMessage().status())
                .build();
    }
}
