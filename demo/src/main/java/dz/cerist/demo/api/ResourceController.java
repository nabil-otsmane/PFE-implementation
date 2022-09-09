package dz.cerist.demo.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dz.cerist.Core.ConsumptionFlow;
import dz.cerist.Core.Job;
import dz.cerist.Core.Job.RequestType;

@RestController
public class ResourceController {
    
    @PostMapping("/job/all")
    public ConcreteResult job(@Validated @RequestBody List<ConcreteResource> resources) throws Exception {

        Job job = new Job(RequestType.ALL);
        resources.forEach(e -> {
            job.addResource(e.toResource());
        });

        // System.out.println(job.getResources());

        ConsumptionFlow cf = new ConsumptionFlow(job);

        return new ConcreteResult(cf.generate().get(0));
    }
}
