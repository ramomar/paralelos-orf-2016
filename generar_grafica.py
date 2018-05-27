import plotly
import plotly.graph_objs as go

f = open('resultados.txt', 'r')

eje_x = []
eje_y = []

for linea in f:
  columnas = linea.split(' ')
  eje_x.append(columnas[0])
  eje_y.append(columnas[1])

trace = go.Scatter(
    x = eje_x,
    y = eje_y
)

layout = dict(title = "Desempeño ORF al analizar 11974 secuencias",
                       xaxis = dict(title = "Numéro de threads"),
                       yaxis = dict(title = "Tiempo (ms)")
                       )

data = [trace]

plotly.offline.plot(dict(data = data, layout = layout))
