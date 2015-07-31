target Step

moduleset Set {
	csv "myfile"
}

constructor Step {
	source Set
	implementation "myimplementation"
}

op Step2 {
	dependency Step s
	implementation "impl"
}