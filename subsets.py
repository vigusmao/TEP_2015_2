def backtrack(n, min_size, max_size, subset, all_subsets):
    # check the current state
    if min_size <= len(subset) <= max_size:
        all_subsets.append(subset[:])
    if len(subset) == max_size:
        return

    # generate next states
    biggest_element = 0
    if len(subset) > 0:
        biggest_element = subset[-1]
    for next_element in range(biggest_element + 1, n + 1):
        subset += [next_element]  # subset.append(next_element)
        backtrack(n, min_size, max_size, subset, all_subsets)
        subset.pop()
   


def generate_subsets(n, min_size, max_size):
    all_subsets = []
    subset = []
    backtrack(n, min_size, max_size, subset, all_subsets)
    for s in all_subsets:
        print(s)

        




