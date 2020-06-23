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
#include "screen.h"

void arrows(char* buffer,int content_size[],int* x,int* y,bool* end,int* z,char*** content,int* lines,int* r){
    //////////////UP/////////////////
    if(buffer[2] == 65 && buffer[1]== 91)
    {
        if((*z>-1 && *x > content_size[*y + *z - 1])|| *x == content_size[*y + *z])
        {
            *x = content_size[*y + *z - 1];
        }
        if(*y>1)
        {
            *y = *y - 1;
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }else if(*z>-1){
            echo_screen(content,content_size,*z,*r);
            *z=*z-1;
            printf("\x1b[%d;%dH",1,*x);
            fflush(stdout);
        }
        buffer[1]= '\0';
        buffer[2]= '\0';
        ////////////////DOWN/////////////
    }else if(buffer[2] == 66 && buffer[1]== 91)
    {
        if((*z+*y-1 > 0 && *x > content_size[*y+*z+1]) || *x==content_size[*y+*z]){
        *x=content_size[*y+*z+1];
        }
        if(*y<*r-1){
            *y=*y+1;
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }else if(*lines-*z>=*r-1){
            
            *z=*z+1;
            echo_screen(content,content_size,*z+1,*r);
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }
        
        buffer[1]= '\0';
        buffer[2]= '\0';
        
        ////////////////RIGHT///////////////
    }else if(buffer[2] == 67 && buffer[1]== 91){
        if(*x>=content_size[*y+*z]){
            *y=*y+1;
            *x=1;
            *end = true;
        }
        if(*end==false){
            *x=*x+1;
        }
        *end = false;
        buffer[1]= '\0';
        buffer[2]= '\0';
        fflush(stdout);
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
        //////////////////LEFT///////////////                    
    }else if(buffer[2] == 68 && buffer[1]== 91 && *x+*y>2){
        if(*x==1){
            *y=*y-1;
            *x = content_size[*y+*z];
            printf("\x1b[%d;%dH",*y,content_size[*y+*z]);
            fflush(stdout);
        }else{    
            *x=*x-1;
            fflush(stdout);
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }
        buffer[1]= '\0';
        buffer[2]= '\0';
    }
}   

void enter_key(int content_size[],int* x,int* y,int* z,char*** content,int* lines,int* r){
    *lines=*lines+1;
    char s = ' ';
    for(int i = *lines;i>*y+*z;i--){
        content_size[i]=content_size[i-1];
        for(int u = 1;u<=content_size[i];u++){
            *content[i][u]=*content[i-1][u];
        }
    }
    if(*x == content_size[*y+*z]){
        for(int i = 1; i<content_size[*y+1+*z];i++){
            *content[*y+*z+1][i]=s;
        }
        content_size[*y+1+*z]=1;
        *y=*y+1;
        *x=1;
        
        echo_screen(content,content_size,*z+1,*r);
        printf("%d",*lines); 
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
    }else{
        //                                     content_size[y+1+z]=1;
        int ns = content_size[*y+*z]-*x;
        char* aux=malloc(sizeof(char*)*ns);
        for(int i = 1; i<=content_size[*y+*z+1];i++){
            *content[*y+*z+1][i]=s;
        }
        for(int i = *x; i<=content_size[*y+*z];i++){
            aux[i-*x+1]=*content[*y+*z][i];
        }
        for(int i = 1; i<=ns;i++){
            *content[*y+*z+1][i] = aux[i];
        }
        for(int i = *x; i<content_size[*y+*z];i++){
            *content[*y+*z][i]=s;
        }
        free(aux);
        content_size[*y+*z]= *x;
        content_size[*y+1+*z]=ns+1;
        *y=*y+1;
        *x=1;
        
        echo_screen(content,content_size,*z+1,*r);
        //                                     content_size[*y+z]=ns+1;
        //                                     printf("%d",*lines); 
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
    }
    //                                     z++;
    
}

void back_space(int content_size[],int* x,int* y,int* z,char*** content,int* lines,int* r,int* c){
    char u = ' ';
    if(*x==1){
        int cond;
        if((content_size[*y+*z]+content_size[*y+*z-1])>*c){
            cond = *c-content_size[*y+*z-1];
            int i = 1;
            while(i <= cond){
                *content[*y+*z-1][content_size[*y+*z-1]+i-1] = *content[*y+*z][i];
                i++;
            }
            char s = ' ';
            char* aux=malloc(sizeof(char*)*(content_size[*z+*y]-cond));
            for(int j = 1; j<=content_size[*z+*y]-cond;j++){
                aux[j]=*content[*y+*z][cond+j];
            }
            for(int j = 1; j<=content_size[*y+*z];j++){
                *content[*y+*z][j]=s;
            }
            for(int j = 1; j<=content_size[*z+*y]-cond;j++){
                *content[*y+*z][j] = aux[j];
            }
            content_size[*y+*z]=content_size[*z+*y]-cond;
            *y=*y-1;
            *x = content_size[*y+*z];                                   
            content_size[*y+*z]+=cond;
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }
        else{
            cond = content_size[*y+*z];
            //                                         tb = 0;
            int i = 1;
            while(i < cond+1){
                *content[*y+*z-1][content_size[*y+*z-1]+i-1] = *content[*y+*z][i];
                i++;
                //                                         ns += 1;
            }
            *y=*y-1;
            *x = content_size[*y+*z];                                   
            content_size[*y+*z]+=cond-1;
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
            char* aux=malloc(sizeof(char*)*cond);
            for(int i = cond; i<=content_size[*y+*z+1];i++){
                aux[i-cond+1] = *content[*y+*z+1][i];
            }
            //                                         for(int i = 1; i<content_size[*y+1+z];i++){
            //                                             *content[*y+z+1][i]=u;
            //                                         }
            for(int i = 1; i<=content_size[*y+*z+1]-cond;i++){
                *content[*y+*z+1][i] = aux[i];
            }
            //                                     content_size[*y+z+1] = content_size[*y+z+1]-ns+1;
            
            *lines=*lines-1;
            for(int i = *y+*z+1;i<*lines;i++){
                content_size[i]=content_size[i+1];
                for(int u = 1;u<=content_size[i];u++){
                    *content[i][u]=*content[i+1][u];
                }
            }
            //                                     content_size[*y+z]-=1;
        }
        
        echo_screen(content,content_size,*z+1,*r);
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
    }else{
        ////////////////////COPY DANS LA CAS D'AVANT///////////////////////////
        *x=*x-1;
        content_size[*y+*z]--;
        //////////////////////////////////////////////////////////bug HERE j<content_size[*y+z] OR j<=content_size[*y+z]///////////
        for(int j = *x-1;j<content_size[*y+*z];j++){
            *content[*y+*z][j+1]=*content[*y+*z][j+2];
        }
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
        
        for(int i = *x;i<=content_size[*y+*z];i++){
            write(0,content[*y+*z][i],1);
            fflush(stdout);
        }
        *content[*y+*z][content_size[*y+*z]]=u;
        printf("\x1b[%d;%dH",*y,content_size[*y+*z]);
        write(0,&u,1);
        fflush(stdout);
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
    }
}
