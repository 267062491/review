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
import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.sdk.api.request.RelationSpsRelationMedicineProductRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsRelationMedicineProductResponseDto;
import com.letv.tbtSps.service.RelationSpsRelationMedicineProductService;

/**
 * sps信息表-相关农产品关联表REST服务：提供有关sps信息表-相关农产品关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsRelationMedicineProductResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsRelationMedicineProductService relationSpsRelationMedicineProductService; 

    /**
     * 查询sps信息表-相关农产品关联表信息
     * 
     * @param request
     *            sps信息表-相关农产品关联表请求参数
     * @return sps信息表-相关农产品关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsRelationMedicineProduct/getRelationSpsRelationMedicineProduct")
    public Wrapper<?> getRelationSpsRelationMedicineProduct(RelationSpsRelationMedicineProductRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsRelationMedicineProduct 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = relationSpsRelationMedicineProductService.getRelationSpsRelationMedicineProductById(request.getId());
            RelationSpsRelationMedicineProductResponseDto responseDto = convert(relationSpsRelationMedicineProduct);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-相关农产品关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsRelationMedicineProductResponseDto convert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        if (null == relationSpsRelationMedicineProduct) {
            return null;
        }

        RelationSpsRelationMedicineProductResponseDto relationSpsRelationMedicineProductResponseDto = new RelationSpsRelationMedicineProductResponseDto();
        BeanUtils.copyProperties(relationSpsRelationMedicineProduct, relationSpsRelationMedicineProductResponseDto);
        return relationSpsRelationMedicineProductResponseDto;
    }

    // 数据转换
    private List<RelationSpsRelationMedicineProductResponseDto> convertList(List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProducts) {
        if (CollectionUtils.isEmpty(relationSpsRelationMedicineProducts)) {
            return null;
        }

        List<RelationSpsRelationMedicineProductResponseDto> list = new ArrayList<RelationSpsRelationMedicineProductResponseDto>(relationSpsRelationMedicineProducts.size());
        for (RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct : relationSpsRelationMedicineProducts) {
            list.add(convert(relationSpsRelationMedicineProduct));
        }
        return list;
    } 

}
