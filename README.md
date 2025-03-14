## Philosophers Problem  
There are 5 philosophers  
Each philosopher must wait for both of its chopsticks to be free to use, then it will eat  

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
