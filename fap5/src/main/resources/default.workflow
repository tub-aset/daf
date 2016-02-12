target UpdateExcelOp

var exportFolder = "temp/${system.date}"

var sourceExcelFile = "I:\\E-I\\FAP5\\4_Lastenhefte\\4-3_Lastenhefte\\Allgemein\\FAP5_mpanagi_SLH_KLH_progress_tracking.xlsx"
var targetExcelFile = "I:\\E-I\\FAP5\\4_Lastenhefte\\4-3_Lastenhefte\\Allgemein\\FAP5_mpanagi_SLH_KLH_progress_tracking.xlsx"
var backupExcelFile = "C:\\Users\\jonwink\\Desktop\\backup\\FAP5_mpanagi_SLH_KLH_progress_tracking_${system.date}_${system.time}.xlsx"
//var targetExcelFile = "C:\\Users\\jonwink\\Desktop\\FAP5_mpanagi_SLH_KLH_progress_tracking_modified.xlsx"

moduleset CodeBeamer {
	csvfolder "${exportFolder}"
}

op ExportModulesOp {
	implementation "de.jpwinkler.daf.fap5.ExportModulesOp"
}

constructor CodeBeamerModelConstructor {
	dependency ExportModulesOp export
	source CodeBeamer
	implementation "de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"
}

op UpdateExcelOp {
	dependency CodeBeamerModelConstructor codeBeamerModels
	implementation "de.jpwinkler.daf.fap5.UpdateExcelOp"
}
