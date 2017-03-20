echo start to package maven project with profile %1%
echo.
mvn clean package -Dmaven.test.skip=true -P%1%