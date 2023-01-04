package com.keke.cloud.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keke.cloud.web.domain.UserBean;

public interface UserMapper extends BaseMapper<UserBean> {
    int insertUser(UserBean userBean);

    int insertUserRole(long userId, long roleId);

}
