#
# References:
#   - https://en.m.wikipedia.org/wiki/Modular_exponentiation#Right-to-left_binary_method
#

import random
import math
import pickle
import copy
from itertools import combinations

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
    a = abs(a)
    b = abs(b)
    if a < b:
        a, b = b, a
    while b != 0:
        a, b = b, a % b
    return a

# Extended Euclidean Algorithm
def egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
    gcd = b
    return gcd, x, y

# CHECK THIS FUNCTION
def coPrime(l):
    # returns 'True' if the values in the list L are all co-prime
    # otherwise, it returns 'False'.
    for i, j in combinations(l, 2):
        if gcd(i, j) != 1:
            return False
    return True

# CHECK THIS FUNCTION
def modInv(a, m):
    # returns the multiplicative inverse of a in modulo m as a
    # positive value between zero and m-1
    # notice that a and m need to co-prime to each other.
    if coPrime([a, m]):
        linearCombination = egcd(a, m)
        return linearCombination[1] % m
    else:
        return 0

def blocks2numList(blocks, n):
    """inverse function of numList2blocks."""
    toProcess = copy.copy(blocks)
    returnList = []
    for numBlock in toProcess:
        inner = []
        for i in range(0, n):
            inner.append(numBlock % 256)
            numBlock >>= 8
        inner.reverse()
        returnList.extend(inner)
    return returnList

# CHECK THIS FUNCTIO
def int2baseTwo(x):
    """x is a positive integer. Convert it to base two as a list of integers
    in reverse order as a list."""
    # repeating x >>= 1 and x & 1 will do the trick
    assert x >= 0
    bitInverse = []
    while x != 0:
        bitInverse.append(x & 1)
        x >>= 1
    return bitInverse

# CHECK THIS FUNCTION
def modExp(a, d, n):
    """returns a ** d (mod n)"""
    assert d >= 0
    assert n >= 0
    base2D = int2baseTwo(d)
    base2DLength = len(base2D)
    modArray = []
    result = 1
    for i in range(1, base2DLength + 1):
        if i == 1:
            modArray.append(a % n)
        else:
            modArray.append((modArray[i - 2] ** 2) % n)
    for i in range(0, base2DLength):
        if base2D[i] == 1:
            result *= base2D[i] * modArray[i]
    return result % n

def string2numList(strn):
    """Converts a string to a list of integers based on ASCII values"""
    return [ ord(chars) for chars in pickle.dumps(strn) ]


def numList2string(l):
    """Converts a list of integers to a string based on ASCII values"""
    return pickle.loads(''.join(map(chr, l)))


def numList2blocks(l, n):
    """Take a list of integers(each between 0 and 127), and combines them
    into block size n using base 256. If len(L) % n != 0, use some random
    junk to fill L to make it."""
    # Note that ASCII printable characters range is 0x20 - 0x7E
    returnList = []
    toProcess = copy.copy(l)
    if len(toProcess) % n != 0:
        for i in range(0, n - len(toProcess) % n):
            toProcess.append(random.randint(32, 126))
    for i in range(0, len(toProcess), n):
        block = 0
        for j in range(0, n):
            block += toProcess[i + j] << (8 * (n - j - 1))
        returnList.append(block)
    return returnList

# CHECK THIS FUNCTION
def encrypt(message, modN, e, blockSize):
    """given a string message, public keys and blockSize, encrypt using
    RSA algorithms."""
    numList = string2numList(message)
    numBlocks = numList2blocks(numList, blockSize)
    return [modExp(blocks, e, modN) for blocks in numBlocks]

# CHECK THIS FUNCTION
def decrypt(secret, modN, d, blockSize):
    """reverse function of encrypt"""
    numBlocks = [modExp(blocks, d, modN) for blocks in secret]
    numList = blocks2numList(numBlocks, blockSize)
    return numList2string(numList)

# Message
message = "attack at dawn asdsa"

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
    if (coPrime([e, x])):
        break

# Generating d
d = modInv(e, x)

print('p = ' + str(p))
print('q = ' + str(q))
print('n = ' + str(n))
print('x = ' + str(x))
print('e = ' + str(e))
print('d = ' + str(d))

cipher = encrypt(message, n, e, 15)
print('Cipher: ')
print(cipher)
deciphered = decrypt(cipher, n, d, 15)
print(deciphered)

# Encryption
#for i in range(0, len(message)):
#    print(len(message), i)
#    eMess.append(( (ord(message[i]) ** e) % n))
#
# Decryption
#print(eMess)
#
#for i in range(0, len(eMess)):
#    print(eMess[i])
    #char = (eMess[i] ** d) % n
    #print(char)
    #print(str(unichr(char)))
