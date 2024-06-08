@echo off
REM COPY
cd ..\..\
cd ..\target
copy /Y OneWorldSponge-0.1-SNAPSHOT.jar ..\..\ServerOneWorld\Server1\mods
copy /Y OneWorldSponge-0.1-SNAPSHOT.jar ..\..\ServerOneWorld\Server2\mods

echo Файлы успешно скопированы.
exit