from time import time
memo = {}


def fatorial(n):
    if n < 2:
        return 1
    return n * fatorial(n-1)

def fatorial_nr(n):
    resultado = 1
    for i in range(1, n+1):
        resultado *= i
    return resultado



def fib(n):
    if n in memo:
        return memo[n]
    
    if n <= 1:
        result = 1
    else:
        result = fib(n-1) + fib(n-2)

    memo[n] = result
        
    return result


def fib_nr(n):
    a, b = 1, 1

    cont = 1
    while cont < n:
        a, b = b, a+b
        cont += 1

    return b




start = time()
fib(400)
elapsed_rec = time() - start
print(elapsed_rec)

start = time()
fib_nr(400)
elapsed_nr = time() - start
print(elapsed_nr)












    


