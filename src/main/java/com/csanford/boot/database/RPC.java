package com.csanford.boot.database;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Entity
public class RPC implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String name;
    private String ipaddr;
    private String username;
    private String password;

	@OneToMany(mappedBy = "rpc", cascade = CascadeType.ALL)
	private Set<Outlet> outlets;

    protected RPC()
    {
    }

    public RPC( String name, String ipaddr, String username,
	    String password)
    {
	this.name = name;
	this.ipaddr = ipaddr;
	this.username = username;
	this.password = password;
    }

    public Long getId()
    {
	return id;
    }

    public void setId( Long id )
    {
	this.id = id;
    }

    public String getName()
    {
	return name;
    }

    public void setName( String name )
    {
	this.name = name;
    }

    public String getIpaddr()
    {
	return ipaddr;
    }

    public void setIpaddr( String ipaddr )
    {
	this.ipaddr = ipaddr;
    }

    public String getUsername()
    {
	return username;
    }

    public void setUsername( String username )
    {
	this.username = username;
    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword( String password )
    {
	this.password = password;
    }

	public Set<Outlet> getOutlets()
	{
		return outlets;
	}

	public void setOutlets( Set<Outlet> outlets )
	{
		this.outlets = outlets;
	}



    @Override
    public String toString()
    {
	return String.format(
		"RPC[id=%d, name='%s', ipaddr='%s', username='%s', password='%s', outlets=%s]",
		id, name, ipaddr, username, password, outlets );
    }

}
