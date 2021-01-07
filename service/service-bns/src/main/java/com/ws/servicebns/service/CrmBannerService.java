package com.ws.servicebns.service;

import com.ws.servicebns.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-26
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> listBanner();
}
