# GitHub Actions APK构建详细指南

## 🎯 为什么选择GitHub Actions？

✅ **永久免费** - 每月2000分钟构建时间
✅ **零配置** - 无需本地环境搭建
✅ **自动构建** - 推送代码自动触发构建
✅ **稳定可靠** - GitHub官方服务
✅ **即时下载** - 构建完成立即下载APK

## 🚀 30分钟快速上手

### 第1步：创建GitHub仓库（5分钟）

1. **打开GitHub**
   - 访问 https://github.com
   - 用邮箱注册免费账户

2. **创建新仓库**
   - 点击右上角 "+" → "New repository"
   - 仓库名：`ChildEnglishStudyApp`
   - 设为 **Public**（免费版需要）
   - 勾选 "Add a README file"
   - 点击 "Create repository"

### 第2步：上传项目文件（10分钟）

#### 方法A：通过网页上传（推荐新手）

1. **在仓库页面点击 "uploading an existing file"**
2. **批量上传所有项目文件**
   - 拖拽所有项目文件到上传区域
   - 或点击 "choose your files" 选择文件
   - **重要**：确保上传这些文件夹和文件：
     ```
     📁 app/
     📁 gradle/
     📄 gradlew
     📄 gradlew.bat
     📄 build.gradle
     📄 settings.gradle
     📄 local.properties (如果有)
     ```

3. **提交上传**
   - 滚动到底部
   - Commit message: `Initial commit - Child English Study App`
   - 点击 "Commit changes"

#### 方法B：通过Git命令行（适合有经验用户）

```bash
# 1. 克隆仓库到本地
git clone https://github.com/你的用户名/ChildEnglishStudyApp.git
cd ChildEnglishStudyApp

# 2. 复制项目文件到仓库目录
# 将你的Android项目所有文件复制到当前目录

# 3. 提交并推送
git add .
git commit -m "Initial commit - Child English Study App"
git push origin main
```

### 第3步：配置自动构建（5分钟）

1. **在GitHub仓库页面点击 "Actions" 标签**

2. **选择工作流模板**
   - 点击 "New workflow"
   - 选择 "Simple workflow"
   - 或搜索 "Android" 选择Android模板

3. **编辑工作流文件**
   - 将以下完整配置复制粘贴：

```yaml
name: Build APK

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - name: Checkout代码
      uses: actions/checkout@v3
    
    - name: 设置JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    
    - name: 设置Android SDK
      uses: android-actions/setup-android@v2
    
    - name: 授权Gradle执行
      run: chmod +x gradlew
    
    - name: 构建Debug APK
      run: ./gradlew assembleDebug
    
    - name: 上传APK文件
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

4. **保存工作流**
   - 点击 "Start commit"
   - Commit message: `Add Android APK build workflow`
   - 点击 "Commit new file"

### 第4步：触发构建（2分钟）

1. **推送代码触发构建**
   ```bash
   git add .
   git commit -m "Trigger first build"
   git push origin main
   ```

2. **查看构建状态**
   - 返回GitHub仓库页面
   - 点击 "Actions" 标签
   - 看到正在运行的workflow

### 第5步：下载APK（3分钟）

1. **等待构建完成**
   - 通常需要5-10分钟
   - 绿色✅表示成功，红色❌表示失败

2. **下载APK文件**
   - 点击成功的workflow运行
   - 在 "Artifacts" 部分找到 "app-debug"
   - 点击下载APK文件

## 🔧 常见问题解决

### 问题1：构建失败 "SDK not found"
**解决**：工作流已经包含SDK设置，通常会自动解决

### 问题2：构建失败 "Gradle permission denied"
**解决**：确保工作流包含 `chmod +x gradlew` 步骤

### 问题3：下载APK显示404
**解决**：检查构建日志，确认构建成功完成

### 问题4：仓库私有无法使用Actions
**解决**：免费版GitHub Actions需要Public仓库

## ⚡ 高级功能

### 自动发布到GitHub Releases
在工作流末尾添加：
```yaml
    - name: 创建Release
      uses: actions/create-release@v1
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
      with:
        tag_name: v1.0.${{ github.run_number }}
        release_name: Release v1.0.${{ github.run_number }}
        draft: false
        prerelease: false
```

### 构建Release APK
将构建命令改为：
```yaml
    - name: 构建Release APK
      run: ./gradlew assembleRelease
```

## 📱 安装到手机

1. **下载APK文件到电脑**
2. **传输到手机**（通过USB、微信、QQ等）
3. **手机设置**：
   - 设置 → 安全 → 允许安装未知来源应用
   - 或在安装时允许该来源
4. **安装APK**
   - 点击APK文件开始安装
   - 按照提示完成安装

## 🎉 成功！

恭喜！您已经成功配置了GitHub Actions自动构建系统：
- ✅ 每次推送代码自动构建APK
- ✅ 永久免费使用
- ✅ 无需本地环境
- ✅ 即时下载最新版本

## 🔄 后续使用

以后更新应用只需要：
1. 修改代码
2. 推送代码：`git push origin main`
3. 5-10分钟后下载新的APK

**太简单了！** 🚀

## 💡 小贴士

- 可以设置多个分支分别构建Debug和Release版本
- 可以配置自动部署到应用商店
- 可以集成自动化测试
- 可以设置构建状态通知

需要我帮您设置其他高级功能吗？