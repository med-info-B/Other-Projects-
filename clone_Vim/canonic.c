#include <stdio.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>
#include <unistd.h>
#include <sys/errno.h>
#include <termios.h>
#include <string.h>
#include <curses.h>
#include <poll.h>
#include <sys/ioctl.h>
#include <sys/wait.h>
#include <stdbool.h>

struct termios  term, term_defl;

void term_save(){
    tcgetattr(STDIN_FILENO,&term_defl);
    term = term_defl;
    cfmakeraw(&term);
    tcsetattr(STDIN_FILENO,TCSANOW,&term);
}
void term_default(){
    tcsetattr(STDIN_FILENO,TCSANOW,&term_defl);
}
