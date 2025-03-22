## Dining Philosophers Problem  
In the default run configuration there are 5 philosophers and 5 chopsticks  
Each philosopher must wait for both of its chopsticks to be free to use, then it will eat, release chopsticks, think, then try to eat again cyclically

### Prerequisites
- Java Development Kit (JDK) 21.0.4

**Options for Running:**

- Terminal: Ant version 1.10.15
- IDE: Any Java IDE (this project was developed with IntelliJ IDEA)



### How to Run
Clone the repository
```bash
git clone https://github.com/codegard4/DiningPhilosophers
```

#### Option 1: Running with Ant
```bash
ant compile
```

Normal execution:
```bash
ant run
```

Execution with verbose output (showing chopstick acquisitions and releases):
```bash
ant run-verbose
```

#### Option 2: Running with IDE
- Open the project in your IDE
- Run `Table.java` 
- To enable verbose output, add the JVM argument: `-Dverbose=true`

#### Configurations
In the `Table.java` main method you can change the following configuration:
- `NUMBER_OF_PHILOSOPHERS` - Number of philosophers

### Verbose Mode
When enabled, verbose mode prints detailed information about chopstick acquisitions and releases, showing which philosopher acquires or releases which chopstick. This helps in visualizing the resource contention and understanding how the program avoids deadlocks.

### Generate UML

To generate UML diagrams using PlantUML, follow these steps:

1. Download the latest plantuml.jar from the official site: https://plantuml.com/download.
2. Place plantuml.jar in your project directory (or any desired location).
3. Create or update your UML diagram file (e.g., diagram.uml).
4. Run the following command in your terminal to generate a png:
   ```bash
   java -jar plantuml.jar diagram.uml
   ```
   or to generate an svg:
   ```bash
   java -jar plantuml.jar -tsvg *.puml
   ```
5. The diagram image  will be generated in the same directory.


## Description of Our Solution

### Table  
- a table has N number of chopsticks and philosophers  
- Each philosopher has two specified chopsticks assigned to it  

### Chopsticks  
- A chopstick belongs to one philosopher at a time  
- It can be acquired or not acquired

### Philosophers  
- Run 5 philosophers  
- Handle race conditions and deadlocks  
- The philosophers must eat, give up chopsticks, get chopsticks and not hold onto chopsticks for too long
