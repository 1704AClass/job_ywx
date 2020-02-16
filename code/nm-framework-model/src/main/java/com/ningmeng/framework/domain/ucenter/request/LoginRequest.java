package com.ningmeng.framework.domain.ucenter.request;

import com.ningmeng.framework.domain.cms.request.RequestData;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
