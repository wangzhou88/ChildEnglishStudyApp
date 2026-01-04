Write-Host "🚀 GitHub Actions APK Build - Upload Script" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Green

# Check if git is installed
$gitExists = Get-Command git -ErrorAction SilentlyContinue
if (-not $gitExists) {
    Write-Host "❌ Please install Git first: https://git-scm.com/download/win" -ForegroundColor Red
    exit 1
}

# Get user input
$githubUsername = Read-Host "Enter your GitHub username"
$email = Read-Host "Enter your email address"
$repoName = Read-Host "Repository name (default: ChildEnglishStudyApp)"
if ([string]::IsNullOrEmpty($repoName)) {
    $repoName = "ChildEnglishStudyApp"
}

Write-Host ""
Write-Host "📋 Preparing to upload to GitHub..." -ForegroundColor Yellow
Write-Host "Username: $githubUsername" -ForegroundColor Cyan
Write-Host "Email: $email" -ForegroundColor Cyan
Write-Host "Repository: $repoName" -ForegroundColor Cyan
Write-Host ""

# Initialize Git repository
Write-Host "📁 Initializing Git repository..." -ForegroundColor Yellow
git init
git add .
git commit -m "Initial commit - Child English Study App with GitHub Actions"

# Set Git user info
git config user.name $githubUsername
git config user.email $email

Write-Host ""
Write-Host "🔗 Please follow these steps:" -ForegroundColor Yellow
Write-Host "1. Open browser and visit: https://github.com/new" -ForegroundColor White
Write-Host "2. Repository name: $repoName" -ForegroundColor White
Write-Host "3. Set to Public (required for free version)" -ForegroundColor White
Write-Host "4. Do NOT check 'Add a README file' (we already have one)" -ForegroundColor White
Write-Host "5. Click 'Create repository'" -ForegroundColor White
Write-Host "6. Copy the repository URL and paste here:" -ForegroundColor White

$actualRepoUrl = Read-Host "Repository URL"

# Add remote repository
git remote add origin $actualRepoUrl

# Push code
Write-Host ""
Write-Host "📤 Pushing code to GitHub..." -ForegroundColor Yellow
git branch -M main
git push -u origin main

Write-Host ""
Write-Host "✅ Upload completed!" -ForegroundColor Green
Write-Host ""
Write-Host "🎯 Next steps:" -ForegroundColor Yellow
Write-Host "1. Visit: $actualRepoUrl" -ForegroundColor White
Write-Host "2. Click 'Actions' tab" -ForegroundColor White
Write-Host "3. Check auto-build status" -ForegroundColor White
Write-Host "4. Download APK file in 5-10 minutes" -ForegroundColor White
Write-Host ""
Write-Host "🚀 Congratulations! Your APK build pipeline is ready!" -ForegroundColor Green