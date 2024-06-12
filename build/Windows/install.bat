@echo off
REM COPY

cd ..\..\target
copy /Y OneWorldSponge-0.1-SNAPSHOT.jar ..\..\server_repo\Server1\mods
copy /Y OneWorldSponge-0.1-SNAPSHOT.jar ..\..\server_repo\Server2\mods

echo Файлы успешно скопированы.
exit