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
    private Long id;
    private Long rpcId;
    private String name;
    private Boolean status; // true is on, false is off

    protected Outlet()
    {
    }

    public Outlet( Long id, Long rpcId, String name, Boolean status )
    {
	this.id = id;
	this.rpcId = rpcId;
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

    public Long getRpcId()
    {
	return rpcId;
    }

    public void setRpcId( Long rpcId )
    {
	this.rpcId = rpcId;
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
	return "Outlet[id=" + this.id + ", rpc_id=" + this.rpcId
		+ ", name=" + this.name + ", status=" + translatedStatus + "]";
    }

}
