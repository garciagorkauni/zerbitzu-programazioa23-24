#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    const char* fifoPath = "myfifo";
    char message[100];
    
    // Open the FIFO for writing
    int pipefd = open(fifoPath, O_WRONLY);

    if (pipefd == -1) {
        perror("open");
        return 1;
    }

    // Prompt the user to enter a message
    printf("Enter a message to send to the FIFO: ");
    scanf("%s", message);

    // Write the message to the FIFO
    ssize_t bytesWritten = write(pipefd, message, strlen(message));

    if (bytesWritten == -1) {
        perror("write");
        return 1;
    }

    // Close the FIFO
    close(pipefd);

    return 0;
}
