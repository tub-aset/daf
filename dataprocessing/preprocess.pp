typedef QUOTED_STRING
typedef PLACEHOLDER_TOKEN
typedef UNIT
typedef NUMBER(EXPRESSION)
typedef NUMBER_DECIMAL(NUMBER)
typedef RANGE(NUMBER)
typedef NUMBER_HEXADECIMAL(NUMBER)
typedef NUMBER_WITH_UNIT(NUMBER)
typedef DIMENSION(NUMBER)
typedef OPERATOR
typedef INTERVAL
typedef COMPARISON
typedef ELLIPSIS
typedef EMAIL
typedef SIGN
typedef PART_IDENT_NR(NUMBER)
typedef MONTH
typedef DATE
typedef TELEPHONE_NUMBER
typedef REFERENCE
typedef WEB
typedef REFERENCE_WITH_REV(REFERENCE)
typedef CHAPTER_REF(REFERENCE)
typedef DOORS_ID(REFERENCE)
typedef NORM(REFERENCE)
typedef FILENAME(REFERENCE)
typedef NAMED_REFERENCE(REFERENCE)
typedef QEV(REFERENCE)
typedef VEHICLE_SERIES
typedef STAR_NR
typedef KLEMME

typedef EXPRESSION

typedef WORD
typedef DELIMITER

//placeholders
"»","t",".","b",".","d",".","«" -> concat(PLACEHOLDER_TOKEN)
"»","TT",".","MM",".","JJJJ","«" -> concat(PLACEHOLDER_TOKEN)
"»",".",".",".","«" -> concat(PLACEHOLDER_TOKEN)

// stuff
("+","/","-")|"±" -> concat(SIGN)
"…"|(".",".",".") -> concat(ELLIPSIS)
"Januar"|"Februar"|"März"|"April"|"Mai"|"Juni"|"Juli"|"August"|"September"|"Oktober"|"November"|"Dezember" -> concat(MONTH)

// quoted strings
"»"|"«"|"„"|"“"|"”"|(">",">")|("<","<") -> replace("____")
"\"",_*,"\"" -> concat(QUOTED_STRING, " ")

// numbers
'[0-9]*'+ -> concat(NUMBER)
"0","x",'[0-9A-Fa-f]*'+ -> concat(NUMBER_HEXADECIMAL)

// dates
t:NUMBER,".",t:MONTH,t:NUMBER -> concat(DATE)
'[0-9]{2}',".",'[0-9]{2}',".",'[0-9]{4}' -> concat(DATE)
'[0-9]{2}',".",'[0-9]{2}',".",'[0-9]{2}' -> concat(DATE)

// document references
"DC","-"?,t:NUMBER -> concat(REFERENCE)
"MBN","LV"?,t:NUMBER -> concat(REFERENCE)
"LV",t:NUMBER -> concat(REFERENCE)
("DIN"|"EN"|"ISO"){1-},t:NUMBER -> concat(NORM)
t:REFERENCE,("-",t:NUMBER){1-} -> concat(REFERENCE)
t:REFERENCE,"Rev","-"?,'[A-Za-z]' -> concat(REFERENCE_WITH_REV)
"[",t:REFERENCE,"]" -> concat(REFERENCE)
'QEV(.*)' -> type(QEV)

// other references
"A",t:NUMBER -> concat(PART_IDENT_NR)
'A[0-9]+' -> concat(PART_IDENT_NR)
"+",t:NUMBER,(("-"|"/"),t:NUMBER)+ -> concat(TELEPHONE_NUMBER)
"Kapitel"|("Kap",".")|("Sec","."),t:NUMBER,(".",t:NUMBER)* -> concat(CHAPTER_REF)
"[",_+,"-",t:NUMBER,"]" -> concat(DOORS_ID)
('[a-zA-Zäöüß]*'|"."|"-")+,"@",'[a-zA-Z]*',".",'[a-zA-Z]*' -> concat(EMAIL)
"http"|"https",":","/","/",_+,(".","com"|"de") -> concat(WEB)
'[A-Z]'|"BR",'[0-9]{3}' -> concat(VEHICLE_SERIES)
'[A-Z][0-9]{3}' -> concat(VEHICLE_SERIES)
"Star",t:NUMBER,(".","x"|t:NUMBER)? -> concat(STAR_NR)

// named references
t:QUOTED_STRING,t:REFERENCE -> concat(NAMED_REFERENCE)
t:REFERENCE,t:QUOTED_STRING -> concat(NAMED_REFERENCE)

// other
("Klemme")|("KL"|"Kl","."?),"-"?,t:NUMBER,(("#",t:NUMBER)|'[A-Za-z]'|("_","x"))? -> concat(KLEMME)

// units
'(µ|k|m|M|G)?(A|s|V|Hz|g|Hz|l|N|Ohm|m)'|"qmm"|"sec"|"O"|"Volt"|"min"|"hPa"|"pF"|"O"|"dB"|"ppb"|"ppm"|("°","C")|"°"|"h"|"%"|"K" -> concat(UNIT)
repeat {
	t:UNIT,"²"|"³" -> concat(UNIT)
	t:UNIT,"/",t:UNIT -> concat(UNIT)
	"(",t:UNIT,")" -> concat(UNIT)
}

// operators
("<","=")|(">","=")|("=","=")|"<"|">"|"="|"*"|"+"|"-"|"/" -> concat(OPERATOR)

// advanced numbers
repeat t:NUMBER,".",t:NUMBER -> concat(NUMBER)
t:NUMBER,",",t:NUMBER -> concat(NUMBER_DECIMAL)
t:NUMBER,"^","-"?,t:NUMBER -> concat(NUMBER)
("+"|"-"|t:SIGN)?,t:NUMBER,t:UNIT -> concat(NUMBER_WITH_UNIT, " ")
t:NUMBER,"±"|t:ELLIPSIS|"-"|"bis",t:NUMBER -> concat(RANGE)
"(",t:NUMBER,")",t:UNIT -> concat(NUMBER_WITH_UNIT, " ")
t:NUMBER,"x",t:NUMBER,"x",t:NUMBER -> concat(DIMENSION)

// formulaes
//t:NUMBER,t:OPERATOR,_,t:OPERATOR,t:NUMBER -> concat(INTERVAL)
//_,t:OPERATOR,_ -> concat(COMPARISON)
repeat {
	t:EXPRESSION,t:OPERATOR,t:EXPRESSION -> concat(EXPRESSION)
	"(",t:EXPRESSION,")" -> concat(EXPRESSION)
}

(!t:*) & '[a-zA-ZöäüßÖÄÜ\\-0-9]*' -> type(WORD)
"."|";"|","|"/"|":"|"("|")"|"?"|"!" -> type(DELIMITER)
!t:* -> remove
//_ -> lowercase
t:WORD|t:DELIMITER -> untype
t:* -> generalize
