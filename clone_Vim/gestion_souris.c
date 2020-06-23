#include "gestion_souris.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
// La Fonction récupére  les postions lu à partir du discripture du fichier "/dev/input/mice ouvert" ouvert en lecture puis elle les mis dans la structure.
coord_S_t* position_souris(char* buffer)
{
	coord_S_t* e = malloc(sizeof(coord_S_t));
	if( e == NULL)
	{
		perror("malloc");
		exit(1);
	}
    e -> pos_x = buffer[2];
    e -> pos_y = buffer[1];
    return e; 
}
// La Fonction affiche le cursuer de coordornnée x et y  sur le terminal en respectant la taille de ce dernier
// @ structure standard contenant laille de l'écran y compris la largeur et la longueur  
void  affiche_curseur(coord_S_t* s, coord_C_t* c )
{
	if(s == NULL || c == NULL)
	{
		char* erreur = "Segmentation fault\n";
		write(1,erreur, sizeof(erreur));
		exit(1);
	}
	//@ Structure 1 
    struct winsize w;
    ioctl(0, TIOCGWINSZ, &w);
    if(c -> Curs_x >= 0 && c -> Curs_y >= 0)
    {    
        c -> Curs_x += s ->  pos_x;
        c -> Curs_y += s ->  pos_y;
    }
    else
    {
        c -> Curs_x = 1;
        c -> Curs_y = 1;   
    }    
    int size_x =  w.ws_col;
    if(c -> Curs_x >= size_x)
    {
    	c -> Curs_x = size_x;
    }
    //char buff[9];
    if(c -> Curs_x >= 0 && c -> Curs_y >= 0) 
    {    // quand j'utilise les deux fonctions standard j'ai du problème d'affichage sur l'ecran.
    	// sprintf(buff,"\x1b[%d;%dH", c -> Curs_y, c -> Curs_x);
    	// write(1,buff,sizeof(buff));
     	printf("\x1b[%d;%dH", c -> Curs_y, c -> Curs_x);
    	fflush(stdout);
    }	
}
