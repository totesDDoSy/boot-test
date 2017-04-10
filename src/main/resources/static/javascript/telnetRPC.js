/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function ()
{
    tableSelector();

    function tableSelector()
    {
	$('tr').on('click', function ()
	{
	    let id = $(this).children().first().text();
	    window.location.href = '/telnet/outlets/' + id;
	});
    }
});
