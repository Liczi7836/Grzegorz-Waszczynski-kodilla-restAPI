call C:\Users\grzeg\Desktop\tasks\tasks\runcrud.bat
if "%ERRORLEVEL%" == "0" goto web
echo.
echo Compilation has errors - breaking work
goto fail

:web
start firefox http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished