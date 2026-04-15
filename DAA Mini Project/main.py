import time
from bellman_ford import all_pairs_shortest_path
from ford_fulkerson import ford_fulkerson

# -------- Bellman-Ford --------
graph_edges = [
    (0, 1, 4),
    (0, 2, 5),
    (1, 2, -3),
    (2, 3, 4),
    (3, 1, 6)
]
V = 4

print("=" * 45)
print("  All Pairs Shortest Path - Bellman Ford")
print("=" * 45)

start = time.time()
apsp = all_pairs_shortest_path(graph_edges, V)
end = time.time()

for i, row in enumerate(apsp):
    if row is None:
        print(f"From vertex {i}: Negative cycle detected")
    else:
        formatted = ["inf" if x == float('inf') else str(x) for x in row]
        print(f"From vertex {i}: {formatted}")

print(f"Execution Time: {round((end - start) * 1000, 4)} ms")

# -------- Ford-Fulkerson --------
graph_capacity = [
    [0, 8, 0, 0, 3, 0],
    [0, 0, 9, 0, 0, 0],
    [0, 0, 0, 7, 0, 2],
    [0, 0, 0, 0, 0, 5],
    [0, 0, 7, 4, 0, 0],
    [0, 0, 0, 0, 0, 0]
]
source = 0
sink = 5

print()
print("=" * 45)
print("   Maximum Flow - Ford Fulkerson")
print("=" * 45)

start = time.time()
max_flow = ford_fulkerson(graph_capacity, source, sink)
end = time.time()

print(f"Max Flow: {max_flow}")
print(f"Execution Time: {round((end - start) * 1000, 4)} ms")
print("=" * 45)
