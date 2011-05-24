/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.server.rrd.Job;
import org.neo4j.server.rrd.JobScheduler;
import org.neo4j.server.rrd.ScheduledJob;

public class RoundRobinJobScheduler implements JobScheduler {
    
    private List<ScheduledJob> scheduledJobs = new LinkedList<ScheduledJob>();

    public void scheduleToRunEveryXSeconds(Job job, String jobName, int runEveryXSeconds) {
        ScheduledJob scheduledJob = new ScheduledJob(job, jobName, 3);
        scheduledJobs.add(scheduledJob);
    }

    public void stopJobs() {
        for (ScheduledJob job : scheduledJobs) {
            job.kill();
        }
    }
}
