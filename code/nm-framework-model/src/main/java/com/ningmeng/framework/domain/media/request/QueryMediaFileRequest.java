package com.ningmeng.framework.domain.media.request;

import com.ningmeng.framework.domain.cms.request.RequestData;
import lombok.Data;

@Data
public class QueryMediaFileRequest extends RequestData {

    private String fileOriginalName;
    private String processStatus;
    private String tag;
}
