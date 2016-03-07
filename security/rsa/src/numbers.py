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

def gcd(a, b):
    r = 1
    while (r != 0):
        r = a % b
        a = b
        b = r

    return a

# Extended Euclidean Algorithm
def egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
    gcd = b
    return y

# Message
message = "attack at dawn"

# Encrypted Message
eMess = [ ]


# Generating p
p = random.getrandbits(127) + (1 << 127)
if (p % 2 == 0):
    p += 1

while (not isPrime(p)):
    p = random.getrandbits(127) + (1 << 127)
    if (p % 2 == 0):
        p += 1

# Generating q
q = random.getrandbits(127) + (1 << 127)
if (q % 2 == 0):
    q += 1
while (not isPrime(q)):
    q = random.getrandbits(127) + (1 << 127)
    if (q % 2 == 0):
        q += 1

# Generating n
n = p * q

# Generating e
x = (p-1) * (q-1)
e = 65537
while True:
    e = e + 1
    if (gcd(x, e) == 1):
        break

# Generating d
d = egcd(x, e)

print('p = ' + str(p))
print('q = ' + str(q))
print('n = ' + str(n))
print('x = ' + str(x))
print('e = ' + str(e))
print('d = ' + str(d))

# Encryption
for i in range(0, len(message)):
    print(len(message), i)
    eMess.append(( (ord(message[i]) ** e) % n))

# Decryption
print(eMess)

for i in range(0, len(eMess)):
    print(eMess[i])
    #char = (eMess[i] ** d) % n
    #print(char)
    #print(str(unichr(char)))
