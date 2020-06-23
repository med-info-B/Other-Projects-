#ifndef __keyboard_h__
#define __keyboard_h__


void arrows(char* buffer,int content_size[],int* x,int* y,bool* end,int* z,char*** content,int* lines,int* r);
void enter_key(int content_size[],int* x,int* y,int* z,char*** content,int* lines,int* r);
void back_space(int content_size[],int* x,int* y,int* z,char*** content,int* lines,int* r,int* c);
void echo_line(char*** content,int content_size[],int y);
void clear_screen();
void echo_screen(char*** content,int content_size[],int y,int r);


#endif
