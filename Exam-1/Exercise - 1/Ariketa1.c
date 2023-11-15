#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h> 
#include <signal.h>
#include <sys/wait.h>

void signalHandler(){
    // Show the message
    printf("Signal received.");
    exit(EXIT_SUCCESS);
}

int main(){
    // Define some variables
    pid_t pidOne;
    pid_t pidTwo;

    int totalSum = 0;
    int finalSum;

    int statusOne;
    int statusTwo;


    // In this program, a FIFO is not necessary due to the fact that the processes are relationed
    // Define and create the pipes for communication betwen processes
    int pipefdOneToTwo[2];
    int pipefdTwoToParent[2];

    pipe(pipefdOneToTwo);
    pipe(pipefdTwoToParent);

    // Define signal handler
    signal(SIGINT, signalHandler);
    signal(SIGTERM, signalHandler);

    // Create the first child
    pidOne = fork();

    if(pidOne == -1){
        // Error output with the fork
        printf("Error creating the first child...\n");
    } else if(pidOne == 0){
        // Child one
        // Calculate the numbers divisible by 7 in [1, 5000] and pass them to child two
        printf("First child created\n");
        close(pipefdOneToTwo[0]); // Close read end

        for (int i = 1; i <= 5000; i++){
            // If i is divisible by 7, write it in the pipe
            if((i%7) == 0){
                write(pipefdOneToTwo[1], &i, sizeof(int));
            }
        }
        printf("Numbers divisible by 7 wrote");

        close(pipefdOneToTwo[1]); // Close write end
        
    } else {
        // Parent
        // Create the second child
        pidTwo = fork();

        if(pidTwo == -1){
            // Error output with the fork
            printf("Error creating the second child...\n");
        } else if(pidTwo == 0){
            // Child two
            // Read the numbers divisible by 7, calculate the sum of all of them and pass it to parent

            printf("Second child created\n");
            // Define some variables
            ssize_t bytesRead;
            int receivedNum;

            close(pipefdOneToTwo[1]); // Close write end

            // Read the data from the pipe
            while ((bytesRead = read(pipefdOneToTwo[0], &receivedNum, sizeof(int))) > 0){
                totalSum = totalSum + receivedNum; // Increment the total sum
            }
            printf("%d\n", totalSum);
            // Finished reading
            if(bytesRead == -1){
                printf("Error reading the data\n");
            } else if(bytesRead == 0){
                printf("Child two reaches end of file (EOF)\n");
            }

            close(pipefdOneToTwo[0]); // Close read end

            // Pass the calculated sum to parent process
            close(pipefdTwoToParent[0]); // Close read end

            write(pipefdTwoToParent[1], &totalSum, sizeof(int)); // Write into the pipe

            close(pipefdTwoToParent[1]); // Close write end
            

        } else {
            // Parent
            // Wait for the childrens, read the total sum, and show it
            waitpid(pidOne, &statusOne, 0);
            waitpid(pidTwo, &statusTwo, 0);

            // Read from the pipe
            close(pipefdTwoToParent[1]); // Close write end

            read(pipefdTwoToParent[0], &finalSum, sizeof(int));

            close(pipefdTwoToParent[0]); // Close read end

            // Show the results
            printf("Final sum: %d\n", finalSum);
        }
    }
    return 0;
}