# szyl-server-wy 项目 AGENTS.md

## 一、项目概览

**项目名称**: szyl-server-wy（数字院落 - 物业管理系统后端）

**组织**: 杭州九麦网络科技有限公司 (com.jiumai)

**技术栈**:
- Java 1.8 + Spring Boot 2.7.14
- MyBatis-Plus 3.5.3.1（ORM）
- MySQL 8.0.33（主数据库）
- Redis（缓存 / Session 共享）
- Elasticsearch（搜索引擎）
- Quartz（定时任务调度）
- JWT (jjwt 0.11.5)（无状态鉴权）
- Knife4j 4.1.0（API 文档，对应 Swagger 2）
- Hutool 5.8.20 / Fastjson2 2.0.35 / Commons-Lang3
- EasyPoi 4.4.0（Excel 导入导出）
- 阿里云 OSS（对象存储）
- Maven 多模块（父子 POM，依赖外置瘦身打包）

**项目定位**: 面向社区 / 物业管理的综合业务后台，涵盖住户管理、物业报修、安防巡逻、保洁监管、停车管理、积分商城、党建管理等数十个业务模块。

---

## 二、模块结构

```
szyl-server-wy/
├── base-runner      # 启动模块（Spring Boot 入口），依赖 base-biz
├── base-biz         # 业务模块（所有具体业务 Controller/Service/Mapper/Entity）
├── base-sm          # 系统管理模块（组织、角色、用户、权限、字典、定时任务管理）
├── base-common      # 公共基础设施（拦截器、过滤器、JWT、异常处理、工具类、Quartz 封装等）
```

**依赖关系**:
```
base-runner → base-biz → base-sm → base-common
```

---

## 三、各模块详细说明

### 3.1 base-common —— 公共基础设施

| 子包 | 职责 |
|------|------|
| `core/annotation` | 自定义注解（`@OpLog` 操作日志、`@Quartz` 定时任务标记） |
| `core/aspectj` | AOP 切面（`OpLogAspect` 操作日志自动记录） |
| `core/constant` | 公共常量（如 `LoginPlatform` 登录平台标识） |
| `core/controller` | 通用控制器（`CommFileUploadController` 文件上传） |
| `core/dto` | 通用 DTO（`ResultDTO<T>` 统一响应体、`LoginOperator`、`TokenDTO`、分页查询等） |
| `core/entity` | 通用实体（操作日志、定时任务相关） |
| `core/enums` | 通用枚举（`ResultCodeEnum` 响应码、`BusinessTypeEnum`、`YesOrNoEnum` 等） |
| `core/exception` | `BizException` 业务异常 + `GlobalExceptionHandler` 全局异常处理 |
| `core/jwt` | JWT 令牌生成与解析（`JwtHelper` + `JwtConfig`） |
| `core/manager` | 异步任务管理器（`AsyncManager` + `AsyncFactory`） |
| `core/mapper` | 公共 Mapper（操作日志、定时任务） |
| `core/quartz` | Quartz 定时任务完整封装（配置、初始化、Job 监听、Trigger CRUD） |
| `core/query` | 通用查询入参（`BasePage`、`PageParam`） |
| `core/security` | 安全工具（`MD5Util`） |
| `core/service` | 当前操作人服务接口与 Redis 实现 |
| `core/session` | `OperatorUtil` 操作人上下文工具 |
| `core/Thread` | 线程池配置 |
| `core/utils` | **工具类库**（40+ 工具类，详见下文） |
| `filter` | 全局过滤器（`GlobalDataFilter` + 请求/响应包装器） |
| `interceptor` | 拦截器（`TokenInterceptor` 鉴权、跨域、Knife4j 认证） |
| `mybatis` | MyBatis 相关配置 |
| `redis` | Redis 配置 |

**核心工具类**（`core/utils/`）:
- `DateUtils`、`LocalDateTimeUtils` — 日期时间
- `StringUtils`、`ConvertUtils`、`CharsetUtils` — 字符串/编码
- `HttpUtils`、`ServletUtils`、`IpUtils`、`CookieUtils` — Web 相关
- `SpringUtils` — Spring 上下文持有
- `ExcelUtils` — Excel 操作
- `OSSUtils` — 阿里云 OSS
- `FileUtils` — 文件操作
- `MoneyUtils` — 金额处理
- `TreeUtils` — 树形结构
- `PageUtils` — 分页
- `RandomUtils`、`BusiCodeGenerate` — 编码生成
- `ExpressionEvaluator` — 表达式引擎（JEXL）
- `email/SendMailUtils`、`email/ReceiveMailUtils` — 邮件收发
- `qrCode/QrCodeUtil` — 二维码生成
- `password/PasswordCheckUtil` — 密码强度校验

### 3.2 base-sm —— 系统管理模块

