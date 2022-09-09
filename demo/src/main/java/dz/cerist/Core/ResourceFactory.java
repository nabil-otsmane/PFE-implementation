package dz.cerist.Core;

public class ResourceFactory {
    

    public static PrimitiveResource unlimitedResource(int id, String name, int beginTime, boolean shareable) {
        return new PrimitiveResource(id, name, beginTime, Integer.MAX_VALUE, shareable, ResourceType.LimitedExtensiblePR);
    }

    public static PrimitiveResource limitedResource(int id, String name, int beginTime, int endTime, boolean shareable) {
        return new PrimitiveResource(id, name, beginTime, endTime, shareable, ResourceType.LimitedPR);
    }

    public static PrimitiveResource limitedExtensibleResource(int id, String name, int beginTime, int endTime, boolean shareable) {
        return new PrimitiveResource(id, name, beginTime, endTime, shareable, ResourceType.LimitedExtensiblePR);
    }

    public static PrimitiveResource clone(PrimitiveResource resource) {
        return new PrimitiveResource(resource.getId(), resource.getName(), resource.getBeginTime(), resource.getEndTime(), resource.isShareable(), resource.getType());
    }
}
