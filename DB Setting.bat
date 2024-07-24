@echo off
:menu
cls
echo ------------------------------
echo DataBase_Main Config
echo ------------------------------
echo 1. Main_Connecting
echo 2. Backup_Connection
echo ------------------------------
set /p choice="Enter your choice (1-2): "

if "%choice%"=="1" goto Main_connecting
if "%choice%"=="2" goto Backup_connecting
goto menu

:Main_connecting
echo Connecting MySQL...
mysql -P 3307 -u root -p

:Backup_connecting
echo Connecting MySQL...
mysql -P 3308 -u root -p