| 包 | 核心功能 |
|----|----------|
| `controller` | 组织管理、角色管理、操作人管理、资源管理、接口配置、字典管理、定时任务管理、表配置 |
| `entity` | `SmOrganization`（组织）、`SmRole`（角色）、`SmOperator`（操作人/用户）、`SmResource`（资源/菜单）、`SmArea`（区域）、`SmQuartz`（定时任务）、`RelRoleInterface`（角色-接口关联）等 |
| `service` | 对应业务逻辑，含 RBAC 权限模型实现 |
| `enums` | 系统管理枚举 |
| `utils` | `DictionaryUtils`（字典工具）、`ObjConvertUtils` |
| `annotation/aspect` | SM 模块自定义注解与切面 |

**RBAC 权限模型**:
- 操作人 (Operator) → 角色 (Role) → 资源 (Resource) / 接口 (Interface)
- 支持多对多关联：`SmRelOpRole`、`SmRelOpOrg`、`SmRelOpArea`、`SmRelOrgRole`、`SmRelResRole`、`SmRelRoleInterface`
- Token 拦截器会校验 URI 对应的角色/资源权限

### 3.3 base-biz —— 业务模块

**业务分类**:

| 业务领域 | Controller / Entity 前缀 | 功能说明 |
|----------|--------------------------|----------|
| **住户管理** | `User`、`UserCar`、`UserHouseProperty`、`FamilyCardInfo`、`HouseProperty` | 住户信息、车辆绑定、房产绑定、户口卡 |
| **物业缴费** | `PayMonthlyRule`、`PayMonthlyUserInfo`、`UtilityBill`、`RenewalBill`、`OilCard`、`OilCardRecord` | 月租规则、水电费账单、续费账单、油卡管理 |
| **报修服务** | `OneRepair`、`RepairType`、`ServiceOrder`、`ServiceOrderEvaluate`、`ServiceOrderFlow` | 一键报修、维修类型、服务工单、评价、流程 |
| **安防巡逻** | `PatrolList`、`PatrolListPoint`、`PatrolPoint`、`PatrolRecord`、`PatrolRuleList`、`PatrolRulePoint`、`PatrolRuleSuitUser` | 巡逻清单、巡逻点位、巡逻记录、巡逻规则 |
| **保洁管理** | `CleaningArea`、`CleaningSituation`、`CleaningClockInRecord`、`CleaningFeedback` | 保洁区域、保洁情况、保洁打卡、保洁反馈 |
| **停车管理** | `ParkingLot`、`ParkingLeaveOrder`、`TemporaryParking` | 停车场、离场订单、临时停车 |
| **考勤管理** | `AttendanceRecord`、`AttendanceRule`、`AttendanceRuleDate`、`AttendanceRuleOp` | 考勤记录、考勤规则、考勤日期、考勤人 |
| **巡检考核** | `ExamineRule`、`ExamineTeam`、`ExamineTeamDetails` | 考核规则、考核小组、考核明细 |
| **装修管理** | `DecorationReport` | 装修报备 |
| **积分商城** | `IntegralConfig`、`IntegralGoods`、`IntegralGoodsExchange`、`IntegralRecord`、`NewIntegralConfig`、`NewIntegralRecord` | 积分配置、积分商品、兑换、积分记录 |
| **邻里商城** | `NeighborhoodGoods`、`NeighborhoodGoodsRecord`、`Order`、`Shop` | 邻里商品、交易记录、订单、店铺 |
| **党建管理** | `PartyMember`、`PartyMemberServiceCenter` | 党员信息、党群服务中心 |
| **网格管理** | `GridPersonnel`、`PropertyArea`、`PropertyWorkstation` | 网格人员、物业区域、物业工作站 |
| **设备管理** | `Device`、`Camera`、`CamerasLocation`、`PropertyDevice`、`PropertyDeviceRecord`、`MqSource`、`MqttConfig` | 设备、摄像头、物业设备、MQTT 配置 |
| **流程审批** | `Process`、`ProcessAudit`、`ProcessAuditConfig`、`ProcessRecord`、`Proceedings`、`OvertimeProcess`、`OvertimeProcessConfig`、`OvertimeProcessFlow` | 流程定义、审批、加班流程 |
| **通知消息** | `Message`、`MessageNotice`、`Notice`、`PrivateChat`、`PrivateChatRelation` | 消息、通知公告、私聊 |
| **文档管理** | `DocumentManagement`、`PictureDocument`、`PictureDocumentFile` | 文件档案、图片文档 |
| **社区活动** | `Activity`、`ActivitySignUp` | 社区活动、活动报名 |
| **意见反馈** | `UserOpinion`、`LeavingMessage`、`Help` | 用户意见、留言、帮助 |
| **执法管理** | `LawEnforcementInstrumentManage`、`RectificationForm`、`RectificationItem` | 执法文书、整改单、整改项 |
| **其他** | `PostAddress`、`PostConfig`、`PostInfo`、`PostPersonInfo`、`ScheduleRecord`、`WelcomeCheck`、`WelcomeCheckFile`、`SkilledCraftsmen`、`KeepGreen`、`ReplacementRecord`、`GeneralTransaction`、`MiniClickStatistics`、`MiniClickStatisticsDetails`、`ModuleOpenAreaConfig` | 岗位管理、排班、欢迎检查、工匠、绿化养护、配件更换、通用流水、小程序点击统计 |

### 3.4 base-runner —— 启动模块

