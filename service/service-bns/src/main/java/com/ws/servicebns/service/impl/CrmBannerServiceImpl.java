package com.ws.servicebns.service.impl;

import com.ws.servicebns.entity.CrmBanner;
import com.ws.servicebns.mapper.CrmBannerMapper;
import com.ws.servicebns.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-26
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner", key = "'selectIndexList'")
    public List<CrmBanner> listBanner() {
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
