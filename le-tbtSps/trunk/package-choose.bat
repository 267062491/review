@echo off
mode con cols=100 lines=40
title Welcom to use Maven auto package tools
:loop
cls
echo -------------------------------------------------------------------------------------------
echo    Choose the number you want to package with the profile, and then press the enter key.
echo -------------------------------------------------------------------------------------------
echo.
echo        1 local
echo        2 dev
echo        3 test
echo        4 release
echo.
echo        0 quit
echo.

set/p n=      Please choose:
if %n%==1 goto local
if %n%==2 goto dev
if %n%==3 goto test
if %n%==4 goto release
if %n%==0 goto end
goto loop
 

 
:local
call subCommand.bat local
goto end

:dev
call subCommand.bat dev
goto end

:test
@call subCommand.bat test
goto end

:release
@call subCommand.bat release
goto end

:end
pause
exit
