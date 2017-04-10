package com.csanford.boot.database;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Entity
public class Outlet implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
	private Long outletId;

	@ManyToOne
	@JoinColumn( name = "rpc_id")
    private RPC rpc;
    private String name;
    private Boolean status; // true is on, false is off

    protected Outlet()
    {
    }

    public Outlet( Long outletId, RPC rpc, String name, Boolean status )
    {
	this.outletId = outletId;
	this.rpc = rpc;
	this.name = name;
	this.status = status;
    }

    public Long getId()
    {
	return id;
    }

    public void setId( Long id )
    {
	this.id = id;
    }

	public Long getOutletId()
	{
		return outletId;
	}

	public void setOutletId( Long outletId )
	{
		this.outletId = outletId;
	}

    public RPC getRpc()
    {
	return rpc;
    }

    public void setRpc( RPC rpc )
    {
	this.rpc = rpc;
    }

    public String getName()
    {
	return name;
    }

    public void setName( String name )
    {
	this.name = name;
    }

    public Boolean getStatus()
    {
	return status;
    }

    public void setStatus( Boolean status )
    {
	this.status = status;
    }

    @Override
    public String toString()
    {
	String translatedStatus = this.status ? "ON" : "OFF";
	return "Outlet[id=" + this.id + ", rpc_id=" + this.rpc.getId()
		+ ", name=" + this.name + ", status=" + translatedStatus + "]";
    }

}
