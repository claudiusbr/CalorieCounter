build:
	mvn install

run:	
	java -cp target/caloriecounter-1.0-SNAPSHOT.jar old.caloriecounter.CalorieCounter &
