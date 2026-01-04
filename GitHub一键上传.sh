#!/bin/bash

echo "🚀 GitHub Actions APK构建 - 一键上传脚本"
echo "=============================================="

# 检查是否安装了git
if ! command -v git &> /dev/null; then
    echo "❌ 请先安装Git: https://git-scm.com/download/win"
    exit 1
fi

# 获取用户输入
read -p "请输入您的GitHub用户名: " github_username
read -p "请输入您的邮箱地址: " email
read -p "仓库名 (默认: ChildEnglishStudyApp): " repo_name

# 设置默认值
repo_name=${repo_name:-ChildEnglishStudyApp}

echo ""
echo "📋 准备上传到GitHub..."
echo "用户名: $github_username"
echo "邮箱: $email"
echo "仓库名: $repo_name"
echo ""

# 初始化Git仓库
echo "📁 初始化Git仓库..."
git init
git add .
git commit -m "Initial commit - Child English Study App with GitHub Actions"

# 设置Git用户信息
git config user.name "$github_username"
git config user.email "$email"

# 创建GitHub仓库的URL
repo_url="https://github.com/$github_username/$repo_name.git"

echo ""
echo "🔗 请按以下步骤操作："
echo "1. 打开浏览器访问: https://github.com/new"
echo "2. 仓库名输入: $repo_name"
echo "3. 设为Public (免费版需要)"
echo "4. 不要勾选 'Add a README file' (我们已经有了)"
echo "5. 点击 'Create repository'"
echo "6. 复制仓库URL并在这里粘贴:"

read -p "仓库URL: " actual_repo_url

# 添加远程仓库
git remote add origin "$actual_repo_url"

# 推送代码
echo ""
echo "📤 推送代码到GitHub..."
git branch -M main
git push -u origin main

echo ""
echo "✅ 上传完成！"
echo ""
echo "🎯 下一步："
echo "1. 访问: $actual_repo_url"
echo "2. 点击 'Actions' 标签"
echo "3. 查看自动构建状态"
echo "4. 5-10分钟后下载APK文件"
echo ""
echo "🚀 恭喜！您的APK构建流水线已经配置完成！"