// Counts letter occurance in a file

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

    while ( (c = fgetc(f)) != EOF )
    {
        if ( c >= 97 && c <= 122)
        {
            c -= 32;
        }
        if (c >= 65 && c <= 90)
        {
            letters[c-65]++;
        }
    }

    for (i = 0; i < 26; i++)
    {
        printf("%c: %d\n", i+65, letters[i]);
    }

    return 0;
}
