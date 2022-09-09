package dz.cerist.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dz.cerist.Core.ConsumptionFlow;
import dz.cerist.Core.Job;
import dz.cerist.Core.Job.RequestType;


@RestController
public class ResourcePartController {
    @PostMapping("/job/part")
    public List<ConcreteResult> job(@Validated @RequestBody List<ConcreteResource> resources) throws Exception {

        Job job = new Job(RequestType.PART);
        resources.forEach(e -> {
            job.addResource(e.toResource());
        });

        // System.out.println(job.getResources());

        ConsumptionFlow cf = new ConsumptionFlow(job);

        return cf.generate().stream().map(cr -> new ConcreteResult(cr)).collect(Collectors.toList());
    }

}
