Write-Host "🚀 GitHub Actions APK构建 - Windows PowerShell上传脚本" -ForegroundColor Green
Write-Host "===============================================" -ForegroundColor Green

# 检查是否安装了git
$gitExists = Get-Command git -ErrorAction SilentlyContinue
if (-not $gitExists) {
    Write-Host "❌ 请先安装Git: https://git-scm.com/download/win" -ForegroundColor Red
    Read-Host "按任意键退出"
    exit 1
}

# 获取用户输入
$githubUsername = Read-Host "请输入您的GitHub用户名"
$email = Read-Host "请输入您的邮箱地址"
$repoName = Read-Host "仓库名 (默认: ChildEnglishStudyApp)"
if ([string]::IsNullOrEmpty($repoName)) {
    $repoName = "ChildEnglishStudyApp"
}

Write-Host ""
Write-Host "📋 准备上传到GitHub..." -ForegroundColor Yellow
Write-Host "用户名: $githubUsername" -ForegroundColor Cyan
Write-Host "邮箱: $email" -ForegroundColor Cyan
Write-Host "仓库名: $repoName" -ForegroundColor Cyan
Write-Host ""

# 初始化Git仓库
Write-Host "📁 初始化Git仓库..." -ForegroundColor Yellow
git init
git add .
git commit -m "Initial commit - Child English Study App with GitHub Actions"

# 设置Git用户信息
git config user.name $githubUsername
git config user.email $email

Write-Host ""
Write-Host "🔗 请按以下步骤操作：" -ForegroundColor Yellow
Write-Host "1. 打开浏览器访问: https://github.com/new" -ForegroundColor White
Write-Host "2. 仓库名输入: $repoName" -ForegroundColor White
Write-Host "3. 设为Public (免费版需要)" -ForegroundColor White
Write-Host "4. 不要勾选 'Add a README file' (我们已经有了)" -ForegroundColor White
Write-Host "5. 点击 'Create repository'" -ForegroundColor White
Write-Host "6. 复制仓库URL并在这里粘贴:" -ForegroundColor White

$actualRepoUrl = Read-Host "仓库URL"

# 添加远程仓库
git remote add origin $actualRepoUrl

# 推送代码
Write-Host ""
Write-Host "📤 推送代码到GitHub..." -ForegroundColor Yellow
git branch -M main
git push -u origin main

Write-Host ""
Write-Host "✅ 上传完成！" -ForegroundColor Green
Write-Host ""
Write-Host "🎯 下一步：" -ForegroundColor Yellow
Write-Host "1. 访问: $actualRepoUrl" -ForegroundColor White
Write-Host "2. 点击 'Actions' 标签" -ForegroundColor White
Write-Host "3. 查看自动构建状态" -ForegroundColor White
Write-Host "4. 5-10分钟后下载APK文件" -ForegroundColor White
Write-Host ""
Write-Host "🚀 恭喜！您的APK构建流水线已经配置完成！" -ForegroundColor Green

Read-Host "按任意键退出"