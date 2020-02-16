package com.ningmeng.framework.domain.order.request;

import com.ningmeng.framework.domain.cms.request.RequestData;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class CreateOrderRequest extends RequestData {

    String courseId;

}
