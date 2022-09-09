package dz.cerist.Core;

public class PrimitiveResource {

	private int id;
	protected String name;
	private int beginTime;
	private int endTime;

	private ResourceType type;
	
	private boolean shareable;
	
	private static int nbResources = 0;

	PrimitiveResource(int id, String name, int beginTime, int endTime, boolean shareable, ResourceType type) {
		nbResources++;
		
		this.id = id;
		this.name = name;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.type = type;
		this.shareable = shareable;
	}
	
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	
	public int getBeginTime() {
		return beginTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ResourceType getType() {
		return type;
	}
	
	public boolean isShareable() {
		return shareable;
	}
	
	public void setShareable(boolean shareable) {
		this.shareable = shareable;
	}
	
//	// this function is used to declare the usage of the resource
//	// it can be used to set constraints over the usage or block this usage
//	public abstract boolean use(int begin, int end);
//	
	/*
	 * checks if the first interval [begin1, end1] is a subinterval of [begin2, end2]
	 */
	public boolean isSubinterval(int begin1, int end1, int begin2, int end2) {
		return begin1 >= begin2 && 
				end1 <= end2 && 
				begin1 <= end1 && 
				begin2 <= end2;
	}

	public static int getNbResources() {
		return nbResources;
	}

	
	public String toString() {
		return "R" + id + "[" + beginTime + ", " + endTime + "]";
	}
	
	@Override
	public boolean equals(Object R2) {
		return beginTime == ((PrimitiveResource) R2).getBeginTime() && endTime == ((PrimitiveResource) R2).getEndTime();
	}
}
