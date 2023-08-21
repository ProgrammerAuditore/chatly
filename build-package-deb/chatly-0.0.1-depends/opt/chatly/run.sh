#!/bin/sh

# Add enviroment to Chatly
CHATLY_LIB="/usr/lib/chatly"
CHATLY_JAR="/opt/chatly/Chatly.jar"
PATH=$PATH:$CHATLY_LIB/bin

# Run Chatly Jar
$CHATLY_LIB/bin/java -Xrs -jar $CHATLY_JAR
