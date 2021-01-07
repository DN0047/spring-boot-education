package com.ws.vod.service.imlp;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.ws.serviceedu.R1;
import com.ws.vod.service.VodPlayService;
import com.ws.vod.until.VodBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VodPlayServiceimlp implements VodPlayService {
    @Override
    public String selectPlay(String vodId) {
        DefaultAcsClient client = null;
        try {
            client = initVodClient(VodBean.ACCESS_KEY_ID, VodBean.ACCESS_KEY_SECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

        try {



            request.setVideoId(vodId);
            response=   client.getAcsResponse(request);




        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        //返回播放凭证

        return response.getPlayAuth();
    }

    @Override
    public R1 delectPlay(List<String> listID) {


        try {






            DefaultAcsClient  client = initVodClient(VodBean.ACCESS_KEY_ID, VodBean.ACCESS_KEY_SECRET);


            DeleteVideoRequest request = new DeleteVideoRequest();

            String join = StringUtils.join(listID.toArray(), ",");

            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(join);
            DeleteVideoResponse  response = client.getAcsResponse(request);





        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            return R1.error();
        }






            return R1.ok();




    }

    @Override
    public R1 dePlay(String vodId) {


        try {

            DefaultAcsClient   client = initVodClient(VodBean.ACCESS_KEY_ID, VodBean.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
           /* String join = StringUtils.join(listID.toArray(), ",");*/
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(vodId);
            DeleteVideoResponse   response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            return R1.error();
        }





            return R1.ok();
        }




    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }



}
