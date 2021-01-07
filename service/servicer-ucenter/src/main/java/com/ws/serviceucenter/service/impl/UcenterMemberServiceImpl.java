package com.ws.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import com.ws.serviceedu.exception.AllException;
import com.ws.serviceedu.exception.MyException;
import com.ws.serviceedu.jvmuntil.JwtUtils;
import com.ws.serviceedu.jvmuntil.MD5;
import com.ws.serviceucenter.entity.UcenterMember;
import com.ws.serviceucenter.mapper.UcenterMemberMapper;
import com.ws.serviceucenter.openfein.Open;
import com.ws.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.serviceucenter.vo.RegisterUserBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate redisTemplate;

@Autowired

private Open open;
    @Override
    public boolean registerUser(RegisterUserBean registerUserBean) {
        String mobile = registerUserBean.getMobile();
        QueryWrapper<UcenterMember> qw = new QueryWrapper<>();
        qw.eq("mobile",mobile);
        Integer integer = baseMapper.selectCount(qw);
        if (integer>0){
            new MyException("手机号已注册");
        }
        int code = registerUserBean.getCode();

        int o = (int) redisTemplate.opsForValue().get("code" + mobile);
        if (code!=o){
            new MyException("验证码错误");
        }

        String password = registerUserBean.getPassword();
        String encrypt = MD5.encrypt(password);
        registerUserBean.setPassword(encrypt);
        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerUserBean,ucenterMember);
        int insert = baseMapper.insert(ucenterMember);

        if (insert==1){
            return true;
        }

        return false;
    }

    /**
     * <p>
     *     登录service
     * </p>
     * @param registerUserBean
     * @return
     */
    @Override
    public String loginUser(RegisterUserBean registerUserBean) {

        QueryWrapper<UcenterMember> qw = new QueryWrapper<>();
        qw.eq("password",registerUserBean.getPassword());
        String mobile = registerUserBean.getMobile();
        String encrypt = MD5.encrypt(mobile);//md5加密查询
        qw.eq("mobile",encrypt);
        UcenterMember ucenterMember = baseMapper.selectOne(qw);
        if (ucenterMember!=null){//判断查询是否登录成功
            String id = registerUserBean.getId();
            String nickname = registerUserBean.getNickname();

            String jwtToken = JwtUtils.getJwtToken(id, nickname);
            //token放入缓存
            redisTemplate.opsForValue().set(registerUserBean.getMobile(),jwtToken);
            return jwtToken;
        }

        return null;
    }
}
