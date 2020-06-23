#ifndef __escmode_h__
#define __escmode_h__



#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "screen.h"
#include "canonic.h"
#include "files.h"
#include <stdbool.h>
#include "keyboard.h"
#include "gestion_souris.h"

int esc_mode(char*** content,int content_size[],int fd2, coord_C_t* c1, coord_S_t* s ,int x,int y,int z,bool* insert_mode,int r,char* file,int* lines);


#endif
