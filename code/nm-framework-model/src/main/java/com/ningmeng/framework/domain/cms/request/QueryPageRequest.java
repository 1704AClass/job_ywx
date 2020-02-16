package com.ningmeng.framework.domain.cms.request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Created by 岳万鑫 on 2020/2/11.
 */
@Data
public class QueryPageRequest extends RequestData{
    @ApiModelProperty("站点id")
    private String siteId;
    @ApiModelProperty("页面ID")
    private String pageId;
    @ApiModelProperty("页面名称")
    private String pageName;
    @ApiModelProperty("页面别名")
    private String pageAliase;
    @ApiModelProperty("模版id")
    private String templateId;
}
