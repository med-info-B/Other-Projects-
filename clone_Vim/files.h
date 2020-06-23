#ifndef __files_h__
#define __files_h__





#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "files.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


void file_into_array(int fd3,int* x,int* y,char*** content,int content_size[],int* tlus,int c);
void save_changes(char*** content,int content_size[],char* file,int* lines);

#endif
