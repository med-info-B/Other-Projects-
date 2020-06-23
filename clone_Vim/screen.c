#include "screen.h"
void echo_line(char*** content,int content_size[],int y){
    for(int i = 1;i<=content_size[y];i++){
        write(1,content[y][i],1); 
        fflush(stdout);
    }
    printf("\n");
    fflush(stdout);
}

void clear_screen(){
    pid_t p;
    if((p=fork())==0)
    {
        char** ll = malloc(2*sizeof(char*));
        ll[0] = "clear";
        execvp(ll[0],ll);
        free(ll);
    }
    else{  
        int status;
        waitpid(p,&status,0);
    }
}

void echo_screen(char*** content,int content_size[],int y,int r){
    clear_screen();
    int a;
    for(int i=0;i<r-1;i++){
        a = i+2;
        echo_line(content,content_size,i+y);
        printf("\x1b[%d;%dH",a,1); 
        fflush(stdout);
    }
    printf("\x1b[%d;%dH",r,1); 
    fflush(stdout);
    printf("\x1b[1m%s","");
    fflush(stdout);
    write(1,"--MODE INSERTION--",18);
    fflush(stdout);
    printf("\x1b[0m%s","");
    printf("\x1b[%d;%dH",a,1); 
    fflush(stdout);
}


void content_write(char* buffer,int content_size[],int* x,int* y,bool* end,int* z,char*** content,int* lines,int* r,int* c){
    if(content_size[*y+*z] == *c){
        if(*x == *c){
            *y=*y+1;
            *x=1;
        }
        if(content_size[*y+*z+1]==*c){
            *lines=*lines+1;
            char s = ' ';
            for(int i = *lines;i>*y+*z;i--){
                content_size[i]=content_size[i-1];
                for(int u = 1;u<=content_size[i];u++){
                    *content[i][u]=*content[i-1][u];
                }
            }
            for(int i = 1; i<=content_size[*y+*z+1];i++){
                *content[*y+*z+1][i]=s;
            }
            content_size[*y+*z+1]=1;
            
            echo_screen(content,content_size,*z+1,*r);
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }else{   
            content_size[*y+*z+1]++;
            for(int j = content_size[*y+*z+1];j>1;j--){
                *content[*y+*z+1][j]=*content[*y+*z+1][j-1];
            }
            *content[*y+*z+1][1]=*content[*y+*z][content_size[*z+*y]];
            for(int j = content_size[*y+*z];j>*x-1;j--){
                *content[*y+*z][j]=*content[*y+*z][j-1];
            }
            *content[*y+*z][*x]=*buffer;
            fflush(stdout);
            *x=*x+1;
            
            echo_screen(content,content_size,*z+1,*r);
            printf("\x1b[%d;%dH",*y,*x);
            fflush(stdout);
        }
    }else{
        
        if(*x == *c){
            *y=*y+1;
            *x=1;
        }
        
        
        char* aux=malloc(sizeof(char*)*(content_size[*y+*z]));
        for(int i = content_size[*y+*z];i>*x-1;i--){
            aux[i]=*content[*y+*z][i-1];
        }
        for(int i = content_size[*y+*z];i>*x-1;i--){
            *content[*y+*z][i]=aux[i];
        }
        content_size[*y+*z]++;
        free(aux);
        *content[*y+*z][*x]=*buffer;
        fflush(stdout);
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
        *x=*x+1;
        write(0,buffer,1);  
        fflush(stdout);
        
        echo_screen(content,content_size,*z+1,*r);
        printf("\x1b[%d;%dH",*y,*x);
        fflush(stdout);
        //     tlus++;
        buffer[0]= '\0';
        buffer[1]= '\0';
        buffer[2]= '\0';
        //                         }
        ///FIN ECRIR A L'ECRAN ET ECRIR DANS CONTENT////////
    }
}
