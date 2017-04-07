$(document).ready(function () {
    navSetup();

    function navSetup()
    {
	switch ($(location).attr('pathname')) {
	    case '/':
		$('#homelink').addClass('active').children('a').first().attr('href', '#');
		break;
	    case '/about':
		$('#aboutlink').addClass('active').children('a').first().attr('href', '#');
		break;
	    case '/telnet':
		$('#telnetlink').addClass('active').children('a').first().attr('href', '#');
		break;
	    default:
		console.log("URL: " + url);
	}
    }
});