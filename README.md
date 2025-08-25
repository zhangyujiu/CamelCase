# CamelCase 命名风格转换插件

一款强大的IntelliJ IDEA插件，支持多种编程命名风格之间的快速转换。

## 功能特性

- ✅ **一键循环转换** - 通过 **Ctrl+Alt+C** 快捷键在四种命名风格间循环切换
- ✅ **智能风格检测** - 自动识别当前命名风格并转换为下一种风格
- ✅ **四种命名风格支持**：
  - 驼峰命名 (camelCase)
  - 帕斯卡命名 (PascalCase) 
  - 下划线命名 (snake_case)
  - 短横线命名 (kebab-case)
- ✅ **智能单词识别** - 自动识别各种命名风格的分隔符
- ✅ **自动文本选择** - 无需选择文本，光标定位即可转换当前单词

- ✅ **右键菜单集成** - 在编辑器右键菜单中提供转换选项
- ✅ **编辑菜单集成** - 在主菜单编辑选项中提供转换功能

## 使用方法

### 方法一：快捷键操作（推荐）
1. 选中要转换的文本（或将光标定位到单词上）
2. 按下 **Ctrl+Alt+C** 快捷键进行循环转换
3. 支持在驼峰、帕斯卡、下划线、短横线命名风格之间循环切换
4. 每次按下快捷键会切换到下一种命名风格
5. 多次按下快捷键可循环切换不同命名风格：
   - 第1次：转换为驼峰命名 (camelCase)
   - 第2次：转换为帕斯卡命名 (PascalCase)
   - 第3次：转换为下划线命名 (snake_case)
   - 第4次：转换为短横线命名 (kebab-case)
   - 第5次：回到驼峰命名，继续循环...

### 方法二：右键菜单
1. 选中要转换的文本
2. 右键打开上下文菜单
3. 点击"循环切换命名风格"选项

### 方法三：主菜单
1. 选中要转换的文本
2. 打开"编辑"菜单
3. 点击"循环切换命名风格"选项

## 转换示例

### 循环转换流程
以 `hello_world` 为例，多次按 `Ctrl+Alt+C`：

```
hello_world  →  helloWorld  →  HelloWorld  →  hello_world  →  hello-world  →  helloWorld ...
   (原始)        (驼峰命名)      (帕斯卡命名)     (下划线命名)     (短横线命名)      (循环继续)
```

### 更多转换示例

| 原始文本 | 第1次转换 | 第2次转换 | 第3次转换 | 第4次转换 |
|---------|----------|----------|----------|----------|
| `hello_world` | `helloWorld` | `HelloWorld` | `hello_world` | `hello-world` |
| `HelloWorld` | `helloWorld` | `HelloWorld` | `hello_world` | `hello-world` |
| `hello-world` | `helloWorld` | `HelloWorld` | `hello_world` | `hello-world` |
| `getUserName` | `getUserName` | `GetUserName` | `get_user_name` | `get-user-name` |

## 技术实现

- **开发语言**: Kotlin
- **构建工具**: Gradle
- **插件框架**: IntelliJ Platform Plugin SDK
- **支持版本**: IntelliJ IDEA 2025.1+

## 项目结构

```
src/main/kotlin/com/camel_case/camelcase/
├── NamingStyleConverter.kt          # 核心转换逻辑
├── BaseConvertAction.kt             # Action基类
├── ConvertToCamelCaseAction.kt      # 驼峰命名转换Action
├── ConvertToPascalCaseAction.kt     # 帕斯卡命名转换Action
├── ConvertToSnakeCaseAction.kt      # 下划线命名转换Action
└── ConvertToKebabCaseAction.kt      # 短横线命名转换Action
```

## 构建和安装

### 构建插件
```bash
./gradlew build
```

### 运行测试环境
```bash
./gradlew runIde
```

### 生成插件包
```bash
./gradlew buildPlugin
```

生成的插件包位于 `build/distributions/` 目录下。

## 安装方法

1. 下载插件包（.zip文件）
2. 打开IntelliJ IDEA
3. 进入 `File` → `Settings` → `Plugins`
4. 点击齿轮图标，选择 `Install Plugin from Disk...`
5. 选择下载的插件包文件
6. 重启IDE

## 许可证

本项目采用MIT许可证。

## 贡献

欢迎提交Issue和Pull Request来改进这个插件！

---

**享受高效的命名风格转换体验！** 🚀