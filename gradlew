#!/bin/sh

##############################################################################
##
##  Gradle start up script for POSIX generated manually
##
##############################################################################

APP_HOME=$(cd "${0%/*}" && pwd -P)
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

JAVACMD=${JAVA_HOME:+$JAVA_HOME/bin/java}
JAVACMD=${JAVACMD:-java}

exec "$JAVACMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
