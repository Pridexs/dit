#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int gcd(int a, int b)
{
    int r;

    do
    {
        r = a % b;
        a = b;
        b = r;
        // for debug
        //printf("r=%d a=%d b=%d\n", r, a, b);
    } while (r != 0);

    return a;
}

int main()
{
    int a, b, ans;

    printf("Type a and b: ");
    scanf("%d %d", &a, &b);
    ans = gcd(a, b);
    printf("gcd(%d,%d) = %d\n", a, b, ans);

    return 0;
}
