grammar DoorsTreeNodeFilter;

/**
  *
  * The following is the Anltr grammar for the filter language implemented by dafcore.
  * If such a filter is applied to a tree of folders, projects and objects, only
  * entities matching the filter (or entities which are parents of entities matching
  * the filter) will be accessible.
  *
 **/

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

/**
 *
 * attributeName can be any attribute of a DoorsTreeNode (i.e. a project, a folder, or an object).
 * In addition, the following special attributes are supported:
 *    "__level__": The actual object level (not the one specified in the 
 *                 "Object Level" column
 *    "__path__":  The full path of a tree node. Please not that the path element 
 *                 for a doors object is null, so this makes only sense to match projects or folders.
 *    "__name__":  The path element name of a tree node. The remark for __path__ applies as well: 
 *                 for a DoorsObject, this will return null.
 *    "__text__":  The object text on normal objects and the object heading on heading objects. 
 *                 Null for everything else.
 *    "__type__":  "DoorsTableRow" for a row of a table, "DoorsObject" for objects, 
 *                 "DoorsFolder" for folders, "DoorsProject" for projects.
 *
**/