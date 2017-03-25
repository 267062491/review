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
import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.sdk.api.request.RelationTbrelationMedicineProductRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationTbrelationMedicineProductResponseDto;
import com.letv.tbtSps.service.RelationTbrelationMedicineProductService;

/**
 * tbt信息表-相关农产品关联表REST服务：提供有关tbt信息表-相关农产品关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationTbrelationMedicineProductResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationTbrelationMedicineProductService relationTbrelationMedicineProductService; 

    /**
     * 查询tbt信息表-相关农产品关联表信息
     * 
     * @param request
     *            tbt信息表-相关农产品关联表请求参数
     * @return tbt信息表-相关农产品关联表返回对象
     * 
     */
    @POST
    @Path("/relationTbrelationMedicineProduct/getRelationTbrelationMedicineProduct")
    public Wrapper<?> getRelationTbrelationMedicineProduct(RelationTbrelationMedicineProductRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationTbrelationMedicineProduct 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationTbrelationMedicineProduct relationTbrelationMedicineProduct = relationTbrelationMedicineProductService.getRelationTbrelationMedicineProductById(request.getId());
            RelationTbrelationMedicineProductResponseDto responseDto = convert(relationTbrelationMedicineProduct);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表-相关农产品关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationTbrelationMedicineProductResponseDto convert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        if (null == relationTbrelationMedicineProduct) {
            return null;
        }

        RelationTbrelationMedicineProductResponseDto relationTbrelationMedicineProductResponseDto = new RelationTbrelationMedicineProductResponseDto();
        BeanUtils.copyProperties(relationTbrelationMedicineProduct, relationTbrelationMedicineProductResponseDto);
        return relationTbrelationMedicineProductResponseDto;
    }

    // 数据转换
    private List<RelationTbrelationMedicineProductResponseDto> convertList(List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProducts) {
        if (CollectionUtils.isEmpty(relationTbrelationMedicineProducts)) {
            return null;
        }

        List<RelationTbrelationMedicineProductResponseDto> list = new ArrayList<RelationTbrelationMedicineProductResponseDto>(relationTbrelationMedicineProducts.size());
        for (RelationTbrelationMedicineProduct relationTbrelationMedicineProduct : relationTbrelationMedicineProducts) {
            list.add(convert(relationTbrelationMedicineProduct));
        }
        return list;
    } 

}
