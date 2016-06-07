# checks if CalorieCounter.class is up to date with
# its source file and only compiles it if not
./bin/CalorieCounter.class: ./src/*.java 
	javac -sourcepath ./src -d ./bin ./src/*.java

# runs CalorieCounter and puts it in the background
run: ./bin/CalorieCounter.class
	java -classpath ./bin/ CalorieCounter &
