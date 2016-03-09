#
# References:
#   - https://en.m.wikipedia.org/wiki/Modular_exponentiation#Right-to-left_binary_method
#

import random
import math
import pickle
import copy
from itertools import combinations

class RSA():

    def __init__(self):
        self.genKeys()

    def getPublicKey(self):
        return self.n, self.e

    def getPrivateKey(self):
        return self.d

    def setPublicKey(self, n, e):
        self.n, self.e = n, e

    def setPrivateKey(self, d):
        self.d = d

    def genKeys(self):
        p = self.genPrime()
        q = self.genPrime()

        # Generating n
        self.n = p * q

        # Generating e
        self.x = (p-1) * (q-1)
        self.e = 65537
        while True:
            if (self.coPrime([self.e, self.x])):
                break
            e = e + 1

        # Generating d
        self.d = self.modInv(self.e, self.x)

        print('Variables: ')
        print('p = ' + str(p))
        print('q = ' + str(q))
        print('n = ' + str(self.n))
        print('x = ' + str(self.x))
        print('e = ' + str(self.e))
        print('d = ' + str(self.d))
        print('-'*30)

    # Generates a 128-bit prime number
    def genPrime(self):
        num = random.getrandbits(127) + (1 << 127)
        if (num % 2 == 0):
            num += 1

        while (not self.isPrime(num)):
            num = random.getrandbits(127) + (1 << 127)
            if (num % 2 == 0):
                num += 1

        return num

    def isPrime(self, num):
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

    def gcd(self, a, b):
        a = abs(a)
        b = abs(b)
        if a < b:
            a, b = b, a
        while b != 0:
            a, b = b, a % b
        return a

    # Extended Euclidean Algorithm
    def egcd(self, a, b):
        x,y, u,v = 0,1, 1,0
        while a != 0:
            q, r = b//a, b%a
            m, n = x-u*q, y-v*q
            b,a, x,y, u,v = a,r, u,v, m,n
        gcd = b
        return gcd, x, y

    # CHECK THIS FUNCTION
    def coPrime(self, l):
        # returns 'True' if the values in the list L are all co-prime
        # otherwise, it returns 'False'.
        for i, j in combinations(l, 2):
            if self.gcd(i, j) != 1:
                return False
        return True

    # CHECK THIS FUNCTION
    def modInv(self, a, m):
        # returns the multiplicative inverse of a in modulo m as a
        # positive value between zero and m-1
        # notice that a and m need to co-prime to each other.
        if self.coPrime([a, m]):
            linearCombination = self.egcd(a, m)
            return linearCombination[1] % m
        else:
            return 0

    def blocks2numList(self, blocks, n):
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
    def int2baseTwo(self, x):
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
    def modExp(self, a, d, n):
        """returns a ** d (mod n)"""
        assert d >= 0
        assert n >= 0
        base2D = self.int2baseTwo(d)
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

    def string2numList(self, strn):
        """Converts a string to a list of integers based on ASCII values"""
        print(strn)
        return [ chars for chars in pickle.dumps(strn) ]


    def numList2string(self, l):
        """Converts a list of integers to a string based on ASCII values"""
        return pickle.loads(''.join(map(chr, l)))


    def numList2blocks(self, l, n):
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
    def encrypt(self, message, modN, e, blockSize=15):
        """given a string message, public keys and blockSize, encrypt using
        RSA algorithms."""
        numList = self.string2numList(message)
        numBlocks = self.numList2blocks(numList, blockSize)
        return [self.modExp(blocks, e, modN) for blocks in numBlocks]

    # CHECK THIS FUNCTION
    def decrypt(self, secret, modN, d, blockSize=15):
        """reverse function of encrypt"""
        numBlocks = [self.modExp(blocks, d, modN) for blocks in secret]
        numList = self.blocks2numList(numBlocks, blockSize)
        return numList2string(numList)


#cipher = encrypt(message, n, e, 15)
#print('Cipher: ')
#print(cipher)
#deciphered = decrypt(cipher, n, d, 15)
#print(deciphered)

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
