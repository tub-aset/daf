constructor c {}

constructor o {
	dependency c d1
	dependency c {} d2
	dependency c {
		var x = "a"
		var y[] = {"a", "b"}
	} d3
}
