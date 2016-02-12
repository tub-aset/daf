typedef NUMBER_HEXADECIMAL(NUMBER)

//placeholders
"»","t",".","b",".","d",".","«" -> concat(PLACEHOLDER_TOKEN)
"»","TT",".","MM",".","JJJJ","«" -> concat(PLACEHOLDER_TOKEN)
"»",".",".",".","«" -> concat(PLACEHOLDER_TOKEN)

//strings
"»"|"«"|"„"|"“"|"”"|(">",">")|("<","<") -> replace("\"")
"\"",_*,"\"" -> concat(QUOTED_STRING, " ")

//numbers
"0",'x[0-9A-Fa-f]+' -> concat(NUMBER_HEXADECIMAL)
'[0-9]*'+ -> concat(NUMBER)
repeat t:NUMBER,".",t:NUMBER -> concat(NUMBER)
repeat t:NUMBER,t:NUMBER -> concat(NUMBER)
t:NUMBER,",",t:NUMBER -> concat(NUMBER)
t:NUMBER,"^","-"?,t:NUMBER -> concat(NUMBER)

//units
'(µ|k|m|M|G)?(A|s|V|Hz|g|Hz|l|N|Ohm|m)'|"qmm"|"sec"|"O"|"Volt"|"min"|"hPa"|"pF"|"O"|"dB"|"ppb"|"ppm"|("°","C")|"°"|"h"|"%"|"K" -> concat(UNIT)
repeat {
	t:UNIT,"²"|"³" -> concat(UNIT)
	t:UNIT,"^",t:NUMBER -> concat(UNIT)
	t:UNIT,"/",t:UNIT -> concat(UNIT)
	"(",t:UNIT,")" -> concat(UNIT)
}
("+","/","-")|"±" -> concat(SIGN)
("+"|"-"|t:SIGN)?,t:NUMBER,t:UNIT -> concat(NUMBER_WITH_UNIT, " ")
t:UNIT -> untype //sequences looking like units not in the context of numbers are not considered units.

//stuff
//("Klemme")|("KL"|"Kl","."?),"-"?,t:NUMBER,(("#",t:NUMBER)|'[A-Za-z]'|("_","x"))? -> concat(KLEMME)

//references
//'[A-Z]+',"-"?,t:NUMBER -> concat(REFERENCE)
"[",_+,"]" -> concat(REFERENCE)
t:QUOTED_STRING,t:REFERENCE -> concat(REFERENCE)
t:REFERENCE,t:QUOTED_STRING -> concat(REFERENCE)

//Identifier
!t:*&'.+[0-9].*' -> concat(ID)
repeat t:ID,t:NUMBER -> concat(ID)

//brackets
"(",_+,")" -> remove


t:* -> generalize