package com.jiumai.base.biz.controller;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：cz
 * @ Date       ：Created in 2022-11-2217:26
 * @ Description：
 * @ Modified By：
 */
@Api(tags = {"测试工具"})
@RequestMapping("test")
@Slf4j
@RestController
public class TestController {

    @GetMapping("saveEs")
    @ApiOperation(value = "saveEs")
    public ResultDTO<String> saveEs(String str) {
        ResultDTO<String> resultDTO = new ResultDTO<>();
//        esService.
        log.info("GET测试方法，成功了：{}", str);
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, str);
    }

    @GetMapping("getOne")
    @ApiOperation(value = "GET测试方法")
    public ResultDTO<String> getOne(String str) {
        ResultDTO<String> resultDTO = new ResultDTO<>();
        log.info("GET测试方法，成功了：{}", str);
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, str);
    }

    @PostMapping("getJson")
    @ApiOperation(value = "POST测试方法")
    public ResultDTO<JSONObject> getJson(@RequestBody JSONObject jsonObject) {
        ResultDTO<JSONObject> resultDTO = new ResultDTO<>();
        log.info("POST测试方法，成功了：{}", jsonObject);
        return resultDTO.set(ResultCodeEnum.SUCCESS, null, jsonObject);
    }


    public static void main(String[] args) {
        String key = "T@DYgepx&ckmu3nm";
//        String body = "{\"limit\": 10,\"page\": 1}";
        String body = "111";
//        String body = "SYS_292f42b8-8d27-4a0d-bdc7-6fc8c0e9c9f2";
        String enStr = "UbwvEOxxkBE3suLj0t4qD4E/aB2JMbTMVRlu6R4mD9x6Bpjzy7N5tyw9X0hzJ+GsSr4OQdG5jOw/pab4A6h5SMZ0QLNG9f2YPPuDVdMpzf23YhQ1w/2eQLdnL6U5l2RBWbfmnw0LgZ51v8TJhIxfY+H4TwW5lB36nmjKWnQrZMEijV4jt1ivH2/Bo3KwxI0SxQO/A31kGdI9eHxiQ70qPtluovpQf6PehOyqjOy/axfDTVWHxZR8e/K7cP0qpjnNzOdd0HnRcmRZSOvCT/xbWFwf/ZNmPTnX8ERknwu5yTLze78zS0ARy05Y1Uuo9iZ/m6OnIOtQjCy1ok9e+jtpjSejdMLRaVYeY/bVpDSJnxtILTIt1CPOuIR/fg2YNHBk6wqLIf74/SZJtweP7KcSgyDMtG+atvyoZ9Y8UfBE6u2I8uBDnV3QnRB4gNM1gJhAhXdg6iXVgKNxmoLE7Jfl1/1ehfA02V40LLu9zQqO0m+04VIf/yTPJPJsexqQ57+dKs+/+Yi/cc+hZl1NKLdU+9clTIqkjo6VOplc58LCHIo4zSMEBTT5SOfJ0Yr4MNdwS/MJolFGxjv8g8gV8XCw0eVT+cEaAWOlK5anxPBbHYP8azp/g3XjJdwcichbobcX89qtGJwn14S9ckI+lZkWZC3J2D5IAOAEm33gKeQbAvSDIlPZgGR4sGgjlDtWp2u4V/Bz6bIwA33m9LrTIMFHjW8qe11hG0gou+o2N9E7wYYqSk3+0dQHz0Z1+ojri85nyP2Ph2fNms4OQDG3ai+tcYsCS2EvpvPYtfNCLQ9XP/KakfCH2OdR9rslPFSxu5eCYM9ubmGBM5GHMhPAqn3mLKKnSq4EcJf3xSBFFPljk+3KKPmPTTukbPsy2T5+Rhu1bJC+8jXTPni7UghRpNDrIHGILF3tVSjWaseJ8lwZc8JfCqpZ8H+E9vnGi6BAKFxtP0FYG7coyuhO07TiRz+yYNiDiP21gjfED0jyPOVfmbDlIYMmJ0HKVnZKAloGGg6ydpQuai3YJ2mlG+c3qY+krtodAdWpvKOaMSWBOOhqj8DeejC+zOffmTIBy3jjsJm8FZxISOyhclYu8NjRJ525mFDI95Fv92Xv+Xf8LpZ7Cbl6JuMMfZruserhNNqrmUOv6h7f3yDAuM7ZZJSJDYXEiINlLrKtVH1+FQjFTJEW0Vx3QZgu7QNhahmTqHM2RnS9c1sbhW5wEMJXlHFfTeP5+5/N/RFY/q0HNVcQ8+LkeivfdrsTZym+YqBmDsXtbgYB5j1lI13Bv/wQGz9w4iTGeX3v5l3eqKf/PpEYtsRxyRwymt7OedZqYozrlbTP7IZKyGYBmWbuJ+MNBLHZKI4/HrNtd9d5RBhtqr2OaFGZIKx6uSG/XfPaMxlb6jbhqTZ0ycNUkWsT+twyrVeewfR2mA==";
        byte[] bytes = key.getBytes();
        SM4 sm4 = SmUtil.sm4(bytes);
        byte[] encrypt = sm4.encrypt(bytes);
        String encrypt3 = sm4.encryptBase64(body);

        System.out.println("加密后3：" + encrypt3);

        String decrypt2 = sm4.decryptStr(enStr);

        System.out.println("解密后：" + decrypt2);

    }
}
