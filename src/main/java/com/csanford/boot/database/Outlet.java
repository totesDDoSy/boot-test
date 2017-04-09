package com.csanford.boot.database;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Entity
public class Outlet implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;
    private long rpc_id;
    private String name;
    private Boolean status; // true is on, false is off

    protected Outlet()
    {
    }

    public Outlet( long id, long rpc_id, String name, Boolean status )
    {
	this.id = id;
	this.rpc_id = rpc_id;
	this.name = name;
	this.status = status;
    }

    public long getId()
    {
	return id;
    }

    public void setId( long id )
    {
	this.id = id;
    }

    public long getRpc_id()
    {
	return rpc_id;
    }

    public void setRpc_id( long rpc_id )
    {
	this.rpc_id = rpc_id;
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
	return "Outlet[id=" + this.id + ", rpc_id=" + this.rpc_id
		+ ", name=" + this.name + ", status=" + translatedStatus + "]";
    }

}
