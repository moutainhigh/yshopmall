package co.yixiang.modules.activity.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponQueryCriteria;
import co.yixiang.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-11-09
*/
@Api(tags = "优惠券管理")
@RestController
@RequestMapping("api")
public class YxStoreCouponController {

    @Autowired
    private YxStoreCouponService yxStoreCouponService;

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCoupon")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPON_ALL','YXSTORECOUPON_SELECT')")
    public ResponseEntity getYxStoreCoupons(YxStoreCouponQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxStoreCoupon")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPON_ALL','YXSTORECOUPON_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCoupon resources){
        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        return new ResponseEntity(yxStoreCouponService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxStoreCoupon")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPON_ALL','YXSTORECOUPON_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCoupon resources){
        yxStoreCouponService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCoupon/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPON_ALL','YXSTORECOUPON_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        YxStoreCoupon resources = new YxStoreCoupon();
        resources.setId(id);
        resources.setIsDel(1);
        yxStoreCouponService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }
}