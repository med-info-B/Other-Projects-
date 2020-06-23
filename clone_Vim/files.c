
#include "files.h"

void file_into_array(int fd3,int* x,int* y,char*** content,int content_size[],int* tlus,int c){
    int lus;
    char buf[1];
    while((lus = read(fd3,buf,1))!=0){
        if(buf[0] == '\n'|| *x == c){  
            content_size[*y] = *x;
            *y=*y+1;
            *x=1;
        }else{   
            *content[*y][*x]=*buf;
            *tlus = *tlus + lus;
            *x=*x+1;
        }
    }
    close(fd3);
}


void save_changes(char*** content,int content_size[],char* file,int* lines)
{
    
    int fd = open(file , O_RDWR | O_TRUNC);
    if(fd == -1)
    {
        perror("open");
        exit(1);
    } 

    for(int i = 0; i <= *lines; i++)
    {
        for(int j = 1;j < content_size[i]; j++)
        {
            write(fd, content[i][j], 1);
        }
        write(fd,"\n",1);
    }
}
















