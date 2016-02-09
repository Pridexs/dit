// Caeser Cypher

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int letters[26] = { 0 };
    int i;
    char c;
    FILE *f;

    if (argc != 2)
    {
        printf("Usage: ./x file_name\n");
        return 0;
    }

    if ( (f = fopen(argv[1], "r")) == NULL)
    {
        printf("File not found\n");
        return 0;
    }

    int cont = 0;
    while ( (c = fgetc(f)) != EOF )
    {
        // Convert lowercase letter to uppercase
        if ( c >= 97 && c <= 122)
        {
            c -= 32;
        }

        if (c >= 65 && c <= 90)
        {
            printf("%c", ( ((c-65) + 9) % 26) + 65 );
        }
        else
        {
            printf("%c", c);
        }
    }
    printf("\n");
    return 0;
}
