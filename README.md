![AIDEPlus](https://socialify.git.ci/AndroidIDE-CN/AIDE-Plus/image?description=1&font=KoHo&forks=1&issues=1&logo=https%3A%2F%2Fraw.githubusercontent.com%2FAndroidIDE-CN%2FAIDE-Plus%2Frefs%2Fheads%2F2.3%2F.idea%2Ficon.svg&name=1&owner=1&pattern=Circuit+Board&pulls=1&stargazers=1&theme=Auto)

# 重要公告：仓库闭源说明

我们痛心地发现，部分个人/组织在未遵守 AGPLv3 开源协议的情况下使用了本仓库代码，并**通过虚拟机保护(VMP)等技术手段加固DEX文件**以规避源代码公开义务。  
此行为严重违反了开源精神与协议法律约束，伤害了社区信任。

作者创建本项目的初心，是希望构建一个**自由开放**的环境：
- 允许所有开发者自由查看、修改和学习代码
- 通过开源协作推动项目创新
- 践行 AGPLv3 协议要求的"开放共享"原则

鉴于持续发生的**违规使用**和**使用VMP等技术规避协议**的行为，我们不得不做出艰难决定：
1. **即刻闭源**：本仓库不再公开源代码
2. **仅发布版本**：仓库仅用于发布编译后的APK

任何基于**历史 AGPL 版本**的衍生作品，**必须**：
1. 完全公开修改后的源代码
2. 明确声明代码来源
3. 保持相同的 AGPLv3 协议

---

# Important Notice: Repository Going Closed-Source

We regret to discover that certain individuals/organizations have used this repository's code in violation of the AGPLv3 license, **employing Virtual Machine Protection (VMP) and other advanced techniques to fortify DEXes** and circumvent source code disclosure obligations.  
This constitutes a severe breach of open-source principles and legal agreements, damaging community trust.

The author's vision for this project was to create a **free and open** environment:
- Allow all developers to freely view, modify, and learn from the code
- Drive innovation through open-source collaboration
- Uphold the "share-alike" principle required by AGPLv3

Due to persistent **license violations** and **technical circumvention using VMP**, we must make this difficult decision:
1. **Immediate Closed-Sourcing**: Source code will no longer be publicly available
2. **Release-Only Repository**: This repo will only host compiled APK releases

Any derivative works based on **historical AGPL versions MUST**:
1. Fully disclose modified source code
2. Clearly attribute the original codebase
3. Maintain the same AGPLv3 license

---

# AIDE-Plus

## 🪪 仓库信息
[![GitHub contributors](https://img.shields.io/github/contributors/AndroidIDE-CN/AIDE-Plus)](https://github.com/AndroidIDE-CN/AIDE-Plus/graphs/contributors)
[![GitHub last commit](https://img.shields.io/github/last-commit/AndroidIDE-CN/AIDE-Plus)](https://github.com/AndroidIDE-CN/AIDE-Plus/commits/)
[![Repository Size](https://img.shields.io/github/repo-size/AndroidIDE-CN/AIDE-Plus)](https://github.com/AndroidIDE-CN/AIDE-Plus)
[![GitHub Release](https://img.shields.io/github/v/release/AndroidIDE-CN/AIDE-Plus)](https://github.com/AndroidIDE-CN/AIDE-Plus/releases)
[![Total downloads](https://img.shields.io/github/downloads/AndroidIDE-CN/AIDE-Plus/total)](https://github.com/AndroidIDE-CN/AIDE-Plus/releases)

## 📝 已实现功能
- [x] 构建服务优化和重写
- [x] aapt更替为aapt2
- [x] dx更替为D8
- [x] 一些Java8语法的实现(默认语法分析)
- [x] 一些Java9语法的实现(默认语法分析)
- [x] 一些Java11语法的实现(默认语法分析)
- [x] Java23编译(通过ecj实现，需要设置开启)
- [x] Java高版本格式化(通过ecj实现，需要设置开启)
- [x] 自定义class解析器，以实现高版本的class特性
- [x] 实现了`runtimeOnly`，`compileOnly`，`libgdxNatives`
- [x] 重写Gradle解析器
- [x] 新的Maven下载器(bom已支持)
- [x] 应用冷启动优化
- [x] 添加更多语法高亮
- [x] 使用D8进行混淆
- [x] AIDE默认框架替换为Androidx
- [x] 还原了部分AIDE的dex混淆
- [x] 代码自定义颜色
- [x] ViewBinding支持
- [x] DateBinding支持
- [x] 清单合并工具更新
- [x] 修复静默安装和支持更多安装器(shizuku)
- [x] 新UI的实现
- [x] 修复了补全和高亮丢失问题
- [x] 修复了创建签名的问题
- [x] ApkSign支持了的v1-v3签名
- [x] 新增一些基础的语法补全
- [x] 修复软件内的git问题
- [x] apk资源对齐
- [x] Java项目支持安卓api
- [x] Lambda实现 (ecj) [fbf450d](https://github.com/AndroidIDE-CN/AIDE-Plus/commit/fbf450dba15ccaf51a7a6dd77db300d50551e98b)
- [x] 支持cmake构建 [e702347](https://github.com/AndroidIDE-CN/AIDE-Plus/commit/e702347df0c10b718df5aeb4798402802334e310)
- [x] Xml补全修改逻辑 [0ecb637](https://github.com/AndroidIDE-CN/AIDE-Plus/commit/f7960418b9326231d55726514f10385396e9e8b6)
- [x] 支持 exclude (exclude group: "xxx", module: "xxx")

### 🏗️ 计划实现
- [ ] 更多补全 (Lsp)
- [ ] Apks/AAB的生成(未添加)
- [ ] 清单文件编辑
- [ ] 矢量图获取
- [ ] 重写布局可视化


## 🌠 相关资源
- [AIDE-Ndk-Install](https://github.com/ZeroAicy/AIDE-Ndk-Install) NDK安装器
- [AIDE-Repair](https://github.com/ZeroAicy/AIDE-Repair) 反混淆

# 📬️ 相关信息
- QQ群
  * [487145957](https://qm.qq.com/q/W0WJq5qne2)
  * [1002980489](https://qm.qq.com/q/W0WJq5qne2) 
- [QQ频道](https://pd.qq.com/s/auq589py2)
- [网站](https://plus.androidide.cn)

# 🏅 特别感谢
> [!TIP]
> 如果没有看到您或者您的项目可以联系我们添加上去
### 😎 项目相关
- [@墨凡尘轩](https://github.com/ZeroAicy) 项目创始人
- [@烂泥扶上墙](https://github.com/eirv) 项目合作者
- [@原罪](https://github.com/neu233) 项目合作者
- [@0047ol](https://github.com/0047ol) 项目合作者
- [@梔锿-雨桐](https://iyutong.cn) 项目合作者
- [@hujiayucc](https://github.com/hujiayucc) 项目合作者
- [@dev-bz](https://github.com/dev-bz) (不知者来此) 提供了帮助
### 🐱 开源集成
- [AndroidIDE](https://github.com/AndroidIDEOfficial/AndroidIDE) 借鉴了部分代码
- [CodeAssist](https://github.com/tyron12233/CodeAssist) 借鉴了部分代码
- [Termux-app](https://github.com/termux/termux-app) 集成到软件内部
- [AIDE-Pro](https://github.com/AndroidIDE-CN/) 借鉴UI

