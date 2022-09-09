package dz.cerist.demo.api;

import dz.cerist.Core.PrimitiveResource;
import dz.cerist.Core.ResourceFactory;

public class ConcreteResource {


    private final int id;
    private final String name;
	private final int beginTime;
	private final int endTime;
	
	private final boolean shareable;

    public ConcreteResource(int id, String name, int beginTime, int endTime, boolean shareable) {
        this.id = id;
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;

        this.shareable = shareable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean getShareable() {
        return shareable;
    }

    public PrimitiveResource toResource() {
        return ResourceFactory.limitedResource(id, name, beginTime, endTime, shareable);
    }
    
}
