package org.jacorb.notification.engine;

/*
 *        JacORB - a free Java ORB
 *
 *   Copyright (C) 1999-2003 Gerald Brose
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Library General Public
 *   License as published by the Free Software Foundation; either
 *   version 2 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

/**
 * FilterSupplierAdminTask.java
 *
 *
 * @author Alphonse Bendt
 * @version $Id$
 */

public class FilterSupplierAdminTask extends FilterTaskBase 
{
    boolean skip_ = false;

    public FilterSupplierAdminTask()
    {
	
    }

    public void setSkip(boolean skip) {
	skip_ = skip;
    }

    public void reset() {
	skip_ = false;
    }
    
    public void doWork() {
	if (getStatus() == DISPOSABLE) {
	    return;
	}

	boolean _forward = filter();
	
	setStatus( DONE );	
    }
    
    private boolean filter()
    {
        boolean _forward = false;

	// eval attached filters. as an Event passes only 1
	// SupplierAdmin we can assume constant array size here 
	
	if (!skip_) {
	    _forward = event_.match( arrayCurrentFilterStage_[ 0 ] );
	} else {
	    _forward = true;
	}

        if ( _forward )
	    {
		listOfFilterStageToBeProcessed_.addAll( arrayCurrentFilterStage_[ 0 ].getSubsequentFilterStages() );
	    }
	

        return _forward;
    }

}
