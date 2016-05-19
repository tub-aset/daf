"(",_*,")" -> remove
"[",_*,"]" -> remove
"\"",_*,"\"" -> replace("QS")

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