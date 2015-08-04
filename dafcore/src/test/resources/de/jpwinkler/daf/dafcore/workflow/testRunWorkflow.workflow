target Operation2

moduleset Set {
	csv "src/test/resources/de/jpwinkler/daf/dafcore/workflow/slh-wl.csv"
}

var a = "test"
var array1[] = {"a", "b", "c"}
var array2[] = {"1", "2", "3"}

constructor Constructor1 {
	source Set
	implementation "de.jpwinkler.daf.dafcore.workflow.WorkflowProcessorTest$Constructor1"
}

op Operation1 {
	implementation "de.jpwinkler.daf.dafcore.workflow.WorkflowProcessorTest$Operation1"
	dependency Constructor1 c1
}

op Operation2 {
	implementation "de.jpwinkler.daf.dafcore.workflow.WorkflowProcessorTest$Operation2"
	dependency Operation1 o1
	for e1 in array1 {
		for e2 in array2 {
			dependency Constructor1 {
				var p = "${e1}${e2}"
			} c1
		}
	}
}