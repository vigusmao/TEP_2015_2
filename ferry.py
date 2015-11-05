def backtrack(state, vehicles, L, n_boarded_vehicles, max_boarded_vehicles):
    if state[0] > L:
        return  # invalid state

    if n_boarded_vehicles > max_boarded_vehicles[0]:
        max_boarded_vehicles[0] = n_boarded_vehicles

    length_next_vehicle = vehicles[n_boarded_vehicles]

    new_state = [state[0] + length_next_vehicle, state[1]]
    backtrack(new_state, vehicles, L, n_boarded_vehicles + 1, max_boarded_vehicles)

    new_state = [state[0], state[1] + length_next_vehicle]
    new_state.sort(reverse=True)  # [more_loaded, less_loaded] by convention
    backtrack(new_state, vehicles, L, n_boarded_vehicles + 1, max_boarded_vehicles)


def ferry(vehicles, L):
    max_boarded_vehicles = [0]
    state = [0,0]
    backtrack(state, vehicles, L, 0, max_boarded_vehicles)
    
    print("We can board up to %d vehicles" % max_boarded_vehicles[0])
