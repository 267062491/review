package com.letv.tbtSps.common.controller;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * WrapController ,wrap simple result return.
 * 
 * @author yuguodong
 * 
 */
public abstract class WrapController {

    /**
     * 成功返回，code = 200
     * 
     * @return
     */
    protected Wrapper<?> ok() {
        return WrapMapper.ok();
    }

    /**
     * 异常返回，code = 500
     * 
     * @return
     */
    protected Wrapper<?> error() {
        return WrapMapper.error();
    }

    /**
     * 参数非法返回，code = 100
     * 
     * @return
     */
    protected Wrapper<?> illegalArgument() {
        return WrapMapper.illegalArgument();
    }
}
