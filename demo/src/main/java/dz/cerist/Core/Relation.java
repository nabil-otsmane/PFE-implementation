package dz.cerist.Core;

import java.util.ArrayList;
import java.util.List;

public class Relation {
	
	public enum RelationType { equals, starts, finishes, overlaps, during, meets, precedes };

	private RelationType type;
	private PrimitiveResource PR1;
	private PrimitiveResource PR2;
	
	public Relation(PrimitiveResource PR1, PrimitiveResource PR2) {
		this.PR1 = PR1;
		this.PR2 = PR2;
		
		// process these PRs to figure out the type of the relation
		this.type = identifyType();
	}
	
	private RelationType identifyType() {

		if (PR1.getBeginTime() == PR2.getBeginTime()) {
			
			if (PR1.getEndTime() == PR2.getEndTime()) {
				
				return RelationType.equals;
			} else {
				
				if (PR1.getEndTime() > PR2.getEndTime())
					swap();
				return RelationType.starts;
			}
			
		} else { 
			if (PR1.getEndTime() == PR2.getEndTime()) {
				
				if (PR1.getBeginTime() < PR2.getBeginTime()) {
					
					swap();
				}
				return RelationType.finishes;
			} else {
				if (PR1.getEndTime() == PR2.getBeginTime() || PR2.getEndTime() == PR1.getBeginTime())
					return RelationType.meets;
				if (PR1.getBeginTime() > PR2.getBeginTime()) {

					if (PR2.getEndTime() < PR1.getBeginTime())
						return RelationType.precedes;
					
					if (PR1.getEndTime() < PR2.getEndTime()) {
						
						return RelationType.during;						
					} else {

						swap();
						
						return RelationType.overlaps;
					}
				} else {

					if (PR2.getEndTime() < PR1.getBeginTime())
						return RelationType.precedes;

					if (PR1.getEndTime() > PR2.getBeginTime()) {
						
						return RelationType.overlaps;
					}
				}
			}
		}
		return RelationType.precedes;
	}

	public RelationType getType() {
		return type;
	}
	
	public PrimitiveResource getPR1() {
		return PR1;
	}
	
	public void setPR1(int begin, int end) {
		PR1 = ResourceFactory.clone(PR1);
	}
	
	public PrimitiveResource getPR2() {
		return PR2;
	}
	
	public void setPR2(int begin, int end) {
		PR2 = ResourceFactory.clone(PR2);
	}

	public List<PrimitiveResource> getResources() {
		List<PrimitiveResource> l = new ArrayList<>();

		if (PR1 instanceof CompositeResource) {
			l.addAll(((CompositeResource)PR1).getResources());
			
		} else {
			l.add(PR1);
		}

		if (PR2 instanceof CompositeResource) {
			l.addAll(((CompositeResource)PR2).getResources());
			
		} else {
			l.add(PR2);
		}

		return l;
	}

	public int getBeginTime() {
		if (PR1.getType() == ResourceType.LimitedExtensiblePR) {
			// LOOKSS WRONG !!!
			return PR1.getBeginTime();
			// PR12.setPR1(PR12.getPR1().getBeginTime(), PR12.getPR2().getEndTime());
//			((LimitedExtensiblePR)PR12.getPR1()).extend(PR12.getPR2().getEndTime() - PR12.getPR1().getEndTime());
		}
		if (getType() == RelationType.overlaps) {
			

			return PR2.getBeginTime();
			// PR12.setPR1(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
			// PR12.setPR2(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
			
//			R1.setBeginTime(PR12.getPR2().getBeginTime());
//			R2.setEndTime(PR12.getPR1().getEndTime());
		} else {

			return PR1.getBeginTime();
			
			// PR12.setPR2(PR12.getPR1().getBeginTime(), PR12.getPR1().getEndTime());
			
//			R2.setBeginTime(PR12.getPR1().getBeginTime());
//			R2.setEndTime(PR12.getPR1().getEndTime());
		}
	}

	public int getEndTime() {
		if (PR1.getType() == ResourceType.LimitedExtensiblePR) {
			
			return PR2.getEndTime();
			// PR12.setPR1(PR12.getPR1().getBeginTime(), PR12.getPR2().getEndTime());
//			((LimitedExtensiblePR)PR12.getPR1()).extend(PR12.getPR2().getEndTime() - PR12.getPR1().getEndTime());
		}
		if (getType() == RelationType.overlaps) {
			

			return PR1.getEndTime();
			// PR12.setPR1(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
			// PR12.setPR2(PR12.getPR2().getBeginTime(), PR12.getPR1().getEndTime());
			
//			R1.setBeginTime(PR12.getPR2().getBeginTime());
//			R2.setEndTime(PR12.getPR1().getEndTime());
		} else {

			return PR1.getEndTime();
			
			// PR12.setPR2(PR12.getPR1().getBeginTime(), PR12.getPR1().getEndTime());
			
//			R2.setBeginTime(PR12.getPR1().getBeginTime());
//			R2.setEndTime(PR12.getPR1().getEndTime());
		}
	}

	public ResourceType getResourceType() {
		if (PR1.getType() == ResourceType.UnlimitedPR && PR2.getType() == ResourceType.UnlimitedPR) {
			return ResourceType.UnlimitedPR;
		}

		if (PR1.getType() == ResourceType.LimitedExtensiblePR) {
			if (PR2.getType() != ResourceType.LimitedPR)
				return ResourceType.LimitedExtensiblePR;
		}

		if (PR2.getType() == ResourceType.LimitedExtensiblePR) {
			if (PR1.getType() != ResourceType.LimitedPR)
				return ResourceType.LimitedExtensiblePR;
		}

		return ResourceType.LimitedPR;
	}

	public boolean isShareable() {
		if (PR1.isShareable() && PR2.isShareable()) {
			return true;
		}

		return false;
	}

	public String toString() {
		return type + "(" + PR1 + ", " + PR2 + ")";
	}
	
	private void swap() {
		PrimitiveResource tmp = PR1;
		PR1 = PR2;
		PR2 = tmp;
	}
	
}
