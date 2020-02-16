package com.ningmeng.api.controller;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 岳万鑫 on 2020/2/11.
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口、提供的增删改查")
public interface CmsPageControllerApi {
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({ @ApiImplicitParam(name="page",value="页 码",required=true,paramType="path",dataType="int"),@ApiImplicitParam(name="size",value="每页记录 数",required=true,paramType="path",dataType="int")})
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);
    @ApiOperation("通过ID查询页面")
    public  CmsPage findById(String id);
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id,CmsPage cmsPage);
    @ApiOperation("通过ID删除页面")
    public ResponseResult delete(String id );
}
