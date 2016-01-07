from random import randint

N_ELEMENTOS = 1000
FATOR_DE_CARGA = 0.2
LIMITE_CUCADAS = 1000


def criar_cuckoo_hash(capacidade, fator_de_carga):
    tamanho = 3771
    t0 = [None] * tamanho
    t1 = [None] * tamanho
    return (t0, t1)

def h0(cuckoo, chave):
    tamanho = len(cuckoo[0])
    return chave * 1399 % tamanho

def h1(cuckoo, chave):
    tamanho = len(cuckoo[0])
    return (chave - 2) * 5237 % tamanho

def h(cuckoo, chave, index_tabela):
    if index_tabela == 0:
        return h0(cuckoo, chave)
    return h1(cuckoo, chave)

def getpos(cuckoo, index_tabela, pos):
    return cuckoo[index_tabela][pos]

def putpos(cuckoo, index_tabela, pos, chave):
    cuckoo[index_tabela][pos] = chave

def contains(cuckoo, chave):
    pos = h0(cuckoo, chave)
    if cuckoo[0][pos] == chave:
        return True
    pos = h1(cuckoo, chave)
    if cuckoo[1][pos] == chave:
        return True
    return False

def put(cuckoo, chave):
    if contains(cuckoo, chave):
        return 0
        
    index_tabela = 0
    cont_cucadas = 0
    while cont_cucadas < LIMITE_CUCADAS:
        pos = h(cuckoo, chave, index_tabela)
        old_chave = getpos(cuckoo, index_tabela, pos)
        putpos(cuckoo, index_tabela, pos, chave)
        if old_chave == None:
            return cont_cucadas # ninguem foi ejetado
        index_tabela = (index_tabela + 1) % 2
        chave = old_chave
        cont_cucadas += 1
    # estourou o limite, precisamos fazer um rehash
    print("REHASH!!!")
    return None



elementos = set()
while len(elementos) < N_ELEMENTOS:
    elementos.add(randint(1, 10**6))

cuckoo = criar_cuckoo_hash(N_ELEMENTOS, FATOR_DE_CARGA)
total_cucadas = 0
total_inserts = 0
for elemento in elementos:
    resultado = put(cuckoo, elemento)
    if resultado == None:  # nao conseguiu inserir
        break
    total_cucadas += resultado
    total_inserts += 1

print("cucadas = %d, inserts = %d" % (total_cucadas, total_inserts))
    
    
 

     
        
        
    
    
