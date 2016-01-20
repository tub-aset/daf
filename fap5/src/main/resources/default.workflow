target UpdateExcelOp

var database = "44055@doors10.rd.corpintra.net"
var username = "jonwink"
var password = ""
var exportFolder = "C:\\WORK\\DOORS\\export\\fap5\\${system.date}"
var moduleList = "C:\\WORK\\DOORS\\modulelists\\fap5-2.txt"

var sourceExcelFile = "C:\\Users\\jonwink\\Desktop\\FAP5_mpanagi_SLH_KLH_progress_tracking.xlsx"
var targetExcelFile = "C:\\Users\\jonwink\\Desktop\\FAP5_mpanagi_SLH_KLH_progress_tracking_modified.xlsx"

moduleset CodeBeamer {
	csvfolder "${exportFolder}"
}

op ExportModulesOp {
	implementation "de.jpwinkler.daf.fap5.ExportModulesOp"
}

constructor CodeBeamerModelConstructor {
	source CodeBeamer
	dependency ExportModulesOp exportModules
	implementation "de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"
}

op UpdateExcelOp {
	dependency CodeBeamerModelConstructor codeBeamerModels
	implementation "de.jpwinkler.daf.fap5.UpdateExcelOp"
}
