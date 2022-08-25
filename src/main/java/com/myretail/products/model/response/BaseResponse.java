package com.myretail.products.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private String responseCode;
    private String responseMessage;
}
