# blind-date 相亲社交应用

## 项目简介

`blind-date` 是一款基于 **uni-app** 开发的相亲社交应用，专注于提供安全、高效的相亲匹配服务。项目采用模块化设计，支持多端（小程序、H5、App）部署，涵盖用户注册、个人信息管理、智能推荐、实时聊天、好友验证等核心功能，助力用户快速建立信任关系，开启相亲之旅。

## 项目结构

plaintext:

```plaintext
blind-date/
├── uniCloud/                  # 云服务（腾讯云，未关联，可扩展后端逻辑）
├── .hbuilderx/                # HBuilderX 配置文件
├── common/                    # 公共工具
│   ├── help.js                # 辅助函数
│   └── request.js             # 网络请求封装（统一接口调用）
├── components/                # 公共组件（如引导页、评论组件，可复用）
├── js_sdk/                    # 第三方 SDK 集成（如即时通讯、支付等）
├── pages/                     # 业务页面
│   ├── aboutOur/              # 关于我们（品牌介绍、隐私政策）
│   ├── chat/                  # 实时聊天（支持文字、图片消息，敏感词过滤）
│   ├── detailsAndAddFriend/   # 好友详情与添加（查看资料、发起验证）
│   ├── footer/                # 底部导航栏（全局导航，如首页、消息、个人中心）
│   ├── friendList/            # 好友列表（管理已添加好友，分组展示）
│   ├── index/                 # 首页（推荐卡片、快捷入口）
│   ├── myself/                # 个人中心（设置、收藏、互动记录）
│   ├── personalInformation/   # 个人信息（填写择偶条件、上传认证资料）
│   ├── recommend/             # 智能推荐（基于算法匹配潜在对象，支持筛选）
│   ├── register/              # 注册登录（手机号/验证码，实名认证集成）
│   └── verifyFriend/          # 好友验证（处理申请，记录互动历史）
├── static/                    # 静态资源（图标、图片，统一管理）
│   ├── blind_date_icon.png    # 应用图标
│   ├── chat_icon.png          # 聊天功能图标
│   ├── head_image.png         # 默认头像
│   ├── index_picture.png      # 首页 banner 图
│   ├── logo.png               # 应用 logo
│   ├── myself_icon.png        # 个人中心图标
│   ├── new_friend_icon.png    # 新朋友提示图标
│   ├── nls_head_picture.png   # 备用头像
│   └── recommend_icon.jpg     # 推荐功能图标
├── uni_modules/               # uni-app 插件（如 UI 组件库、工具类）
├── unpackage/                 # 打包输出（自动生成，多端编译结果）
├── App.vue                    # 应用入口（全局配置、生命周期管理）
├── index.html                 # 网页入口（H5 端基础页面）
├── main.js                    # 应用初始化（Vue 实例创建，全局状态注入）
├── manifest.json              # 应用配置（名称、版本、权限、多端设置）
├── pages.json                 # 页面路由（定义页面路径、导航栏样式）
├── uni.promisify.adaptor.js   # Promise 适配器（兼容 uni-app 异步 API）
└── uni.scss                   # 全局样式（变量、混合器，统一视觉风格）
```

## 核心功能

### 1. 信任体系

- **实名认证**：集成身份核验（通过 `uniCloud` 或第三方服务），确保用户真实性。
- **择偶标签**：在 `personalInformation` 中设置年龄、地域、兴趣等，为智能推荐提供数据支撑。

### 2. 智能匹配

- **算法推荐**：`recommend` 页面基于协同过滤，推送符合择偶条件的用户，支持手动筛选（如 “同城”“年龄范围”）。
- **互动反馈**：用户对推荐对象的 “点赞”“收藏” 行为反哺算法，提升匹配精度。

### 3. 安全社交

- **实时聊天**：`chat` 模块支持消息加密、已读提醒，内置敏感词过滤，保障交流安全。
- **约见管理**：`detailsAndAddFriend` 提供线下约见指南（如首次见面建议、紧急联系人设置），降低风险。

### 4. 多端适配

- 基于 uni-app，一次开发即可发布至 **微信 / 支付宝小程序、H5、安卓 /iOS App**，覆盖全场景用户（如小程序端轻量使用，App 端深度运营）。

## 技术亮点

- **模块化架构**：页面按业务场景解耦（如 `register` 处理用户认证，`recommend` 专注匹配），便于功能迭代（如新增 “红娘牵线” 服务）。
- **组件复用**：`components` 目录封装高频交互（如实名认证弹窗、择偶标签选择器），减少重复开发，统一 UI/UX（温馨、信任的视觉风格，契合相亲场景）。
- **数据安全**：敏感信息（如身份证号、约见地址）通过 `common/request.js` 加密传输，结合后端权限控制，满足婚恋行业合规要求。

