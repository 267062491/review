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
import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.sdk.api.request.RelationSpsRelationMedicineRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsRelationMedicineResponseDto;
import com.letv.tbtSps.service.RelationSpsRelationMedicineService;

/**
 * sps信息表-相关农药关联表REST服务：提供有关sps信息表-相关农药关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsRelationMedicineResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsRelationMedicineService relationSpsRelationMedicineService; 

    /**
     * 查询sps信息表-相关农药关联表信息
     * 
     * @param request
     *            sps信息表-相关农药关联表请求参数
     * @return sps信息表-相关农药关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsRelationMedicine/getRelationSpsRelationMedicine")
    public Wrapper<?> getRelationSpsRelationMedicine(RelationSpsRelationMedicineRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsRelationMedicine 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsRelationMedicine relationSpsRelationMedicine = relationSpsRelationMedicineService.getRelationSpsRelationMedicineById(request.getId());
            RelationSpsRelationMedicineResponseDto responseDto = convert(relationSpsRelationMedicine);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-相关农药关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsRelationMedicineResponseDto convert(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        if (null == relationSpsRelationMedicine) {
            return null;
        }

        RelationSpsRelationMedicineResponseDto relationSpsRelationMedicineResponseDto = new RelationSpsRelationMedicineResponseDto();
        BeanUtils.copyProperties(relationSpsRelationMedicine, relationSpsRelationMedicineResponseDto);
        return relationSpsRelationMedicineResponseDto;
    }

    // 数据转换
    private List<RelationSpsRelationMedicineResponseDto> convertList(List<RelationSpsRelationMedicine> relationSpsRelationMedicines) {
        if (CollectionUtils.isEmpty(relationSpsRelationMedicines)) {
            return null;
        }

        List<RelationSpsRelationMedicineResponseDto> list = new ArrayList<RelationSpsRelationMedicineResponseDto>(relationSpsRelationMedicines.size());
        for (RelationSpsRelationMedicine relationSpsRelationMedicine : relationSpsRelationMedicines) {
            list.add(convert(relationSpsRelationMedicine));
        }
        return list;
    } 

}
