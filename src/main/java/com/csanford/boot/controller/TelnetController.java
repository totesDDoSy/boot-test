package com.csanford.boot.controller;

import com.csanford.boot.database.Outlet;
import com.csanford.boot.database.OutletRepository;
import com.csanford.boot.database.RPC;
import com.csanford.boot.database.RPCRepository;
import com.csanford.boot.utils.SocketHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Controller
@RequestMapping( "/telnet" )
public class TelnetController
{

	private static final Logger LOG = Logger.getLogger( TelnetController.class.
		getName() );
	private SocketHelper telnetHelper;
	private Boolean addedDb = false;

	@Autowired
	RPCRepository rpcRepository;

	@Autowired
	OutletRepository outletRepository;

	@Value( value = "${telnet.server}" )
	private final String server = "127.0.0.1";

	@RequestMapping( "" )
	public String display( Model model )
	{
		// Display the list of RPCs
		if( !addedDb )
		{
			//databaseSetup();
			addedDb = true;
		}

		ArrayList<RPC> rpcs = new ArrayList<>();
		rpcRepository.findAll().forEach( rpcs::add );
		model.addAttribute( "rpclist", rpcs );
		return "telnet";
	}

	@RequestMapping( "/outlets/{rpcid}" )
	public String displayOutlets( @PathVariable(value="rpcid") Long rpcId, Model model )
	{

		ArrayList<Outlet> outlets = new ArrayList<>();
		outletRepository.findByRpc( rpcRepository.findOne( rpcId ) )
			.stream()
			.sorted( (o1, o2) -> o1.getOutletId().compareTo( o2.getOutletId() ) )
			.forEach(
				outlets::add
			);
		model.addAttribute( "outletlist", outlets );
		return "outlets";
	}

	@RequestMapping( "/garbageportgarbagedontgohere/dummy" )
	public String displayPing( Integer port, String server, Model model )
	{
		String output = "Error";
		try
		{
			output = doTelnetStuff( 23, "192.168.99.24", "" );
		}
		catch ( IOException ex )
		{
			LOG.log( Logger.Level.ERROR, ex );
		}
		model.addAttribute( "output", output );
		return "ping";
	}

	private String doTelnetStuff( Integer port, String rpc_server,
		String... commands ) throws IOException
	{
		if ( telnetHelper == null )
		{
			telnetHelper = new SocketHelper( port, rpc_server );
		}
		BufferedWriter writer = telnetHelper.getWriter();
		BufferedReader reader = telnetHelper.getReader();

		if ( writer != null && reader != null )
		{
			//	telnetWriter.println( "ping" );
		}
		else
		{
			return "ERROR";
		}

		StringJoiner joiner = new StringJoiner( " " );
		String line;
		do
		{
			line = reader.readLine();
			joiner.add( line );
			System.out.println( line );
		}
		while ( !line.contains( "6)...Logout" ) );
		return joiner.toString();
	}

	private void databaseSetup()
	{
		rpcRepository.deleteAll();
		outletRepository.deleteAll();

		RPC rpc1 = new RPC( "rpc1", "192.168.1.1", "user", "password" );
		RPC rpc2 = new RPC( "rpc2", "192.168.1.2", "user", "pass");

		// new Outlet( id, outletid, rpcid, name, on/off )
		Set<Outlet> outlets1 = new HashSet<>();
		outlets1.add( new Outlet( 1L, rpc1, "outlet 1", Boolean.FALSE ) );
		outlets1.add( new Outlet(2L, rpc1, "outlet 2", Boolean.FALSE ) );
		outlets1.add(new Outlet( 3L, rpc1, "outlet 3", Boolean.FALSE ) );
		outlets1.add(new Outlet( 4L, rpc1, "outlet 4", Boolean.FALSE ) );
		outlets1.add(new Outlet( 5L, rpc1, "outlet 5", Boolean.FALSE ) );
		outlets1.add( new Outlet( 6L, rpc1, "outlet 6", Boolean.FALSE ) );
		rpc1.setOutlets( outlets1 );

		rpcRepository.save( rpc1 );

		Set<Outlet> outlets2 = new HashSet<>();
		outlets2.add( new Outlet( 1L, rpc2, "outlet 1", Boolean.FALSE ) );
		outlets2.add( new Outlet( 2L, rpc2, "outlet 2", Boolean.FALSE ) );
		outlets2.add( new Outlet( 3L, rpc2, "outlet 3", Boolean.FALSE ) );
		outlets2.add( new Outlet( 4L, rpc2, "outlet 4", Boolean.FALSE ) );
		outlets2.add( new Outlet( 5L, rpc2, "outlet 5", Boolean.FALSE ) );
		outlets2.add( new Outlet( 6L, rpc2, "outlet 6", Boolean.FALSE ) );
		rpc2.setOutlets( outlets2 );
		rpcRepository.save( rpc2 );
	}
}
