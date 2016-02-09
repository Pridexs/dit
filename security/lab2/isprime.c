//
//  main.c
//  prime
//
//  Created by Fábio Dayrell Rosa on 09/02/2016.
//  Copyright © 2016 Fábio Dayrell Rosa. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int main(int argc, const char * argv[]) {
    
    int num = atoi(argv[1]);
    int numSqrt, i;
    
    if((num % 2) == 0)
    {
        printf("%d is not prime\n", num);
        return 0;
    }
    
    numSqrt = sqrt(numSqrt);
    
    for(i = 2; i*i < numSqrt; i++)
        if((num % i) != 0)
            return 0;
    
    printf("%d is prime\n", num);
    
    return 0;
}
