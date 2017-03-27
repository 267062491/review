package com.letv.tbtSps.service;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.domain.query.UserQuery;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-28
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public interface IndexService {
    public LetvResponse<User> login(UserQuery userQuery);

    /**
     * 根据用户编码查询用户拥有的资源
     * @param roleResourceQuery
     * @return
     */
    public LetvResponse<List<TreeDomain>> getLoginUserResource(RoleResourceQuery roleResourceQuery) ;

    /**
     * 根据cookieValue获取redis中存储的用户登录信息
     * @param cookieValue
     * @return
     */
    public LetvResponse<String> getLoginUserInfoFromRedis(String cookieValue);
}
