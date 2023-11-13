#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    // Create the pipe for the comunication betwen the father and child1 proccesses
    int pipe_fd1[2];
    if (pipe(pipe_fd1) == -1) {
        perror("Error");
        exit(EXIT_FAILURE);
    }

    // Create the first child
    pid_t first_child_pid = fork();

    if (first_child_pid == -1) {
        perror("Error");
        exit(EXIT_FAILURE);
    }

    if (first_child_pid == 0) { // First child
        close(pipe_fd1[1]); // Close the write end

        char message[100];
        read(pipe_fd1[0], message, sizeof(message));

        // Modify the message
        strcat(message, " (mofified by the first child)");

        // Create the pipe for the comunication betwen child1 and child2 proccesses
        int pipe_fd2[2];
        if (pipe(pipe_fd2) == -1) {
            perror("Error");
            exit(EXIT_FAILURE);
        }

        // Create the second child
        pid_t second_child_pid = fork();

        if (second_child_pid == -1) {
            perror("Error");
            exit(EXIT_FAILURE);
        }

        if (second_child_pid == 0) { // Second child
            close(pipe_fd2[1]); // Close the write end

            char message2[100];
            read(pipe_fd2[0], message2, sizeof(message2));

            printf("Second child received: %s\n", message2);

            close(pipe_fd2[0]); // Close the read end

            exit(EXIT_SUCCESS);
        } else { // First child
            close(pipe_fd2[0]); // Close the read end

            // Send the message
            write(pipe_fd2[1], message, strlen(message) + 1);

            close(pipe_fd2[1]); // Close the write end

            wait(NULL);

            close(pipe_fd1[0]); // Close the read end

            exit(EXIT_SUCCESS);
        }
    } else { // Parent proccess
        close(pipe_fd1[0]); // Close the read end

        char message[] = "Hello from parent";

        // Send the message
        write(pipe_fd1[1], message, strlen(message) + 1);

        close(pipe_fd1[1]); // Close the write end

        wait(NULL);

        exit(EXIT_SUCCESS);
    }
}
