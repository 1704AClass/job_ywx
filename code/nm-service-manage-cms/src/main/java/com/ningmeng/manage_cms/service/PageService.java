package com.ningmeng.manage_cms.service;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_cms.dao.CmsPageRepository;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by 岳万鑫 on 2020/2/12.
 */

@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage=new CmsPage();
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        page=page-1;
        Pageable pageable=new PageRequest(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult<CmsPage> cmsPageQueryResult = new QueryResult<>();
        cmsPageQueryResult.setList(all.getContent());
        cmsPageQueryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,cmsPageQueryResult);
    }

    public CmsPageResult add(CmsPage cmsPage) {
        //校验页面面是否存在
        CmsPage cmsPage1=cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if(cmsPage1==null){
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);
            //返回结果
            CmsPageResult cmsPageResult=new CmsPageResult(CommonCode.SUCCESS,cmsPage);
            return cmsPageResult;
        }
        return  new CmsPageResult(CommonCode.FAIL,null);
    }

    //根据ID查询页面
    public CmsPage getById(String id) {
        Optional<CmsPage> optional=cmsPageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        //返回空
        return null;
    }
    //跟新页面信息
    public CmsPageResult edit(String id, CmsPage cmsPage) {
        //根据id查询
        CmsPage one=this.getById(id);
        if(one!=null){
            //更新模板id
            one.setTemplateId(cmsPage.getTemplateId());
            //跟新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //跟新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //跟新页面名称
            one.setPageName(cmsPage.getPageName());
            //跟新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //跟新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //执行跟新
            ;CmsPage save=cmsPageRepository.save(one);
            if(save!=null){
                //返回成功
                CmsPageResult cmsPageResult=new CmsPageResult(CommonCode.SUCCESS,save);
            }
        }
        //反悔失败
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    //删除页面
    public ResponseResult delete(String id) {
        CmsPage one =this.getById(id);
        if(one!=null){
            //删除页面
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
