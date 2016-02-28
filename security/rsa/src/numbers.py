import random
import math

def isPrime(x):
    # Dumb way to find if the number is prime.
    j = math.sqrt(x)
    i = 2
    while True:
        if (x % i == 0):
            return False
        i = i + 1
    return True

x = 1random.getrandbits(127) + (1 << 127)
if (x % 2 == 0):
    x = x + 1

while (not isPrime(x)):
    x = x + 2
    x = random.getrandbits(128)
    if (x % 2 == 0):
        x = x + 1

print(x)
