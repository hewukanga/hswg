package com.jiumai.base.common.core.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.jiumai.base.common.core.dto.LoginOperator;
import com.jiumai.base.common.core.dto.TokenDTO;
import com.jiumai.base.common.core.exception.BizException;
import com.jiumai.base.common.core.utils.CommonConstant;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.jiumai.base.common.core.enums.ResultCodeEnum.ERROR_AUTH_TIME_OUT;

@Slf4j
@Service(value = "redisCurrentOperator")
public class RedisCurrentOperatorServiceImpl implements CurrentOperatorService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final int timeout = 12;

    public static final String REFRESH_TOKEN = "refresh:token:";
    public static final String REQUEST_TOKEN = "request:token:";
    public static final String REFRESH_FOR_REQUEST = "refresh:for:request:";
    public static final String REQUEST_FOR_REFRESH = "request:for:refresh:";
    // 分钟
    public static final Long REQUEST_TOKEN_EXIST_TIME = 15L;
    // 小时
    public static final Long REFRESH_TOKEN_EXIST_TIME = 24L;

    @Override
    public LoginOperator getCurentOperatorByToken(String token) throws Exception {
        String opInfo = this.stringRedisTemplate.opsForValue().get(REQUEST_TOKEN + token);
        if (opInfo == null) {
            log.error("根据token从Redis获取用户信息失败，用户TOKEN：{}", token);
            return null; // 无效token
        }
        LoginOperator op = JSON.parseObject(opInfo, LoginOperator.class);

        return op;
    }

    @Override
    public String genToken(LoginOperator op) throws Exception {
        return null;
//        String token = PRE_TOKEN + UUID.randomUUID().toString();
//        String opInfo = JSON.toJSONString(op);
//        this.stringRedisTemplate.opsForValue().set(REDIS_PRE_TOKEN + token, opInfo, timeout, TimeUnit.HOURS);
//        this.stringRedisTemplate.opsForValue().set(REDIS_PRE_ID + token, op.getOpId() + "", timeout, TimeUnit.HOURS);
//        this.stringRedisTemplate.opsForValue().set(REDIS_PRE_LOGIN_TOKEN + op.getOpId(), token, timeout, TimeUnit.HOURS);
//        return token;
    }

    @Override
    public TokenDTO genTokenByNewWay(LoginOperator op) {
        String opInfo = JSON.toJSONString(op);
        String requestToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        long nowTime = System.currentTimeMillis();
        this.stringRedisTemplate.opsForValue().set(REQUEST_TOKEN + requestToken, opInfo, REQUEST_TOKEN_EXIST_TIME, TimeUnit.MINUTES);
        this.stringRedisTemplate.opsForValue().set(REFRESH_TOKEN + refreshToken, opInfo, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        this.stringRedisTemplate.opsForValue().set(REQUEST_FOR_REFRESH + requestToken, refreshToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        this.stringRedisTemplate.opsForValue().set(REFRESH_FOR_REQUEST + refreshToken, requestToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setRefreshToken(refreshToken);
        tokenDTO.setRefreshTokenExpirationTime(nowTime + REFRESH_TOKEN_EXIST_TIME * 60 * 60 * 1000L);
        tokenDTO.setRequestToken(requestToken);
        tokenDTO.setRequestTokenExpirationTime(nowTime + REQUEST_TOKEN_EXIST_TIME * 60 * 1000L);
        return tokenDTO;
    }

    @Override
    public void updateRedisToken(String token, LoginOperator op) {
        if (op == null) {
            return;
        }
        String refreshToken = stringRedisTemplate.opsForValue().get(REQUEST_FOR_REFRESH + token);
        String opJson = JSON.toJSONString(op);
        stringRedisTemplate.opsForValue().set(REQUEST_TOKEN + token, opJson);
        stringRedisTemplate.opsForValue().set(REFRESH_TOKEN + refreshToken, opJson);
    }

    @Override
    public void refToken(String token) {
        try {
            long mils = System.currentTimeMillis();
            long mi = mils / 1000 / 60;
            if (mi % 2 == 0) { //减少刷新次数
                String refreshToken = this.stringRedisTemplate.opsForValue().get(REQUEST_FOR_REFRESH + token);
                this.stringRedisTemplate.expire(REFRESH_TOKEN + refreshToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
                this.stringRedisTemplate.expire(REFRESH_FOR_REQUEST + refreshToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
                this.stringRedisTemplate.expire(REQUEST_FOR_REFRESH + token, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
            }
        } catch (Exception ex) {
            log.error("刷新Token失败：" + token);
        }
    }

    @Override
    public Long getOpIdFromToken(String token) throws Exception {
        return this.getCurentOperatorByToken(token).getOpId();
    }

    @Override
    public String getTokenFromOpId(Long opId) {
//        return this.stringRedisTemplate.opsForValue().get(REDIS_PRE_LOGIN_TOKEN + opId);
        return null;
    }

    @Override
    public void destroyToken(String token) {
        try {
            String refreshToken = stringRedisTemplate.opsForValue().get(REQUEST_FOR_REFRESH + token);

            this.stringRedisTemplate.delete(REQUEST_TOKEN + token);
            this.stringRedisTemplate.delete(REQUEST_FOR_REFRESH + token);
            this.stringRedisTemplate.delete(REFRESH_TOKEN + refreshToken);
            this.stringRedisTemplate.delete(REFRESH_FOR_REQUEST + refreshToken);
        } catch (Exception ex) {
            log.error("注销redis Toke 失败：", ex);
        }

    }

    @Override
    public TokenDTO refreshTokenByNewWay(String refreshToken) {
        //判断refreshToken是否有效
        String opInfo = this.stringRedisTemplate.opsForValue().get(REFRESH_TOKEN + refreshToken);
        if (StringUtils.isEmpty(opInfo)) {
            throw new BizException(ERROR_AUTH_TIME_OUT);
        }
        //删除老的requestToken
        String oldRequestToken = this.stringRedisTemplate.opsForValue().get(REFRESH_FOR_REQUEST + refreshToken);
        if (StringUtils.isEmpty(oldRequestToken)) {
            throw new BizException(ERROR_AUTH_TIME_OUT);
        }
        String value = this.stringRedisTemplate.opsForValue().get(REQUEST_TOKEN + oldRequestToken);
        if (!StringUtils.isEmpty(value)) {
            this.stringRedisTemplate.delete(REQUEST_TOKEN + oldRequestToken);
        }
        //生产新的requestToken
        long nowTime = System.currentTimeMillis();
        String requestToken = UUID.randomUUID().toString();
        this.stringRedisTemplate.opsForValue().set(REQUEST_TOKEN + requestToken, opInfo, REQUEST_TOKEN_EXIST_TIME, TimeUnit.MINUTES);
        this.stringRedisTemplate.opsForValue().set(REFRESH_TOKEN + refreshToken, opInfo, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        this.stringRedisTemplate.opsForValue().set(REFRESH_FOR_REQUEST + refreshToken, requestToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        this.stringRedisTemplate.opsForValue().set(REQUEST_FOR_REFRESH + requestToken, refreshToken, REFRESH_TOKEN_EXIST_TIME, TimeUnit.HOURS);
        //返回token信息
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setRequestToken(requestToken);
        tokenDTO.setRequestTokenExpirationTime(nowTime + REQUEST_TOKEN_EXIST_TIME * 60 * 1000L);
        tokenDTO.setRefreshToken(refreshToken);
        tokenDTO.setRefreshTokenExpirationTime(nowTime + REFRESH_TOKEN_EXIST_TIME * 60 * 60 * 1000L);
        return tokenDTO;
    }

    @Override
    public List<Object> getRoleIdsByToken(String token) {
        //取出token对应的角色id
        String roleIdStr = stringRedisTemplate.opsForValue().get(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_ROLE_KEY, token));
        if (CommonFuntions.isNotEmptyObject(roleIdStr)) {
            List<String> roleIds = JSONUtil.toList(roleIdStr, String.class);
            List<Object> roleIdInterfaceValues = stringRedisTemplate.opsForHash().multiGet(CommonConstant.INTERFACE_ROLE_BASE_KEY, new ArrayList<Object>(roleIds));
            return roleIdInterfaceValues.stream().filter(Objects::nonNull).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Object> getResourceIdsByToken(String token) {
        String resourceIdStr = stringRedisTemplate.opsForValue().get(String.format(CommonConstant.INTERFACE_AUTH_TOKEN_REL_RESOURCE_KEY, token));
        if (CommonFuntions.isNotEmptyObject(resourceIdStr)) {
            List<String> resourceIds = JSONUtil.toList(resourceIdStr, String.class);
            List<Object> resourceIdInterfaceValues = stringRedisTemplate.opsForHash().multiGet(CommonConstant.INTERFACE_RESOURCE_BASE_KEY, new ArrayList<Object>(resourceIds));
            return resourceIdInterfaceValues.stream().filter(Objects::nonNull).collect(Collectors.toList());
        }
        return null;
    }

}
