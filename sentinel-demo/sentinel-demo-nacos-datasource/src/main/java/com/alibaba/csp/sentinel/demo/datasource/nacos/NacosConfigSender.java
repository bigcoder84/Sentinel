/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
package com.alibaba.csp.sentinel.demo.datasource.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import java.util.Properties;

/**
 * Nacos config sender for demo.
 *
 * @author Eric Zhao
 */
public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String groupId = "SENTINEL_GROUP";
        final String dataId = "sentinel-demo-flow-rules";
        // 创建ConfigService实例
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "10.10.10.12:8848");
        // 指定namespace
        properties.put(PropertyKeyConst.NAMESPACE, "SENTINEL");
        final String rule = "[\n"
            + "  {\n"
            + "    \"resource\": \"GET:/user/getById\",\n"
            + "    \"controlBehavior\": 0,\n"
            + "    \"count\": 1,\n"
            + "    \"grade\": 1,\n"
            + "    \"limitApp\": \"default\",\n"
            + "    \"strategy\": 0\n"
            + "  }\n"
            + "]";
        ConfigService configService = NacosFactory.createConfigService(properties);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}
