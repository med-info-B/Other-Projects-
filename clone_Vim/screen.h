#ifndef __screen_h__
#define __screen_h__

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

void echo_line(char*** content,int content_size[],int y);
void clear_screen();
void echo_screen(char*** content,int content_size[],int y,int r);
void content_write(char* buffer,int content_size[],int* x,int* y,bool* end,int* z,char*** content,int* lines,int* r,int* c);

#endif
