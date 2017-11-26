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
import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.sdk.api.request.RelationMedicineProductRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationMedicineProductResponseDto;
import com.letv.tbtSps.service.RelationMedicineProductService;

/**
 * 相关农产品REST服务：提供有关相关农产品的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationMedicineProductResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationMedicineProductService relationMedicineProductService; 

    /**
     * 查询相关农产品信息
     * 
     * @param request
     *            相关农产品请求参数
     * @return 相关农产品返回对象
     * 
     */
    @POST
    @Path("/relationMedicineProduct/getRelationMedicineProduct")
    public Wrapper<?> getRelationMedicineProduct(RelationMedicineProductRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationMedicineProduct 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationMedicineProduct relationMedicineProduct = relationMedicineProductService.getRelationMedicineProductById(request.getId());
            RelationMedicineProductResponseDto responseDto = convert(relationMedicineProduct);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询相关农产品数据异常", e);
            return WrapMapper.error();
        }
    }

    @POST
    @Path("/relationMedicineProduct/saveRelationMedicineProduct")
    public Wrapper<?> saveRelationMedicineProduct(RelationMedicineProductRequest request) {
        if (null == request || null == request.getCaCode()) {
            this.logger.error("getRelationMedicineProduct 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationMedicineProduct relationMedicineProduct = relationMedicineProductService.getRelationMedicineProductById(request.getId());
            RelationMedicineProductResponseDto responseDto = convert(relationMedicineProduct);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询相关农产品数据异常", e);
            return WrapMapper.error();
        }
    }

    // 数据转换
    private RelationMedicineProductResponseDto convert(RelationMedicineProduct relationMedicineProduct) {
        if (null == relationMedicineProduct) {
            return null;
        }

        RelationMedicineProductResponseDto relationMedicineProductResponseDto = new RelationMedicineProductResponseDto();
        BeanUtils.copyProperties(relationMedicineProduct, relationMedicineProductResponseDto);
        return relationMedicineProductResponseDto;
    }

    // 数据转换
    private List<RelationMedicineProductResponseDto> convertList(List<RelationMedicineProduct> relationMedicineProducts) {
        if (CollectionUtils.isEmpty(relationMedicineProducts)) {
            return null;
        }

        List<RelationMedicineProductResponseDto> list = new ArrayList<RelationMedicineProductResponseDto>(relationMedicineProducts.size());
        for (RelationMedicineProduct relationMedicineProduct : relationMedicineProducts) {
            list.add(convert(relationMedicineProduct));
        }
        return list;
    } 

}
