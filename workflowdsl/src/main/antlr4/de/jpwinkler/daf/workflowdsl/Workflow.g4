grammar Workflow;

WS
:
	[ \t\r\n]+ -> skip
; // skip spaces, tabs, newlines
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip;

workflow
:
	(
		elements += workflowElement
	)*
;

workflowElement
:
	target
	| step
	| moduleSet
	| variable
;

target
:
	'target' stepRef = ID
;

moduleSet
:
	'moduleset' name = ID '{'
	(
		moduleSetEntry
	)* '}'
;

MODULE_SET_ENTRY_TYPE
:
	'csv'
	| 'doors'
	| 'doorsurl'
	| 'csvfolder'
;

moduleSetEntry
:
	type = MODULE_SET_ENTRY_TYPE reference = STRING
;

variable
:
	simpleVariable
	| arrayVariable
;

simpleVariable
:
	'var' name = ID '=' value = STRING
;

arrayVariable
:
	'var' name = ID '[]' '=' '{'
	(
		items += STRING
		(
			',' items += STRING
		)*
	)? '}'
;

step
:
	modelConstructorStep
	| modelOperationStep
;

modelConstructorStep
:
	'constructor' name = ID '{'
	(
		features += operationFeature
	)* '}'
;

modelOperationStep
:
	'op' name = ID '{'
	(
		features += operationFeature
	)* '}'
;

operationFeature
:
	dependencyFeature
	| implementationFeature
	| forFeature
	| sourceFeature
;

sourceFeature
:
	'source' moduleSetRef = ID
;

forFeature
:
	'for' loopVar = ID 'in' arrayVar = ID '{'
	(
		features += operationFeature
	)* '}'
;

dependencyFeature
:
	'dependency' stepRef = ID
	(
		'{'
		(
			variables += variable
		)* '}'
	)?
	(
		name = ID
	)?
;

implementationFeature
:
	'implementation' implementation = STRING
;

fragment ESC_SEQ : '\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\');
STRING :   '"' ( ESC_SEQ | ~('\\'|'"') )* '"';

ID
:
	(
		'a' .. 'z'
		| 'A' .. 'Z'
		| '_'
	)
	(
		'a' .. 'z'
		| 'A' .. 'Z'
		| '0' .. '9'
		| '_'
	)*
;

//field:
//    name=Name NL
//    (points NL)+
//    (burial NL)+
//    EOF;
//
//points: treasure=Name WS 'scores' WS value=Int WS 'points';
//burial: treasure=Name WS 'is' WS 'buried' WS 'at' WS at=location ;
//location: x=Int ',' y=Int;
//
//Name: '"' ('A'..'Z' | 'a'..'z' | ' ')+ '"' ;
//Int: ('0'..'9')+;
//
//WS: (' ' | '\t')+;
//NL:  '\r'? '\n';
