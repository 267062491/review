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
import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.sdk.api.request.RelationMedicineRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationMedicineResponseDto;
import com.letv.tbtSps.service.RelationMedicineService;

/**
 * 相关农药REST服务：提供有关相关农药的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationMedicineResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationMedicineService relationMedicineService; 

    /**
     * 查询相关农药信息
     * 
     * @param request
     *            相关农药请求参数
     * @return 相关农药返回对象
     * 
     */
    @POST
    @Path("/relationMedicine/getRelationMedicine")
    public Wrapper<?> getRelationMedicine(RelationMedicineRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationMedicine 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationMedicine relationMedicine = relationMedicineService.getRelationMedicineById(request.getId());
            RelationMedicineResponseDto responseDto = convert(relationMedicine);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询相关农药数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationMedicineResponseDto convert(RelationMedicine relationMedicine) {
        if (null == relationMedicine) {
            return null;
        }

        RelationMedicineResponseDto relationMedicineResponseDto = new RelationMedicineResponseDto();
        BeanUtils.copyProperties(relationMedicine, relationMedicineResponseDto);
        return relationMedicineResponseDto;
    }

    // 数据转换
    private List<RelationMedicineResponseDto> convertList(List<RelationMedicine> relationMedicines) {
        if (CollectionUtils.isEmpty(relationMedicines)) {
            return null;
        }

        List<RelationMedicineResponseDto> list = new ArrayList<RelationMedicineResponseDto>(relationMedicines.size());
        for (RelationMedicine relationMedicine : relationMedicines) {
            list.add(convert(relationMedicine));
        }
        return list;
    } 

}
