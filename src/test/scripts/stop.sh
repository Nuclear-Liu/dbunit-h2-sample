	#! /bin/sh
#java -cp h2*.jar org.h2.tools.Server > /dev/null 2>&1 &

echo "Stopping server..."
java -cp $@ org.h2.tools.Server -tcpShutdown tcp://localhost:9092 > h2.log 2>&1 &

exit 0