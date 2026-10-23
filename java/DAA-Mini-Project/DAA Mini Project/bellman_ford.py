import sys

def bellman_ford(graph, V, src):
    dist = [float('inf')] * V
    dist[src] = 0

    for _ in range(V - 1):
        for u, v, w in graph:
            if dist[u] != float('inf') and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w

    # Check for negative weight cycle
    for u, v, w in graph:
        if dist[u] != float('inf') and dist[u] + w < dist[v]:
            print("Graph contains a negative weight cycle!")
            return None

    return dist


def all_pairs_shortest_path(graph, V):
    result = []
    for i in range(V):
        res = bellman_ford(graph, V, i)
        result.append(res)
    return result
