#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int pipe_fd[2];
    pid_t child_pid;

    if (pipe(pipe_fd) == -1) {
        perror("Pipe creation failed");
        exit(EXIT_FAILURE);
    }

    child_pid = fork();

    if (child_pid == -1) {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    }

    if (child_pid == 0) { // Child process
        close(pipe_fd[0]); // Close the read end of the pipe

        // Redirect the standard output (stdout) to the write end of the pipe
        if (dup2(pipe_fd[1], STDOUT_FILENO) == -1) {
            perror("dup2 failed");
            exit(EXIT_FAILURE);
        }

        // Close the original file descriptor of the write end of the pipe
        close(pipe_fd[1]);

        // Execute a command in the child process (e.g., "ls")
        execlp("ls", "-l", NULL);

        // If execlp fails, we print an error message
        perror("execlp failed");
        exit(EXIT_FAILURE);
    } else { // Parent process
        close(pipe_fd[1]); // Close the write end of the pipe

        char buffer[4096];
        ssize_t bytes_read;

        printf("Child process output:\n");

        while ((bytes_read = read(pipe_fd[0], buffer, sizeof(buffer))) > 0) {
            write(STDOUT_FILENO, buffer, bytes_read); // Write the output to the standard output
        }

        close(pipe_fd[0]); // Close the read end of the pipe

        wait(NULL); // Wait for the child process to complete

        exit(EXIT_SUCCESS);
    }
}
