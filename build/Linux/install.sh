#!/bin/bash

# Переместиться в каталог target
cd ../../target || exit

# Скопировать файл OneWorldSponge-0.1-SNAPSHOT.jar в нужные директории
cp -f OneWorldSponge-0.1-SNAPSHOT.jar ../../ServerOneWorld/Server1/mods
cp -f OneWorldSponge-0.1-SNAPSHOT.jar ../../ServerOneWorld/Server2/mods

# Вывести сообщение о завершении копирования
echo "Файлы успешно скопированы."