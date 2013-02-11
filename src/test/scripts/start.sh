#! /bin/sh
#java -cp h2*.jar org.h2.tools.Server > /dev/null 2>&1 &

echo "Starting server..."
java -cp $@ org.h2.tools.Server -tcp > h2.log 2>&1 &

exit 0