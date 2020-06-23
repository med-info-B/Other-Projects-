#ifndef __GESTION_SOURIS_H__
#define __GESTION_SOURIS_H__

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ioctl.h>

typedef struct coord_S coord_S_t;
typedef struct coord_C coord_C_t;

/* Structure  contenant les coordonnées relatives par rapport au repère liée à la souris à chaque deplacement de cette dernière */
struct coord_S
{
	signed char pos_x;
	signed char pos_y;
}; 
/* Structure contenat les coordoonnées absolu de curseur principale*/
struct coord_C
{
	signed char Curs_x;
	signed char Curs_y;
};
                                //////////////////       PROTYPES DES FONCTIONS         //////////////////////////////
coord_S_t* position_souris( char* buffer);
void affiche_curseur(coord_S_t* e, coord_C_t* c);

#endif
