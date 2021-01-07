package com.ws.serviceorder.service.impl;

import com.ws.serviceorder.entity.TOrder;
import com.ws.serviceorder.mapper.TOrderMapper;
import com.ws.serviceorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-30
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
