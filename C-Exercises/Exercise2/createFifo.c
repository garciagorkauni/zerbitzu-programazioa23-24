#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    const char* fifoPath = "myfifo";
    mode_t fifoMode = 0666;

    if (mkfifo(fifoPath, fifoMode) == -1) {
        perror("mkfifo");
        return 1;
    }

    printf("FIFO 'myfifo' created successfully.\n");

    return 0;
}
