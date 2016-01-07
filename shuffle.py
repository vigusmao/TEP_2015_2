from random import randint
from math import factorial

N = 3


def eduardo_shuffle(lista):
    n = len(lista)
    for i in range(n):
        # sorteie o indice que farah swap com o indice i
        indice = randint(0, n-1)
        lista[i], lista[indice] = lista[indice], lista[i]


def knuth_shuffle(lista):
    n = len(lista)
    for i in range(n):
        # sorteie o indice que farah swap com o indice i
        indice = randint(i, n-1)
        lista[i], lista[indice] = lista[indice], lista[i]


while True:
    ocorrencias = {}
    for _ in range(factorial(N) * 100000):
        minha_lista = list(range(1, N+1))
        knuth_shuffle(minha_lista)
        tupla = tuple(minha_lista)  # imutavel, serve de chave para hash map
        ocorrencias[tupla] = ocorrencias.get(tupla, 0) + 1

    for tupla, contador in ocorrencias.items():
        print(tupla, "--->", contador)

    input()
    
    
