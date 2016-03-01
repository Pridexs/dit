import random
import math

def isPrime(num):
    # Miller-Rabin primality test
    s = num - 1
    t = 0
    while (s % 2 == 0):
        s = s // 2
        t += 1

    for trials in range(64):
        a = random.randrange(2, num - 1)
        v = pow(a, s, num)
        if v != 1:
            i = 0
            while (v != (num -1)):
                if i == t - 1:
                    return False
                else:
                    i = i + 1
                    v = (v ** 2) % num
    return True

def isReallyPrime(num):
    i = 2
    m = int(math.sqrt(num) + 1)
    while (i < m):
        if num % i == 0:
            return False
        i += 1
    return True

x = random.getrandbits(127) + (1 << 127)
if (x % 2 == 0):
    x = x + 1

while (not isPrime(x)):
    x = random.getrandbits(128)
    if (x % 2 == 0):
        x = x + 1
'''
if (isReallyPrime(x)):
    print(x)
else:
    print('Not Really')
'''
print(x)
