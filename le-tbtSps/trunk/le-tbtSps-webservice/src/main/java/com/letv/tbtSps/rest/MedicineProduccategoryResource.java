package com.letv.tbtSps.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.sdk.api.request.MedicineProduccategoryRequest;
import com.letv.tbtSps.sdk.api.response.dto.MedicineProduccategoryResponseDto;
import com.letv.tbtSps.service.MedicineProduccategoryService;

/**
 * 农产品分类REST服务：提供有关农产品分类的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class MedicineProduccategoryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private MedicineProduccategoryService medicineProduccategoryService; 

    /**
     * 查询农产品分类信息
     * 
     * @param request
     *            农产品分类请求参数
     * @return 农产品分类返回对象
     * 
     */
    @POST
    @Path("/medicineProduccategory/getMedicineProduccategory")
    public Wrapper<?> getMedicineProduccategory(MedicineProduccategoryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getMedicineProduccategory 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            MedicineProduccategory medicineProduccategory = medicineProduccategoryService.getMedicineProduccategoryById(request.getId());
            MedicineProduccategoryResponseDto responseDto = convert(medicineProduccategory);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询农产品分类数据异常", e);
            return WrapMapper.error();
        }
    }

    @POST
    @Path("/medicineProduccategory/saveMedicineProduccategory")
    public Wrapper<?> saveMedicineProduccategory(MedicineProduccategoryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getMedicineProduccategory 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            MedicineProduccategory medicineProduccategory = medicineProduccategoryService.getMedicineProduccategoryById(request.getId());
            MedicineProduccategoryResponseDto responseDto = convert(medicineProduccategory);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询农产品分类数据异常", e);
            return WrapMapper.error();
        }
    }

    // 数据转换
    private MedicineProduccategoryResponseDto convert(MedicineProduccategory medicineProduccategory) {
        if (null == medicineProduccategory) {
            return null;
        }

        MedicineProduccategoryResponseDto medicineProduccategoryResponseDto = new MedicineProduccategoryResponseDto();
        BeanUtils.copyProperties(medicineProduccategory, medicineProduccategoryResponseDto);
        return medicineProduccategoryResponseDto;
    }

    // 数据转换
    private List<MedicineProduccategoryResponseDto> convertList(List<MedicineProduccategory> medicineProduccategorys) {
        if (CollectionUtils.isEmpty(medicineProduccategorys)) {
            return null;
        }

        List<MedicineProduccategoryResponseDto> list = new ArrayList<MedicineProduccategoryResponseDto>(medicineProduccategorys.size());
        for (MedicineProduccategory medicineProduccategory : medicineProduccategorys) {
            list.add(convert(medicineProduccategory));
        }
        return list;
    } 

}
