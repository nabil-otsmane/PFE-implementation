package dz.cerist.Core;

import java.util.ArrayList;
import java.util.List;

import dz.cerist.Core.Job.RequestType;
import dz.cerist.Core.Relation.RelationType;

public class ConsumptionFlow {

	private List<PrimitiveResource> UPR = new ArrayList<PrimitiveResource>();
	private Event event;
	private Job.RequestType type;
	private List<CompositeResource> CF = new ArrayList<CompositeResource>();
	private int rounds = 0;
	
	public ConsumptionFlow(Job job) throws Exception {
		event = job;
		type = job.getType();
		
		selection(job);
	}
	
	private void selection(Job job) throws Exception {
		for (PrimitiveResource PR : job.getResources()) {
			if (PR.getName() == null)
				throw new Exception("Each resource must have a name. Resource with id " + PR.getId() + " does not have a name");
			if (PR.getBeginTime() >= PR.getEndTime())
				throw new Exception("Invalid begin and end times for resource " + PR.getName());

			UPR.add(PR);
		}
	}
	
	public List<CompositeResource> generate() throws Exception {
		if (type == RequestType.ALL) {
			return generateAll();
		} else {
			List<CompositeResource> r = generatePart2();
			return r;
		}
	}

	private List<CompositeResource> generateAll() throws Exception {
		CompositeResource last = new CompositeResource(UPR.get(0));

		for (int i = 1; i < UPR.size(); i++) {
			Relation r = new Relation(last, UPR.get(i));

			if (r.getType() != RelationType.precedes && r.getType() != RelationType.meets) {
				last = new CompositeResource(r, event);
			} else {
				throw new Exception("couldn't create consumption flow. REASON: divergent intervals.");
			}
		}
		List<CompositeResource> result = new ArrayList<>();
		result.add(last);

		return result;
	}

	private List<CompositeResource> generatePart2() {

		boolean done = false;
		while (!done && UPR.size() > 1) {
			done = true;
			int n = UPR.size();
			System.out.println("Here!");

			for (int i = 0; i < n - 1; i++) {
				boolean throughAll = true;
				PrimitiveResource ri = UPR.get(i);
				CompositeResource ci;
				
				if (ri instanceof CompositeResource) {
					ci = (CompositeResource)ri;
				}
				else {
					ci = new CompositeResource(ri);
				}
				
				
				for (int j = i + 1; j < n; j++) {
					
					Relation r = new Relation(ci, UPR.get(j));
					if (r.getType() != RelationType.precedes && r.getType() != RelationType.meets) {
						CompositeResource c = new CompositeResource(r, event);
						
						if (CF.contains(c))
							continue;
						done = false;
			
						ci = c;
					} else {
						throughAll = false;
					}
					
				}
				
				CF.add(ci);
				if (throughAll) {
					break;
				}

			}

			UPR.clear();
			UPR.addAll(CF);
			CF.clear();
		}

		List<CompositeResource> result = new ArrayList<>();
		for (PrimitiveResource r: UPR) {
			if (!(r instanceof CompositeResource)) {
				System.out.println("NOO!!!!");
			} else {
				result.add((CompositeResource)r);
			}
		}

		return result;
	}
	
	public int getRounds() {
		return rounds;
	}
}
