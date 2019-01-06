function loadJQuery() {
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.charset = "UTF-8";		  
    oScript.src = "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";	
    document.getElementsByTagName("head")[0].appendChild(oScript);
}