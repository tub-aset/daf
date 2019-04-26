grammar DBSearch;

WS: [ \t\r\n] -> skip;

ID: [a-zA-Z_]+;

STRING: '"' (ESC|.)*? '"' ;
fragment ESC : '\\"' | '\\\\' ; // 2-char sequences \" and \\

searchExpression
	:	'!' searchExpression #NotSearchExpression
	|	searchExpression searchExpression #AndSearchExpression
	|	searchExpression '|' searchExpression #OrSearchExpression
	|	'(' searchExpression ')' #BracketSearchExpression
	|	'name' ':' name = STRING #NameSearchExpression
	|	'tag' ':' tag = STRING #TagSearchExpression
	|	'.' attName = STRING ':' attValue = STRING #AttributeSearchExpression
	;
