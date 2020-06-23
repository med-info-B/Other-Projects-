#include "escmode.h"

// Fonction  gère les invitte de commande en mode normal
int esc_mode(char*** content,int content_size[],int fd2,coord_C_t* c1, coord_S_t* s ,int x,int y,int z,bool* insert_mode,int r,char* file,int* lines)
{
    printf("\x1b[%d;%dH",r,1); 
    fflush(stdout);
    printf("\x1b[1m%s","");
    fflush(stdout);
    write(1,"--ESC MODE (:q)for exit (:w) to save (:wq) to save and exit--",61);
    fflush(stdout);
    printf("\x1b[0m%s",""); 
    fflush(stdout);
    char buff[4];
    bool cont = true;
    int fd1  = STDIN_FILENO;
     struct pollfd pfds[2];
        pfds[0].fd = fd1;
        pfds[1].fd = fd2;
        pfds[0].events=POLL_IN;
        pfds[1].events=POLL_IN;
    while(cont)
    {  
       poll(pfds,2,-1);
       for(int i = 0; i<2;i++)
       {
        if(pfds[i].revents & POLLIN)
        {
            read(pfds[i].fd,buff ,4); 
            if(i == 0)
            {    
                if(buff[0]== 27 && buff[1]== 91)
                {
                    arrows(buff, content_size, & x, & y, & cont, &z,content, lines, &r);
                } 
      
            } 
        }
            if(i == 1)
            {
              s = position_souris(buff);
              affiche_curseur(s, c1);
               x = c1 -> Curs_y;
               y = c1 -> Curs_x;
            }    
     } // for
 }//while
    return 0;
}


  