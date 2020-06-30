@echo off
cd /d %~dp0
cd .
dir /s /B *.class > sources.txt
powershell -Command "(gc sources.txt) -replace 'C:\\Users\\Jared\\Documents\\Mewsubi\\Plugins\\SlimeRain\\build\\', '' | Out-File -encoding ASCII sources.txt"
jar cmf slimerain.mf ../SlimeRain.jar plugin.yml @sources.txt
cd ..