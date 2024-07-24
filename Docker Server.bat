@echo off
:menu
cls
echo ------------------------------
echo RPA 자동화 프로젝트
echo Docker 서버 가동
echo ------------------------------
echo 1. Start (docker-compose start)
echo 2. Stop (docker-compose stop)
echo 3. Restart (docker-compose restart)
echo 4. Web (docker-compose start web)
echo 5. Exit
echo ------------------------------
set /p choice="Enter your choice (1-5): "

if "%choice%"=="1" goto start
if "%choice%"=="2" goto stop
if "%choice%"=="3" goto restart
if "%choice%"=="4" goto resweb
if "%choice%"=="5" goto exit
goto menu

:start
echo Starting Docker Compose...
docker-compose start
pause
goto menu

:stop
echo Stopping Docker Compose...
docker-compose stop
pause
goto menu

:restart
echo Restarting Docker Compose...
docker-compose stop
timeout /t 2 /nobreak > NUL
docker-compose start
docker-compose logs
pause
goto menu

:resweb
echo Restarting Docker Web Service...
docker-compose restart web
pause
goto menu

:exit
echo Exiting...
exit