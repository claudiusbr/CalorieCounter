build:
	mvn install

run:	
	java -cp target/caloriecounter-1.0-SNAPSHOT.jar caloriecounter.CalorieCounter &
