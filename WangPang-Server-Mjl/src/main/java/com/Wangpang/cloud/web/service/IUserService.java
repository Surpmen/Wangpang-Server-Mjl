package com.keke.cloud.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.keke.cloud.common.domain.RestResult;
import com.keke.cloud.web.domain.UserBean;

/*用户管理接口*/
public interface IUserService extends IService<UserBean> {
    /*
    * 根据token获取用户
    * */
    UserBean getUserBeanByToken(String token);

    UserBean selectUserByopenid(String openid);

    RestResult<String> registerUser(UserBean userBean);

    UserBean findUserInfoByTelephone(String telephone);
}

