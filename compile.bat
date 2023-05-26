@echo off

set folderDevelopment=Project
set folderRelease=Release

rem Get into the development directory
cd %folderDevelopment%

rem Remove any existing .class files from the bin directory
rd /s /q .\bin

rem Create the bin directory if it doesn't exist
mkdir .\bin

rem Copy the assets directory to the bin directory
xcopy /E .\assets .\bin\assets
xcopy /E .\icons .\bin\icons

rem Generate the CLASSPATH by iterating over JAR files in the lib directory and its subdirectories
set lib_dir=lib
set class_path=

rem Find all JAR files in the lib directory and its subdirectories
for /R "%lib_dir%" %%F in (*.jar) do (
    echo %%F | find /i "javafx" > nul
    if errorlevel 1 (
        set "class_path=%class_path%;%%F"
    )
)

rem Remove the leading ';' from the class_path
set "class_path=%class_path:~1%"

rem Generate MODULEPATH and ICON depending on the OS and architecture
if "%OSTYPE%"=="linux-gnu" (
    set MODULEPATH=./lib/javafx-linux/lib
    set ICON=
)

if "%OSTYPE%"=="darwin*" (
    if "%PROCESSOR_ARCHITECTURE%"=="AMD64" (
        set MODULEPATH=./lib/javafx-osx-intel/lib
        set ICON=-Xdock:icon=icons/iconOSX.png
    ) else if "%PROCESSOR_ARCHITECTURE%"=="ARM64" (
        set MODULEPATH=./lib/javafx-osx-arm/lib
        set ICON=-Xdock:icon=iconOSX.png
    )
)

rem Compile the Java source files and place the .class files in the bin directory
javac -d .\bin\ .\src\*.java --module-path "%MODULEPATH%" --add-modules javafx.controls,javafx.fxml

rem Create the Project.jar file with the specified manifest file and the contents of the bin directory
jar cfm .\Project.jar .\Manifest.txt -C bin .

rem Remove any .class files from the bin directory
rd /s /q .\bin

rem Get out of the development directory
cd ..

rem Move the Project.jar file to the release directory
rd /s /q .\%folderRelease%
mkdir .\%folderRelease%
move .\%folderDevelopment%\Project.jar .\%folderRelease%\Project.jar
xcopy /E .\%folderDevelopment%\lib .\%folderRelease%\lib

rem If OSX copy the icon to the release directory
if "%OSTYPE%"=="darwin*" (
    copy .\%folderDevelopment%\icons\iconOSX.png .\%folderRelease%\iconOSX.png
)

rem Create the 'run.bat' file
echo @echo off > run.bat
echo java %ICON% --module-path %MODULEPATH% --add-modules javafx.controls,javafx.fxml -cp Project.jar;%CLASSPATH% Main >> run.bat

rem Run the Project.jar file
cd .\%folderRelease%
call run.bat
cd ..
