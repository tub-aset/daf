grammar DoorsTreeNodeFilter;

WS: [ \t\r\n] -> skip;
ID: [a-zA-Z_]+;

STRING: '"' (ESC|.)*? '"' ;
fragment ESC : '\\"' | '\\\\' ; // 2-char sequences \" and \\

filterExpression
	:	'!' filterExpression #NotFilterExpression
	|	filterExpression '&' filterExpression #AndFilterExpression
	|	filterExpression '|' filterExpression #OrFilterExpression
        |	filterExpression '^' filterExpression #ExclusiveOrFilterExpression
        |       filterExpression '=>' filterExpression #ImplicationExpression
        |       filterExpression '<=>' filterExpression #EquivalenceExpression
	|	'(' filterExpression ')' #BracketFilterExpression
	|	attributeName = STRING 'MISSING' #AttributeMissingExpression
	|	attributeName = STRING 'HAS' attributeValue = STRING #AttributeLikeExpression
	|	attributeName = STRING 'LIKE' regexp = STRING #AttributeRegexpExpression
	|	attributeName = STRING 'IS' attributeValue = STRING #AttributeIsExpression
	;