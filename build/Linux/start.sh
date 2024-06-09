#!/bin/bash
# RUN start.sh

# Перемещение в родительскую директорию
cd ../../

# Переход в папку ServerOneWorld/Server1 и запуск start.sh
cd ServerOneWorld/Server1
./start.sh

# Переход в папку ServerOneWorld/Server2 и запуск start.sh
cd ../Server2
./start.sh

# Переход в папку ServerOneWorld/proxy_server и запуск start.sh
cd ../proxy_server
./start.sh

# Переход в папку ServerOneWorld/hub и запуск start.sh
cd ../hub
./start.sh

echo "Серверы запущены."