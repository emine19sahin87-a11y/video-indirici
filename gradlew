#!/bin/sh

##############################################################################
# Gradle startup script for POSIX
##############################################################################

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}

# Attempt to set APP_HOME
SAVED="$(pwd)"
cd "$(dirname \"$0\")" || exit
APP_HOME="$(pwd -P)" || exit
cd "$SAVED" || exit

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use to start the JVM
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

# Increase the maximum file descriptors if we can
MAX_FD="maximum"
MAX_FD_LIMIT=$(ulimit -H -n) || true
if [ "$MAX_FD_LIMIT" != "unlimited" ]; then
    MAX_FD=$(ulimit -S -n) || true
    if [ "$MAX_FD" = "unlimited" ] || [ "$MAX_FD" -gt "$MAX_FD_LIMIT" ]; then
        MAX_FD="$MAX_FD_LIMIT"
    fi
    ulimit -n "$MAX_FD" || true
fi

exec "$JAVACMD" "-Dorg.gradle.appname=$APP_BASE_NAME" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"

