package com.ws.vod.service;

import com.ws.serviceedu.R1;

import java.util.List;

public interface VodPlayService {
    String selectPlay(String vodId);

    R1 delectPlay(List<String> listID);

    R1 dePlay(String vodId);
}
