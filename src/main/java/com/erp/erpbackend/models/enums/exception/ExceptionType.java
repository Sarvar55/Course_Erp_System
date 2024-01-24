package com.erp.erpbackend.models.enums.exception;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.exception.types.NotFoundExceptionType;
import com.erp.erpbackend.models.base.BaseResponse;

public enum ExceptionType {
    NOT_FOUND {
        @Override
        public BaseResponse.Meta handle(BaseException e) {
            NotFoundExceptionType notFoundData = e.getNotFoundData();
            return BaseResponse.Meta.of(
                    String.format(e.getResponseMessage().key(), notFoundData.getTarget().toLowerCase()),
                    String.format(e.getResponseMessage().message(), notFoundData.getTarget(), notFoundData.getFields().toString())
            );
        }
    },
    DEFAULT {
        @Override
        public BaseResponse.Meta handle(BaseException e) {
            return BaseResponse.Meta.of(e.getResponseMessage());
        }
    };

    public abstract BaseResponse.Meta handle(BaseException e);
}
