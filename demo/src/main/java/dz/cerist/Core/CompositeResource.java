package dz.cerist.Core;

import java.util.HashSet;
import java.util.Set;

public class CompositeResource extends PrimitiveResource {
	
	private Event event;
	private Set<PrimitiveResource> UPR = new HashSet<>();
	
	private static int nbCompositeResources = 0;
	
	public CompositeResource(Relation relation, Event event) {
		super(nbCompositeResources, "composite-resource-" + nbCompositeResources, relation.getBeginTime(), relation.getEndTime(), relation.isShareable(), relation.getResourceType());
		nbCompositeResources++;
		this.event = event;

		UPR.addAll(relation.getResources());

		// adjustAvailabilityTime();
		
	}

	public CompositeResource(PrimitiveResource r) {
		super(r.getId(), r.getName(), r.getBeginTime(), r.getEndTime(), r.isShareable(), r.getType());
		nbCompositeResources++;
		this.event = null;

		UPR.add(r);
	}
	
// 	private void adjustAvailabilityTime() {
		
// 		if (PR12.getPR1().getType() == ResourceType.LimitedExtensiblePR) {
			
// 			PR12.setPR1(PR12.getPR1().getBeginTime(), PR12.getPR2().getEndTime());
// //			((LimitedExtensiblePR)PR12.getPR1()).extend(PR12.getPR2().getEndTime() - PR12.getPR1().getEndTime());
// 		}
// 		if (PR12.getType() == RelationType.overlaps) {
			
// 			PR12.setPR1(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
// 			PR12.setPR2(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
			
// //			R1.setBeginTime(PR12.getPR2().getBeginTime());
// //			R2.setEndTime(PR12.getPR1().getEndTime());
// 		} else {
			
// 			PR12.setPR2(PR12.getPR1().getBeginTime(), PR12.getPR1().getEndTime());
			
// //			R2.setBeginTime(PR12.getPR1().getBeginTime());
// //			R2.setEndTime(PR12.getPR1().getEndTime());
// 		}		
		
// 	}
	
	public String getName() {
		return name;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public Set<PrimitiveResource> getResources() {
		return UPR;
	}
	
	public String toString() {

		String res = UPR.stream().map(r -> r.toString()).reduce("", (total, item) -> total + ", " + item);

		return name + "[ " + getBeginTime() + ", " + getEndTime() + " ]-[ " + res + " ]\n";
//		return name + "[ " + getBeginTime() + ", " + getEndTime() + " ]\n";
	}

	@Override
	public boolean equals(Object R) {
		return getBeginTime() == ((PrimitiveResource) R).getBeginTime() && getEndTime() == ((PrimitiveResource) R).getEndTime();
	}

}
