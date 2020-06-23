EXEC=supervim
SOURCES=canonic.c escmode.c keyboard.c screen.c super-vim-main.c files.c gestion_souris.c
OBJECTS=$(SOURCES:.c=.o)
CC=gcc
CFLAGS = -Wall -std=gnu99 -g  # options du compilateur
LFLAGS = -lreadline
.PHONY: default clean
 
default: $(EXEC)
 
shell.o: canonic.c escmode.c keyboard.c screen.c super-vim-main.c files.c
 
%.o: %.c
	$(CC) -o $@ -c $< $(CFLAGS)

$(EXEC): $(OBJECTS)
	$(CC) -o $@ $^ $(LFLAGS)

clean:
	rm  $(EXEC) $(OBJECTS) $(SOURCES:.c=.c~) $(SOURCES:.c=.h~) Makefile~
