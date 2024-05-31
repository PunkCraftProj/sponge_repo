@echo off
REM COPY
cd ..\target
copy /Y WorldSwapper2-0.1-SNAPSHOT.jar ..\Test_servers\Server1\mods
copy /Y WorldSwapper2-0.1-SNAPSHOT.jar ..\Test_servers\Server2\mods

REM RUN start.bat
cd ..\Test_servers
cd Server1
start start.bat
cd ..\Server2
start start.bat
cd ..\proxy_server
start start.bat

echo Файлы успешно скопированы и серверы запущены.
exit