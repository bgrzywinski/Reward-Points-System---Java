# Instructions to Upload Project to GitHub

## Step 1: Install Git (if not already installed)
Download and install from: https://git-scm.com/download/win

## Step 2: Initialize Git Repository
Open PowerShell in your project folder and run:

```powershell
git init
```

## Step 3: Add All Files
```powershell
git add .
```

## Step 4: Create Initial Commit
```powershell
git commit -m "Initial commit: Java reward points system"
```

## Step 5: Create GitHub Repository
1. Go to https://github.com and sign in
2. Click the "+" icon in the top right → "New repository"
3. Choose a repository name (e.g., "java-reward-points-system")
4. Choose Public or Private
5. **DO NOT** initialize with README, .gitignore, or license (we already have these)
6. Click "Create repository"

## Step 6: Connect and Push
GitHub will show you commands. Run these (replace YOUR_USERNAME and REPO_NAME):

```powershell
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/REPO_NAME.git
git push -u origin main
```

## Alternative: Using GitHub Desktop
1. Download: https://desktop.github.com/
2. Install and sign in
3. File → Add Local Repository → Select your project folder
4. Click "Publish repository" button