- `com.jiumai.base.Application` — Spring Boot 启动入口
- `com.jiumai.base.WebMvcConfiguration` — Web MVC 配置（拦截器注册）
- `com.jiumai.base.listener.EnumListener` — 枚举初始化监听器
- 启动类扫描包：`com.jiumai.base`
- Mapper 扫描：`com.jiumai.base.*.mapper` 和 `com.jiumai.base.*.*.mapper`

---

## 四、核心架构设计

### 4.1 统一响应体
- `ResultDTO<T>` 包含 `success`、`errCode`、`errDesc`、`result` 四个字段
- 遵循 `ResultCodeEnum` 错误码体系（10000 成功, 20000 失败, 30000+ 系统错误, 40000+ 业务错误）

### 4.2 鉴权流程
1. 客户端请求携带 `token`（Header 或参数）
2. `TokenInterceptor` 拦截所有请求（放行 auth/login/autoLogin/public/refresh/getSysInfo 及 OPTIONS）
3. 通过 `CurrentOperatorService`（默认 Redis 实现）获取当前操作人
4. 校验接口权限（角色关联接口 / 资源关联接口）
5. opId=1 为超级管理员，跳过鉴权

### 4.3 当前操作人服务
- 接口：`CurrentOperatorService`
- 实现：`RedisCurrentOperatorServiceImpl`（默认）、`DbCurrentOperator`（备选）
- 通过 `spring.current.currentOperatorServiceName` 配置切换

### 4.4 定时任务
- 基于 Quartz + JDBC JobStore
- `QuartzInit` 在应用启动后扫描 `@Quartz` 注解并初始化 Job
- `TriggerService` 提供动态 CRUD 管理
- 任务执行记录写入 `SmQuartzRunRecord`

### 4.5 操作日志
- `@OpLog` 注解 + `OpLogAspect` 切面自动记录
- 日志数据写入 `op_log` 表

### 4.6 文件上传
- 统一入口：`CommFileUploadController`
- 支持上传到阿里云 OSS

---

## 五、配置说明

| 配置项 | 说明 |
|--------|------|
| `server.port=8009` | 服务端口 |
| `server.servlet.context-path=/base-server` | 上下文路径 |
| `spring.profiles.active=dev` | 当前激活环境（dev/test/prod） |
| `spring.jwt.*` | JWT 签名密钥、过期时间（默认 86400000ms = 24h） |
| `spring.current.currentOperatorServiceName` | 操作人服务实现选择 |
| `mybatis-plus.mapper-locations=classpath*:/mapper/*/*.xml` | Mapper XML 位置 |
| `knife4j.basic.*` | API 文档认证 |
| `spring.redis.*` | Redis 连接（Lettuce） |

**多环境配置文件**:
- `application-dev.properties` — 开发环境
- `application-test.properties` — 测试环境
- `application-prod.properties` — 生产环境
- `application-example.properties` — 配置示例

---

## 六、开发规范

### 6.1 新增业务模块
1. 在 `base-biz` 下按标准分层创建包（controller / service / entity / mapper / dto / query / vo / enums）
2. Controller 统一返回 `ResultDTO<T>`
3. Service 遵循接口 + 实现类分离（`XxxService` / `XxxServiceImpl`）
4. Entity 使用 MyBatis-Plus 注解（`@TableName`、`@TableId`、`@TableLogic` 逻辑删除字段 `is_deleted`）

### 6.2 注意
- **Java 版本为 1.8**，不允许使用 JDK 9+ 特性
- 所有文件使用 **UTF-8** 编码
- 注释使用**简体中文**
- 项目采用**依赖外置瘦身打包**，启动命令：`java -jar -Dloader.path=./lib base-runner.jar`
- 新增模块时需在 `base-runner/pom.xml` 的 `spring-boot-maven-plugin` 中添加 `include`

### 6.3 禁止事项
- 禁止将业务代码写入 `base-sm`（SM 只放系统管理通用功能）
- 禁止使用 `System.out.println`，必须用 SLF4J
- 禁止空 catch 块
- 禁止在 Controller 中写大段 try-catch
- 禁止 Entity 直接暴露给前端

### 6.4 权限
- 新增接口后，需在"接口管理"中配置接口资源，并关联到对应角色，否则普通用户无权访问

---

## 七、打包与部署

```bash
# 打包（跳过测试）
mvn clean package -DskipTests

# 启动（依赖外置模式）
java -jar -Dloader.path=./lib base-runner.jar

# 如果是完整 jar 包
java -jar base-runner.jar
```

- API 文档地址：`http://host:8009/base-server/doc.html`
- Knife4j 认证：admin / Jm@1207++

---

## 八、常见问题

1. **找不到 Mapper XML** → 确认 XML 放在 `resources/mapper/biz/` 或 `resources/mapper/sm/` 下
2. **Token 鉴权失败** → 检查接口路径是否在 `TokenInterceptor` 的白名单中，或是否配置了接口角色关联
3. **定时任务不执行** → 检查 `@Quartz` 注解是否完整配置，Quartz 表是否存在
4. **文件上传失败** → 检查 OSS 配置及 `spring.servlet.multipart.*` 参数
