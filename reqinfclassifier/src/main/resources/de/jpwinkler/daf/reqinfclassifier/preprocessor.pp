"(",_*,")" -> remove
"[",_*,"]" -> remove
//"\"",_*,"\"" -> replace("QS")

'[0-9]*' -> concat(NUMBER)
repeat {
  t:NUMBER,t:NUMBER -> concat(NUMBER)
  t:NUMBER,".",t:NUMBER -> concat(NUMBER)
  t:NUMBER,",",t:NUMBER -> concat(NUMBER)
}
t:NUMBER -> replace("0")

"allg","." -> replace("allgemein")
"z",".","B","." -> replace("zum Beispiel")
"z",".","b","." -> replace("zum Beispiel")
"bzw","." -> replace("beziehungsweise")
"sog","." -> replace("sogenannt")
"bzgl","." -> replace("bezüglich")
"ggf","." -> replace("gegebenenfalls")
"ggü","." -> replace("gegenüber")
"evtl","." -> replace("eventuell")
"bspw","." -> replace("beispielsweise")
"inkl","." -> replace("inklusive")
"Ca","." -> replace("zirka")
"ca","." -> replace("zirka")