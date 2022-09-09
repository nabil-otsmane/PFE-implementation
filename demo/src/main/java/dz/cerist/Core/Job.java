package dz.cerist.Core;

import java.util.ArrayList;
import java.util.List;

public class Job implements Event {

	public enum RequestType {ALL, PART};

	private List<PrimitiveResource> UPR;
	private int id;
	private RequestType type;
	private static int jobCount = 0;
	
	public Job(RequestType type) {
		id = jobCount++;
		this.type = type;
		UPR = new ArrayList<PrimitiveResource>();
	}
	
	public void addResource(PrimitiveResource PR) {
		UPR.add(PR);
	}
	
	public List<PrimitiveResource> getResources() {
		return UPR;
	}

	public int getId() {
		return id;
	}

	public RequestType getType() {
		return type;
	}

	@Override
	public void consume(CompositeResource CR) {}
	
	
}
