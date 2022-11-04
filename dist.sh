./gradlew clean
./gradlew desktop:dist
rm -rf out-mac
java -jar packr.jar packr-config.json