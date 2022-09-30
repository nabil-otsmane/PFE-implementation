package dz.cerist.Core;

import java.util.ArrayList;
import java.util.List;

import dz.cerist.Core.Job.RequestType;
import dz.cerist.Core.Relation.RelationType;

public class ConsumptionFlow {

	private List<PrimitiveResource> UPR = new ArrayList<PrimitiveResource>();
	private Event event;
	private Job.RequestType type;
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

		List<PrimitiveResource> UPRCopy = new ArrayList<>();
		List<CompositeResource> result = new ArrayList<>();

		UPRCopy.addAll(UPR);

		while (UPRCopy.size() != 0) {
			List<PrimitiveResource> remaining = new ArrayList<>();
			CompositeResource first = new CompositeResource(UPRCopy.get(0));

			for (int i = 1; i < UPRCopy.size(); i++) {
				Relation r = new Relation(first, UPRCopy.get(i));

				if (r.getType() != RelationType.precedes && r.getType() != RelationType.meets) {
					first = new CompositeResource(r, event);
				} else {
					remaining.add(UPRCopy.get(i));
				}
			}

			result.add(first);
			UPRCopy.clear();
			UPRCopy.addAll(remaining);
		}

		return result;
	}
	
	public int getRounds() {
		return rounds;
	}
}
