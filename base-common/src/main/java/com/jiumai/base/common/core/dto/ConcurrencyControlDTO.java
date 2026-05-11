/*
 * ConcurrencyControlDTO.java Created on 2017年4月28日 下午5:34:57
 * Copyright 2018 Hangzhou JiuMai Network Technology Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiumai.base.common.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * http请求并发控制对象
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
@Data
public class ConcurrencyControlDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private AtomicLong concurrencyNum = new AtomicLong(0L);// 并发数
    private AtomicLong maxConcurrencyNum = new AtomicLong(0L);// 最大并发数
    private AtomicLong totalReqNum = new AtomicLong(0L);// 总请求数
    private AtomicLong successReqNum = new AtomicLong(0L);// 成功数
    private AtomicLong failReqNum = new AtomicLong(0L);// 失败数
    private AtomicLong concurrencyPeak = new AtomicLong(0L);// 并发数峰值
    private AtomicLong reqTime = new AtomicLong(0L);// 请求总耗时

    private Map<String, ConcurrencyControlDTO> reqMap;


}
