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

#include "canonic.h"
#include "keyboard.h"
#include "screen.h"
#include "escmode.h"
#include "files.h"
#include "gestion_souris.h"


int main(int argc,char* argv[]){    
    
    int fd2 = open("/dev/input/mice", O_RDONLY);
    if( fd2 == -1)
    {
        perror("open");
        exit(1);
    }
    coord_C_t* c1 = malloc(sizeof(coord_C_t));
    
    struct winsize w;
    ioctl(0, TIOCGWINSZ, &w);
    bool end = false;
    bool insert_mode = false;
    char buffer[4];
    int fd3,fd1;
    coord_S_t* s = position_souris(buffer);
    if(c1 == NULL || s == NULL)
    {
        perror("malloc");
        exit(1);
    }
    fd1 = STDIN_FILENO;
    int x = 1;
    int y = 0;
    int z = 1;
    int r = w.ws_row;
    int c = w.ws_col;
    int lines = 0;
    int tlus = 0;
    char*** content = malloc(sizeof(char*)*1024*w.ws_col);
    int content_size[1024];
    for(int i = 0;i<=1023;i++){
        content[i] = malloc(sizeof(char*)*1024*w.ws_col);
        for(int j = 0;j<=w.ws_col;j++){
            content[i][j] = malloc(sizeof(char*));
        }   
        content_size[i]= 1;
    }
    fd3 = open(argv[1],O_RDWR | O_CREAT, 0755);
    if(fd3 == -1)
    {
        perror("open");
        exit(1);
    }
    else{
        ////////COPY TEXT FROM FILE INTO ARRAY////////
        file_into_array(fd3,&x,&y,content,content_size,&tlus,c);
        //////////////////////CLEAR AND ECHO SCREEN////////////////////////////////////////
        lines = y+1;
        if(lines > r)
        {
            z = lines - r;
        }
        else
        {
            z = 0;
        }
        echo_screen(content,content_size,z,r);
        y = r;
        z -= 1;
        x = 1;
        term_save();
        ////POLL//////
        struct pollfd pfds[2];
        pfds[0].fd = fd1;
        pfds[1].fd = fd2;
        pfds[0].events=POLL_IN;
        pfds[1].events=POLL_IN;
        /////FIN POLL///
        /////STARTS POLL CALL/////////
        while(1)
        {
            poll(pfds,2,-1);
            for(int i = 0; i<2;i++)
            {
                if(pfds[i].revents & POLLIN)
                {
                    ssize_t nr=read(pfds[i].fd,buffer,4);
                    ///////////////////////START READING FROM STDIN_FILENO and MOUSE//////////////////////////
                    if(nr!=0)
                    {
                        
                        if(i==0)
                        {
                            
                            if(buffer[0]!= 27 && buffer[1]!= 91)
                            {
                            /////////////ON BACK SPACE///////////////////////
                                if(buffer[0] == 127 && (x + y >2))
                                {
                                    back_space(content_size,&x,&y,&z,content,&lines,&r,&c);
                                }
                            /////////////////////ON ENTER///////////////////
                            else if(buffer[0] == 13)
                            {
                                enter_key(content_size,&x,&y,&z,content,&lines,&r);
                                buffer[0] = '\0';
                                buffer[1] = '\0';
                                buffer[2] = '\0'; 
                            }
                            ///////////////WRITE IN THE SCREEN////////////// 
                            else if(buffer[0] != 127 && buffer[0] != 13 && buffer[1] != 10)
                            {
                                content_write(buffer,content_size,&x,&y,&end,&z,content,&lines,&r,&c);
                            }
                        }
                        else if(buffer[0]== 27 && buffer[1]== 91)
                        {
                            ///////////////////////START ARROWS CALL/////////////////////////
                            arrows(buffer,content_size,& x,& y,& end, &z,content,&lines,&r);
                        }
                        else
                            /////////// START ESC MODE /////////////////
                            if(buffer[0]== 27)
                            { 
                                if(esc_mode(content,content_size,fd2,c1, s,x, y, z, &insert_mode, r, argv[1], &lines) == -1)
                                {
                                    return 0;
                                }   
                                echo_screen(content,content_size,z+1,r);
                                printf("\x1b[%d;%dH",y,x);
                                fflush(stdout);
                            }
                        }
                        else{
                            ////GESTION DE LA SOURIS//////
                                s = position_souris(buffer);
                                affiche_curseur(s, c1);
                                x = c1 -> Curs_y;
                                y = c1 -> Curs_x;
                             
                        }
                        
                    }
                }
            }
        }
    }
}
