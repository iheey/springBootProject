package org.com.controller;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import org.com.dao.UserMapper;
import org.com.entity.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class UserController {
    @Value("${SignName}")
    private String signName;
    @Value("${TemplateCode}")
    private String templateCode;
    @Resource
    private UserMapper userMapper;
    @Resource
    private Client client;
    @Resource
    private StringRedisTemplate template;
    @GetMapping("/getcode")
    public RespBean getCode(String phone) throws Exception {
        Integer integer = userMapper.vilidataPhoneCount(phone);
        if (integer>0){
            Integer code=new Random().nextInt(900000)+100000;
            SendSmsRequest smsRequest=new SendSmsRequest()
                    .setSignName(signName)
                    .setTemplateCode(templateCode)
                    .setPhoneNumbers(phone)
                    .setTemplateParam("{code:"+code.toString()+"}");
            client.sendSms(smsRequest);
            template.opsForValue().setIfAbsent(phone,code.toString(),2, TimeUnit.MINUTES);
            return RespBean.accuess("验证码发送成功",code);
        }else{
            return RespBean.error("手机号码未注册");
        }

    }
    @PostMapping("/login")
    public RespBean login(String phone,String code){
        String s = template.opsForValue().get(phone);
        if (!StringUtils.isEmpty(s)&&s.equals(code)){
            return RespBean.accuess("登录成功");
        }else{
            return RespBean.error("验证码错误");
        }

    }
}
