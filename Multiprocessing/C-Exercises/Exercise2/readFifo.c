#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    const char* fifoPath = "myfifo";
    char message[100];
    
    // Open the FIFO for reading
    int pipefd = open(fifoPath, O_RDONLY);

    if (pipefd == -1) {
        perror("open");
        return 1;
    }

    // Read the message from the FIFO
    ssize_t bytesRead = read(pipefd, message, sizeof(message));

    if (bytesRead == -1) {
        perror("read");
        return 1;
    }

    // Null-terminate the received message
    message[bytesRead] = '\0';

    // Display the received message
    printf("Received message from FIFO: %s\n", message);

    // Close the FIFO
    close(pipefd);

    return 0;
}
