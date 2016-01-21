grammar DoorsObjectFilter;

WS: [ \t\r\n] -> skip;

ID: [a-zA-Z_]+;

STRING: '"' (ESC|.)*? '"' ;
fragment ESC : '\\"' | '\\\\' ; // 2-char sequences \" and \\

filterExpression
	:	'!' filterExpression #NotFilterExpression
	|	filterExpression '&' filterExpression #AndFilterExpression
	|	filterExpression '|' filterExpression #OrFilterExpression
	|	'(' filterExpression ')' #BracketFilterExpression
	|	attributeName = STRING 'MISSING' #AttributeMissingExpression
	|	(attributeName = STRING)? 'HAS' attributeValue = STRING #AttributeExpression
	|	(attributeName = STRING)? 'LIKE' regexp = STRING #AttributeRegexpExpression
	;