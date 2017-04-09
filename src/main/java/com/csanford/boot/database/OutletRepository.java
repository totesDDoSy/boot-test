/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csanford.boot.database;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
public interface OutletRepository extends CrudRepository<Outlet, Long>
{
    List<Outlet> findByRpcId( Long rpcId );
}
