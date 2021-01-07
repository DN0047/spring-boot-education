package com.ws.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String addVod(MultipartFile file);
}
