@echo off

:: Путь к вашему серверному файлу SpongeForge
set JAR_FILE=velocity-3.3.0-SNAPSHOT-396.jar

:: Установите минимальный и максимальный размер кучи памяти
set MIN_MEMORY=2G
set MAX_MEMORY=4G

:: Установите дополнительные JVM аргументы для производительности
:: Данный набор флагов показал хорошую производительность и стабильность в большинстве случаев,
:: но может потребоваться настройка под ваш конкретный сценарий.
set JVM_ARGS=-XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:G1MixedGCLiveThresholdPercent=90 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1NewSizePercent=20 -XX:G1MaxNewSizePercent=30 -XX:G1ReservePercent=20 -XX:SurvivorRatio=32 -XX:MaxTenuringThreshold=1 -Dfml.readTimeout=180 -Dio.netty.leakDetection.level=DISABLED

:: Запуск сервера
"C:\Program Files\Java\jdk-17.0.2\bin\java.exe" -Xms%MIN_MEMORY% -Xmx%MAX_MEMORY% %JVM_ARGS% -jar %JAR_FILE% nogui