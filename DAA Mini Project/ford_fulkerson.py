from collections import deque

def bfs(rGraph, s, t, parent):
    visited = [False] * len(rGraph)
    queue = deque([s])
    visited[s] = True

    while queue:
        u = queue.popleft()
        for v, capacity in enumerate(rGraph[u]):
            if not visited[v] and capacity > 0:
                queue.append(v)
                visited[v] = True
                parent[v] = u
                if v == t:
                    return True
    return False


def ford_fulkerson(graph, source, sink):
    rGraph = [row[:] for row in graph]
    parent = [-1] * len(graph)
    max_flow = 0

    while bfs(rGraph, source, sink, parent):
        path_flow = float("inf")
        s = sink
        while s != source:
            path_flow = min(path_flow, rGraph[parent[s]][s])
            s = parent[s]

        max_flow += path_flow

        v = sink
        while v != source:
            u = parent[v]
            rGraph[u][v] -= path_flow
            rGraph[v][u] += path_flow
            v = parent[v]

    return max_flow
