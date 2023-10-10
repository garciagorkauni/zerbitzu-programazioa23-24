#include <unistd.h>
#include <string.h>
#include <stdio.h>

int main() {
    
    // Create pipe's file descriptors
    int pipefd[2];

    // Define the String (in C char array) for the message
    char buffer[20];

    // Create the pipe
    pipe(pipefd);

    // Fork a child process
    pid_t pid = fork();

    // Condition depending on the pid of each process
    if (pid == -1){
        // Error creating the fork
        printf("An error occurs with the fork.");

    } else if (pid == 0) {
        // Child process
        close(pipefd[1]); // Close the write end

        // Read from the pipe
        read(pipefd[0], buffer, sizeof(buffer));
        printf("Child process received: %s\n", buffer);

        close(pipefd[0]); // Close the read end

    } else if (pid > 0) {
        // Parent process
        close(pipefd[0]); // Close the read end

        // Write to the pipe
        char message[] = "This is the message from parent to child";
        write(pipefd[1], message, strlen(message));

        close(pipefd[1]); // Close the write end
        
    } 
    
    return 0;
}