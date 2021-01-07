package com.ws.serviceucenter.service;

import com.ws.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.serviceucenter.vo.RegisterUserBean;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    boolean registerUser(RegisterUserBean registerUserBean);

    String loginUser(RegisterUserBean registerUserBean);
}
