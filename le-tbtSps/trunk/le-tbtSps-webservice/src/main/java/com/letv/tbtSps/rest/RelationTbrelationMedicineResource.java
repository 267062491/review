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
import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.sdk.api.request.RelationTbrelationMedicineRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationTbrelationMedicineResponseDto;
import com.letv.tbtSps.service.RelationTbrelationMedicineService;

/**
 * tbt信息表-相关农药关联表REST服务：提供有关tbt信息表-相关农药关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationTbrelationMedicineResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationTbrelationMedicineService relationTbrelationMedicineService; 

    /**
     * 查询tbt信息表-相关农药关联表信息
     * 
     * @param request
     *            tbt信息表-相关农药关联表请求参数
     * @return tbt信息表-相关农药关联表返回对象
     * 
     */
    @POST
    @Path("/relationTbrelationMedicine/getRelationTbrelationMedicine")
    public Wrapper<?> getRelationTbrelationMedicine(RelationTbrelationMedicineRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationTbrelationMedicine 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationTbrelationMedicine relationTbrelationMedicine = relationTbrelationMedicineService.getRelationTbrelationMedicineById(request.getId());
            RelationTbrelationMedicineResponseDto responseDto = convert(relationTbrelationMedicine);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表-相关农药关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationTbrelationMedicineResponseDto convert(RelationTbrelationMedicine relationTbrelationMedicine) {
        if (null == relationTbrelationMedicine) {
            return null;
        }

        RelationTbrelationMedicineResponseDto relationTbrelationMedicineResponseDto = new RelationTbrelationMedicineResponseDto();
        BeanUtils.copyProperties(relationTbrelationMedicine, relationTbrelationMedicineResponseDto);
        return relationTbrelationMedicineResponseDto;
    }

    // 数据转换
    private List<RelationTbrelationMedicineResponseDto> convertList(List<RelationTbrelationMedicine> relationTbrelationMedicines) {
        if (CollectionUtils.isEmpty(relationTbrelationMedicines)) {
            return null;
        }

        List<RelationTbrelationMedicineResponseDto> list = new ArrayList<RelationTbrelationMedicineResponseDto>(relationTbrelationMedicines.size());
        for (RelationTbrelationMedicine relationTbrelationMedicine : relationTbrelationMedicines) {
            list.add(convert(relationTbrelationMedicine));
        }
        return list;
    } 

}