## 快速启动

1. **环境准备**：
   - 安装 [HBuilderX](https://www.dcloud.io/hbuilderx.html)。
   - 配置 uni-app 开发环境（如微信小程序开发者工具）。
2. **运行项目**：
   - 导入项目到 HBuilderX，选择目标运行环境（如微信小程序模拟器），点击 **运行**。

## 贡献指南

### 1. **Fork 仓库**

在 GitHub 上复制项目到个人仓库：

1. 打开项目主页：https://github.com/Nathan-code-development/blind-date
2. 点击右上角的 **Fork** 按钮（等待自动复制完成）
3. 复制成功后，你将在自己的 GitHub 账户下看到 `blind-date` 仓库

### 2. **克隆到本地**

将个人仓库的代码下载到本地：

bash:

```bash
# 克隆你的 Fork 仓库（替换为你的 GitHub 用户名）
git clone https://github.com/你的用户名/blind-date.git

# 进入项目目录
cd blind-date

# 添加原始仓库作为上游（方便同步更新）
git remote add upstream https://github.com/Nathan-code-development/blind-date.git

# 查看远程仓库配置
git remote -v
# 应显示：
# origin    https://github.com/你的用户名/blind-date.git (fetch)
# origin    https://github.com/你的用户名/blind-date.git (push)
# upstream  https://github.com/Nathan-code-development/blind-date.git (fetch)
# upstream  https://github.com/Nathan-code-development/blind-date.git (push)
```

### 3. **同步最新代码**

在开始开发前，确保本地代码是最新的：

bash:

```bash
# 切换到 main 分支
git checkout main

# 从原始仓库拉取最新代码
git pull upstream main

# 将更新推送到你的 Fork 仓库
git push origin main
```

### 4. **创建功能分支**

基于 `main` 分支创建新的开发分支：

bash:

```bash
# 创建并切换到新分支（替换为你的分支名，如 feature/chat-improve）
git checkout -b feature/chat-improve

# 查看当前分支
git branch
# 应显示你当前所在的分支
```

### 5. **开发与提交代码**

遵循代码规范进行开发，完成后提交：

bash:

```bash
# 添加所有修改的文件
git add .

# 提交代码（替换为你的提交信息，如 "优化聊天消息加载速度"）
git commit -m "优化聊天消息加载速度"

# 推送到你的 Fork 仓库（替换为你的分支名）
git push origin feature/chat-improve
```

### 6. **发起 Pull Request (PR)**

1. 打开你的 Fork 仓库页面（如 `https://github.com/你的用户名/blind-date`）
2. 点击 **Compare & pull request** 按钮
3. 选择源分支（你的功能分支，如 `feature/chat-improve`）和目标分支（原仓库的 `main`）
4. 填写 PR 标题和描述（说明你的修改内容和目的）
5. 点击 **Create pull request** 提交

### 7. **同步更新后的主分支**

当你的 PR 被合并后，或需要获取原仓库的最新更新：

bash:

```bash
# 切换到 main 分支
git checkout main

# 从原始仓库拉取最新代码
git pull upstream main

# 将更新推送到你的 Fork 仓库
git push origin main

# 删除已合并的本地分支（可选）
git branch -d feature/chat-improve

# 删除已合并的远程分支（可选，替换为你的分支名）
git push origin --delete feature/chat-improve
```

### 代码规范

- **Vue 组件**：遵循单文件组件结构（template、script、style 分离）
- **SCSS 变量**：使用 `uni.scss` 中定义的全局变量（如颜色、字体）
- **注释**：添加必要的功能注释（如复杂逻辑、API 调用）
- **提交信息**：使用简洁明了的描述（如 "修复：注册页面手机号验证错误"）

## 许可证

本项目采用 **MIT 许可证**，详见 LICENSE 文件。

## 联系我们

- 项目地址：https://github.com/your-username/blind-date
- 反馈建议：在 GitHub Issues 中提交，我们会及时处理。

## 未来规划

- **扩展功能**：新增视频聊天、线下活动报名、情感咨询等模块，打造 “工具 + 服务” 闭环。
- **生态合作**：接入婚恋大数据平台、支付 SDK，构建相亲服务生态，提升商业价值。

通过 `blind-date`，我们致力于用技术打破相亲社交的信息壁垒，让每一次相遇都充满温暖与信任。期待与开发者、创业者共同探索相亲社交的无限可能！

#   b l i n d d a t e  
 