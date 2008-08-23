	function displaymessage() {
		alert("Hello World!" + "The current value of the selected item is:"
				+ document.qryCfg.domainApp.value);
	}

	function displayFormData() {
		win2 = open("", "window2")
		win2.document.open("text/plain")
		win2.document.writeln("This document has " + document.forms.length
				+ " forms.")
		var i = 0;
		var j = 0;
		for (i = 0; i < document.forms.length; ++i) {
			win2.document.writeln("Form " + i + " has "
					+ document.forms[i].elements.length + " elements.")
			win2.document.writeln("Form name:" + document.forms[i].name)
			for (j = 0; j < document.forms[i].elements.length; ++j) {
				win2.document.writeln((j + 1) + " A "
						+ document.forms[i].elements[j].type + " element.")
				win2.document.writeln((j + 1) + " Name: "
						+ document.forms[i].elements[j].name + " !")
				win2.document.writeln((j + 1) + " Value: "
						+ document.forms[i].elements[j].value + " ...")
			}
		}
		win2.document.close()
		return false
	}