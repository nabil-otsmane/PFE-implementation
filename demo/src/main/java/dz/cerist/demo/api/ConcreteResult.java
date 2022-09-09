package dz.cerist.demo.api;

import java.util.ArrayList;
import java.util.List;

import dz.cerist.Core.CompositeResource;
import dz.cerist.Core.Job;
import dz.cerist.Core.PrimitiveResource;

public class ConcreteResult {

    public int beginTime;
    public int endTime;
    public int eventId;

    public int nbResources = 0;
    public List<PrimitiveResource> resources = new ArrayList<>(); 

    public ConcreteResult(CompositeResource cr) {
        beginTime = cr.getBeginTime();
        endTime = cr.getEndTime();
        resources.addAll(cr.getResources());
        nbResources = cr.getResources().size();
        eventId = ((Job)cr.getEvent()).getId();
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public List<PrimitiveResource> getResources() {
        return resources;
    }
    
}
